/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUI_ONSITECOURSE;

import BUS.CourseBUS;
import BUS.OnsiteCourseBUS;
import DAO.ConnectDB;
import DTO.KhoaDTO;
import DTO.OnsiteCourseDTO;
import DTO.PersonDTO;
import DTO.PhanCongDTO;
import GUI.GUI_KETQUA.CustomTableCellRenderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class jpnOnsite extends javax.swing.JPanel {
    
//	FORMAT
	private Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
	private Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
	private Font sgUI15I = new Font("Segoe UI", Font.ITALIC, 15);
	private Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
	private Font sgUI13I = new Font("Segoe UI", Font.ITALIC, 13);
	private Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
	private Font sgUI18b = new Font("Segoe UI", Font.BOLD, 17);
	private Font tNR13 = new Font("Times New Roman", Font.ITALIC, 13);
	private Font fontTittle = new Font("Tahoma", Font.BOLD, 25);
	private Font fontSubTittle = new Font("Tahoma", Font.BOLD, 20);
	private Font fontTable = new Font("Segoe UI", Font.BOLD, 13);
	private DecimalFormat dcf = new DecimalFormat("###,###");
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Color btnoldColor = new Color(52, 152, 219);
	private Color texfieldColor = new Color(45, 52, 54);
	private String colorTableCode = "#dee9fc";
	MatteBorder matteBorderCB = new MatteBorder(2, 2, 2, 2, Color.decode("#EFEFEF"));
	LineBorder lineCB = new LineBorder(Color.white);
	MatteBorder matteBorderCBDark = new MatteBorder(2, 2, 2, 2, Color.decode("#919191"));
	MatteBorder borderTxt = new MatteBorder(2, 2, 2, 2, Color.decode("#EFEFEF"));
	MatteBorder borderTxtDark = new MatteBorder(2, 2, 2, 2, Color.decode("#919191"));
	EmptyBorder emptyBorderTxt = new EmptyBorder(0, 7, 0, 7);
	EmptyBorder emptyBorderCB = new EmptyBorder(0, 7, 0, 0);

	CourseBUS coursebus = new CourseBUS();
        OnsiteCourseDTO onsite = new OnsiteCourseDTO();
        OnsiteCourseBUS onsiteBUS = new OnsiteCourseBUS();

    /**
     * Creates new form QLOnsite
     */
    public jpnOnsite() {
        initComponents();
        showTable();
    }
    
    public void showTable() {
        OnsiteCourseBUS onsiteBUS = new OnsiteCourseBUS();
        List<OnsiteCourseDTO> list = onsiteBUS.findAll();
        
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }           
        };
        
        onsiteTable.setModel(dtm);
        
        DefaultTableModel model = (DefaultTableModel) onsiteTable.getModel();
        
        model.setColumnIdentifiers(new Object[]{
            "ID", "Tên môn học", "Mã khoa", "Thứ", "Thời gian", "Địa điểm", "Số tín chỉ"
        });
        
        for(OnsiteCourseDTO onsiteCourse: list) {
            model.addRow(new Object[]{
                onsiteCourse.getId(), onsiteCourse.getTittle(),onsiteCourse.getMaKhoa(),onsiteCourse.getDays(),onsiteCourse.getTime(),onsiteCourse.getLocation(), onsiteCourse.getCredits()
            });
        }
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        
        onsiteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1 && onsiteTable.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) onsiteTable.getModel();
                    int selectedRowIndex1 = onsiteTable.getSelectedRow();
                    int selectedRowIndex = onsiteTable.convertRowIndexToModel(selectedRowIndex1);
                    
                    onsite.setId((int) model.getValueAt(selectedRowIndex,0));
                    onsite.setTittle(model.getValueAt(selectedRowIndex, 1).toString());
                    onsite.setMaKhoa((int) model.getValueAt(selectedRowIndex, 2));
                    onsite.setDays(model.getValueAt(selectedRowIndex, 3).toString());
                    onsite.setTime( model.getValueAt(selectedRowIndex, 4).toString());
                    onsite.setLocation(model.getValueAt(selectedRowIndex, 5).toString());
                    onsite.setCredits((int) model.getValueAt(selectedRowIndex, 6));                    
                }
                
                if(e.getClickCount() == 2 && onsiteTable.getSelectedRow() != -1) {

                    jlbTenKhoaHoc.setText(onsite.getTittle());
                    
                    try {
                        Connection cons = ConnectDB.getConnection();

                        String sql = "SELECT DepartmentID, Name FROM department";
                        PreparedStatement ps = cons.prepareCall(sql);
                        ResultSet rs = ps.executeQuery();

                        List<KhoaDTO> list = new ArrayList<>();

                        while(rs.next()) {
                            KhoaDTO khoa = new KhoaDTO();
                            khoa.setId(rs.getInt("DepartmentID"));
                            khoa.setName(rs.getString("Name"));
                            list.add(khoa);
                        }
                        ps.close();
                        rs.close();
                        cons.close();

                        for(KhoaDTO khoa: list) {
                            if(khoa.getId() == onsite.getMaKhoa())
                                jlbTenKhoa.setText(khoa.getName());
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    
                    try {
                        Connection cons = ConnectDB.getConnection();

                        String sql = "SELECT CourseID, PersonID FROM courseinstructor";
                        PreparedStatement ps = cons.prepareCall(sql);
                        ResultSet rs = ps.executeQuery();

                        List<PhanCongDTO> list = new ArrayList<>();

                        while(rs.next()) {
                            PhanCongDTO phanCong = new PhanCongDTO();
                            phanCong.setCourseId(rs.getInt("CourseID"));
                            phanCong.setPersonId(rs.getInt("PersonID"));
                            list.add(phanCong);
                        }
                        ps.close();
                        rs.close();
                        
                        int personId = 0;
                        
                        for(PhanCongDTO phanCong: list) {
                            if(phanCong.getCourseId()== onsite.getId())
                                personId = phanCong.getPersonId();
                        }
                        
                        if(personId == 0)
                            jlbTenGiaoVien.setText("Chưa phân công");
                        
                        String sql1 = "SELECT PersonID, Lastname, Firstname FROM person";
                        PreparedStatement ps1 = cons.prepareCall(sql1);
                        ResultSet rs1 = ps1.executeQuery();

                        List<PersonDTO> list1 = new ArrayList<>();

                        while(rs1.next()) {
                            PersonDTO person = new PersonDTO();
                            person.setId(rs1.getInt("PersonID"));
                            person.setFirstName(rs1.getString("Firstname"));
                            person.setLastName(rs1.getString("Lastname"));
                            list1.add(person);
                        }
                        ps1.close();
                        rs1.close();                       
                        cons.close();
                        
                        for(PersonDTO person: list1) {
                            if(person.getId()== personId) {
                                String name = person.getFirstName() + " " + person.getLastName();
                                jlbTenGiaoVien.setText(name);
                            }
                                
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    
                    jlbNgayHoc.setText(onsite.getDays());
                    jlbThoiGian.setText(onsite.getTime());
                    jlbSoTinChi.setText(String.valueOf(onsite.getCredits()));

                    jdlThongTinChiTiet.setLocationRelativeTo(null);
                    jdlThongTinChiTiet.setVisible(true);
                }
            }
            
        });
    }
    
    private void performSearch() {
        
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(onsiteTable.getModel());
        onsiteTable.setRowSorter(rowSorter);
        
        String text = jtfSearch.getText();

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (text.trim().length() > 0) {
            filters.add(RowFilter.regexFilter("(?i)" + text));
        }

        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
        rowSorter.setRowFilter(combinedFilter);
    }

    
    public void refresh() {
        jtfSearch.setText("");
        onsite.setId(0);
        onsiteTable.clearSelection();
//        showTable();
    }
    
    public boolean ktNhapDayDu() {
        if(jtfTenMonHoc.getText().isEmpty() || jtfDiaDiem.getText().isEmpty() || jtfSoTinChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn nhập thiếu thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;        
    }
    
    public String ngayHoc() {
        String days = "";
        if(jcbThu2.isSelected() == true) 
            days += "M"; 
        if(jcbThu3.isSelected() == true) 
            days += "T";
        if(jcbThu4.isSelected() == true) 
            days += "W";
        if(jcbThu5.isSelected() == true) 
            days += "H";
        if(jcbThu6.isSelected() == true) 
            days += "F";
        if(jcbThu7.isSelected() == true) 
            days += "S";
        if(jcbChuNhat.isSelected() == true) 
            days += "U";
        
        return days;
    }
    
    public String thoiGianHoc() {
        String time = null;
        
        int gio = (int) jspGio.getValue();
        int phut = (int) jspPhut.getValue();
        
        time = "" + gio + ":" + phut + ":00";
        
        return time;
    }
    
    public void setValueJcbMaKhoa() {

        try {
            Connection cons = ConnectDB.getConnection();
            
            String sql = "SELECT DepartmentID FROM department";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            List<String> list = new ArrayList<>();

            while(rs.next()) {              
                list.add(String.valueOf(rs.getInt("DepartmentID")));
            }
            ps.close();
            rs.close();
            cons.close();
            
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list.toArray(new String[0]));
            jcbMaKhoa.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void resetJdlThemSua() {
        jtfId.setText(coursebus.getNewestId()+"");
        jtfTenMonHoc.setText("");
        setValueJcbMaKhoa();
        jcbThu2.setSelected(false);
        jcbThu3.setSelected(false);
        jcbThu4.setSelected(false);
        jcbThu5.setSelected(false);
        jcbThu6.setSelected(false);
        jcbThu7.setSelected(false);
        jcbChuNhat.setSelected(false);
        jspGio.setValue(0);
        jspPhut.setValue(0);
        jtfDiaDiem.setText("");
        jtfSoTinChi.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jdlThongTinChiTiet = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jlbTenKhoaHoc = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlbTenKhoa = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlbTenGiaoVien = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jlbNgayHoc = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jlbThoiGian = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlbSoTinChi = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        btnTroVe = new javax.swing.JButton();
        jdlThemSua = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtfTenMonHoc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfDiaDiem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtfSoTinChi = new javax.swing.JTextField();
        jcbMaKhoa = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jspGio = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jspPhut = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jcbThu2 = new javax.swing.JCheckBox();
        jcbThu3 = new javax.swing.JCheckBox();
        jcbThu4 = new javax.swing.JCheckBox();
        jcbThu5 = new javax.swing.JCheckBox();
        jcbThu6 = new javax.swing.JCheckBox();
        jcbThu7 = new javax.swing.JCheckBox();
        jcbChuNhat = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        jtfId = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        onsiteScroll = new javax.swing.JScrollPane();
        onsiteTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton("Thêm");
        btnDelete = new javax.swing.JButton("Xóa");
        btnUpdate = new javax.swing.JButton("Sửa");
        btnReset = new javax.swing.JButton("Refresh");

//        jdlThongTinChiTiet.setMinimumSize(new java.awt.Dimension(500, 500));
        jdlThongTinChiTiet.setModal(true);
//        jdlThongTinChiTiet.setPreferredSize(new java.awt.Dimension(500, 500));
        jdlThongTinChiTiet.setSize(400,300);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));

        
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setMaximumSize(new java.awt.Dimension(32767, 35));
        jPanel13.setBackground(Color.decode(colorTableCode));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Thông Tin Chi Tiết");
        jPanel13.add(jLabel11);

        jPanel12.add(jPanel13);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagLayout jPanel15Layout = new java.awt.GridBagLayout();
        jPanel15Layout.columnWidths = new int[] {0, 20, 0, 20, 0};
        jPanel15Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel15.setLayout(jPanel15Layout);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Tên khóa học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel12, gridBagConstraints);

        jlbTenKhoaHoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbTenKhoaHoc.setText("jLabel13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jlbTenKhoaHoc, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Tên khoa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel14, gridBagConstraints);

        jlbTenKhoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbTenKhoa.setText("jLabel15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jlbTenKhoa, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Tên giáo viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel16, gridBagConstraints);

        jlbTenGiaoVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbTenGiaoVien.setText("jLabel17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jlbTenGiaoVien, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Ngày học (Thứ)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel18, gridBagConstraints);

        jlbNgayHoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbNgayHoc.setText("jLabel19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jlbNgayHoc, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Thời gian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel20, gridBagConstraints);

        jlbThoiGian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbThoiGian.setText("jLabel21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jlbThoiGian, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Số tín chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel22, gridBagConstraints);

        jlbSoTinChi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbSoTinChi.setText("jLabel23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jlbSoTinChi, gridBagConstraints);

        jPanel12.add(jPanel15);

//        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
//        jPanel14.setMaximumSize(new java.awt.Dimension(32767, 35));
//        jPanel14.setMinimumSize(new java.awt.Dimension(82, 35));
//        jPanel14.setPreferredSize(new java.awt.Dimension(700, 35));

        btnTroVe.setText("Trở về");
        btnTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroVeActionPerformed(evt);
            }
        });
        jPanel14.add(btnTroVe);

        jPanel12.add(jPanel14);

        jdlThongTinChiTiet.getContentPane().add(jPanel12, java.awt.BorderLayout.CENTER);

        jdlThemSua.setTitle("Thông tin khóa học Onsite");
//        jdlThemSua.setMinimumSize(new java.awt.Dimension(700, 550));
        jdlThemSua.setModal(true);
//        jdlThemSua.setPreferredSize(new java.awt.Dimension(700, 285));
        jdlThemSua.setSize(500,400);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.Y_AXIS));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 250));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Tên môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel5, gridBagConstraints);

        jtfTenMonHoc.setPreferredSize(new java.awt.Dimension(200, 30));
        jtfTenMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTenMonHocActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel8.add(jtfTenMonHoc, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Mã khoa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Ngày");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 38;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Thời gian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 54;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Địa điểm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel9, gridBagConstraints);

        jtfDiaDiem.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 70;
        jPanel8.add(jtfDiaDiem, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Số tín chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 86;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel10, gridBagConstraints);

        jtfSoTinChi.setPreferredSize(new java.awt.Dimension(200, 30));
        jtfSoTinChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSoTinChiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 86;
        jPanel8.add(jtfSoTinChi, gridBagConstraints);

        jcbMaKhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbMaKhoa.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        jPanel8.add(jcbMaKhoa, gridBagConstraints);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jspGio.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));
        jspGio.setEditor(new javax.swing.JSpinner.NumberEditor(jspGio, ""));
        jPanel10.add(jspGio);

        jLabel2.setText("giờ");
        jPanel10.add(jLabel2);

        jspPhut.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
        jPanel10.add(jspPhut);

        jLabel4.setText("phút");
        jPanel10.add(jLabel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 54;
        jPanel8.add(jPanel10, gridBagConstraints);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new Dimension(250, 100));
        jcbThu2.setText("Thứ 2");
        jcbThu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbThu2ActionPerformed(evt);
            }
        });
        jPanel11.add(jcbThu2);

        jcbThu3.setText("Thứ 3");
        jcbThu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbThu3ActionPerformed(evt);
            }
        });
        jPanel11.add(jcbThu3);

        jcbThu4.setText("Thứ 4");
        jPanel11.add(jcbThu4);

        jcbThu5.setText("Thứ 5");
        jPanel11.add(jcbThu5);

        jcbThu6.setText("Thứ 6");
        jPanel11.add(jcbThu6);

        jcbThu7.setText("Thứ 7");
        jPanel11.add(jcbThu7);

        jcbChuNhat.setText("Chủ Nhật");
        jPanel11.add(jcbChuNhat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 38;
        jPanel8.add(jPanel11, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setText("Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel24, gridBagConstraints);

        jtfId.setPreferredSize(new java.awt.Dimension(200, 30));
        jtfId.setEditable(false);
        jtfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel8.add(jtfId, gridBagConstraints);

        jPanel7.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMinimumSize(new java.awt.Dimension(236, 50));

        btnThem.setBackground(new java.awt.Color(255, 153, 153));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel9.add(btnThem);

        btnSua.setBackground(new java.awt.Color(102, 153, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel9.add(btnSua);

        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel9.add(btnThoat);

        jPanel7.add(jPanel9);

        jdlThemSua.getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

//        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
//        jPanel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(getWidth(), 80));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setFont(fontTittle); // NOI18N
        jLabel1.setText("Khóa học Onsite");
        
        jPanel2.add(jLabel1);
        jPanel2.setBackground(Color.decode(colorTableCode));

        add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(890, 50));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 20));
        jPanel6.setPreferredSize(new java.awt.Dimension(700, 110));

        jLabel3.setFont(sgUI13b); // NOI18N
        jLabel3.setText("Tìm kiếm:");
        jPanel6.add(jLabel3);

        jtfSearch.setPreferredSize(new Dimension(200, 30));
		jtfSearch.setFont(sgUI13);
		jtfSearch.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
		jtfSearch.setForeground(Color.black);
        jPanel6.add(jtfSearch);

        jPanel3.add(jPanel6);

        add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        onsiteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        onsiteScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		onsiteScroll.setBorder(BorderFactory.createEmptyBorder());
		onsiteScroll.setViewportBorder(null);

		onsiteTable.setShowGrid(false);
		onsiteTable.setIntercellSpacing(new Dimension(0, 0));
		TableCellRenderer renderer = new CustomTableOnlsiteCourse();
		for (int i = 0; i < onsiteTable.getColumnCount(); i++) {
			onsiteTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		onsiteTable.setRowHeight(35);
		onsiteTable.getTableHeader().setPreferredSize(new Dimension(1, 30));
		onsiteTable.getTableHeader().setFont(fontTable);
		onsiteTable.getTableHeader().setBorder(null);
		onsiteTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		onsiteTable.getTableHeader().setBackground(Color.decode(colorTableCode));
		onsiteTable.getColumnModel().getColumn(0).setPreferredWidth(50);

		onsiteTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		onsiteTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		onsiteTable.getColumnModel().getColumn(3).setPreferredWidth(50);
//		onsiteTable.getColumnModel().getColumn(4).setPreferredWidth(100);
//		onsiteTable.getColumnModel().getColumn(5).setPreferredWidth(100);
//		onsiteTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		
        onsiteScroll.setViewportView(onsiteTable);

        jPanel4.add(onsiteScroll, java.awt.BorderLayout.CENTER);

        add(jPanel4);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(890, 50));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));
        btnThem.setVisible(false);
        btnSua.setVisible(false);
        ImageIcon addIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/btnAdd.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnAdd.setIcon(addIcon);
		btnAdd.setFont(sgUI13b);
		btnAdd.setFocusPainted(false);
		btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnThem.setVisible(true);
                btnSua.setVisible(false);
                btnAddActionPerformed(evt);
                
            }
        });
        btnAdd.setBackground(Color.decode("#ebf2fc"));
        jPanel1.add(btnAdd);

        ImageIcon delIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/btnDel.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnDelete.setIcon(delIcon);
		btnDelete.setFont(sgUI13b);
		btnDelete.setFocusPainted(false);
		btnDelete.setBorderPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        btnDelete.setBackground(Color.decode("#ebf2fc"));
        jPanel1.add(btnDelete);

        ImageIcon saveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/pencil.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnUpdate.setIcon(saveIcon);
		btnUpdate.setFont(sgUI13b);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnSua.setVisible(true);
                btnThem.setVisible(false);
                btnUpdateActionPerformed(evt);
                
            }
        });
        btnUpdate.setBackground(Color.decode("#ebf2fc"));
        jPanel1.add(btnUpdate);

        ImageIcon refreshIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/Refresh-icon.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnReset.setIcon(refreshIcon);
		btnReset.setFont(sgUI13b);
		btnReset.setFocusPainted(false);
		btnReset.setBorderPainted(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        btnReset.setBackground(Color.decode("#ebf2fc"));
        jPanel1.add(btnReset);

        add(jPanel1);
        
        jPanel1.setBackground(Color.white);
        jPanel10.setBackground(Color.white);
        jPanel11.setBackground(Color.white);
        jPanel12.setBackground(Color.white);
//        jPanel13.setBackground(Color.white);
        jPanel14.setBackground(Color.white);
        jPanel15.setBackground(Color.white);
        jPanel2.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        jPanel6.setBackground(Color.white);
        jPanel7.setBackground(Color.white);
        jPanel8.setBackground(Color.white);
        jPanel9.setBackground(Color.white);
        onsiteScroll.setBackground(Color.white);
        jcbChuNhat.setBackground(Color.white);
        jcbMaKhoa.setBackground(Color.white);
        jcbThu2.setBackground(Color.white);
        jcbThu3.setBackground(Color.white);
        jcbThu4.setBackground(Color.white);
        jcbThu5.setBackground(Color.white);
        jcbThu6.setBackground(Color.white);
        jcbThu7.setBackground(Color.white);
        
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        resetJdlThemSua();
        jdlThemSua.setLocationRelativeTo(null);
        jdlThemSua.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        // TODO add your handling code here:
        jdlThongTinChiTiet.setVisible(false);
    }//GEN-LAST:event_btnTroVeActionPerformed

    private void jtfTenMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTenMonHocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTenMonHocActionPerformed

    private void jtfSoTinChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSoTinChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSoTinChiActionPerformed

    private void jcbThu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbThu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbThu2ActionPerformed

    private void jcbThu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbThu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbThu3ActionPerformed

    private void jtfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
