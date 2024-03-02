/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.OnsiteCourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OnsiteCourseDaoImpl implements OnsiteCourseDAO1 {
    
    @Override
    public List<OnsiteCourseDTO> getList() {
        try {
            Connection cons = ConnectDB.getConnection();
            
            String sql = "SELECT onsitecourse.CourseID, onsitecourse.Location, onsitecourse.Days, onsitecourse.Time, " +
                           "course.Title, course.Credits, course.DepartmentID " +
                           "FROM onsitecourse " +
                           "JOIN course ON onsitecourse.CourseID = course.CourseID";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            List<OnsiteCourseDTO> list = new ArrayList<>();
//            PreparedStatement ps = cons.prepareCall(sql);
//            ResultSet rs = ps.executeQuery();
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
//            ps.close();
            rs.close();
//            cons.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       
        return null;
    }
    public static void main(String[] args) {
        OnsiteCourseDAO1 onsiteCourseDao = new OnsiteCourseDaoImpl();
        System.out.println(onsiteCourseDao.getList());
    }
}
