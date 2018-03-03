/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschooldto;

/**
 *
 * @author Sandman
 */
public class ExamReportDTO {
    private String custName;
    private String custTel;
    private String vehicleClass;
    private String examDate;
    private String attempt;

    public ExamReportDTO() {
    }

    public ExamReportDTO(String custName, String custTel, String vehicleClass, String examDate, String attempt) {
        this.custName = custName;
        this.custTel = custTel;
        this.vehicleClass = vehicleClass;
        this.examDate = examDate;
        this.attempt = attempt;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }
    
    
}
