/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbcontroller;

import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschooldto.ExamLogDTO;
import edu.ijse.gdse41.drivingschooldto.ExamReportDTO;
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
public class WrittenExamDBController {
    public static boolean addExamDetails(WrittenExamDTO exdto,String exId,String rID,ExamLogDTO exlog) throws ClassNotFoundException, SQLException{
        String query="INSERT INTO writtenExam VALUES(?,?,?)";
        Connection conn=DBConnection.getDBConnection().getConnection();
        PreparedStatement state=conn.prepareStatement(query);
        
        state.setObject(1, exdto.getWrId());
        state.setObject(2, exdto.getExDate());
        state.setObject(3, exdto.getAttemptNo());
        
        try{
            conn.setAutoCommit(false);
            if(state.executeUpdate()>0){
                if(ExamDetailsDBController.addExamDetail(exId, exdto.getWrId())){
                    if(RegistrationDBController.updateExDetails(exId, rID)){
                        if(WrittenLogDBController.addWrittenLog(exlog)){
                            conn.commit();
                            return true;
                        }
                       
                    }
                }
            }
            conn.rollback();
            return false;
        }finally{
            conn.setAutoCommit(true);
        }
    }
    public static boolean updatWrEx(WrittenExamDTO exdto,ExamLogDTO exlg) throws SQLException, ClassNotFoundException{
        String query="UPDATE writtenExam SET Examdate='"+exdto.getExDate()+"', attemptNo="+exdto.getAttemptNo()+" WHERE wrId='"+exdto.getWrId()+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        try{
            conn.setAutoCommit(false);
            if(stm.executeUpdate(query)>0){
                if(WrittenLogDBController.addWrittenLog(exlg)){
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
    public static WrittenExamDTO getWrittenEx(String exdId) throws ClassNotFoundException, SQLException{
        String query="SELECT * \n" +
                "FROM writtenExam\n" +
                "INNER JOIN examDetail ON writtenExam.wrId=examDetail.wrId\n" +
                "WHERE examDetail.exdId= '"+exdId+"'";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        ResultSet rst=stm.executeQuery(query);
        if(rst.next()){
            WrittenExamDTO exdto=new WrittenExamDTO(rst.getString("wrId"),rst.getString("Examdate"),rst.getInt("attemptNo"));
            return exdto;
        }
        return null;
    }
    public static ArrayList<ExamReportDTO> getWrittenExReport() throws ClassNotFoundException, SQLException{
        String query="SELECT customer.custInitialName, customer.tel1, vehicleClass.className, writtenExam.examdate, writtenExam.attemptNo\n" +
                        "FROM (((((customer\n" +
                        "INNER JOIN preRegistration ON customer.addmissionId=preRegistration.addmissionid)\n" +
                        "INNER JOIN registration ON preRegistration.prid= registration.prid)\n" +
                        "INNER JOIN vehicleClass ON registration.vcid=vehicleClass.vcid)\n" +
                        "INNER JOIN examDetail ON examDetail.exdId=registration.exdId)\n" +
                        "INNER JOIN writtenExam ON writtenExam.wrId=examDetail.wrId)\n" +
                        "WHERE examDetail.status=false";
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        
        ResultSet rst=stm.executeQuery(query);
        ArrayList<ExamReportDTO> examList=new ArrayList<>();
        while(rst.next()){
            ExamReportDTO exreDto=new ExamReportDTO(rst.getString("custInitialName"), rst.getString("tel1"), rst.getString("className"), rst.getString("examdate"),Integer.toString(rst.getInt("attemptNo")));
            examList.add(exreDto);
        }
        return examList; 
    }
    public static int getAttemptForValidate(String exdId) throws ClassNotFoundException, SQLException{
        String query="SELECT writtenExam.attemptNo \n" +
            "from (writtenExam\n" +
            "INNER JOIN examDetail ON writtenExam.wrId=examDetail.wrId)\n" +
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
