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
public class TrialExamDTO {
    private String trId;
    private String trDate;
    private int attemptNo;

    public TrialExamDTO() {
    }

    public TrialExamDTO(String trId, String trDate, int attemptNo) {
        this.trId = trId;
        this.trDate = trDate;
        this.attemptNo = attemptNo;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getTrDate() {
        return trDate;
    }

    public void setTrDate(String trDate) {
        this.trDate = trDate;
    }

    public int getAttemptNo() {
        return attemptNo;
    }

    public void setAttemptNo(int attemptNo) {
        this.attemptNo = attemptNo;
    }
    
}
