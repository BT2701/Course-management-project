package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BUS.CourseBUS;
import DTO.OnlineCourseDTO;
import DTO.courseDTO;

public class OnlineCourseDAO implements iDAO<OnlineCourseDTO> {

    CourseBUS courseBUS = new CourseBUS();
    ConnectDB db = new ConnectDB();

    public boolean hasID(int id) {
        try (Connection conn = db.getConnection()) {
            String query = "select * from OnlineCourse where CourseID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int insert(OnlineCourseDTO a) {
        if (!hasID(a.getId())) {
            try (Connection conn = db.getConnection()) {
                System.out.println(courseBUS.insert(a));
                String query = "insert into OnlineCourse values(?,?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, a.getId());
                statement.setString(2, a.getUrl());
                int x = statement.executeUpdate();
                if (x > 0) {
                    return 1;
                } else {
                    return 2;
                }
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 3;
        }
    }

    @Override
    public int delete(OnlineCourseDTO a) {
        if (hasID(a.getId())) {
            try (Connection conn = db.getConnection()) {
                courseBUS.delete(a);
                String query = "delete from OnlineCourse where CourseID=?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, a.getId());
                int x = statement.executeUpdate();
                if (x > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 2;
        }
    }

    @Override
    public int update(OnlineCourseDTO onl) {
        if (hasID(onl.getId())) {
            try (Connection conn = db.getConnection()) {
                courseBUS.update(onl);
                String query = "update OnlineCourse set url=? where CourseID=?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, onl.getUrl());
                statement.setInt(2, onl.getId());
                int x = statement.executeUpdate();
                if (x > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 2;
        }
    }

    @Override
    public List<OnlineCourseDTO> findAll() {
        List<OnlineCourseDTO> list = new ArrayList();
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * from OnlineCourse";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                OnlineCourseDTO onl = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
                list.add(onl);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OnlineCourseDTO findByID(int id) {
        OnlineCourseDTO olDTO = new OnlineCourseDTO();
        try (Connection conn = db.getConnection()) {
            String query = "select * from OnlineCourse where CourseID=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                olDTO = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
            }
            return olDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public List<OnlineCourseDTO> findByUrl(String condition) {
        List<OnlineCourseDTO> list = new ArrayList();
        String dk = "%" + condition + "%";
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM Course c join OnlineCourse ol on c.CourseID=ol.CourseID where ol.url like ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dk);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                OnlineCourseDTO onl = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
                list.add(onl);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public List<OnlineCourseDTO> findByCourseID(String condition) {
        List<OnlineCourseDTO> list = new ArrayList();
        String dk = "%" + condition + "%";
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM Course c join OnlineCourse ol on c.CourseID=ol.CourseID where c.CourseID like ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dk);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                OnlineCourseDTO onl = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
                list.add(onl);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<OnlineCourseDTO> findByDepartment(String condition) {
        List<OnlineCourseDTO> list = new ArrayList();
        String dk = "%" + condition + "%";
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM Course c join OnlineCourse ol on c.CourseID=ol.CourseID\n" +
                    "JOIN Department dp on dp.DepartmentID=c.DepartmentID\n" +
                    "where dp.Name like ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dk);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                OnlineCourseDTO onl = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
                list.add(onl);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<OnlineCourseDTO> findByTitle(String condition) {
        List<OnlineCourseDTO> list = new ArrayList();
        String dk = "%" + condition + "%";
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM Course c join OnlineCourse ol on c.CourseID=ol.CourseID where c.Title like ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dk);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                OnlineCourseDTO onl = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
                list.add(onl);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<OnlineCourseDTO> findByCredits(String condition) {
        List<OnlineCourseDTO> list = new ArrayList();
        String dk = "%" + condition + "%";
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM Course c join OnlineCourse ol on c.CourseID=ol.CourseID where c.Credits like ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, condition);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String url = rs.getString("url");
                courseDTO cr = courseBUS.findById(id);
                OnlineCourseDTO onl = new OnlineCourseDTO(id, cr.getTittle(), cr.getCredits(), cr.getMaKhoa(), url);
                list.add(onl);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
