/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sandman
 */
public class UserLoginDBConnection {
    public static String getLogPass(String username,String password) throws ClassNotFoundException, SQLException{
        String query="SELECT * FROM users WHERE username='"+username+"' && password='"+password+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        
        if(rst.next()){
            return rst.getString("password");
        }
        return "empty";
    }
}
