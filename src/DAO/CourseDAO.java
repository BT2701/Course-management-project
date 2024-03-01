package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.OnlineCourseDTO;
import DTO.courseDTO;

public class CourseDAO implements iDAO<courseDTO>{
	ConnectDB db =new ConnectDB();
	@Override
	public boolean hasID(int id) {
		try {
			Connection conn =db.getConnection();
			String query="select * from Course where CourseID = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public int insert(courseDTO a) {
		if(!hasID(a.getId())) {
			try(Connection conn=db.getConnection()){
				String query="insert into Course values(?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, a.getId());
				statement.setString(2, a.getTittle());
				statement.setInt(3, a.getCredits());
				statement.setInt(4, a.getMaKhoa());
				int rs = statement.executeUpdate();
				if (rs>0) return 1;
				else return 2;
			}catch (Exception e) {
				return 0;
			}
		}else return 3;
	}

	@Override
	public int delete(courseDTO a) {
		if(hasID(a.getId())) {
			try(Connection conn=db.getConnection()){
				String query="delete from Course where CourseID=?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, a.getId());
				int x=statement.executeUpdate();
				if(x>0) return 1;
				else return 0;
			}catch(Exception e) {
				return 0;
			}
		}else return 2;
	}

	@Override
	public int update(courseDTO onl) {
		if(hasID(onl.getId())) {
			try(Connection conn= db.getConnection()){
				String query="UPDATE Course SET Title = ?, Credits = ?,DepartmentID=? WHERE CourseID = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, onl.getTittle());
				statement.setInt(2, onl.getCredits());
				statement.setInt(3, onl.getMaKhoa());
				statement.setInt(4, onl.getId());
				int x=statement.executeUpdate();
				if(x>0) return 1;
				else return 0;
			}catch(Exception e) {
				return 0;
			}
		}else return 2;
	}

	@Override
	public List<courseDTO> findAll() {
		List<courseDTO> list = new ArrayList();
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * from Course";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	courseDTO c=new courseDTO();
            	c.setId(rs.getInt(1));
            	c.setTittle(rs.getString(2));
            	c.setCredits(rs.getInt(3));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public courseDTO findByID(int id) {
		courseDTO course=new courseDTO();
		try {
			Connection conn =db.getConnection();
			String query="select * from Course where CourseID = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				String title=rs.getString("Title");
				int credits=rs.getInt("Credits");
				int departmentID=rs.getInt("DepartmentID");
				course=new courseDTO(id,title,credits,departmentID);
			}
			return course;
		}catch(Exception e) {
			return null;
		}
	}

	

}
