/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sandman
 */
public class DashBoardDBController {
    public static MainSearchDTO getMainSearch(String idType,String id) throws ClassNotFoundException, SQLException{
        String query="select customer.addmissionId,registration.rid,registration.exdId,registration.cldId, preRegistration.prid,vehicleClass.className,preRegistration.lcId, customer.custInitialName, customer.address,customer.tel1, customer.lessonType, custAmount.totalAmount, custAmount.balance\n" +
                        "from ((((customer\n" +
                        "INNER JOIN preRegistration ON customer.addmissionId=preRegistration.addmissionid)\n" +
                        "INNER JOIN registration ON preRegistration.prid= registration.prid)\n" +
                        "INNER JOIN vehicleClass ON registration.vcid=vehicleClass.vcid)\n" +
                        "INNER JOIN CustAmount ON preRegistration.aid=custAmount.aid) WHERE customer."+idType+"='"+id+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            MainSearchDTO mainDto=new MainSearchDTO(rst.getString("addmissionId"), rst.getString("rid"),
                                    
                                    rst.getString("prid"),rst.getString("className"),rst.getString("lcId"),
                                    rst.getString("exdId"),rst.getString("cldId"), rst.getString("custinitialName"), 
                                    rst.getString("address"), rst.getInt("tel1"), rst.getBoolean("lessontype"),
                                    rst.getDouble("totalAmount"),rst.getDouble("balance"));
            return mainDto;
        }else
            return null;
    }
}
