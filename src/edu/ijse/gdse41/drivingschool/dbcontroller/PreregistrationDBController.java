/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.PreRegistrationDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sandman
 */
public class PreregistrationDBController {
    public static boolean preRegStep(PreRegistrationDTO prDto) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO preregistration VALUES(?,?,?,?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, prDto.getPrId());
        state.setObject(2, prDto.getAddmissionId());
        state.setObject(3, prDto.getLcId());
        state.setObject(4, prDto.getVcId());
        state.setObject(5, prDto.getaId());
        state.setObject(6, false);
        return state.executeUpdate()>0;
        
    }
    public static String getPrId(String Id) throws ClassNotFoundException, SQLException{
        String query="select prId from  preregistration WHERE addmissionId='"+Id+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            return rst.getString("prId"); 
        }else{
            return null;
        }
        
    }
    public static ArrayList<String> getAllAddmissionIDs() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery("Select * from preregistration where state=false");
        ArrayList <String>addmissionIds=new ArrayList<>();
        while(rst.next()){
            addmissionIds.add(rst.getString("addmissionId"));
        }
        return addmissionIds;
    }
    
    public static String getAId(String addmissionId) throws SQLException, ClassNotFoundException{
        String query="Select aid from preregistration WHERE addmissionId='"+addmissionId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            return rst.getString("aid");
        }else{
            return null;
        }
    }
    public static boolean updateState(String addmissionId) throws ClassNotFoundException, SQLException{
        String query="UPDATE preregistration SET state=true WHERE addmissionId='"+addmissionId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement state=conn.createStatement();
        
        return state.executeUpdate(query)>0;
    }
}
 