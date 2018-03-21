/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.helpers;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschool.dbcontroller.IdDBController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sandman
 */
public class IdGenarateHelper {
    
    
    public static String genarateAddmissionId () throws ClassNotFoundException, SQLException {
        String date = new SimpleDateFormat("YYMMdd").format(new Date());
//        String query="SELECT addmissionId FROM customer";
//        Connection conn=DBConnection.getDBConnection().getConnection();
//        Statement stm=conn.createStatement();
//        ResultSet rst=stm.executeQuery(query);
//        rst.last();
        String lastId = IdDBController.getLastId("customer", "addmissionid");

        String addmissioinId = "";
        int tempAdd;

        if (lastId != null) {
            char[] addmission = lastId.toCharArray();
            for (int i = 6; i < addmission.length; i++) {
                addmissioinId = addmissioinId + addmission[i];
            }

            if (addmissioinId.equals("99")) {
                addmissioinId = "00";
            }
            tempAdd = Integer.parseInt(addmissioinId);

            if (tempAdd < 9) {
                addmissioinId = date + "0" + (tempAdd + 1);

            } else {
                addmissioinId = date + (tempAdd + 1);
            }
            //System.out.println(addmissioinId);
            return addmissioinId;
        }else {
            addmissioinId = date+"00";
            return addmissioinId;
        }
    }
    
    public static String genaratePrId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("preRegistration", "prId");
        String newId;
        String prefix="PR";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
   public static String genarateRId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("registration", "rId");
        String newId;
        String prefix="RE";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    
    public static String genarateAID() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("CustAmount", "aId");
        String newId;
        String prefix="A";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
     public static String genaratePID() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("paymentLog", "paymentId");
        String newId;
        String prefix="P";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    public static String genarateWrId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("writtenExam", "wrId");
        String newId;
        String prefix="W";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    public static String genarateTRId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("trailExam", "trId");
        String newId;
        String prefix="T";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    public static String genarateExDetailId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("examDetail", "exdId");
        String newId;
        String prefix="D";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    public static String genarateExLogId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("writtenExamLog", "wrLogId");
        String newId;
        String prefix="E";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    public static String genaratTrLogId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("trialLog", "trLog");
        String newId;
        String prefix="L";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
    public static String genarateDLDId() throws SQLException, ClassNotFoundException{
        String lastId = IdDBController.getLastId("custLicenseDetail", "cldId");
        String newId;
        String prefix="C";
        int len=prefix.length();
        if (lastId != null) {
            String Id="";
            char[] reg=lastId.toCharArray();
            for(int i=len;i<reg.length;i++){
                Id+=reg[i];
            }
            int r=Integer.parseInt(Id);
            if(r<9){
                newId= prefix+"000"+(r+1);
            }else if(r<99){
                newId= prefix+"00"+(r+1);
            }else if(r<999){
                newId= prefix+"0"+(r+1);
            }else
                newId= prefix+(r+1);
        }else{
            newId = prefix+"0001";
        }
        return newId;
    }
}
