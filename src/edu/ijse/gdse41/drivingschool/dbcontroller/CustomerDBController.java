/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.CustAmount;
import edu.ijse.gdse41.drivingschooldto.Customer;
import edu.ijse.gdse41.drivingschooldto.PreRegistrationDTO;
import edu.ijse.gdse41.drivingschooldto.RegistrationDTO;
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
public class CustomerDBController {
    public static boolean custPreReg (Customer customer,PreRegistrationDTO prDto,CustAmount custAm) throws ClassNotFoundException, SQLException{
        String query="insert into customer(addmissionId,custName,address,tel1,dob,transType) VALUES (?,?,?,?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        
        state.setObject(1, customer.getAddmissionId());
        state.setObject(2, customer.getCustName());
        state.setObject(3, customer.getAddress());
        state.setObject(4, customer.getTel1());
        state.setObject(5, customer.getDob());
        state.setObject(6, customer.getTransType());
        try{
            conn.setAutoCommit(false);
            if(state.executeUpdate()>0){
                if(CustAmountDBController.addCustAmount(custAm)){
                    if(PreregistrationDBController.preRegStep(prDto)){
                        conn.commit();
                        return true;
                    } 
                }
            }
            conn.rollback();
            return false;   
        }finally{
            conn.setAutoCommit(true);
        }
        
    }
    
    public static boolean custFullReg (Customer customer, RegistrationDTO regDto) throws ClassNotFoundException, SQLException{
        String query="UPDATE customer SET custFullName=?, custInitialName=?, nic=?, gender=?, tel2=?, tel3=?, lessonType=? WHERE addmissionId=?";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state= conn.prepareStatement(query);
        
        state.setObject(1, customer.getCutFullName());
        state.setObject(2, customer.getCustInitialName());
        state.setObject(3, customer.getNic());
        state.setObject(4, customer.getGender());
        state.setObject(5, customer.getTel2());
        state.setObject(6, customer.getTel3());
        state.setObject(7, customer.getLessonType());
        state.setObject(8, customer.getAddmissionId());
        
        try{
            conn.setAutoCommit(false);
            if(state.executeUpdate()>0){
                if(RegistrationDBController.addRegistration(regDto)){
                    if(PreregistrationDBController.updateState(customer.getAddmissionId()))
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
//    public static boolean testCustPreReg (Customer customer) throws ClassNotFoundException, SQLException{
//        String query="insert into customer(addmissionId,custName,address,tel1,dob,lessonType) VALUES (?,?,?,?,?,?)";
//        Connection conn=DBConnection.getDBConnection().getConnection();
//        PreparedStatement state = conn.prepareStatement(query);
//        
//        state.setObject(1, customer.getAddmissionId());
//        state.setObject(2, customer.getCustName());
//        state.setObject(3, customer.getAddress());
//        state.setObject(4, customer.getTel1());
//        state.setObject(5, customer.getDob());
//        state.setObject(6, customer.getLessonType());
//      
//        
//        return state.executeUpdate()>0;
//    }
    
    public static Customer getDetailsFromAddmission(String addmissionId) throws SQLException, ClassNotFoundException{
        String query="SELECT * FROM customer WHERE addmissionId='"+addmissionId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            Customer cust=new Customer(rst.getString("addmissionId"), rst.getString("nic"), rst.getString("custName"),
                    rst.getString("address"), rst.getString("custFullName"), rst.getString("custInitialName"),
                    rst.getString("tel1"), rst.getString("tel2"), rst.getString("tel3"), rst.getString("dob"),
                    rst.getString("gender"),rst.getInt("lessonType"));
            return cust;
        }
        return null;
    }
    public static ArrayList<String> getRegisteredAddmissionIds() throws ClassNotFoundException, SQLException{
        String query="SELECT customer.addmissionId \n" +
                    "from ((customer\n" +
                    "INNER JOIN preRegistration ON customer.addmissionId=preRegistration.addmissionid)\n" +
                    "INNER JOIN registration ON preRegistration.prid= registration.prid)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        ArrayList<String> addmissionIds=new ArrayList<>();
        while(rst.next()){
            addmissionIds.add(rst.getString("addmissionId"));
        }
        return addmissionIds;
                
    }
    public static boolean updateCust(int id,Customer customer) throws SQLException, ClassNotFoundException{
        String personalQuery="UPDATE customer SET custFullName=?,custInitialName=?,nic=?,dob=?,gender=? WHERE addmissionId=?";
        String contactQuery="UPDATE customer SET address=?,tel1=?,tel2=?,tel3=? WHERE addmissionId=?";
        Connection conn=DBConnection.getDBConnection().getConnection();
        boolean out=false;
        switch(id){
            case 1:
                PreparedStatement state1=conn.prepareStatement(personalQuery);
                state1.setObject(1, customer.getCutFullName());
                state1.setObject(2, customer.getCustInitialName());
                state1.setObject(3, customer.getNic());
                state1.setObject(4, customer.getDob());
                state1.setObject(5, customer.getGender());
                state1.setObject(6, customer.getAddmissionId());
                
                out= state1.executeUpdate()>0;
                break;
            case 2:
                PreparedStatement state2=conn.prepareStatement(contactQuery);
                state2.setObject(1, customer.getAddress());
                state2.setObject(2, customer.getTel1());
                state2.setObject(3, customer.getTel2());
                state2.setObject(4, customer.getTel3());
               
                state2.setObject(5, customer.getAddmissionId());
                out= state2.executeUpdate()>0;
                break;
        }
        return out;
    }
    public static String getVehicalClass(String rid) throws SQLException, ClassNotFoundException{
        String query="Select vehicleClass.className  from vehicleClass INNER JOIN registration ON registration.vcid=vehicleClass.vcid where rid='"+rid+"'";
         Connection conn=DBConnection.getDBConnection().getConnection();
         Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            return rst.getString("className");
        }
        return null;
    }
      
}
