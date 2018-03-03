/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.TrialLogDTO;
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
public class TrialLogDBController {
    public static boolean addTrialLog(TrialLogDTO trlog) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO trialLog(trLog,rId,trialDate) VALUES(?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, trlog.getTrLog());
        state.setObject(2, trlog.getRid());
        state.setObject(3, trlog.getTrialDate());
        
        return state.executeUpdate()>0;
    }
    public static boolean updateTrialLog(TrialLogDTO trlog) throws ClassNotFoundException, SQLException{
        String query="UPDATE trialLog SET status="+trlog.getStatus()+",remarks='"+trlog.getRemarks()+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement state=conn.createStatement();
 
        return state.executeUpdate(query)>0;
    }
    public static String getLogId(String rid,String date) throws ClassNotFoundException, SQLException{
        String query="select trLog from trialLog where rid='"+rid+"' && trialDate='"+date+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement state=conn.createStatement();
        ResultSet rst=state.executeQuery(query);
        
        if(rst.next()){
            return rst.getString("trLog");
        }
        return null;
    }
    public static ArrayList<TrialLogDTO> getLogList(String rid) throws ClassNotFoundException, SQLException{
        String query="SELECT * from trialLog WHERE rid='"+rid+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        
        ArrayList<TrialLogDTO> trDtoList=new ArrayList<>();
        while(rst.next()){
           TrialLogDTO trdto=new TrialLogDTO(rst.getString("trLog"), rst.getString("rid"), rst.getString("trialDate"), rst.getInt("status"), rst.getString("remarks"));
            trDtoList.add(trdto);
        }
        return trDtoList;
    }
    public static boolean updateTrialLogState(TrialLogDTO trlog,String exdid) throws ClassNotFoundException, SQLException{
        String query="UPDATE trialLog SET status="+trlog.getStatus()+",remarks='"+trlog.getRemarks()+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement state=conn.createStatement();

        try{
            conn.setAutoCommit(false);
            if(state.executeUpdate(query)>0){
                if(ExamDetailsDBController.updateStatus(exdid)){
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
}
