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
public class PaymentDTO {
    private String pid;
    private String addmissionId;
    private Double amount;
    private String date;

    public PaymentDTO() {
    }

    public PaymentDTO(String pid, String addmissionId, Double amount, String date) {
        this.pid = pid;
        this.addmissionId = addmissionId;
        this.amount = amount;
        this.date = date;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAddmissionId() {
        return addmissionId;
    }

    public void setAddmissionId(String addmissionId) {
        this.addmissionId = addmissionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
