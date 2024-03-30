/*
 * Author: Dương Thành Trưởng
 */

package GUI.GUI_KETQUA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import BUS.KetQuaBUS;

public class AddEditFORM extends JDialog {

//	COMPONENTS
	private JPanel pnNorth, pnCenter, pnSouth, pnCourse, pnStudent, pnGrade;
	private JLabel lbTittle, lbCourseID, lbStudentID, lbGrade;
	private JComboBox<String> cbbCourseId, cbbStudentId;
	private JTextField tfGrade;
	private JButton btnClose, btnAcept;

//	FORMAT
	private Font sgUI15b = new Font("Segoe UI", Font.BOLD, 15);
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

	public AddEditFORM() {
		setSize(320, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initComponents();
		containerAdd();
		componentColors();
		eventAddForm();

		setVisible(true);
	}

	public AddEditFORM(String course, String student, float grade) {
		setSize(320, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initComponents();
		containerUpdate(course, student, grade);
		componentColors();
		eventUpdateForm();

		setVisible(true);
	}

	public void initComponents() {

//		SUB COMPONENTS
		lbTittle = new JLabel("Tittle");
		lbTittle.setFont(sgUI15b);

		lbCourseID = new JLabel("Khóa Học");
		lbCourseID.setPreferredSize(new Dimension(70, 40));
		lbCourseID.setFont(sgUI13b);

		lbStudentID = new JLabel("Sinh Viên");
		lbStudentID.setPreferredSize(new Dimension(70, 40));
		lbStudentID.setFont(sgUI13b);

		lbGrade = new JLabel("Điểm Số");
		lbGrade.setPreferredSize(new Dimension(70, 40));
		lbGrade.setFont(sgUI13b);

		cbbCourseId = new JComboBox<String>();
		cbbCourseId.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
		cbbCourseId.setBackground(Color.white);
		cbbCourseId.setFont(sgUI13);
		cbbCourseId.setPreferredSize(new Dimension(200, 30));
		cbbCourseId.setUI(new BasicComboBoxUI() {
			@Override
			protected ComboPopup createPopup() {
				BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
				basicComboPopup.setBorder(lineCB);
				return basicComboPopup;
			}
		});
		cbbCourseId.setBorder(matteBorderCB);

		cbbStudentId = new JComboBox<String>();
		cbbStudentId.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
		cbbStudentId.setBackground(Color.white);
		cbbStudentId.setFont(sgUI13);
		cbbStudentId.setPreferredSize(new Dimension(200, 30));
		cbbStudentId.setUI(new BasicComboBoxUI() {
			@Override
			protected ComboPopup createPopup() {
				BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
				basicComboPopup.setBorder(lineCB);
				return basicComboPopup;
			}
		});
		cbbStudentId.setBorder(matteBorderCB);

		tfGrade = new JTextField();
		tfGrade.setPreferredSize(new Dimension(200, 30));
		tfGrade.setFont(sgUI13);
		tfGrade.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
		tfGrade.setForeground(Color.black);

		btnAcept = new JButton("Xác nhận");
		ImageIcon iconSubmit = new ImageIcon(
				new ImageIcon(getClass().getResource("/GUI/assets/icons8_checkmark_70px.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnAcept.setIcon(iconSubmit);
		btnAcept.setFont(sgUI13b);
		btnAcept.setFocusPainted(false);
		btnAcept.setBorderPainted(false);

		btnClose = new JButton("Thoát");
		ImageIcon iconClose = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnClose.setIcon(iconClose);
		btnClose.setFont(sgUI13b);
		btnClose.setFocusPainted(false);
		btnClose.setBorderPainted(false);

//		NORTH PANEL
		pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		pnNorth.add(lbTittle);

//		CENTER PANEL
		pnCourse = new JPanel();
		pnCourse.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnCourse.add(lbCourseID);
		pnCourse.add(cbbCourseId);

		pnStudent = new JPanel();
		pnStudent.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnStudent.add(lbStudentID);
		pnStudent.add(cbbStudentId);

		pnGrade = new JPanel();
		pnGrade.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnGrade.add(lbGrade);
		pnGrade.add(tfGrade);

		pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.add(pnCourse);
		pnCenter.add(pnStudent);
		pnCenter.add(pnGrade);
		pnCenter.setBorder(borderTxt);

//		SOUTH PANEL
		pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		pnSouth.add(btnClose);
		pnSouth.add(btnAcept);

//		THE BIGGEST PANEL
		this.setLayout(new BorderLayout());
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
		this.add(pnSouth, BorderLayout.SOUTH);
	}

	public void containerAdd() {
		lbTittle.setText("Thêm Mới");

		// đẩy dữ liệu cbb
		rendererCbbCourse();
		rendererCbbStudent();
	}

	public void containerUpdate(String course, String student, float grade) {
		lbTittle.setText("Cập Nhật");
		rendererCbbCourse();
		rendererCbbStudent();
		cbbCourseId.setSelectedItem(course);
		cbbStudentId.setSelectedItem(student);;
		tfGrade.setText(grade+"");
	}

	public void componentColors() {
		pnCenter.setBackground(Color.white);
		pnCourse.setBackground(Color.white);
		pnGrade.setBackground(Color.white);
		pnSouth.setBackground(Color.white);
		pnStudent.setBackground(Color.white);
		pnNorth.setBackground(Color.decode("#7ed6df"));
		btnAcept.setBackground(Color.decode("#55efc4"));
		btnClose.setBackground(Color.decode("#ff7675"));
	}

	public void eventAddForm() {
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

	}

	public void eventUpdateForm() {
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	public void rendererCbbCourse() {
		DefaultComboBoxModel<String>model=new DefaultComboBoxModel<>(KetQuaBUS.getInstance().getListCourse());
		cbbCourseId.setModel(model);
	}

	public void rendererCbbStudent() {
		DefaultComboBoxModel<String>model=new DefaultComboBoxModel<>(KetQuaBUS.getInstance().getListStudent());
		cbbStudentId.setModel(model);
	}
}
