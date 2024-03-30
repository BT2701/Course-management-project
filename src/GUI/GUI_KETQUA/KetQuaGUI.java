/*
 * Author: Dương Thành Trưởng
 */

package GUI.GUI_KETQUA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Timer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import BUS.KetQuaBUS;
import GUI.GUI_BASIC.ThongBaoDialog;

public class KetQuaGUI extends JPanel {
	// ----------MAIN__COMPONENTS------------
	private JPanel pnNorth, pnCenter, pnSouth, pnNorthTittle, pnNorthContent, pnNorthContentLeft, pnNorthContentRight,
			pnContainerOfContentRight, pnNotification, pnCenterContent, pnContainerOfContentRightTittle;
	private InformationFORM pnCenterInfor;
	private JLabel lbTittle, lbNotification, lbTittleOfSearch;
	private JButton btnSearch, btnAdd, btnDelete, btnUpdate, btnRefresh, btnCancel,btnThongKe;
//	private JComboBox<String> cbbSortContent;
	private JTextField tfSearch;
	private JTable tbMain;
	private JScrollPane scrMain;
	private Timer timer;

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

	public KetQuaGUI() {
		initComponents();
		componentsStyle();
		componentsColor();
		events();
	}

	public void initComponents() {
//		SUB COMPONENTS
		lbTittle = new JLabel("KẾT QUẢ KHÓA HỌC");

		btnRefresh = new JButton();

//		lbSortText = new JLabel("Sắp Xếp");
//
//		cbbSortContent = new JComboBox<String>();

		tfSearch = new JTextField();

		btnSearch = new JButton("Tìm Kiếm");
		
		btnThongKe=new JButton("Thống Kê");

		tbMain = new JTable();
		scrMain = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrMain.setBorder(BorderFactory.createEmptyBorder());
		scrMain.setViewportView(tbMain);
		renderTB(tbMain);
		renderData(tbMain);
		scrMain.setViewportBorder(null);

		tbMain.setShowGrid(false);
		tbMain.setIntercellSpacing(new Dimension(0, 0));
		TableCellRenderer renderer = new CustomTableCellRenderer();
		for (int i = 0; i < tbMain.getColumnCount(); i++) {
			tbMain.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		tbMain.setRowHeight(35);
		tbMain.getTableHeader().setPreferredSize(new Dimension(1, 30));
		tbMain.getTableHeader().setFont(fontTable);
		tbMain.getTableHeader().setBorder(null);
		tbMain.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbMain.getColumnModel().getColumn(0).setPreferredWidth(50);

		tbMain.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbMain.getColumnModel().getColumn(2).setPreferredWidth(200);
		tbMain.getColumnModel().getColumn(3).setPreferredWidth(50);
		tbMain.getColumnModel().getColumn(4).setPreferredWidth(50);

		formatTableGrade(tbMain);

		btnAdd = new JButton("Nhập Điểm");

		btnDelete = new JButton("Xóa");

		btnUpdate = new JButton("Sửa Điểm");

		btnCancel = new JButton("Hủy Điểm");

//		NOTIFICATION PANEL
		pnNotification = new JPanel();
		pnNotification.setLayout(new FlowLayout(FlowLayout.LEFT));
		lbNotification = new JLabel("*Chọn dòng muốn xem sau đó click phải chuột để xem chi tiết");
		lbNotification.setFont(sgUI13I);
		lbNotification.setForeground(Color.gray);
		pnNotification.add(lbNotification);
//		NORTH PANEL CONTENT LEFT
		pnNorthContentLeft = new JPanel();
		pnNorthContentLeft.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
//		pnNorthContentLeft.add(lbSortText);
//		pnNorthContentLeft.add(cbbSortContent);

//		NORTH PANEL CONTENT RIGHT
		pnNorthContentRight = new JPanel();
		pnNorthContentRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 3));
		pnNorthContentRight.add(tfSearch);
		pnNorthContentRight.add(btnSearch);

//		CONTAINER OF PANEL CONTENT RIGHT
		lbTittleOfSearch = new JLabel("Nhấn phím Enter hoặc nút tìm kiếm");
		lbTittleOfSearch.setFont(new Font("Segoe UI", Font.ITALIC, 10));
		lbTittleOfSearch.setForeground(Color.gray);
		pnContainerOfContentRightTittle = new JPanel();
		pnContainerOfContentRightTittle.setLayout(new FlowLayout(FlowLayout.LEFT, 13, 0));
		pnContainerOfContentRightTittle.add(lbTittleOfSearch);
		pnContainerOfContentRight = new JPanel();
		pnContainerOfContentRight.setLayout(new BorderLayout());
		pnContainerOfContentRight.add(pnContainerOfContentRightTittle, BorderLayout.SOUTH);
		pnContainerOfContentRight.add(pnNorthContentRight, BorderLayout.CENTER);
		pnContainerOfContentRightTittle.setBackground(Color.white);
		pnContainerOfContentRight.setBackground(Color.white);

//		NORTH PANEL CONTENT
		pnNorthContent = new JPanel();
		pnNorthContent.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 10));
		pnNorthContent.add(pnNorthContentLeft);
		pnNorthContent.add(pnContainerOfContentRight);

