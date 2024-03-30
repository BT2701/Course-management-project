package GUI.GUI_ONLINECOURSE;

import BUS.CourseBUS;
import BUS.DepartmentBUS;
import java.awt.BorderLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import BUS.OnlineCourseBUS;
import DTO.DepartmentDTO;
import DTO.OnlineCourseDTO;
import GUI.GUI_BASIC.ThongBaoDialog;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class OnlineCourseGUI extends JPanel {
	OnlineCourseBUS olBUS = new OnlineCourseBUS();
	DepartmentBUS depBUS = new DepartmentBUS();
	CourseBUS coursebus=new CourseBUS();
//	private Font fontSubTittle = new Font("Tahoma", Font.BOLD, 20);
	private Font fontbtn = new Font("Tahoma", Font.PLAIN, 13);
//	private Font fontTable = new Font("Segoe UI", Font.BOLD, 13);

//	private Color texfieldColor = new Color(45, 52, 54);
	private DefaultTableModel model;
	private JTable listCourse;
	private JScrollPane listOnlineCourse;
	private JComboBox<String> comboBox;
	private JTextField txtid;
	private JButton btnAccept, btnThem, btnXoa, btnSua;

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

	public OnlineCourseGUI() {
		unitGUI();
	}

	public void unitGUI() {
		setLayout(new BorderLayout());
		setVisible(true);
		JLabel lbTitle = new JLabel("KHOÁ HỌC ONLINE");
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.WHITE);
		lbTitle.setFont(fontSubTittle);
		pnTitle.add(lbTitle);
		pnTitle.setPreferredSize(new Dimension(0, 80));
		listOnlineCourse = tblistCourse();

		JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		btnThem = new JButton("Thêm");
//		ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/searchIcon.png"))
//				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//		btnThem.setIcon(iconSearch);
		btnThem.setFont(sgUI13b);
		btnThem.setFocusPainted(false);
		btnThem.setBorderPainted(false);
//		btnThem.setBackground(Color.decode("#ebf2fc"))
		btnThem.setBackground(Color.decode("#55efc4"));
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JDialog frame = new JDialog();
				frame.setLayout(new BorderLayout());

				frame.setSize(400, 500);
				frame.setLocationRelativeTo(null);
				frame.setModal(true);
				frame.setResizable(false);

				JPanel content = new JPanel();
				content.setBackground(Color.white);
				content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

				JPanel pnID = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnID.setBackground(Color.white);
				JLabel lbid = new JLabel("ID");
				lbid.setPreferredSize(new Dimension(80, 30));
				JTextField txtid = new JTextField();
//        		txtid.setPreferredSize(new Dimension(180, 30));
				txtid.setPreferredSize(new Dimension(200, 30));
				txtid.setFont(sgUI13);
				txtid.setEditable(false);
				txtid.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
				txtid.setForeground(Color.black);
				txtid.setText(coursebus.getNewestId()+"");

				pnID.add(lbid);
				pnID.add(txtid);

				JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnTitle.setBackground(Color.white);
				JLabel lbTitle = new JLabel("Tittle");
				lbTitle.setPreferredSize(new Dimension(80, 30));
				JTextField txtTitle = new JTextField();
				txtTitle.setPreferredSize(new Dimension(200, 30));
				txtTitle.setFont(sgUI13);
				txtTitle.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
				txtTitle.setForeground(Color.black);

				pnTitle.add(lbTitle);
				pnTitle.add(txtTitle);

				JPanel pnMK = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnMK.setBackground(Color.white);
				JLabel lbMK = new JLabel("DepartmentID");
				lbMK.setPreferredSize(new Dimension(80, 30));

				List<DepartmentDTO> options = depBUS.findAll();
				String[] name = new String[options.size()];
				for (int i = 0; i < options.size(); i++) {
					name[i] = options.get(i).getName();
				}
				JComboBox<String> opMK = new JComboBox<>(name);

				opMK.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
				opMK.setBackground(Color.white);
				opMK.setFont(sgUI13);
				opMK.setPreferredSize(new Dimension(200, 30));
				opMK.setUI(new BasicComboBoxUI() {
					@Override
					protected ComboPopup createPopup() {
						BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
						basicComboPopup.setBorder(lineCB);
						return basicComboPopup;
					}
				});
				opMK.setBorder(matteBorderCB);
				pnMK.add(lbMK);
				pnMK.add(opMK);

				JPanel pnCre = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnCre.setBackground(Color.white);
				JLabel lbCre = new JLabel("Credits");
				lbCre.setPreferredSize(new Dimension(80, 30));
				JTextField txtCre = new JTextField();
				txtCre.setPreferredSize(new Dimension(200, 30));
				txtCre.setFont(sgUI13);
				txtCre.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
				txtCre.setForeground(Color.black);
				pnCre.add(lbCre);
				pnCre.add(txtCre);

				JPanel pnUrl = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnUrl.setBackground(Color.white);
				JLabel lbUrl = new JLabel("Url");
				lbUrl.setPreferredSize(new Dimension(80, 30));
				JTextField txtUrl = new JTextField();
				txtUrl.setPreferredSize(new Dimension(200, 30));
				txtUrl.setFont(sgUI13);
				txtUrl.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
				txtUrl.setForeground(Color.black);
				pnUrl.add(lbUrl);
				pnUrl.add(txtUrl);

				content.add(pnID);
				content.add(pnTitle);
				content.add(pnMK);
				content.add(pnCre);
				content.add(pnUrl);

				JPanel pntitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel title = new JLabel("Thông Tin");
				title.setFont(fontSubTittle);
				pntitle.setPreferredSize(new Dimension(0, 50));
				pntitle.add(title);

				JPanel pnL = new JPanel();
				pnL.setBackground(Color.white);
				pnL.setPreferredSize(new Dimension(50, 0));
				JPanel pnR = new JPanel();
				pnL.setPreferredSize(new Dimension(50, 0));

				JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pnBottom.setBackground(Color.white);
				JButton btnAccept = new JButton("Xác nhận");
				ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/tick.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				btnAccept.setIcon(iconSearch);
				btnAccept.setFont(sgUI13b);
				btnAccept.setFocusPainted(false);
				btnAccept.setBorderPainted(false);
				btnAccept.setBackground(Color.decode("#ebf2fc"));
				btnAccept.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(txtid.getText());
						String name = txtTitle.getText();
						DepartmentDTO depa = findDepartmentByName(options, String.valueOf(opMK.getSelectedItem()));
						int dpid = depa.getDepartmentID();
						int cre = Integer.parseInt(txtCre.getText());
						String url = txtUrl.getText();
						OnlineCourseDTO onl = new OnlineCourseDTO(id, name, cre, dpid, url);
						JOptionPane.showMessageDialog(frame, olBUS.insert(onl), "Thong bao",
								JOptionPane.INFORMATION_MESSAGE);
						refreshTable();
					}
				});
				JButton btnDeny = new JButton("Huỷ");
				ImageIcon iconX = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				btnDeny.setIcon(iconX);
				btnDeny.setFont(sgUI13b);
				btnDeny.setFocusPainted(false);
				btnDeny.setBorderPainted(false);
				btnDeny.setBackground(Color.decode("#ebf2fc"));
				btnDeny.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});

				pnBottom.add(btnAccept);
				pnBottom.add(btnDeny);

				frame.add(content, BorderLayout.CENTER);
				frame.add(pntitle, BorderLayout.NORTH);
				pntitle.setBackground(Color.white);
				frame.add(pnL, BorderLayout.WEST);
				frame.add(pnR, BorderLayout.EAST);
				pnR.setBackground(Color.white);
				frame.add(pnBottom, BorderLayout.SOUTH);
				frame.setBackground(Color.white);
				frame.setVisible(true);
			}
		});
