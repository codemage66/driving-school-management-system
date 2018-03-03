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
public class DLDetailsDTO {
    private String cldId;
    private String addmissionId;
    private String vcId;
    private String dlNo;
    private String issueDate;
    private String expierDate;

    public DLDetailsDTO() {
    }

    public DLDetailsDTO(String cldId, String vcId, String dlNo, String issueDate, String expierDate) {
        this.cldId = cldId;
        this.vcId = vcId;
        this.dlNo = dlNo;
        this.issueDate = issueDate;
        this.expierDate = expierDate;
    }

    public DLDetailsDTO(String cldId, String addmissionId, String vcId, String dlNo, String issueDate, String expierDate) {
        this.cldId = cldId;
        this.addmissionId = addmissionId;
        this.vcId = vcId;
        this.dlNo = dlNo;
        this.issueDate = issueDate;
        this.expierDate = expierDate;
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

    public String getVcId() {
        return vcId;
    }

    public void setVcId(String vcId) {
        this.vcId = vcId;
    }

    public String getDlNo() {
        return dlNo;
    }

    public void setDlNo(String dlNo) {
        this.dlNo = dlNo;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpierDate() {
        return expierDate;
    }

    public void setExpierDate(String expierDate) {
        this.expierDate = expierDate;
    }
    
}
