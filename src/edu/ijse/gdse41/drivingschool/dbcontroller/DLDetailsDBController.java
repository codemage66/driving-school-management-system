/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.DLDetailsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sandman
 */
public class DLDetailsDBController {
    public static boolean addDLDetails(DLDetailsDTO dlDto,String rid) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO custLicenseDetail VALUES(?,?,?,?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, dlDto.getCldId());
        state.setObject(2, dlDto.getAddmissionId());
        state.setObject(3, dlDto.getVcId());
        state.setObject(4, dlDto.getDlNo());
        state.setObject(5, dlDto.getIssueDate());
        state.setObject(6, dlDto.getExpierDate());
        
        try{
            conn.setAutoCommit(false);
            if(state.executeUpdate()>0){
                if(RegistrationDBController.updateDLDetails(dlDto.getCldId(), rid)){
                    conn.commit();
                    return true;
                }
            }
            conn.rollback();
            return false;
        }finally{
            conn.setAutoCommit(true);
        }  
    }
    public static DLDetailsDTO getDLDetails(String cldId) throws ClassNotFoundException, SQLException{
        String query="SELECT * FROM custLicenseDetail WHERE cldid='"+cldId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        
        if(rst.next()){
            DLDetailsDTO dlto=new DLDetailsDTO(rst.getString("cldId"), rst.getString("addmissionId"), rst.getString("vcId"), rst.getString("dLNo"), rst.getString("issueDate"), rst.getString("expireDate"));
            return dlto;
        }
        return null;
    }
    public static boolean updateData(DLDetailsDTO dldto) throws ClassNotFoundException, SQLException{
        String query="UPDATE custLicenseDetail SET vcid=?, dlNo=?,issueDate=?,expireDate=? WHERE cldId=?";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, dldto.getVcId());
        state.setObject(2, dldto.getDlNo());
        state.setObject(3, dldto.getIssueDate());
        state.setObject(4, dldto.getExpierDate());
        state.setObject(5, dldto.getCldId());
        
        return state.executeUpdate()>0;
    }
}