//		NORTH PANEL TITTLE
		pnNorthTittle = new JPanel();
		pnNorthTittle.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		pnNorthTittle.add(lbTittle);
		pnNorthTittle.add(btnRefresh);

//		NORTH PANEL
		pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		pnNorth.add(pnNorthTittle, BorderLayout.NORTH);
		pnNorth.add(pnNorthContent, BorderLayout.CENTER);

//		CENTER PANEL
		pnCenterContent = new JPanel();
		pnCenterContent.setLayout(new BorderLayout());
		pnCenterContent.add(scrMain, BorderLayout.CENTER);

		pnCenterInfor = new InformationFORM("Lập trình hướng đối tượng", "Nguyễn Thanh Sang", "Dương Thành Trưởng", 4,
				"Xuất Sắc");

		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.add(pnNotification, BorderLayout.NORTH);
		pnCenter.add(pnCenterContent, BorderLayout.CENTER);
		pnCenter.add(pnCenterInfor, BorderLayout.EAST);

//		SOUTH PANEL
		pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		pnSouth.add(btnAdd);
		pnSouth.add(btnUpdate);
		pnSouth.add(btnCancel);
		pnSouth.add(btnDelete);
		pnSouth.add(btnThongKe);

//		THE BIGGEST PANEL
		this.setLayout(new BorderLayout());
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
		this.add(pnSouth, BorderLayout.SOUTH);

	}

//	FONTS,SIZES,ICONS... OF COMPONENTS
	public void componentsStyle() {
		lbTittle.setFont(fontTittle);
		lbTittle.setSize(300, 50);

		btnRefresh.setSize(30, 30);

//		lbSortText.setFont(sgUI15);
//		lbSortText.setPreferredSize(new Dimension(100, 40));
//
//		cbbSortContent.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
//		cbbSortContent.setBackground(Color.white);
//		cbbSortContent.setFont(sgUI13);
//		cbbSortContent.setPreferredSize(new Dimension(200, 30));
//		cbbSortContent.setUI(new BasicComboBoxUI() {
//			@Override
//			protected ComboPopup createPopup() {
//				BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
//				basicComboPopup.setBorder(lineCB);
//				return basicComboPopup;
//			}
//		});
//		cbbSortContent.setBorder(matteBorderCB);

		tfSearch.setPreferredSize(new Dimension(200, 30));
		tfSearch.setFont(sgUI13);
		tfSearch.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
		tfSearch.setForeground(Color.black);

		ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/searchIcon.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnSearch.setIcon(iconSearch);
		btnSearch.setFont(sgUI13b);
		btnSearch.setFocusPainted(false);
		btnSearch.setBorderPainted(false);

		ImageIcon addIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/btnAdd.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnAdd.setIcon(addIcon);
		btnAdd.setFont(sgUI13b);
		btnAdd.setFocusPainted(false);
		btnAdd.setBorderPainted(false);
		
		ImageIcon tkIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/statistic.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnThongKe.setIcon(tkIcon);
		btnThongKe.setFont(sgUI13b);
		btnThongKe.setFocusPainted(false);
		btnThongKe.setBorderPainted(false);

		ImageIcon cancelIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnCancel.setIcon(cancelIcon);
		btnCancel.setFont(sgUI13b);
		btnCancel.setFocusPainted(false);
		btnCancel.setBorderPainted(false);

		ImageIcon delIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/btnDel.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnDelete.setIcon(delIcon);
		btnDelete.setFont(sgUI13b);
		btnDelete.setFocusPainted(false);
		btnDelete.setBorderPainted(false);

		ImageIcon saveIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/pencil.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnUpdate.setIcon(saveIcon);
		btnUpdate.setFont(sgUI13b);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorderPainted(false);

		ImageIcon refreshIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/Refresh-icon.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnRefresh.setIcon(refreshIcon);
		btnRefresh.setFont(sgUI13b);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBorderPainted(false);

	}