//		btnThem.setFont(fontbtn);
//		btnThem.setPreferredSize(new Dimension(100, 40));
		ImageIcon iconPlus = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/plus2.png")).getImage()
				.getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		btnThem.setIcon(iconPlus);
//		btnThem.setBackground(Color.BLACK);

		btnXoa = new JButton("Xoá");
		btnXoa.setBackground(Color.decode("#ff7675"));
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = listCourse.getSelectedRow();
				if (selectedRow != -1) {
					int value = (Integer) model.getValueAt(selectedRow, getColumnIndex("ID"));
					OnlineCourseDTO onl = olBUS.findById(value);
					JOptionPane.showMessageDialog(new JFrame(), olBUS.delete(onl), "Thong bao",
							JOptionPane.INFORMATION_MESSAGE);
					refreshTable();
				}
			}
		});
		btnXoa.setFont(fontbtn);

		ImageIcon iconXoa = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/trash.png")).getImage()
				.getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		btnXoa.setIcon(iconXoa);
//		ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/searchIcon.png"))
//				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//		btnAccept.setIcon(iconSearch);
		btnXoa.setFont(sgUI13b);
		btnXoa.setFocusPainted(false);
		btnXoa.setBorderPainted(false);
//		btnXoa.setBackground(Color.decode("#ebf2fc"));

		btnSua = new JButton("Sửa");
		btnSua.setFont(sgUI13b);
		btnSua.setFocusPainted(false);
		btnSua.setBorderPainted(false);
		btnSua.setBackground(Color.decode("#ebf2fc"));
		btnSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listCourse.getSelectedRow() < 0) {
					new ThongBaoDialog("Chọn thông tin chỉnh sửa", ThongBaoDialog.INFO_DIALOG);
					return;
				}

				JDialog frame = new JDialog();
				frame.setLayout(new BorderLayout());

				int selectedRow = listCourse.getSelectedRow();
				int value = (Integer) model.getValueAt(selectedRow, getColumnIndex("ID"));
				OnlineCourseDTO onl = olBUS.findById(value);

				
				frame.setModal(true);
				frame.setSize(400, 500);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);

				JPanel content = new JPanel();
				content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
				content.setBackground(Color.white);

				JPanel pnID = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnID.setBackground(Color.white);
				JLabel lbid = new JLabel("ID");
				lbid.setPreferredSize(new Dimension(80, 30));
				JTextField txtid = new JTextField();
				txtid.setText(String.valueOf(onl.getId()));
				txtid.setPreferredSize(new Dimension(200, 30));
				txtid.setFont(sgUI13);
				txtid.setEditable(false);
				txtid.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
				txtid.setForeground(Color.black);

				pnID.add(lbid);
				pnID.add(txtid);

				JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnTitle.setBackground(Color.white);
				JLabel lbTitle = new JLabel("Tittle");
				lbTitle.setPreferredSize(new Dimension(80, 30));
				JTextField txtTitle = new JTextField();
				txtTitle.setText(onl.getTittle());
				txtTitle.setPreferredSize(new Dimension(200, 30));
				txtTitle.setFont(sgUI13);
				txtTitle.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
				txtTitle.setForeground(Color.black);

				pnTitle.add(lbTitle);
				pnTitle.add(txtTitle);

				JPanel pnMK = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnMK.setBackground(Color.white);
				JLabel lbMK = new JLabel("DepartmentID");
				lbMK.setPreferredSize(new Dimension(80, 30));

				List<DepartmentDTO> options = depBUS.findAll();
				String[] name = new String[options.size()];
				for (int i = 0; i < options.size(); i++) {
					name[i] = options.get(i).getName();
				}
				JComboBox<String> opMK = new JComboBox<>(name);

				opMK.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
				opMK.setBackground(Color.white);
				opMK.setFont(sgUI13);
				opMK.setPreferredSize(new Dimension(200, 30));
				opMK.setSelectedItem(onl.getDepartmentName().getName());
				opMK.setUI(new BasicComboBoxUI() {
					@Override
					protected ComboPopup createPopup() {
						BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
						basicComboPopup.setBorder(lineCB);
						return basicComboPopup;
					}
				});
				opMK.setBorder(matteBorderCB);
				pnMK.add(lbMK);
				pnMK.add(opMK);

				JPanel pnCre = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnCre.setBackground(Color.white);
				JLabel lbCre = new JLabel("Credits");
				lbCre.setPreferredSize(new Dimension(80, 30));
				JTextField txtCre = new JTextField();
				txtCre.setText(String.valueOf(onl.getCredits()));
				txtCre.setPreferredSize(new Dimension(200, 30));
				txtCre.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
				txtCre.setForeground(Color.black);
				txtCre.setFont(sgUI13);
				pnCre.add(lbCre);
				pnCre.add(txtCre);

				JPanel pnUrl = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pnUrl.setBackground(Color.white);
				JLabel lbUrl = new JLabel("Url");
				lbUrl.setPreferredSize(new Dimension(80, 30));
				JTextField txtUrl = new JTextField();
				txtUrl.setText(onl.getUrl());
				txtUrl.setPreferredSize(new Dimension(200, 30));
				txtUrl.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
				txtUrl.setForeground(Color.black);

				txtUrl.setFont(sgUI13);
				pnUrl.add(lbUrl);
				pnUrl.add(txtUrl);

				content.add(pnID);
				content.add(pnTitle);
				content.add(pnMK);
				content.add(pnCre);
				content.add(pnUrl);

				JPanel pntitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel title = new JLabel("Thông Tin");
				title.setFont(fontSubTittle);
				pntitle.setPreferredSize(new Dimension(0, 50));
				pntitle.add(title);

				JPanel pnL = new JPanel();
				pnL.setBackground(Color.white);
				pnL.setPreferredSize(new Dimension(50, 0));
				JPanel pnR = new JPanel();
				pnL.setPreferredSize(new Dimension(50, 0));

				JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
				btnAccept = new JButton("Xác nhận");
				ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/tick.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				btnAccept.setIcon(iconSearch);
				btnAccept.setFont(sgUI13b);
				btnAccept.setFocusPainted(false);
				btnAccept.setBorderPainted(false);
				btnAccept.setBackground(Color.decode("#ebf2fc"));
				btnAccept.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(txtid.getText());
						String name = txtTitle.getText();
						DepartmentDTO depa = findDepartmentByName(options, String.valueOf(opMK.getSelectedItem()));
						int dpid = depa.getDepartmentID();
						int cre = Integer.parseInt(txtCre.getText());
						String url = txtUrl.getText();
						OnlineCourseDTO onl = new OnlineCourseDTO(id, name, cre, dpid, url);
						JOptionPane.showMessageDialog(frame, olBUS.update(onl), "Thong bao",
								JOptionPane.INFORMATION_MESSAGE);
						refreshTable();
					}
				});
				JButton btnDeny = new JButton("Huỷ");
				ImageIcon iconX = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				btnDeny.setIcon(iconX);
				btnDeny.setFont(sgUI13b);
				btnDeny.setFocusPainted(false);
				btnDeny.setBorderPainted(false);
				btnDeny.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});

				pnBottom.add(btnAccept);
				pnBottom.add(btnDeny);

				frame.add(content, BorderLayout.CENTER);
				frame.add(pntitle, BorderLayout.NORTH);
				frame.add(pnL, BorderLayout.WEST);
				frame.add(pnR, BorderLayout.EAST);
				pntitle.setBackground(Color.white);
				pnR.setBackground(Color.white);
				pnBottom.setBackground(Color.white);
				frame.add(pnBottom, BorderLayout.SOUTH);
				txtid.setEditable(false);
				txtid.setEnabled(false);
				frame.setVisible(true);
			}
		});
		ImageIcon iconSua = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/pencil.png")).getImage()
				.getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		btnSua.setIcon(iconSua);

		footer.setBackground(Color.WHITE);
		footer.add(btnThem);
		footer.add(btnSua);
		footer.add(btnXoa);

		JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
		search.setBackground(Color.WHITE);
		JLabel lbsearch = new JLabel("Tìm kiếm");
		JTextField txtSearch = new JTextField();
		txtSearch.setPreferredSize(new Dimension(200, 30));
		txtSearch.setFont(sgUI13);
		txtSearch.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
		txtSearch.setForeground(Color.black);

		String[] Choise = { "All", "CourseID", "Title", "Department", "Url", "Credits" };

		comboBox = new JComboBox<>(Choise);
		comboBox.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
		comboBox.setBackground(Color.white);
		comboBox.setFont(sgUI13);
		comboBox.setPreferredSize(new Dimension(200, 30));
		comboBox.setUI(new BasicComboBoxUI() {
			@Override
			protected ComboPopup createPopup() {
				BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
				basicComboPopup.setBorder(lineCB);
				return basicComboPopup;
			}
		});
		comboBox.setBorder(matteBorderCB);

		JButton accept = new JButton("Tìm");
		ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/searchIcon.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		accept.setIcon(iconSearch);
		accept.setFont(sgUI13b);
		accept.setFocusPainted(false);
		accept.setBorderPainted(false);
		accept.setBackground(Color.decode("#ebf2fc"));
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(txtSearch.getText());
				searchByCondition(txtSearch.getText());
				txtSearch.setText("");
			}
		});

		search.add(lbsearch);
		search.add(txtSearch);
		search.add(comboBox);
		search.add(accept);

		JPanel Content = new JPanel(new BorderLayout());
		Content.add(listOnlineCourse, BorderLayout.CENTER);
		Content.add(search, BorderLayout.NORTH);

		add(pnTitle, BorderLayout.NORTH);
		add(Content, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
	}

	public JScrollPane tblistCourse() {
		String column[] = { "ID", "Tên Môn Học", "Khoa", "Giá", "Url" };
		model = new DefaultTableModel(column, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô trong JTable
			}
		};

		List<OnlineCourseDTO> list = olBUS.findAll();
		for (OnlineCourseDTO course : list) {
			Object[] duLieu = { course.getId(), course.getTittle(), course.getDepartmentName().getName(),
					course.getCredits(), course.getUrl() };
			model.addRow(duLieu);
		}
		listCourse = new JTable(model);
		listOnlineCourse = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listOnlineCourse.setBorder(BorderFactory.createEmptyBorder());
		listOnlineCourse.setViewportView(listCourse);
		listOnlineCourse.getViewport().setBackground(Color.white);
		listCourse.getTableHeader().setBackground(Color.decode(colorTableCode));
