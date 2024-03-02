/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.CourseBUS;
import DTO.OnsiteCourseDTO;
import DTO.courseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class OnsiteCourseDAO implements iDAO<OnsiteCourseDTO>{
    
    CourseBUS courseBUS = new CourseBUS();

    @Override
    public boolean hasID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(OnsiteCourseDTO a) {
        try {
            courseBUS.insert(a);
                
            Connection cons = ConnectDB.getConnection();
            String sql = "INSERT INTO onsitecourse (CourseID, Location, Days, Time) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cons.prepareStatement(sql);

            ps.setInt(1, a.getId());
            ps.setString(2, a.getTittle());
            ps.setString(3, a.getDays());
            ps.setString(4, a.getTime());
            int x = ps.executeUpdate();
                
            if (x > 0) {
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
                return 0;
        }
    }

    @Override
    public int delete(OnsiteCourseDTO a) {
        try {
            Connection cons = ConnectDB.getConnection();
            
            String sql = "DELETE FROM onsitecourse WHERE CourseID = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setInt(1, a.getId());
            
            int x = ps.executeUpdate();
            
            ps.close();
            cons.close();
            
            courseBUS.delete(a);
            if (x > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int update(OnsiteCourseDTO a) {
        try {
            courseBUS.update(a);
                
            Connection cons = ConnectDB.getConnection();
            String sql = "Update onsitecourse set Location = ?, Days = ?, Time = ? where CourseID = '" + a.getId() + "'";
            PreparedStatement ps = cons.prepareStatement(sql);

            ps.setString(1, a.getTittle());
            ps.setString(2, a.getDays());
            ps.setString(3, a.getTime());
            int x = ps.executeUpdate();
            if (x > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OnsiteCourseDTO> findAll() {
        try {
            Connection cons = ConnectDB.getConnection();
            
            String sql = "SELECT onsitecourse.CourseID, onsitecourse.Location, onsitecourse.Days, onsitecourse.Time, " +
                           "course.Title, course.Credits, course.DepartmentID " +
                           "FROM onsitecourse " +
                           "JOIN course ON onsitecourse.CourseID = course.CourseID";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            List<OnsiteCourseDTO> list = new ArrayList<>();

            while(rs.next()) {
                OnsiteCourseDTO onsiteCourse = new OnsiteCourseDTO();
                onsiteCourse.setId(rs.getInt("onsitecourse.CourseID"));
                onsiteCourse.setTittle(rs.getString("course.Title"));
                onsiteCourse.setMaKhoa(rs.getInt("course.DepartmentID"));
                onsiteCourse.setDays(rs.getString("onsitecourse.Days"));
                onsiteCourse.setTime(rs.getString("onsitecourse.Time"));
                onsiteCourse.setLocation(rs.getString("onsitecourse.Location"));
                onsiteCourse.setCredits(rs.getInt("course.Credits"));
                list.add(onsiteCourse);
            }
            
            ps.close();
            rs.close();
            cons.close();
            
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public OnsiteCourseDTO findByID(int id) {
        OnsiteCourseDTO onsite = new OnsiteCourseDTO();
        try {
            Connection cons = ConnectDB.getConnection();
            String query = "select * from OnlineCourse where CourseID=?";
            PreparedStatement statement = cons.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                String days = rs.getString("Days");
                String time = rs.getString("Time");
                String location = rs.getString("Location");
                courseDTO course = courseBUS.findById(id);
                onsite = new OnsiteCourseDTO(id, course.getTittle(), course.getCredits(), course.getMaKhoa(), days, time, location);
            }
            
            statement.close();
            rs.close();
            cons.close();
            return onsite;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
