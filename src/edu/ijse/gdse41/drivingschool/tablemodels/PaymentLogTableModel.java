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
public class PaymentLogTableModel {
    private SimpleStringProperty paymentId= new SimpleStringProperty("");
    private SimpleStringProperty amount= new SimpleStringProperty("");
    private SimpleStringProperty date= new SimpleStringProperty("");

    public PaymentLogTableModel() {
    }
    
    public String getPaymenId(){
       return paymentId.get();
    }
    public String getAmount(){
       return amount.get();
    }
    public String getDate(){
       return date.get();
    }
    public void setPaymentId(String paymentId){
        this.paymentId.set(paymentId);
    }
    public void setAmount(String amount){
        this.amount.set(amount);
    }
    public void setDate(String date){
        this.date.set(date);
    }
}
