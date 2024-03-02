/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.OnsiteCourseDAO;
import DTO.OnsiteCourseDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OnsiteCourseBUS implements iBUS<OnsiteCourseDTO>{
    private OnsiteCourseDAO onsiteCourseDao = new OnsiteCourseDAO();

    @Override
    public String insert(OnsiteCourseDTO a) {
        int result = onsiteCourseDao.insert(a);
        if (result == 0)
            return "Thêm không thành công";
	if (result == 1)
            return "Thêm thành công";
	return "Thông tin nhập đã sai";
    }

    @Override
    public String delete(OnsiteCourseDTO a) {
        int result = onsiteCourseDao.delete(a);
        if (result == 0)
                return "Xoá không thành công";
        return "Xoá thành công";

    }

    @Override
    public String update(OnsiteCourseDTO a) {
        int result = onsiteCourseDao.update(a);
        if (result == 0)
                return "Thay đổi không thành công";

        return "Thay đổi thành công";

    }

    @Override
    public List<OnsiteCourseDTO> findAll() {
        return onsiteCourseDao.findAll();
    }

    @Override
    public OnsiteCourseDTO findById(int id) {
        return onsiteCourseDao.findByID(id);
    }
}
