package BUS;

import java.util.List;

import DAO.PhanCongDAO;
import DTO.PhanCongDTO;

public class PhanCongBUS {
	PhanCongDAO phanCongDAO = new PhanCongDAO();
	
	public List<PhanCongDTO> getAllPhanCong() {
		return phanCongDAO.getAllPhanCong();
	}

	public List<PhanCongDTO> getAllPhanCongAfterSortingByPersonName() {
		return phanCongDAO.getAllPhanCongAfterSortingByPersonName();
	}
	public List<PhanCongDTO> getAllPhanCongAfterSortingByPersonID() {
		return phanCongDAO.getAllPhanCongAfterSortingByPersonID();
	}

	public List<PhanCongDTO> getAllPhanCongAfterSortingByCourseTitle() {
		return phanCongDAO.getAllPhanCongAfterSortingByCourseTitle();
	}
	public List<PhanCongDTO> getAllPhanCongAfterSortingByCourseID() {
		return phanCongDAO.getAllPhanCongAfterSortingByCourseID();
	}

	public boolean hasPhanCong(PhanCongDTO pDto) {
        return phanCongDAO.hasPhanCong(pDto.getPersonId(), pDto.getCourseId());
    }

	public String addPhanCong(PhanCongDTO pDto) {
		if (phanCongDAO.hasPhanCong(pDto.getPersonId(), pDto.getCourseId()))
			return "Phân công đã tồn tại";
		if (phanCongDAO.addPhanCong(pDto))
			return "Thêm thành công"; 
		return "Thêm thất bại";
		
	}

	public String editPhanCong(PhanCongDTO newPDto, PhanCongDTO oldPDto) {
		if (phanCongDAO.hasPhanCong(oldPDto.getPersonId(), oldPDto.getCourseId())) {
			if(phanCongDAO.editPhanCong(newPDto, oldPDto)) {
				return "Sửa thành công";
			}
		} else {
			return "Phân công không tồn tại";
		}
		return "Sửa thất bại";
	}

	public String deletePhanCong(int pID, int cID) {
		if (phanCongDAO.hasPhanCong(pID, cID)) {
			if(phanCongDAO.deletePhanCong(pID, cID)) {
				return "Xóa thành công";
			}
		} else {
			return "Phân công không tồn tại";
		}
		return "Xóa thất bại";
	}
}
