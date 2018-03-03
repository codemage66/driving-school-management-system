/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Sandman
 */
public class UsersDBController {
    public static boolean addUser(String username,String pass, String name) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO users VALUES(?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        state.setObject(1, username);
        state.setObject(2, pass);
        state.setObject(3, name);
        
        return state.executeUpdate()>0;
        
    }
}
