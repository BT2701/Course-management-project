package Interface;

import java.util.List;

import DTO.PhanCongDTO;

public interface PhanCongIDAO {
	List<PhanCongDTO> getAllPhanCong();
	List<PhanCongDTO> getAllPhanCongAfterSortingByPersonName();
	List<PhanCongDTO> getAllPhanCongAfterSortingByPersonID();
	boolean addPhanCong(PhanCongDTO pDto);
	boolean editPhanCong(PhanCongDTO newPDto, PhanCongDTO oldPDto);
	boolean deletePhanCong(int pID, int cID);
	boolean hasPhanCong(int pID, int cID);
}
