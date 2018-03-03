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
public class ExamDetailsDBController {
    public static boolean addExamDetail (String exDId,String wrID) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO examDetail(exdId,wrId,status) VALUES('"+exDId+"','"+wrID+"',false)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        return stm.executeUpdate(query)>0;
    }
    public static boolean updatTrEx(String trid,String exdid) throws SQLException, ClassNotFoundException{
        String query="UPDATE examDetail SET trId='"+trid+"' WHERE exdId='"+exdid+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        return stm.executeUpdate(query)>0;
    }
    
    public static String getID (String exDId,String Idtype) throws ClassNotFoundException, SQLException{
        String query="SELECT "+Idtype+" from examDetail WHERE exdId='"+exDId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
       
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            return rst.getString(Idtype);
        }
        return null;
    }
    public static boolean updateStatus(String exdid) throws ClassNotFoundException, SQLException{
        String query="UPDATE examDetail SET status=true where exdId='"+exdid+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        return stm.executeUpdate(query)>0;
    }
}
