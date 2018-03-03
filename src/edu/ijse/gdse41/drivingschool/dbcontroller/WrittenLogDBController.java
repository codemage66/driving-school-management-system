/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.ExamLogDTO;
import edu.ijse.gdse41.drivingschooldto.TrialLogDTO;
import edu.ijse.gdse41.drivingschooldto.WrittenExamDTO;
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
public class WrittenLogDBController {
    public static boolean addWrittenLog(ExamLogDTO exLog) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO writtenExamLog(wrLogId,rId,examDate) VALUES(?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, exLog.getWrLogId());
        state.setObject(2, exLog.getrId());
        state.setObject(3, exLog.getExamDate());
        
        return state.executeUpdate()>0;
    }
    public static boolean updateExamLog(ExamLogDTO exlog) throws ClassNotFoundException, SQLException{
        String query="UPDATE writtenExamLog SET status="+exlog.getStatus()+",remarks='"+exlog.getRemarks()+"' WHERE wrLogId='"+exlog.getWrLogId()+"' ";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement state=conn.createStatement();
 
        return state.executeUpdate(query)>0;
    }
    public static String getLogId(String rid,String date) throws ClassNotFoundException, SQLException{
        String query="select wrLogId from writtenExamLog where rid='"+rid+"' && examdate='"+date+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement state=conn.createStatement();
        ResultSet rst=state.executeQuery(query);
        
        if(rst.next()){
            return rst.getString("wrLogId");
        }
        return null;
    }
    public static ArrayList<ExamLogDTO> getLogList(String rid) throws ClassNotFoundException, SQLException{
        String query="SELECT * from writtenExamLog WHERE rid='"+rid+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        
        ArrayList<ExamLogDTO> exDtoList=new ArrayList<>();
        while(rst.next()){
            ExamLogDTO exdto=new ExamLogDTO(rst.getString("wrLogId"), rst.getString("rid"), rst.getString("examDate"), rst.getInt("status"), rst.getString("remarks"));
            exDtoList.add(exdto);
        }
        return exDtoList;
    }
}
