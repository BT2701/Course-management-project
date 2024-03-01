package BUS;

import java.util.List;

import DAO.CourseDAO;
import DTO.OnlineCourseDTO;
import DTO.courseDTO;

public class CourseBUS implements iBUS<courseDTO> {
	CourseDAO cDAO=new CourseDAO();
	@Override
	public String insert(courseDTO a) {
		int result=cDAO.insert(a);
		if(result==0) return "Thêm không thành công";
		if(result==1) return "Thêm thành công";
		return "Mã đã bị trùng";
	}


	@Override
	public List<courseDTO> findAll() {
		// TODO Auto-generated method stub
		return cDAO.findAll();
	}

	@Override
	public courseDTO findById(int id) {
		return cDAO.findByID(id);
	}

	@Override
	public String delete(courseDTO a) {
		int result=cDAO.delete(a);
		if(result==0) return "Xoá không thành công";
		if(result==1) return "Xoá thành công";
		return "Mã không được tìm thấy";
	}

	@Override
	public String update(courseDTO a) {
		int result=cDAO.update(a);
		if(result==0) return "Thay đổi không thành công";
		if(result==1) return "Thay đổi thành công";
		return "Mã không được tìm thấy";
	}
	public int getNewestId() {
		int id=0;
		for (courseDTO item : findAll()) {
			int newId=item.getId();
            if (newId > id) {
                id = newId;
            }
		}
		return id+1;
	}
	
}
