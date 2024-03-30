/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import java.util.List;

/**
 *
 * @author lythanhphat9523
 */
public class DepartmentBUS implements iBUS<DepartmentDTO>{

    private DepartmentDAO depDAO=new DepartmentDAO();
    
    @Override
    public String insert(DepartmentDTO a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(DepartmentDTO a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(DepartmentDTO a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return depDAO.findAll();
    }

    @Override
    public DepartmentDTO findById(int id) {
        return depDAO.findByID(id);
    }
    
}
