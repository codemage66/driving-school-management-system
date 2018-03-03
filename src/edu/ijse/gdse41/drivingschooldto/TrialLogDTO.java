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
public class TrialLogDTO {
    private String trLog;
    private String rid;
    private String trialDate;
    private int status;
    private String remarks;

    public TrialLogDTO() {
    }

    public TrialLogDTO(String trLog,int status, String remarks) {
        this.trLog=trLog;
        this.status = status;
        this.remarks = remarks;
    }

    public TrialLogDTO(String trLog, String rid, String trialDate) {
        this.trLog = trLog;
        this.rid = rid;
        this.trialDate = trialDate;
    }

    public TrialLogDTO(String trLog, String rid, String trialDate, int status, String remarks) {
        this.trLog = trLog;
        this.rid = rid;
        this.trialDate = trialDate;
        this.status = status;
        this.remarks = remarks;
    }

    public String getTrLog() {
        return trLog;
    }

    public void setTrLog(String trLog) {
        this.trLog = trLog;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getTrialDate() {
        return trialDate;
    }

    public void setTrialDate(String trialDate) {
        this.trialDate = trialDate;
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
