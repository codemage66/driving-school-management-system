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
public class WrittenExamDTO {
    private String wrId;
    private String exDate;
    private int attemptNo;

    public WrittenExamDTO() {
    }

    public WrittenExamDTO(String wrId, String exDate, int attemptNo) {
        this.wrId = wrId;
        this.exDate = exDate;
        this.attemptNo = attemptNo;
    }

    public String getWrId() {
        return wrId;
    }

    public void setWrId(String wrId) {
        this.wrId = wrId;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public int getAttemptNo() {
        return attemptNo;
    }

    public void setAttemptNo(int attemptNo) {
        this.attemptNo = attemptNo;
    }
    
    
}
