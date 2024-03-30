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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationFORM extends JPanel{
	
	//------------COMPONENTS------------
	private JPanel pnNorth, pnCenter, pnSouth, pnInfor, pnTeacher, pnStudent, pnGrade, pnRank;
	private JLabel lbNorthTittle, lbTxtInfor, lbInfor, lbTxtGV,lbGV,lbTxtSv,lbSV,lbTxtKq, lbKq,lbTxtRank,lbRank;
	private JButton btnClose;
	
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
    private Color colorContent= Color.decode("#dff9fb");
	
	public InformationFORM() {
		initComponents();
		componentsStyle();
	}
	public InformationFORM(String courseName,String teacherName, String studentName, float grade, String rank) {
		initComponents();
		componentsStyle();
		setDatas(courseName, teacherName, studentName, grade, rank);
		eventClose();
		setVisible(false);
	}
	public void initComponents() {

//		SUB COMPONENTS
		lbNorthTittle=new JLabel("CHI TIẾT");
		lbNorthTittle.setFont(sgUI15b);
		lbNorthTittle.setBackground(Color.decode(colorTableCode));
		
		lbGV=new JLabel("Nguyễn Văn a");
		lbGV.setPreferredSize(new Dimension(200, 50));
		lbGV.setFont(sgUI13);
		
		lbInfor=new JLabel("Khóa học iels trường đại học sài gòn");
		lbInfor.setPreferredSize(new Dimension(200, 50));
		lbInfor.setFont(sgUI13);
		
		lbKq=new JLabel("7.0");
		lbKq.setPreferredSize(new Dimension(200, 50));
		lbKq.setFont(sgUI13);
		
		lbSV=new JLabel("Nguyễn Văn a");
		lbSV.setPreferredSize(new Dimension(200, 50));
		lbSV.setFont(sgUI13);
		
		lbRank=new JLabel("Xuất sắc");
		lbRank.setPreferredSize(new Dimension(200, 50));
		lbRank.setFont(sgUI13);
		
		lbTxtGV=new JLabel("Giáo Viên: ");
		lbTxtGV.setPreferredSize(new Dimension(70, 50));
		lbTxtGV.setFont(sgUI13b);
		
		lbTxtRank=new JLabel("Xếp Loại: ");
		lbTxtRank.setPreferredSize(new Dimension(70, 50));
		lbTxtRank.setFont(sgUI13b);
		
		lbTxtInfor=new JLabel("Thông Tin: ");
		lbTxtInfor.setPreferredSize(new Dimension(70, 50));
		lbTxtInfor.setFont(sgUI13b);
		
		lbTxtKq=new JLabel("Điểm: ");
		lbTxtKq.setPreferredSize(new Dimension(70, 50));
		lbTxtKq.setFont(sgUI13b);
		
		lbTxtSv=new JLabel("Sinh Viên");
		lbTxtSv.setPreferredSize(new Dimension(70, 50));
		lbTxtSv.setFont(sgUI13b);
		
		btnClose=new JButton("Close");
		ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnClose.setIcon(iconSearch);
		btnClose.setFont(sgUI13b);
		btnClose.setFocusPainted(false);
		btnClose.setBorderPainted(false);
		btnClose.setBackground(Color.decode("#d63031"));
		
//		NORTH PANEL
		pnNorth=new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnNorth.add(lbNorthTittle);
		
//		CENTER PANEL
		pnInfor=new JPanel();
		pnInfor.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pnInfor.add(lbTxtInfor);
		pnInfor.add(lbInfor);
		
		pnTeacher=new JPanel();
		pnTeacher.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pnTeacher.add(lbTxtGV);
		pnTeacher.add(lbGV);
		
		pnStudent=new JPanel();
		pnStudent.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pnStudent.add(lbTxtSv);
		pnStudent.add(lbSV);
		
		pnGrade=new JPanel();
		pnGrade.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pnGrade.add(lbTxtKq);
		pnGrade.add(lbKq);
		
		pnRank=new JPanel();
		pnRank.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pnRank.add(lbTxtRank);
		pnRank.add(lbRank);
		
		pnCenter=new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.add(pnInfor);
		pnCenter.add(pnTeacher);
		pnCenter.add(pnStudent);
		pnCenter.add(pnGrade);
		pnCenter.add(pnRank);
		
//		SOUTH PANEL
		pnSouth=new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		pnSouth.add(btnClose);
		
//		THE BIGGEST PANEL
		this.setLayout(new BorderLayout());
		this.add(pnNorth,BorderLayout.NORTH);
		this.add(pnCenter,BorderLayout.CENTER);
		this.add(pnSouth,BorderLayout.SOUTH);
		
	}
	
	public void setDatas(String courseName,String teacherName, String studentName, float grade, String rank) {
		lbInfor.setText(courseName);
		lbGV.setText(teacherName);
		lbSV.setText(studentName);
		lbKq.setText(grade+"");
		lbRank.setText(rank);
	}
	public void componentsStyle() {
		pnCenter.setBackground(colorContent);
		pnGrade.setBackground(colorContent);
		pnInfor.setBackground(colorContent);
		pnNorth.setBackground(Color.decode("#7ed6df"));
		pnSouth.setBackground(colorContent);
		pnStudent.setBackground(colorContent);
		pnTeacher.setBackground(colorContent);
		pnRank.setBackground(colorContent);
		
	}
	public void eventClose() {
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
}
