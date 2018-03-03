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
public class CustAmount {
    private String aId;
    private double totalAmount;
    private double balance;
    private String lastUpdate;

    public CustAmount(String aId, double totalAmount, double balance, String lastUpdate) {
        this.aId = aId;
        this.totalAmount = totalAmount;
        this.balance = balance;
        this.lastUpdate = lastUpdate;
    }

    public CustAmount() {
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
}
