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
public class NotificationTableModel {
    private SimpleStringProperty custName= new SimpleStringProperty("");
    private SimpleStringProperty tel1= new SimpleStringProperty("");
    
    private SimpleStringProperty cata= new SimpleStringProperty("");
    private SimpleStringProperty lClass= new SimpleStringProperty("");

    public NotificationTableModel() {
    }

    public String getCustName(){
       return custName.get();
    }
    
    public String getTel1(){
        return tel1.get();
    }
    
    public String getCata(){
        return cata.get();
    }
    public String getLClass(){
        return lClass.get();
    }
    
    
    public void setCustName(String custName){
        this.custName.set(custName);
    }
    
    public void setTel1(String tel1){
        this.tel1.set(tel1);
    }
    
    public void setCata(String cata){
        this.cata.set(cata);
    }
    public void setLClass(String lClass){
        this.lClass.set(lClass);
    }

    
    
}