//		renderTB(listCourse);
//		renderData(tbMain);
		listOnlineCourse.setViewportBorder(null);

		listCourse.setShowGrid(false);
		listCourse.setIntercellSpacing(new Dimension(0, 0));
		TableCellRenderer renderer = new TableCellRendererOnlineCourse();
		for (int i = 0; i < listCourse.getColumnCount(); i++) {
			listCourse.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		listCourse.setRowHeight(35);
		listCourse.getTableHeader().setPreferredSize(new Dimension(1, 30));
		listCourse.getTableHeader().setFont(fontTable);
		listCourse.getTableHeader().setBorder(null);
		listCourse.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCourse.getColumnModel().getColumn(0).setPreferredWidth(50);

		listCourse.getColumnModel().getColumn(1).setPreferredWidth(200);
		listCourse.getColumnModel().getColumn(2).setPreferredWidth(50);
		listCourse.getColumnModel().getColumn(3).setPreferredWidth(50);
		listCourse.getColumnModel().getColumn(4).setPreferredWidth(200);
		for (int i = 0; i < listCourse.getColumnCount(); i++) {
			listCourse.getColumnModel().getColumn(i).setCellEditor(null);
		}
		listCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Kiểm tra double click
					int selectedRow = listCourse.getSelectedRow();
					if (selectedRow != -1) {
						JDialog frame = new JDialog();
						frame.setBackground(Color.white);
						frame.setModal(true);
						frame.setLayout(new BorderLayout());
						int value = (Integer) model.getValueAt(selectedRow, getColumnIndex("ID"));
						OnlineCourseDTO onl = olBUS.findById(value);
						frame.setSize(400, 500);
						frame.setLocationRelativeTo(null);
						frame.setResizable(false);
						JPanel content = new JPanel();
						content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

						JPanel pnID = new JPanel(new FlowLayout(FlowLayout.LEFT));
						pnID.setBackground(Color.white);
						JLabel lbid = new JLabel("ID");
						lbid.setPreferredSize(new Dimension(80, 30));
						JTextField txtid = new JTextField();
						txtid.setForeground(Color.black);
						txtid.setEditable(false);
						txtid.setText(String.valueOf(onl.getId()));
						txtid.setPreferredSize(new Dimension(200, 30));
						pnID.add(lbid);
						pnID.add(txtid);

						JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
						JLabel lbTitle = new JLabel("Tittle");
						lbTitle.setPreferredSize(new Dimension(80, 30));
						JTextField txtTitle = new JTextField();
						pnTitle.setBackground(Color.white);
						txtTitle.setForeground(Color.black);
						txtTitle.setEditable(false);
						txtTitle.setText(onl.getTittle());
						txtTitle.setPreferredSize(new Dimension(200, 30));
						pnTitle.add(lbTitle);
						pnTitle.add(txtTitle);

						JPanel pnMK = new JPanel(new FlowLayout(FlowLayout.LEFT));
						JLabel lbMK = new JLabel("DepartmentID");
						lbMK.setPreferredSize(new Dimension(80, 30));
						JTextField txtMK = new JTextField();
						pnMK.setBackground(Color.white);
						txtMK.setForeground(Color.black);
						txtMK.setEditable(false);
						txtMK.setText(onl.getDepartmentName().getName());
						txtMK.setPreferredSize(new Dimension(200, 30));
						pnMK.add(lbMK);
						pnMK.add(txtMK);

						JPanel pnCre = new JPanel(new FlowLayout(FlowLayout.LEFT));
						JLabel lbCre = new JLabel("Credits");
						lbCre.setPreferredSize(new Dimension(80, 30));
						JTextField txtCre = new JTextField();
						pnCre.setBackground(Color.white);
						txtCre.setForeground(Color.black);
						txtCre.setEditable(false);
						txtCre.setText(String.valueOf(onl.getCredits()));
						txtCre.setPreferredSize(new Dimension(200, 30));
						pnCre.add(lbCre);
						pnCre.add(txtCre);

						JPanel pnUrl = new JPanel(new FlowLayout(FlowLayout.LEFT));
						JLabel lbUrl = new JLabel("Url");
						lbUrl.setPreferredSize(new Dimension(80, 30));
						JTextField txtUrl = new JTextField();
						pnUrl.setBackground(Color.white);
						txtUrl.setForeground(Color.black);
						txtUrl.setEditable(false);
						txtUrl.setText(onl.getUrl());
						txtUrl.setPreferredSize(new Dimension(200, 30));
						pnUrl.add(lbUrl);
						pnUrl.add(txtUrl);

						content.add(pnID);
						content.add(pnTitle);
						content.add(pnMK);
						content.add(pnCre);
						content.add(pnUrl);

						JPanel pntitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
						JLabel title = new JLabel("Thông Tin");
						pntitle.setBackground(Color.white);
						title.setFont(fontSubTittle);
						pntitle.setPreferredSize(new Dimension(0, 50));
						pntitle.add(title);

						JPanel pnL = new JPanel();
						pnL.setBackground(Color.white);
						pnL.setPreferredSize(new Dimension(50, 0));
						JPanel pnR = new JPanel();
						pnR.setBackground(Color.white);
						pnL.setPreferredSize(new Dimension(50, 0));

						JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

						JButton btnDeny = new JButton("Huỷ");
						ImageIcon iconX = new ImageIcon(
								new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png")).getImage()
										.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
						btnDeny.setIcon(iconX);
						btnDeny.setFont(sgUI13b);
						btnDeny.setFocusPainted(false);
						btnDeny.setBorderPainted(false);
						btnDeny.setBackground(Color.white);
						btnDeny.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
							}
						});
						btnDeny.setPreferredSize(new Dimension(100, 40));
						btnDeny.setBackground(Color.decode("#ebf2fc"));
						btnDeny.setFont(fontbtn);
						pnBottom.add(btnDeny);
						pnBottom.setBackground(Color.white);

						frame.add(content, BorderLayout.CENTER);
						frame.add(pntitle, BorderLayout.NORTH);
						frame.add(pnL, BorderLayout.WEST);
						frame.add(pnR, BorderLayout.EAST);
						frame.add(pnBottom, BorderLayout.SOUTH);
						frame.setVisible(true);
					}
				}
			}
		});
		listCourse.setFont(fontTable);
		JScrollPane SpCourse = new JScrollPane(listCourse);
		return SpCourse;
	}

	private void refreshTable() {
		model.setRowCount(0); // Xóa tất cả dòng trong mô hình
		List<OnlineCourseDTO> list = olBUS.findAll();
		for (OnlineCourseDTO course : list) {
			Object[] duLieu = { course.getId(), course.getTittle(), course.getDepartmentName().getName(),
					course.getCredits(), course.getUrl() };
			model.addRow(duLieu);
		}
	}

	private void searchByCondition(String condition) {
		model.setRowCount(0);
		List<OnlineCourseDTO> list = new ArrayList();
		System.out.println(comboBox.getSelectedItem());
		String choise = String.valueOf(comboBox.getSelectedItem());// Xóa tất cả dòng trong mô hình
		if (choise.equalsIgnoreCase("Url")) {
			list = olBUS.findByUrl(condition);
		} else if (choise.equalsIgnoreCase("CourseID")) {
			list = olBUS.findByCourseID(condition);
		} else if (choise.equalsIgnoreCase("Department")) {
			list = olBUS.findByDepartment(condition);
		} else if (choise.equalsIgnoreCase("Credits")) {
			list = olBUS.findByCredits(condition);
		} else if (choise.equalsIgnoreCase("Title")) {
			list = olBUS.findByTitle(condition);
		} else {
			list = olBUS.findAll();
		}
		if (list != null) {
			for (OnlineCourseDTO course : list) {
				Object[] duLieu = { course.getId(), course.getTittle(), course.getDepartmentName().getName(),
						course.getCredits(), course.getUrl() };
				model.addRow(duLieu);
			}
		}
	}

	private int getColumnIndex(String columnName) {
		for (int i = 0; i < model.getColumnCount(); i++) {
			if (model.getColumnName(i).equals(columnName)) {
				return i;
			}
		}
		return -1;
	}

	public DepartmentDTO findDepartmentByName(List<DepartmentDTO> departmentList, String targetName) {
		for (DepartmentDTO department : departmentList) {
			if (department.getName().equals(targetName)) {
				return department; // Trả về đối tượng khi tìm thấy
			}
		}
		return null; // Trả về null nếu không tìm thấy
	}

}
