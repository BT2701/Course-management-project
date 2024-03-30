package GUI.GUI_KETQUA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.KetQuaBUS;

public class ThongKeFORM extends JDialog {

//	COMPONENTS
	private JPanel pnNorth, pnCenter, pnSouth, pnNorthContent, pnChart, pnTittle;
	private JLabel lbTittle;
	private ArrayList<JLabel> lbTypeGrade;
	private ArrayList<JLabel> lbTypeGradeValue;
	private ArrayList<JPanel> pnTypeGrade;
	private JButton btnExit;
	JFreeChart chartGrade;
	ChartPanel chartPn;

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

	public ThongKeFORM() {

		initComponents();
		style();
		events();
		this.setVisible(true);
	}

	public void initComponents() {
		this.setSize(700, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		setIconImage(new ImageIcon(getClass().getResource("/GUI/assets/school.png")).getImage());
		setTitle("Thống Kê");
//		SUB COMPONENTS
		lbTittle = new JLabel("Thống Kê Điểm");

		btnExit = new JButton("Thoát");
		
		
//		OTHERS PANEL
		pnNorthContent = new JPanel();
		pnNorthContent.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		JLabel lbTemp, lbTempValue;
		JPanel pnTemp;

		lbTypeGrade = new ArrayList<>();
		lbTypeGradeValue = new ArrayList<>();
		pnTypeGrade = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			lbTemp = new JLabel();
			lbTypeGrade.add(lbTemp);

			lbTempValue = new JLabel();
			lbTypeGradeValue.add(lbTempValue);

			pnTemp = new JPanel();
			pnTypeGrade.add(pnTemp);
		}
		for(int i=0;i<5;i++) {
			pnTypeGrade.get(i).add(lbTypeGrade.get(i));
			pnTypeGrade.get(i).add(lbTypeGradeValue.get(i));
		}
		for (int i = 0; i < 5; i++) {
			pnNorthContent.add(pnTypeGrade.get(i));
		}
		
		loadStatistic();
		pnChart = new JPanel();
		pnChart.setLayout(new BorderLayout());
		pnChart.add(chartPn,BorderLayout.CENTER);

		pnTittle = new JPanel();
		pnTittle.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		pnTittle.add(lbTittle);
//		NORTH PANEL
		pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		pnNorth.add(pnTittle, BorderLayout.NORTH);
		pnNorth.add(pnNorthContent, BorderLayout.CENTER);

//		CENTER PANEL
		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.add(pnChart, BorderLayout.CENTER);

//		SOUTH PANEL
		pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		pnSouth.add(btnExit);
//		THE BIGGEST PANEL
		this.setLayout(new BorderLayout());
		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);
		this.add(pnSouth, BorderLayout.SOUTH);
	}

	public void events() {
		exitEvent();
	}

	public void exitEvent() {
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	public void style() {
		lbTittle.setFont(fontTittle);
		for (JLabel jLabel : lbTypeGrade) {
			jLabel.setFont(sgUI15b);
		}
		for (JLabel jLabel : lbTypeGradeValue) {
			jLabel.setFont(sgUI15p);
		}
		ImageIcon iconClose = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/cancel-144.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnExit.setIcon(iconClose);
		btnExit.setFont(sgUI13b);
		btnExit.setFocusPainted(false);
		btnExit.setBorderPainted(false);
		pnCenter.setBackground(Color.white);
		pnChart.setBackground(Color.white);
		pnNorth.setBackground(Color.white);
		pnNorthContent.setBackground(Color.white);
		pnSouth.setBackground(Color.white);
		pnTittle.setBackground(Color.decode(colorTableCode));
		btnExit.setBackground(Color.decode("#ebf2fc"));
		pnTypeGrade.get(0).setBackground(Color.decode("#27ae60"));
		pnTypeGrade.get(1).setBackground(Color.decode("#2ecc71"));
		pnTypeGrade.get(2).setBackground(Color.decode("#1abc9c"));
		pnTypeGrade.get(3).setBackground(Color.decode("#f1c40f"));
		pnTypeGrade.get(4).setBackground(Color.decode("#e74c3c"));
		lbTypeGrade.get(0).setText("Điểm A: ");
		lbTypeGrade.get(1).setText("Điểm B: ");
		lbTypeGrade.get(2).setText("Điểm C: ");
		lbTypeGrade.get(3).setText("Điểm D: ");
		lbTypeGrade.get(4).setText("Điểm F: ");
			
		for (int i = 0; i < 5; i++) {
			if (i == 4) {
				lbTypeGrade.get(i).setForeground(Color.black);
				lbTypeGradeValue.get(i).setForeground(Color.black);

			} else {
				lbTypeGrade.get(i).setForeground(Color.white);
				lbTypeGradeValue.get(i).setForeground(Color.white);
			}
			
		}
	}
	private DefaultCategoryDataset dataset() {
		DefaultCategoryDataset data=new DefaultCategoryDataset();
		data.addValue(KetQuaBUS.getInstance().listCountGrade().get(0), "Điểm A", "A");
		data.addValue(KetQuaBUS.getInstance().listCountGrade().get(1), "Điểm B", "B");
		data.addValue(KetQuaBUS.getInstance().listCountGrade().get(2), "Điểm C", "C");
		data.addValue(KetQuaBUS.getInstance().listCountGrade().get(3), "Điểm D", "D");
		data.addValue(KetQuaBUS.getInstance().listCountGrade().get(4), "Điểm F", "F");
		for(int i=0;i<5;i++) {
			lbTypeGradeValue.get(i).setText(KetQuaBUS.getInstance().listCountGrade().get(i)+"");
		}
		return data;
	}
	public void loadStatistic() {
		chartGrade=ChartFactory.createBarChart(
                "Grade Chart",  // Tiêu đề biểu đồ
                "Category",              // Label trục x
                "Grade",                 // Label trục y
                dataset()                 // Dataset
        );

		chartPn=new ChartPanel(chartGrade);
		CategoryPlot plot = chartGrade.getCategoryPlot();

        // Thiết lập màu nền cho plot
        plot.setBackgroundPaint(Color.white);
//        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Thiết lập kích thước tối đa cho các cột (ở đây là 0.5)
//        renderer.setMaximumBarWidth(2);
	}
}
