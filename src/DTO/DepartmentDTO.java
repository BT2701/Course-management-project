/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author lythanhphat9523
 */
public class DepartmentDTO {
    private int DepartmentID;
    private String name;
    private double Budget;
    private Date startDate;
    private int administration;

    public DepartmentDTO(int DepartmentID, String name, double Budget, Date startDate, int administration) {
        this.DepartmentID = DepartmentID;
        this.name = name;
        this.Budget = Budget;
        this.startDate = startDate;
        this.administration = administration;
    }

    public DepartmentDTO() {
    }
    
    public DepartmentDTO(int DepartmentID,String name){
        this.DepartmentID=DepartmentID;
        this.name=name;
    }
    
    

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return Budget;
    }

    public void setBudget(double Budget) {
        this.Budget = Budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getAdministration() {
        return administration;
    }

    public void setAdministration(int administration) {
        this.administration = administration;
    }
    
    
}
