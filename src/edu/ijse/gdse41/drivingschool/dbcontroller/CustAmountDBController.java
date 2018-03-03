/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.CustAmount;
import edu.ijse.gdse41.drivingschooldto.PaymentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sandman
 */
public class CustAmountDBController {
    public static boolean addCustAmount(CustAmount custAmo) throws SQLException, ClassNotFoundException{
        String query="INSERT INTO custAmount VALUES(?,?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, custAmo.getaId());
        state.setObject(2, custAmo.getTotalAmount());
        state.setObject(3, custAmo.getBalance());
        state.setObject(4, custAmo.getLastUpdate());
        
        return state.executeUpdate()>0;
    }
    public static double getCustBalance(String addmissionId) throws SQLException, ClassNotFoundException{
        String query="select custAmount.balance \n" +
                    "from (custAmount\n" +
                    "INNER JOIN preRegistration ON preRegistration.aid=custAmount.aid)\n" +
                    "WHERE preRegistration.addmissionId='"+addmissionId+"'";
        
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            return rst.getDouble("balance");
        }else{
            return 0.0;
        }
    }
    public static boolean balanceUpdater(PaymentDTO payDto, double newBalance) throws SQLException, ClassNotFoundException{
        String query="UPDATE custAmount \n" +
                    "INNER JOIN preRegistration ON custAmount.aid=preRegistration.aid\n" +
                    "SET custAmount.balance="+newBalance+", \n" +
                    "	custAmount.last_update='"+payDto.getDate()+"'\n" +
                    "WHERE preRegistration.addmissionId='"+payDto.getAddmissionId()+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        return stm.executeUpdate(query)>0;
    }
    public static CustAmount getCustAmount(String addmissionId) throws SQLException, ClassNotFoundException{
        String query="select * \n" +
                    "from (custAmount\n" +
                    "INNER JOIN preRegistration ON preRegistration.aid=custAmount.aid)\n" +
                    "WHERE preRegistration.addmissionId='"+addmissionId+"'";
        
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()) {            
            CustAmount custA=new CustAmount(rst.getString("aid"), rst.getDouble("totalAmount"), rst.getDouble("balance"), rst.getString("last_update"));
            return custA;
        }
        return null;

    }
}
