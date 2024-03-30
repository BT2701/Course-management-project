/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.OnsiteCourseDAO1;
import DAO.OnsiteCourseDaoImpl;
import DTO.OnsiteCourseDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OnsiteCourseServiceImpl implements OnsiteCourseService{
    private OnsiteCourseDAO1 onsiteCourseDao = null;

    public OnsiteCourseServiceImpl() {
        onsiteCourseDao = new OnsiteCourseDaoImpl();
    } 

    @Override
    public List<OnsiteCourseDTO> getList() {
        return onsiteCourseDao.getList();
    }
}
