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
public class ExamLogDTO {
    private String wrLogId;
    private String rId;
    private String examDate;
    private int status;
    private String remarks;

    public ExamLogDTO() {
    }

    public ExamLogDTO(String wrLogId, int status, String remarks) {
        this.wrLogId=wrLogId;
        this.status = status;
        this.remarks = remarks;
    }

    public ExamLogDTO(String wrLogId, String rId, String examDate) {
        this.wrLogId = wrLogId;
        this.rId = rId;
        this.examDate = examDate;
    }

    public ExamLogDTO(String wrLogId, String rId, String examDate, int status, String remarks) {
        this.wrLogId = wrLogId;
        this.rId = rId;
        this.examDate = examDate;
        this.status = status;
        this.remarks = remarks;
    }

    public String getWrLogId() {
        return wrLogId;
    }

    public void setWrLogId(String wrLogId) {
        this.wrLogId = wrLogId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
}
