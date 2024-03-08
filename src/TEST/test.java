package TEST;

import java.util.ArrayList;

import BUS.CourseBUS;
import BUS.OnlineCourseBUS;
import DAO.CourseDAO;
import DAO.KetQuaDAO;
import DTO.courseDTO;
import GUI.GUI_KETQUA.AddEditFORM;
import GUI.GUI_KETQUA.AddOrUpdateForm;
import GUI.GUI_KETQUA.ThongKeFORM;

public class test {
	public static void main(String[] args) {
//		new AddEditFORM();
//		OnlineCourseBUS b= new OnlineCourseBUS();
//		System.out.println(b.getNewestId());
//		ArrayList<String>a=new ArrayList<>();
//		ArrayList<String>b=new ArrayList<>();
//		ArrayList<Float>c=new ArrayList<>();
//		for(int i=0;i<10;i++) {
//			a.add("rpo"+i);
//			b.add("gà");
//			c.add(Float.parseFloat("1"));
//		}
//		new AddOrUpdateForm(10, a, b,c);
//		CourseBUS a=new CourseBUS();
//		System.out.println(a.getNewestId());
		
		new ThongKeFORM();
//		for (float string : KetQuaDAO.getInstance().getListGrade()) {
//			System.out.println(string);
//		}
	}
}
