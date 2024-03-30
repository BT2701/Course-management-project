package BUS;

import java.util.List;

import DAO.OnlineCourseDAO;
import DTO.OnlineCourseDTO;

public class OnlineCourseBUS implements iBUS<OnlineCourseDTO> {
	OnlineCourseDAO OlDAO = new OnlineCourseDAO();

	public List<OnlineCourseDTO> findAll() {
		return OlDAO.findAll();
	}

	@Override
	public OnlineCourseDTO findById(int id) {
		return OlDAO.findByID(id);
	}

	@Override
	public String insert(OnlineCourseDTO a) {
		int result = OlDAO.insert(a);
		if (result == 0)
			return "Thêm không thành công";
		if (result == 1)
			return "Thêm thành công";
		if (result == 3)
			return "Mã đã bị trùng";
		return "Thông tin nhập đã sai";
	}

	@Override
	public String delete(OnlineCourseDTO a) {
		int result = OlDAO.delete(a);
		if (result == 0)
			return "Xoá không thành công";
		if (result == 1)
			return "Xoá thành công";
		return "Mã không được tìm thấy";
	}

	@Override
	public String update(OnlineCourseDTO a) {
		int result = OlDAO.update(a);
		if (result == 0)
			return "Thay đổi không thành công";
		if (result == 1)
			return "Thay đổi thành công";
		return "Mã không được tìm thấy";
	}

	public List<OnlineCourseDTO> findByUrl(String condition) {
		return OlDAO.findByUrl(condition);
	}

	public List<OnlineCourseDTO> findByDepartment(String condition) {
		return OlDAO.findByDepartment(condition);
	}

	public List<OnlineCourseDTO> findByTitle(String condition) {
		return OlDAO.findByTitle(condition);
	}

	public List<OnlineCourseDTO> findByCredits(String condition) {
		return OlDAO.findByCredits(condition);
	}

	public List<OnlineCourseDTO> findByCourseID(String condition) {
		return OlDAO.findByCourseID(condition);
	}
	
}
