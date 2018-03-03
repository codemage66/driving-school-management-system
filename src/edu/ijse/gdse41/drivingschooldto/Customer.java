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
public class Customer {
    private String addmissionId;
    private String nic;
    private String custName;
    private String address;
    private String cutFullName;
    private String custInitialName;
    private String tel1;
    private String tel2;
    private String tel3;
    private String dob;
    private String gender;
    private int lessonType;
    private String transType;

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
    
    public String getAddmissionId() {
        return addmissionId;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public void setAddmissionId(String addmissionId) {
        this.addmissionId = addmissionId;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getCutFullName() {
        return cutFullName;
    }

    public void setCutFullName(String cutFullName) {
        this.cutFullName = cutFullName;
    }

    public String getCustInitialName() {
        return custInitialName;
    }

    public void setCustInitialName(String custInitialName) {
        this.custInitialName = custInitialName;
    }

    public int getLessonType() {
        return lessonType;
    }

    public void setLessonType(int lessonType) {
        this.lessonType = lessonType;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Customer(String addmissionId, String nic, String custName, String address, String cutFullName, String custInitialName, String tel1, String tel2, String tel3, String dob, String gender, int lessonType) {
        this.addmissionId = addmissionId;
        this.nic = nic;
        this.custName = custName;
        this.address = address;
        this.cutFullName = cutFullName;
        this.custInitialName = custInitialName;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.dob = dob;
        this.gender = gender;
        this.lessonType = lessonType;
    }

   

   
    public Customer() {
    }
//pre_reg Construtor//
    public Customer(String addmissionId, String custName, String address, String tel1, String dob,String transType) {
        this.addmissionId = addmissionId;
        this.custName = custName;
        this.address = address;
        this.tel1 = tel1;
        this.dob = dob;
        this.transType=transType;
       
    }

    public Customer(String addmissionId, String nic, String cutFullName, String custInitialName, String tel2, String tel3, String gender, int lessonType) {
        this.addmissionId = addmissionId;
        this.nic = nic;
        this.cutFullName = cutFullName;
        this.custInitialName = custInitialName;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.gender = gender;
        this.lessonType = lessonType;
    }
    public Customer(String addmissionId, String nic, String custName, String cutFullName, String custInitialName, String tel1, String tel2, String dob, String gender, int lessonType) {
        this.addmissionId = addmissionId;
        this.nic = nic;
        this.custName = custName;
        this.cutFullName = cutFullName;
        this.custInitialName = custInitialName;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.dob = dob;
        this.gender = gender;
        this.lessonType = lessonType;
    }
    
}
