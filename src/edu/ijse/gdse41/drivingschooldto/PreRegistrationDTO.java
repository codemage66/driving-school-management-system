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
public class PreRegistrationDTO {
    private String prId;
    private String addmissionId;
    private String lcId;
    private String vcId;
    private String aId;

    public PreRegistrationDTO() {
    }

    public PreRegistrationDTO(String prId, String addmissionId, String lcId, String vcId, String aId) {
        this.prId = prId;
        this.addmissionId = addmissionId;
        this.lcId = lcId;
        this.vcId = vcId;
        this.aId = aId;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getAddmissionId() {
        return addmissionId;
    }

    public void setAddmissionId(String addmissionId) {
        this.addmissionId = addmissionId;
    }

    public String getLcId() {
        return lcId;
    }

    public void setLcId(String lcId) {
        this.lcId = lcId;
    }

    public String getVcId() {
        return vcId;
    }

    public void setVcId(String vcId) {
        this.vcId = vcId;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }
    
}
