/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschool.dbcontroller.TrialLogDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.WrittenLogDBController;
import edu.ijse.gdse41.drivingschool.tablemodels.TrialExamTableModel;
import edu.ijse.gdse41.drivingschool.tablemodels.WrittenExamTableModel;
import edu.ijse.gdse41.drivingschool.viewcontroller.NavigationPageController;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschool.viewcontroller.UpcommingController;
import edu.ijse.gdse41.drivingschooldto.ExamLogDTO;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
import edu.ijse.gdse41.drivingschooldto.TrialLogDTO;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class ExamLogController implements Initializable {

    @FXML
    private TableView  tableWrittenExam;

    @FXML
    private TableColumn tblwrittenDate;

    @FXML
    private TableColumn tblWrittenStatus;

    @FXML
    private TableColumn tblWrittenRemark;

    @FXML
    private TableView  tableTrialExam;

    @FXML
    private TableColumn tblTrialdate;

    @FXML
    private TableColumn tblTrialsStatus;

    @FXML
    private TableColumn tblTrialRemarks;
    @FXML
    private JFXButton bttWEReport;

    @FXML
    private JFXButton bttTEReport;

    @FXML
    void bttTEReportClicked(ActionEvent event) {
        HashMap<String,Object> repoMap=new HashMap<>();
        repoMap.put("AddmissionId", tempMainDto.getAddmissionId());
        repoMap.put("CustName",tempMainDto.getInitName());
        repoMap.put("CustAddress", tempMainDto.getAddress());
        repoMap.put("rID", tempMainDto.getrId());
        repoMap.put("Vclass", tempMainDto.getVcId());
        repoMap.put("date", date);
        
        viewJasper(ExamLogController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/FullTrialReport.jasper"), repoMap);
    }

    @FXML
    void bttWEReportClicked(ActionEvent event) {
        HashMap<String,Object> repoMap=new HashMap<>();
        repoMap.put("AddmissionId", tempMainDto.getAddmissionId());
        repoMap.put("CustName",tempMainDto.getInitName());
        repoMap.put("CustAddress", tempMainDto.getAddress());
        repoMap.put("rID", tempMainDto.getrId());
        repoMap.put("Vclass", tempMainDto.getVcId());
        repoMap.put("date", date);
        
        viewJasper(ExamLogController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/FullExamReport.jasper"), repoMap);
    }
    //--Variables--//
    private final ObservableList<WrittenExamTableModel> Wdata
            = FXCollections.observableArrayList();
    private final ObservableList<TrialExamTableModel> Tdata
            = FXCollections.observableArrayList();
    private MainSearchDTO tempMainDto;
    private String date;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initExamData();
       initTrialData();
       date=new SimpleDateFormat("dd-mm-YYYY").format(new Date());
    }
    
    //////////-------Initializing contains in written exam table---------////////////

    private void initExamData(){
        if(SearchBoardController.mainDtoInSearch!=null){
            tempMainDto=SearchBoardController.mainDtoInSearch;
            try {   
                tblwrittenDate.setCellValueFactory(new PropertyValueFactory<WrittenExamTableModel, String>("examDate"));
                tblWrittenStatus.setCellValueFactory(new PropertyValueFactory<WrittenExamTableModel, String>("status"));
                tblWrittenRemark.setCellValueFactory(new PropertyValueFactory<WrittenExamTableModel, String>("Remarks"));

                tableWrittenExam.setItems(Wdata);
                ArrayList <ExamLogDTO> exLogArray=null;
                exLogArray=WrittenLogDBController.getLogList(SearchBoardController.mainDtoInSearch.getrId());
                for (ExamLogDTO examDto : exLogArray) {
                    WrittenExamTableModel ntd=new WrittenExamTableModel();
                    ntd.setExamDate(examDto.getExamDate());
                    switch(examDto.getStatus()){
                        case 1:
                            ntd.setStatus("Passed");
                            break;
                        case 2:
                            ntd.setStatus("Failed");
                            break;
                        case 3:
                            ntd.setStatus("Absent");
                            break;
                    }
                    ntd.setremarks(examDto.getRemarks());
                    Wdata.add(ntd);
                }
             } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{
            tempMainDto=null;
            
            bttWEReport.setDisable(true);
            
        }
    }
    
    //////////////----initializing Contains in trial table--------------//////////
    private void initTrialData(){       
        if(SearchBoardController.mainDtoInSearch!=null){
            try {   
                tblTrialdate.setCellValueFactory(new PropertyValueFactory<TrialExamTableModel, String>("examDate"));
                tblTrialsStatus.setCellValueFactory(new PropertyValueFactory<TrialExamTableModel, String>("status"));
                tblTrialRemarks.setCellValueFactory(new PropertyValueFactory<TrialExamTableModel, String>("Remarks"));

                tableTrialExam.setItems(Tdata);
                ArrayList <TrialLogDTO> trLogArray=null;
                trLogArray=TrialLogDBController.getLogList(SearchBoardController.mainDtoInSearch.getrId());
                for (TrialLogDTO trialDto : trLogArray) {
                    TrialExamTableModel ntd=new TrialExamTableModel();
                    ntd.setExamDate(trialDto.getTrialDate());
                    switch(trialDto.getStatus()){
                        case 1:
                            ntd.setAmount("Passed");
                            break;
                        case 2:
                            ntd.setAmount("Failed");
                            break;
                        case 3:
                            ntd.setAmount("Absent");
                            break;
                    }
                    ntd.setremarks(trialDto.getRemarks());
                    Tdata.add(ntd);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{
            bttTEReport.setDisable(true);
        } 
    }
    
    private void viewJasper(InputStream st,HashMap<String,Object>map){
        try {
            JasperReport loadReport=(JasperReport) JRLoader.loadObject(st);
            JasperPrint printJasper=JasperFillManager.fillReport(loadReport, map, DBConnection.getDBConnection().getConnection());
            JasperViewer.viewReport(printJasper,false);
        } catch (JRException ex) {
            Logger.getLogger(Pre_paymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpcommingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
