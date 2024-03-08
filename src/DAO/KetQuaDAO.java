package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import DTO.KetQuaDTO;

public class KetQuaDAO implements DaoInterface<KetQuaDTO>{
	public static KetQuaDAO getInstance() {
		return new KetQuaDAO();
	}

	@Override
	public int add(KetQuaDTO t) {
		// TODO Auto-generated method stub
		int check = 0;
        try {
            Connection con = new ConnectDB().getConnection();
            Statement st = con.createStatement();
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            String sql = "INSERT INTO studentgrade(CourseID,StudentID,Grade)\r\n"
                    + "VALUES (?,?,?);";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, t.getCourseId());
            prep.setInt(2, t.getStudentId());
            prep.setFloat(3, t.getIdGrade());
            check = prep.executeUpdate();
            if (check > 0) {
                System.out.println("thêm dữ liệu thành công");
            } else {
                System.out.println("thất bại 8987");
            }
            System.out.println("ban da thucc thi: " + sql);
            System.out.println("so dong thay doi: " + check);

            //buoc 5 ngat ket noi
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("thất bại");
            e.printStackTrace();
        }
        return check;
	}

	@Override
	public int delete(KetQuaDTO t) {
		// TODO Auto-generated method stub
		int check = 0;
        try {
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            Connection con = new ConnectDB().getConnection();
            Statement st = con.createStatement();
            String sql = "DELETE FROM studentgrade "
                    + "WHERE enrollmentid = '" + t.getIdGrade() + "';";
            check = st.executeUpdate(sql);
            if (check > 1) {
                System.out.println("thành công");
            }

            con.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return check;
	}
	public int delete(int courseid, int studentid) {
		// TODO Auto-generated method stub
		int check = 0;
        try {
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            Connection con = new ConnectDB().getConnection();
            Statement st = con.createStatement();
            String sql = "DELETE FROM studentgrade "
                    + "WHERE courseid = " + courseid + " and studentid= "+studentid+";";
            check = st.executeUpdate(sql);
            if (check > 1) {
                System.out.println("thành công");
            }

            con.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return check;
	}

	@Override
	public int update(KetQuaDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<KetQuaDTO> getList() {
		// TODO Auto-generated method stub
		ArrayList<KetQuaDTO> list=new ArrayList<>();
		try {
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT * FROM studentgrade";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	KetQuaDTO kq = new KetQuaDTO();
                kq.setIdGrade(rs.getInt(1));
                kq.setCourseId(rs.getInt(2));
                kq.setStudentId(rs.getInt(3));
                kq.setDiem(rs.getFloat(4));
                
                list.add(kq);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
		return list;
	}
	public Vector<Vector<String>> getListTable() {
		Vector<Vector<String>> vec=new Vector<>();
		try {
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT sg.courseid, c.title, p.firstname, p.lastname, sg.grade " +
                    "FROM studentgrade sg " +
                    "JOIN person p ON sg.studentid = p.personid " +
                    "JOIN course c ON sg.courseid = c.courseid";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	Vector<String> t=new Vector<>();
            	t.add(rs.getInt(1)+"");
            	t.add(rs.getString(2));
            	t.add(rs.getString(4)+" "+rs.getString(3));
            	t.add(rs.getFloat(5)+"");
            	if(rs.wasNull()) {
            		t.add("Chưa Nhập Điểm");
            	}else {
            		if(rs.getFloat(5)>=1) {
                		t.add("Đạt");
                	}
                	else {
                		t.add("Không Đạt");
                	}
            	}
            	vec.add(t);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
		return vec;
	}

	@Override
	public KetQuaDTO getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector<String> getListCourse() {
		Vector<String> list=new Vector<>();
		try {
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT courseid, title FROM course";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String course = "";
                course=rs.getInt(1)+" - "+rs.getString(2);
                
                list.add(course);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
		
		return list;
		
	}
	public Vector<String> getListStudent() {
		Vector<String> list=new Vector<>();
		try {
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT DISTINCT studentgrade.studentid, person.lastname, person.firstname " +
                    "FROM studentgrade " +
                    "INNER JOIN person ON studentgrade.studentid = person.personid";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String course = "";
                course=rs.getInt(1)+" - "+rs.getString(2)+" "+rs.getString(3);
                
                list.add(course);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
		
		return list;
		
	}
	public int getIdbyFullName(String fullName) {
		int id=0;
		String firstname=fullName.split(" ")[1];
		String lastname=fullName.split(" ")[0];
		try {
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT personid " +
                    "FROM person " +
                    "WHERE firstname='"+firstname+"' and lastname='"+lastname+"';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	id=rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;

        }
		return id;
	}
	
	public String getTeacherNameByCourseId(int courseId) {
		String name="";
		try {
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT person.lastname, person.firstname " +
                    "FROM courseinstructor INNER JOIN person ON courseinstructor.personid = person.personid " +
                    "WHERE courseid ="+courseId+";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	name=rs.getString(1)+" "+rs.getString(2);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "";

        }
		return name;
	}
	public int updateGrade(int grade, int courseid, int studentid) {
		int check = 0;
        try {
            Connection con = new ConnectDB().getConnection();
            Statement st = con.createStatement();
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            String sql = "UPDATE studentgrade SET grade = ? WHERE courseid = ? and studentid= ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, grade);
            prep.setInt(2, courseid);
            prep.setFloat(3, studentid);
            check = prep.executeUpdate();
            if (check > 0) {
                System.out.println("thêm dữ liệu thành công");
            } else {
                System.out.println("thất bại 8987");
            }
            System.out.println("ban da thucc thi: " + sql);
            System.out.println("so dong thay doi: " + check);

            //buoc 5 ngat ket noi
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("thất bại");
            e.printStackTrace();
        }
        return check;
	}
	public int cancel(int courseid, int studentid) {
		int check = 0;
        try {
            Connection con = new ConnectDB().getConnection();
            Statement st = con.createStatement();
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            String sql = "UPDATE studentgrade SET grade = NULL WHERE courseid = ? and studentid= ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, courseid);
            prep.setFloat(2, studentid);
            check = prep.executeUpdate();
            if (check > 0) {
                System.out.println("thêm dữ liệu thành công");
            } else {
                System.out.println("thất bại 8987");
            }
            System.out.println("ban da thucc thi: " + sql);
            System.out.println("so dong thay doi: " + check);

            //buoc 5 ngat ket noi
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("thất bại");
            e.printStackTrace();
        }
        return check;
	}
	
	public ArrayList<Float> getListGrade(){
		ArrayList<Float> list=new ArrayList<>();
		try {
            //maHD,maCTT,tienP,tienDV,giamGia,phuThu,tongTien,ngayThanhToan,phuongThucThanhToan,xuLy
            Connection con = new ConnectDB().getConnection();
            String sql = "SELECT Grade FROM studentgrade";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	list.add(rs.getFloat(1));
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
		
		return list;
		
	}
	

}
