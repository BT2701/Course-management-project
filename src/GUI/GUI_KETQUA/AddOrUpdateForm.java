/*
 * @author: Dương Thành Trưởng
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import GUI.GUI_BASIC.ThongBaoDialog;

public class AddOrUpdateForm extends JDialog {
//	MAIN COMPONENTS
	private JPanel pnNorth, pnCenter, pnSouth, pnHeader, pnMainHeader, pnNotification;
	JPanel pnScroll;
	JScrollPane scrContainer;
	private JLabel lbTittle, lbCourse, lbStudent, lbGrade, lbNotification;
	private JButton btnFinish;
	ArrayList<JLabel> lbCourses;
	ArrayList<JLabel> lbStudents;
	ArrayList<JPanel> pnItems;
	ArrayList<JTextField> tfGrades;
	int location = 228;
	SaveEnterEvent event = new SaveEnterEvent(this);

//	FORMAT
	private Font sgUI15b = new Font("Segoe UI", Font.BOLD, 16);
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

	public AddOrUpdateForm(int numOfStudent, ArrayList<String> courseList, ArrayList<String> studentList) {
		initComponents(numOfStudent, courseList, studentList);
		style();
		setTitle("Danh Sách Sinh Viên Chưa Có Điểm");
		lbTittle.setText("Nhập Điểm");
		events(numOfStudent);
		this.setVisible(true);
	}

	public AddOrUpdateForm(int numOfStudent, ArrayList<String> courseList, ArrayList<String> studentList,
			ArrayList<Float> grade) {
		initComponents(numOfStudent, courseList, studentList);
		style();
		setTitle("Danh Sách Sinh Viên");
		lbTittle.setText("Sửa Điểm");
		for (int i = 0; i < numOfStudent; i++) {
			tfGrades.get(i).setText(grade.get(i) + "");
		}
		events(numOfStudent);
		this.setVisible(true);
	}

	public void initComponents(int numOfStudent, ArrayList<String> courseList, ArrayList<String> studentList) {
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/GUI/assets/school.png")).getImage());
//		COMPONENTS
		lbTittle = new JLabel("Nhập Điểm");

		lbCourse = new JLabel("Khóa Học");

		lbStudent = new JLabel("Tên Sinh Viên");

		lbGrade = new JLabel("Điểm");

		btnFinish = new JButton("Xong");

		lbNotification = new JLabel("*Nhấn enter để lưu và xuống dòng");

//		COURSES LABEL
		lbCourses = new ArrayList<>();
		for (int i = 0; i < numOfStudent; i++) {
			JLabel lbTemp = new JLabel();
			lbTemp.setText(courseList.get(i));
			lbCourses.add(lbTemp);
		}

//		STUDENTS LABEL
		lbStudents = new ArrayList<>();
		for (int i = 0; i < numOfStudent; i++) {
			JLabel lbTemp = new JLabel();
			lbTemp.setText(studentList.get(i));
			lbStudents.add(lbTemp);
		}

//		GRADES TEXTFIELD
		tfGrades = new ArrayList<>();
		for (int i = 0; i < numOfStudent; i++) {
			JTextField lbTemp = new JTextField();
			tfGrades.add(lbTemp);
		}

//		ITEMS PANEL
		pnItems = new ArrayList<>();
		for (int i = 0; i < numOfStudent; i++) {
			JPanel pnTemp = new JPanel();
			pnTemp.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
			pnTemp.add(lbCourses.get(i));
			pnTemp.add(lbStudents.get(i));
			pnTemp.add(tfGrades.get(i));
			pnItems.add(pnTemp);
		}

//		NORTH PANEL
		pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		pnNorth.add(lbTittle);

//		CENTER PANEL
		pnHeader = new JPanel();
		pnHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		pnHeader.add(lbCourse);
		pnHeader.add(lbStudent);
		pnHeader.add(lbGrade);

		pnNotification = new JPanel();
		pnNotification.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 3));
		pnNotification.add(lbNotification);

		pnMainHeader = new JPanel();
		pnMainHeader.setLayout(new BorderLayout());
		pnMainHeader.add(pnNotification, BorderLayout.NORTH);
		pnMainHeader.add(pnHeader, BorderLayout.CENTER);

		pnScroll = new JPanel();
		pnScroll.setLayout(new BoxLayout(pnScroll, BoxLayout.Y_AXIS));
//		pnScroll.add(pnHeader);
		for (int i = 0; i < numOfStudent; i++) {
			pnScroll.add(pnItems.get(i));
		}
		scrContainer = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrContainer.setViewportView(pnScroll);

		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.add(pnMainHeader, BorderLayout.NORTH);
		pnCenter.add(scrContainer, BorderLayout.CENTER);

//		SOUTH PANEL
		pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		pnSouth.add(btnFinish);

//		CONTAINER
		this.setLayout(new BorderLayout());
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
		this.add(pnSouth, BorderLayout.SOUTH);
	}

	public void events(int numOfStudent) {
		exitEvent();
		saveEnterEvent(numOfStudent);
	}

	public void style() {
		pnCenter.setBackground(Color.white);
		pnCenter.setBorder(new MatteBorder(0, 2, 2, 2, Color.decode("#EFEFEF")));
		pnHeader.setBackground(Color.decode(colorTableCode));
		pnHeader.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#2d3436")));
		pnNorth.setBackground(Color.white);
		pnScroll.setBackground(Color.white);
		pnSouth.setBackground(Color.white);
		pnMainHeader.setBackground(Color.white);
		pnNotification.setBackground(Color.white);

		lbTittle.setFont(sgUI15b);
		lbCourse.setPreferredSize(new Dimension(200, 40));
		lbCourse.setFont(sgUI13b);
		lbCourse.setBorder(new MatteBorder(0, 0, 0, 1, Color.decode("#636e72")));
		lbStudent.setPreferredSize(new Dimension(150, 40));
		lbStudent.setFont(sgUI13b);
		lbStudent.setBorder(new MatteBorder(0, 0, 0, 1, Color.decode("#636e72")));
		lbGrade.setPreferredSize(new Dimension(70, 40));
		lbGrade.setFont(sgUI13b);
		lbNotification.setFont(sgUI13I);
		lbNotification.setForeground(Color.decode("#636e72"));

		scrContainer.setBorder(BorderFactory.createEmptyBorder());
		scrContainer.setViewportBorder(null);
		scrContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrContainer.getVerticalScrollBar().setUnitIncrement(10);
//		scrContainer.setPreferredSize(new Dimension(this.getWidth(), 200));
//		scrContainer.set

		ImageIcon iconSubmit = new ImageIcon(
				new ImageIcon(getClass().getResource("/GUI/assets/icons8_checkmark_70px.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnFinish.setIcon(iconSubmit);
		btnFinish.setFont(sgUI13b);
		btnFinish.setFocusPainted(false);
		btnFinish.setBorderPainted(false);
		btnFinish.setBackground(Color.decode("#55efc4"));

		for (JPanel pn : pnItems) {
			pn.setBackground(Color.white);
//			pn.setPreferredSize(new Dimension(this.getWidth()-40, 60));
			pn.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#EEEEEE")));
		}

		for (JTextField tf : tfGrades) {
			tf.setPreferredSize(new Dimension(70, 30));
			tf.setFont(sgUI13);
			tf.setBorder(BorderFactory.createCompoundBorder(borderTxt, new EmptyBorder(0, 3, 0, 3)));
			tf.setForeground(Color.black);
		}
		for (JLabel jLabel : lbCourses) {
			jLabel.setPreferredSize(new Dimension(200, 40));
			jLabel.setFont(sgUI13);
		}
		for (JLabel jLabel : lbStudents) {
			jLabel.setPreferredSize(new Dimension(150, 40));
			jLabel.setFont(sgUI13);
		}

	}

	public void exitEvent() {
		btnFinish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	public void saveEnterEvent(int numOfStudent) {
		for (int i = 0; i < numOfStudent; i++) {
			tfGrades.get(i).addKeyListener(event);
			((AbstractDocument) tfGrades.get(i).getDocument()).setDocumentFilter(new DocumentFilter() {
				@Override
				public void replace(FilterBypass fb, int offset, int length, String text,
						javax.swing.text.AttributeSet attrs) throws BadLocationException {
					// Chỉ cho phép thay thế khi là số
					if (text.matches("\\d*")) {
						super.replace(fb, offset, length, text, attrs);
					} else {
						new ThongBaoDialog("Chỉ cho phép nhập số!", ThongBaoDialog.ERROR_DIALOG);

					}
				}

				@Override
				public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr)
						throws BadLocationException {
					// Chỉ cho phép chèn khi là số
					if (string.matches("\\d*")) {
						super.insertString(fb, offset, string, attr);
					} else {
						new ThongBaoDialog("Chỉ cho phép nhập số!", ThongBaoDialog.ERROR_DIALOG);
					}
				}
			});
		}
	}
}
