package DTO;

import java.util.Date;

public class PersonDTO {
	private int id;
	private String lastName;
	private String firstName;
	private Date hireDate;
	private Date EnrollmentDate;
	public PersonDTO(int id, String lastName, String firstName, Date hireDate, Date enrollmentDate) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.hireDate = hireDate;
		EnrollmentDate = enrollmentDate;
	}
	public PersonDTO() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getEnrollmentDate() {
		return EnrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		EnrollmentDate = enrollmentDate;
	}
	
}