//        insert();
        if(ktNhapDayDu()) {
            int id = Integer.parseInt(jtfId.getText());
            String title = jtfTenMonHoc.getText();
            int credits = Integer.parseInt(jtfSoTinChi.getText());
            int maKhoa = Integer.parseInt((String) jcbMaKhoa.getSelectedItem());
            String location = jtfDiaDiem.getText();
            String days = ngayHoc();
            String time = thoiGianHoc();

            OnsiteCourseDTO onsite = new OnsiteCourseDTO(id, title, credits, maKhoa, location, days, time);
            System.out.println(onsite);
            JOptionPane.showMessageDialog(this, onsiteBUS.insert(onsite), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            showTable();
            jdlThemSua.setVisible(false);  
            onsite.setId(0);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(ktNhapDayDu()) {
            int id = Integer.parseInt(jtfId.getText());
            String title = jtfTenMonHoc.getText();
            int credits = Integer.parseInt(jtfSoTinChi.getText());
            int maKhoa = Integer.parseInt((String) jcbMaKhoa.getSelectedItem());
            String location = jtfDiaDiem.getText();
            String days = ngayHoc();
            String time = thoiGianHoc();

            OnsiteCourseDTO onsiteDTO = new OnsiteCourseDTO(id, title, credits, maKhoa, location, days, time);
            System.out.println(onsite);
            JOptionPane.showMessageDialog(this, onsiteBUS.update(onsiteDTO), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            showTable();
            jdlThemSua.setVisible(false);
            onsite.setId(0);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        jdlThemSua.setVisible(false);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if(onsite.getId() == 0)
        JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn khóa học cần xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else {
            int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, onsiteBUS.delete(onsite), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                showTable();
                onsite.setId(0);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if(onsite.getId() == 0){
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn khóa học cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            resetJdlThemSua();
            jtfId.setText(String.valueOf(onsite.getId()));
            jtfTenMonHoc.setText(onsite.getTittle());
            jcbMaKhoa.setSelectedItem(String.valueOf(onsite.getMaKhoa()));
            
            for(int i = 0; i < onsite.getDays().length(); i++) {
                String day = String.valueOf(onsite.getDays().charAt(i));
                switch (day) {
                    case "M":
                        jcbThu2.setSelected(true);
                        break;
                    case "T":
                        jcbThu3.setSelected(true);
                        break;
                    case "W":
                        jcbThu4.setSelected(true);
                        break;
                    case "H":
                        jcbThu5.setSelected(true);
                        break;
                    case "F":
                        jcbThu6.setSelected(true);
                        break;    
                    case "S":
                        jcbThu7.setSelected(true);
                        break;
                    case "U":
                        jcbChuNhat.setSelected(true);
                        break;
                }
            }
            
            String[] parts = onsite.getTime().split(":");
            jspGio.setValue(Integer.parseInt(parts[0]));
            jspPhut.setValue(Integer.parseInt(parts[1]));
            
            jtfDiaDiem.setText(onsite.getLocation());
            jtfSoTinChi.setText(String.valueOf(onsite.getCredits()));

            jdlThemSua.setLocationRelativeTo(null);
            jdlThemSua.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JCheckBox jcbChuNhat;
    private javax.swing.JComboBox<String> jcbMaKhoa;
    private javax.swing.JCheckBox jcbThu2;
    private javax.swing.JCheckBox jcbThu3;
    private javax.swing.JCheckBox jcbThu4;
    private javax.swing.JCheckBox jcbThu5;
    private javax.swing.JCheckBox jcbThu6;
    private javax.swing.JCheckBox jcbThu7;
    private javax.swing.JDialog jdlThemSua;
    private javax.swing.JDialog jdlThongTinChiTiet;
    private javax.swing.JLabel jlbNgayHoc;
    private javax.swing.JLabel jlbSoTinChi;
    private javax.swing.JLabel jlbTenGiaoVien;
    private javax.swing.JLabel jlbTenKhoa;
    private javax.swing.JLabel jlbTenKhoaHoc;
    private javax.swing.JLabel jlbThoiGian;
    private javax.swing.JSpinner jspGio;
    private javax.swing.JSpinner jspPhut;
    private javax.swing.JTextField jtfDiaDiem;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfSoTinChi;
    private javax.swing.JTextField jtfTenMonHoc;
    private javax.swing.JScrollPane onsiteScroll;
    private javax.swing.JTable onsiteTable;
    // End of variables declaration//GEN-END:variables
}
