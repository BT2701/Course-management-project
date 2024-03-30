/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DepartmentDTO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author lythanhphat9523
 */
public class DepartmentDAO implements iDAO<DepartmentDTO>{
    ConnectDB db = new ConnectDB();
    
    @Override
    public boolean hasID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(DepartmentDTO a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(DepartmentDTO a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(DepartmentDTO a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DepartmentDTO> findAll() {
        List<DepartmentDTO> list=new ArrayList();
        try(Connection conn=db.getConnection()){
            String query="select * from Department";
            PreparedStatement statement= conn.prepareStatement(query);
            ResultSet rs =statement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("DepartmentID");
                String name=rs.getString("Name");
                double budget=rs.getDouble("Budget");
                Date start=rs.getDate("StartDate");
                int ad=rs.getInt("Administrator");
                DepartmentDTO de=new DepartmentDTO(id,name,budget,start,ad);
                list.add(de);
            }
            return list;
        }catch(Exception e){
            return null;
        }
    }
    

    @Override
    public DepartmentDTO findByID(int id) {
        DepartmentDTO target=new DepartmentDTO();
        try(Connection conn=db.getConnection()){
            String query="select * from Department where DepartmentID=?";
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs =statement.executeQuery();
            if(rs.next()){
                String name=rs.getString("Name");
                double budget=rs.getDouble("Budget");
                Date start=rs.getDate("StartDate");
                int ad=rs.getInt("Administrator");
                target=new DepartmentDTO(id,name,budget,start,ad);
            }
            return target;
        }catch(Exception e){
            return null;
        }
    }
    
    
}
