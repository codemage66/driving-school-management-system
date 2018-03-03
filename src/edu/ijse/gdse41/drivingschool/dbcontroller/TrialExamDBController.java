/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.ExamReportDTO;
import edu.ijse.gdse41.drivingschooldto.TrialExamDTO;
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
public class TrialExamDBController {
    public static boolean addTrialDetails(TrialExamDTO trdto,String exdId,TrialLogDTO trlog) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO trailExam VALUES(?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, trdto.getTrId());
        state.setObject(2, trdto.getTrDate());
        state.setObject(3, trdto.getAttemptNo());
        
        try{
            conn.setAutoCommit(false);
            if(state.executeUpdate()>0){
                if(ExamDetailsDBController.updatTrEx(trdto.getTrId(), exdId)){
                    if(TrialLogDBController.addTrialLog(trlog)){
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
    public static boolean updatTrEx(TrialExamDTO trto,TrialLogDTO trlog) throws SQLException, ClassNotFoundException{
        String query="UPDATE trailExam SET trialDate='"+trto.getTrDate()+"', attemptNo="+trto.getAttemptNo()+" WHERE trId='"+trto.getTrId()+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        try{
            conn.setAutoCommit(false);
            if(stm.executeUpdate(query)>0){
                if(TrialLogDBController.addTrialLog(trlog)){
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
    public static TrialExamDTO getTrialEx(String exdId) throws ClassNotFoundException, SQLException{
        String query="SELECT * \n" +
                "FROM trailExam\n" +
                "INNER JOIN examDetail ON trailExam.trId=examDetail.trId\n" +
                "WHERE examDetail.exdId= '"+exdId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            TrialExamDTO trdto=new TrialExamDTO(rst.getString("trId"), rst.getString("trialDate"), rst.getInt("attemptNo"));
            return trdto;
        }
        return null;
    }
    public static ArrayList<ExamReportDTO> getTrialExReport() throws ClassNotFoundException, SQLException{
        String query="SELECT customer.custInitialName, customer.tel1, vehicleClass.className, trailExam.trialDate, trailExam.attemptNo\n" +
                        "FROM (((((customer\n" +
                        "INNER JOIN preRegistration ON customer.addmissionId=preRegistration.addmissionid)\n" +
                        "INNER JOIN registration ON preRegistration.prid= registration.prid)\n" +
                        "INNER JOIN vehicleClass ON registration.vcid=vehicleClass.vcid)\n" +
                        "INNER JOIN examDetail ON examDetail.exdId=registration.exdId)\n" +
                        "INNER JOIN trailExam ON trailExam.trId=examDetail.trId)\n" +
                        "WHERE examDetail.status=false";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        ResultSet rst=stm.executeQuery(query);
        ArrayList<ExamReportDTO> trialList=new ArrayList<>();
        while(rst.next()){
            ExamReportDTO exreDto=new ExamReportDTO(rst.getString("custInitialName"), rst.getString("tel1"), rst.getString("className"), rst.getString("trialDate"),Integer.toString(rst.getInt("attemptNo")));
            trialList.add(exreDto);
        }
        return trialList; 
    }
    public static int getAttemptForValidate(String exdId) throws ClassNotFoundException, SQLException{
        String query="SELECT trailExam.attemptNo \n" +
                    "from (trailExam\n" +
                    "INNER JOIN examDetail ON trailExam.trId=examDetail.trId)\n" +
                    "WHERE examDetail.exdId='"+exdId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            return rst.getInt("attemptNo");
        }else{
            return 0;
        }
    }
}
