/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.NotificationDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sandman
 */
public class NotoficationDBController {
    public static ArrayList<NotificationDTO> getCustNotification() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm= conn.createStatement();
        ResultSet rst=stm.executeQuery("select customer.custName, customer.tel1,licenseCat.catagoryName,vehicleClass.className \n" +
                                        "from (((customer\n" +
                                        "INNER JOIN preRegistration ON customer.addmissionId=preRegistration.addmissionid)\n" +
                                        "INNER JOIN licenseCat ON preRegistration.lcid=licenseCat.lcId)\n" +
                                        "INNER JOIN vehicleClass ON preRegistration.vcid=vehicleClass.vcid) WHERE preRegistration.state=false");  
        ArrayList<NotificationDTO> notifiList=new ArrayList<>();
        while(rst.next()){
            NotificationDTO notiDto=new NotificationDTO(rst.getString("custName"), rst.getString("tel1"), rst.getString("catagoryName"), rst.getString("className"));
            notifiList.add(notiDto);
        }
        return  notifiList;
    }
}
