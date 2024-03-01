package DTO;

import BUS.DepartmentBUS;

public class courseDTO {
	private int id;
	private String tittle;
	private int credits;
	private int maKhoa;
        private DepartmentDTO departmentName;
        
        DepartmentBUS depBUS=new DepartmentBUS();
	
	public courseDTO() {
	}

	public courseDTO(int id, String tittle, int credits, int maKhoa) {
		this.id = id;
		this.tittle = tittle;
		this.credits = credits;
		this.maKhoa = maKhoa;
                this.departmentName=depBUS.findById(maKhoa);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(int maPhong) {
		this.maKhoa = maPhong;
	}

    public DepartmentDTO getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(DepartmentDTO departmentName) {
        this.departmentName = departmentName;
    }

	@Override
	public String toString() {
		return "courseDTO [id=" + id + ", tittle=" + tittle + ", credits=" + credits + ", maKhoa=" + maKhoa
				+ ", departmentName=" + departmentName + ", depBUS=" + depBUS + "]";
	}
        
	
	
}
