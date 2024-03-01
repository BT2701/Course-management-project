package DTO;

import java.util.Date;

public class KhoaDTO {
	private int id;
	private String name;
	private float budget;
	private Date startDate;
	private int administrator;
	public KhoaDTO(int id, String name, float budget, Date startDate, int administrator) {
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.startDate = startDate;
		this.administrator = administrator;
	}
	public KhoaDTO() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getAdministrator() {
		return administrator;
	}
	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}
	
	
}
