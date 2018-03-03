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
public class RegistrationDTO {
    private String rId;
    private String prId;
    private String vcId;
    private String exdId;
    private String cldId;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String rId, String prId, String vcId, String exdId, String cldId) {
        this.rId = rId;
        this.prId = prId;
        this.vcId = vcId;
        this.exdId = exdId;
        this.cldId = cldId;
    }

    public RegistrationDTO(String rId, String prId, String vcId) {
        this.rId = rId;
        this.prId = prId;
        this.vcId = vcId;
    }

    

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getVcId() {
        return vcId;
    }

    public void setVcId(String vcId) {
        this.vcId = vcId;
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
    
}
