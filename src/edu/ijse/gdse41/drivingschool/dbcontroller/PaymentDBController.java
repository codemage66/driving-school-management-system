/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschool.helpers.PaymentBalanceHelper;
import edu.ijse.gdse41.drivingschooldto.PaymentDTO;
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
public class PaymentDBController {
    public static boolean addPayment(PaymentDTO payDto) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO paymentLog VALUES(?,?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, payDto.getPid());
        state.setObject(2, payDto.getAddmissionId());
        state.setObject(3, payDto.getAmount());
        state.setObject(4, payDto.getDate());
        
        return state.executeUpdate()>0;
           
         
    }
    
    public static double getPaymentsSum(String addmissionId) throws ClassNotFoundException, SQLException{
        String query="select sum(amount) as totalPay from paymentLog where addmissionId='"+addmissionId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        
        if(rst.next()){
            return rst.getDouble("totalPay");
        }else{
            return 0.0;
        }
    }
    public static ArrayList<PaymentDTO> getPayLog(String addmissionId) throws ClassNotFoundException, SQLException{
        String query="select * from paymentLog where addmissionId='"+addmissionId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        ArrayList<PaymentDTO> payList=new ArrayList<>();
        
        while (rst.next()) {            
            PaymentDTO paydDto=new PaymentDTO(rst.getString("paymentId"), rst.getString("addmissionId"), rst.getDouble("amount"), rst.getString("payment_date"));
            payList.add(paydDto);       
        }
        return payList;
    }
}
