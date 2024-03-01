package DTO;

import java.util.Date;

public class OnsiteCourseDTO extends courseDTO{
	private String location;
	private String days;
	private String time;
	public OnsiteCourseDTO(int id, String tittle, int credits, int maKhoa,String location, String days, String time) {
		super( id,  tittle,  credits,  maKhoa);
		this.location = location;
		this.days = days;
		this.time = time;
	}
	public OnsiteCourseDTO() {
		super();
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
