package DTO;

import java.util.Date;

public class NhiemVuDTO {
	private int instructorId;
	private String location;
	private Date timestamp;
	public NhiemVuDTO(int instructorId, String location, Date timestamp) {

		this.instructorId = instructorId;
		this.location = location;
		this.timestamp = timestamp;
	}
	public NhiemVuDTO() {

	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
