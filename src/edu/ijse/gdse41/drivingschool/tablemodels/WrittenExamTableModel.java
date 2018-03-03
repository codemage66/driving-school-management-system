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
public class WrittenExamTableModel {
    private SimpleStringProperty examDate= new SimpleStringProperty("");
    private SimpleStringProperty status= new SimpleStringProperty("");
    private SimpleStringProperty Remarks= new SimpleStringProperty("");

    public WrittenExamTableModel() {
    }
    public String getExamDate(){
       return examDate.get();
    }
    public String getStatus(){
       return status.get();
    }
    public String getRemarks(){
       return Remarks.get();
    }
    public void setExamDate(String examDate){
        this.examDate.set(examDate);
    }
    public void setStatus(String status){
        this.status.set(status);
    }
    public void setremarks(String remarks){
        this.Remarks.set(remarks);
    }
}
