package DTO;

public class KetQuaDTO {
	private int idGrade;
	private int courseId;
	private int studentId;
	private float diem;
	
	public KetQuaDTO() {
		
	}

	public KetQuaDTO(int idGrade, int courseId, int studentId, float diem) {
		this.idGrade = idGrade;
		this.courseId = courseId;
		this.studentId = studentId;
		this.diem = diem;
	}

	public int getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}
	
	
}
