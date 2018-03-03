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
public class MainSearchDTO {
    private String addmissionId;
    private String rId;
    private String prID;
    private String vcId;
    private String lcId;
    private String exdId;
    private String cldId;
    private String initName;
    private String address;
    private int tel1;
    private boolean lessonType;
    private double courseAmount;
    private double balance;

    public MainSearchDTO() {
    }

    public MainSearchDTO(String addmissionId, String rId, String prID, String vcId, String lcId, String exdId, String cldId, String initName, String address, int tel1, boolean lessonType, double courseAmount, double balance) {
        this.addmissionId = addmissionId;
        this.rId = rId;
        this.prID = prID;
        this.vcId = vcId;
        this.lcId = lcId;
        this.exdId = exdId;
        this.cldId = cldId;
        this.initName = initName;
        this.address = address;
        this.tel1 = tel1;
        this.lessonType = lessonType;
        this.courseAmount = courseAmount;
        this.balance = balance;
    }

    

    public String getExdId() {
        return exdId;
    }

    public void setExdId(String exdId) {
        this.exdId = exdId;
    }

    public String getCldId() {
        return cldId;
    }

    public void setCldId(String cldId) {
        this.cldId = cldId;
    }

    

    public String getAddmissionId() {
        return addmissionId;
    }

    public void setAddmissionId(String addmissionId) {
        this.addmissionId = addmissionId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }

    public String getVcId() {
        return vcId;
    }

    public void setVcId(String vcId) {
        this.vcId = vcId;
    }

    public String getLcId() {
        return lcId;
    }

    public void setLcId(String lcId) {
        this.lcId = lcId;
    }

    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTel1() {
        return tel1;
    }

    public void setTel1(int tel1) {
        this.tel1 = tel1;
    }

    public boolean isLessonType() {
        return lessonType;
    }

    public void setLessonType(boolean lessonType) {
        this.lessonType = lessonType;
    }

    public double getCourseAmount() {
        return courseAmount;
    }

    public void setCourseAmount(double courseAmount) {
        this.courseAmount = courseAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
     
}
