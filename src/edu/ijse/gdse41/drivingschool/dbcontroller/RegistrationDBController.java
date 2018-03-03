/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.RegistrationDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sandman
 */
public class RegistrationDBController {
    public static boolean addRegistration(RegistrationDTO regDto) throws SQLException, ClassNotFoundException{
        String query="INSERT INTO registration(rid,prid,vcid) VALUES(?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, regDto.getrId());
        state.setObject(2, regDto.getPrId());
        state.setObject(3, regDto.getVcId());
        
        return state.executeUpdate()>0;

    }
    
    public static boolean updateExDetails (String exDId,String rid) throws ClassNotFoundException, SQLException{
        String query="UPDATE registration SET exdId='"+exDId+"'  WHERE rid='"+rid+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        return stm.executeUpdate(query)>0;   
    }
    
    public static boolean updateDLDetails (String cldId,String rid) throws ClassNotFoundException, SQLException{
        String query="UPDATE registration SET cldId='"+cldId+"'  WHERE rid='"+rid+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        return stm.executeUpdate(query)>0;   
    }
}