//	COLORS OF COMPONENTS
	public void componentsColor() {
		pnCenter.setBackground(Color.white);
		pnNorth.setBackground(Color.white);
		pnNorthContent.setBackground(Color.white);
		pnNorthTittle.setBackground(Color.decode(colorTableCode));
		pnSouth.setBackground(Color.white);
		pnNorthContentLeft.setBackground(Color.white);
		pnNorthContentRight.setBackground(Color.white);
		pnNotification.setBackground(Color.white);
		this.setBackground(Color.white);

		btnAdd.setBackground(Color.decode("#ebf2fc"));
		btnThongKe.setBackground(Color.decode("#ebf2fc"));
		btnCancel.setBackground(Color.decode("#ebf2fc"));
		btnDelete.setBackground(Color.decode("#ebf2fc"));
		btnRefresh.setBackground(Color.decode(colorTableCode));
		btnUpdate.setBackground(Color.decode("#ebf2fc"));
		btnSearch.setBackground(Color.decode("#ebf2fc"));
		scrMain.getViewport().setBackground(Color.white);
		tbMain.getTableHeader().setBackground(Color.decode(colorTableCode));
	}

//	VALUE OF SORT COMBOBOX
	public void initSortComboboxValue() {

	}

//	EVENT
	public void events() {
		eventRowDetail();
		eventAdd();
		eventUpdate();
		eventRemove();
		eventSearch();
		eventCancel();
		eventStatistic();
//		eventRefresh();
	}

	public void renderTB(JTable tbP) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID Khóa Học");
		dtm.addColumn("Tên Khóa Học");
		dtm.addColumn("Họ Và Tên");
		dtm.addColumn("Điểm Số");
		dtm.addColumn("Kết Quả");

		tbP.setModel(dtm);
		tbP.setBorder(new MatteBorder(1, 1, 1, 1, Color.white));
	}

	public void renderData(JTable tb) {
		DefaultTableModel model = (DefaultTableModel) tb.getModel();
		model.setRowCount(0);
		for (Vector<String> vec : KetQuaBUS.getInstance().getListTable()) {
			model.addRow(vec);
		}
	}

	public void formatTableGrade(JTable tb) {
//		for(int i=0;i<tb.getRowCount();i++) {
//			if(Float.parseFloat(tb.getValueAt(i, 3).toString())<1) {
//				tb.get
//			}
//		}
		tb.getColumnModel().getColumn(3).setCellRenderer(new ColoredTableCellRenderer());
		tb.getColumnModel().getColumn(4).setCellRenderer(new ColoredTableCellRenderer());
	}

	public void eventRowDetail() {
		tbMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {

					new ThongBaoDialog("Xem Chi Tiết", ThongBaoDialog.INFO_DIALOG);
					int choice = ThongBaoDialog.action;

					// Xử lý lựa chọn từ người dùng
					if (choice == ThongBaoDialog.OK_OPTION) {
						// Thực hiện hành động khi người dùng chọn OK
						int row = tbMain.getSelectedRow();
						if (row < 0) {
							new ThongBaoDialog("Chọn dòng để xem", ThongBaoDialog.INFO_DIALOG);
							return;
						}
//                        System.out.println(row);
						int courseId = Integer.parseInt(tbMain.getValueAt(row, 0).toString());
						String courseName = tbMain.getValueAt(row, 1).toString();
						String studentName = tbMain.getValueAt(row, 2).toString();
						float grade = Float.parseFloat(tbMain.getValueAt(row, 3).toString());
						String teacherName = KetQuaBUS.getInstance().getTeacherNameByCourseId(courseId); // DÙNG TẠM
						String rank = "";
						if (grade >= 1 && grade < 2.5) {
							rank = "Trung Bình";
						} else if (grade >= 2.5 && grade < 3.2) {
							rank = "Khá";
						} else if (grade >= 3.2 && grade < 3.6) {
							rank = "Giỏi";
						} else if (grade >= 3.6) {
							rank = "Xuất Sắc";
						} else {
							rank = "Yếu";
						}
						pnCenterInfor.setDatas(courseName, teacherName, studentName, grade, rank);
						pnCenterInfor.setVisible(true);
					} else {
						// Thực hiện hành động khi người dùng chọn Cancel hoặc đóng cửa sổ
						System.out.println("User canceled the action.");
						return;
					}
				}
			}
		});

	}

	public void eventAdd() {
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int numOfStudent = 0;
				ArrayList<String> courses = new ArrayList<>();
				ArrayList<String> students = new ArrayList<>();
				for (int i = 0; i < tbMain.getRowCount(); i++) {
					if (tbMain.getValueAt(i, 4).toString().equalsIgnoreCase("chưa nhập điểm")) {
						String cou = tbMain.getValueAt(i, 0).toString() + " - " + tbMain.getValueAt(i, 1).toString();
						String stu = tbMain.getValueAt(i, 2).toString();
						courses.add(cou);
						students.add(stu);
						numOfStudent++;
					}
				}
				if (numOfStudent < 1) {
					new ThongBaoDialog("Không có sinh viên chưa nhập điểm", ThongBaoDialog.INFO_DIALOG);
					return;
				}
				new AddOrUpdateForm(numOfStudent, courses, students);
				renderData(tbMain);
			}
		});

	}

	public void eventUpdate() {
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int numOfStudent = 0;
				ArrayList<String> courses = new ArrayList<>();
				ArrayList<String> students = new ArrayList<>();
				ArrayList<Float> grades = new ArrayList<>();
				for (int i = 0; i < tbMain.getRowCount(); i++) {
					String cou = tbMain.getValueAt(i, 0).toString() + " - " + tbMain.getValueAt(i, 1).toString();
					String stu = tbMain.getValueAt(i, 2).toString();
					float gra = Float.parseFloat(tbMain.getValueAt(i, 3).toString());
					courses.add(cou);
					students.add(stu);
					grades.add(gra);
					numOfStudent++;
				}
				new AddOrUpdateForm(numOfStudent, courses, students, grades);
				renderData(tbMain);
			}
		});
	}

	public void eventRemove() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tbMain.getSelectedRow();
				if (row < 0) {
					new ThongBaoDialog("Chưa chọn dòng", ThongBaoDialog.INFO_DIALOG);
					return;
				}
				int courseid = Integer.parseInt(tbMain.getValueAt(row, 0).toString());
				String fullName = tbMain.getValueAt(row, 2).toString();
				KetQuaBUS.getInstance().delete(courseid, fullName);
				renderData(tbMain);
			}
		});
	}

	public void eventSearch() {

		DefaultTableModel model = (DefaultTableModel) tbMain.getModel();
		// Create a RowSorter for the table
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		tbMain.setRowSorter(sorter);

		// Add ActionListener to the searchField
		tfSearch.addActionListener(e -> {
			String text = tfSearch.getText();
			if (text.trim().length() == 0) {
				sorter.setRowFilter(null); // If no text, show all rows
			} else {
				// Build a RowFilter that accepts rows if any of the columns contain the text
				RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + text);
				sorter.setRowFilter(rowFilter);
			}
		});
		btnSearch.addActionListener(e -> {
			String text = tfSearch.getText();
			if (text.trim().length() == 0) {
				sorter.setRowFilter(null); // If no text, show all rows
			} else {
				// Build a RowFilter that accepts rows if any of the columns contain the text
				RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + text);
				sorter.setRowFilter(rowFilter);
			}
		});
		btnRefresh.addActionListener(e -> {
			sorter.setRowFilter(null); // If no text, show all rows
			tfSearch.setText("");
			lbNotification.setText("*Chọn dòng muốn xem sau đó click phải chuột để xem chi tiết");
			renderData(tbMain);
		});
	}

	public void eventCancel() {
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tbMain.getSelectedRow();
				if (row < 0) {
					new ThongBaoDialog("Chọn sinh viên", ThongBaoDialog.INFO_DIALOG);
					return;
				}
				int courseid = Integer.parseInt(tbMain.getValueAt(row, 0).toString());
				String fullname = tbMain.getValueAt(row, 2).toString();
				KetQuaBUS.getInstance().cancel(courseid, fullname);
				renderData(tbMain);
			}
		});
	}
	public void eventStatistic() {
		btnThongKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ThongKeFORM();
			}
		});
	}

	public void eventRefresh() {

	}
}
