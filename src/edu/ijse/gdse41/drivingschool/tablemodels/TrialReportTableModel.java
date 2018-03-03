/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.tablemodels;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Sandman
 */
public class TrialReportTableModel {
    private SimpleStringProperty custName= new SimpleStringProperty("");
    private SimpleStringProperty CustTelephone= new SimpleStringProperty("");
    
    private SimpleStringProperty vehicleClass= new SimpleStringProperty("");
    private SimpleStringProperty examDate= new SimpleStringProperty("");
    private SimpleStringProperty attemptTime= new SimpleStringProperty("");

    public TrialReportTableModel() {
    }

   
    

    public String getCustName() {
        return custName.get();
    }

    public void setCustName(String custName) {
        this.custName.set(custName);
    }

    public String getCustTelephone() {
        return CustTelephone.get();
    }

    public void setCustTelephone(String CustTelephone) {
        this.CustTelephone.set(CustTelephone);
    }

    public String getVehicleClass() {
        return vehicleClass.get();
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass.set(vehicleClass);
    }

    public String getExamDate() {
        return examDate.get();
    }

    public void setExamDate(String examDate) {
        this.examDate.set(examDate) ;
    }

    public String getAttemptTime() {
        return attemptTime.get();
    }

    public void setAttemptTime(String attemptTime) {
        this.attemptTime.set(attemptTime);
    }
}
