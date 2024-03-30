package DTO;

public class PhanCongDTO {
	private int courseId,personId;

	public PhanCongDTO(int courseId, int personId) {
		this.courseId = courseId;
		this.personId = personId;
	}

	public PhanCongDTO() {

	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
}
