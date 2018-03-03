/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschool.dbcontroller.TrialExamDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.WrittenExamDBController;
import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import edu.ijse.gdse41.drivingschool.subviewcontroller.Pre_paymentController;
import edu.ijse.gdse41.drivingschool.tablemodels.ExamReportTableModel;
import edu.ijse.gdse41.drivingschooldto.ExamReportDTO;
import java.io.IOException;
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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
public class UpcommingController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private AnchorPane topAnchor;
    
    @FXML
    private AnchorPane overPane;

    @FXML
    private TableView tablewritten;

    @FXML
    private TableColumn tblWCustName;

    @FXML
    private TableColumn tblWTel;

    @FXML
    private TableColumn tblWVehicleClass;

    @FXML
    private TableColumn tblWDate;

    @FXML
    private TableColumn tblWAttempt;

    
    @FXML
    private JFXButton bttWGetReport;

    @FXML
    private TableView tableTrial;

    @FXML
    private TableColumn  tblTCustName;

    @FXML
    private TableColumn tblTTel;

    @FXML
    private TableColumn tblTVheicelclass;

    @FXML
    private TableColumn tblTDate;

    @FXML
    private TableColumn tblTAttempt;
    
    @FXML
    private JFXButton bttTGetReport;
    
    @FXML
    private Button bttBack;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private Label lblSourse;

    @FXML
    void bttBackClicked(ActionEvent event) {
        try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/NavigationPage.fxml"));
            overPane.setVisible(false);
            rootPane.getChildren().add(backScene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void bttTGetReportClicked(ActionEvent event) {
        HashMap<String,Object> reHash=new HashMap<>();
        reHash.put("Date", date);
        
        viewJasper(UpcommingController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/UpTrailList.jasper"), reHash);
    }

    @FXML
    void bttWGetReportClickeed(ActionEvent event) {
        HashMap<String,Object> reHash=new HashMap<>();
        reHash.put("Date", date);
        
        viewJasper(UpcommingController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/UpExamList.jasper"), reHash);
    }

    @FXML
    void bttMaxClicked(ActionEvent event) {
        if(primaryStage.isFullScreen()){
            primaryStage.setFullScreen(false);
        }else{
            primaryStage.setFullScreen(true);
        }
    }

    @FXML
    void bttMiniClicked(ActionEvent event) {
    primaryStage.setIconified(true);
    }

    /**
     * Initializes the controller class.
     */
    private Node backScene;
    private static Stage primaryStage;
    private String date;
    private final ObservableList<ExamReportTableModel> Wdata
            = FXCollections.observableArrayList();
    
    private final ObservableList<ExamReportTableModel> Tdata
            = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttTGetReport.setTooltip(new Tooltip("Get Trial Details"));
        bttWGetReport.setTooltip(new Tooltip("Get Exam Details"));
        
        Platform.runLater(()->{
           primaryStage= (Stage) lblSourse.getScene().getWindow();
           ResizeHelper.addResizeListener(primaryStage);
        });
        date=new SimpleDateFormat("dd-MM-YYYY").format(new Date());
        try {
            // TODO
            ArrayList<ExamReportDTO> exList=WrittenExamDBController.getWrittenExReport();
            ArrayList<ExamReportDTO> trList=TrialExamDBController.getTrialExReport();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpcommingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpcommingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        intiWLog();
        initTLog();
    }    
    
    private void intiWLog(){
        try {   
            tblWCustName.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("custName"));
            tblWTel.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("CustTelephone"));
            tblWVehicleClass.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("vehicleClass"));
            tblWDate.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("examDate"));
            tblWAttempt.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("attemptTime"));
            tablewritten.setItems(Wdata);
            ArrayList <ExamReportDTO> ExLogArray=null;
            ExLogArray=WrittenExamDBController.getWrittenExReport();
            for (ExamReportDTO examReportDTO : ExLogArray) {
                ExamReportTableModel ntd=new ExamReportTableModel();
                ntd.setCustName(examReportDTO.getCustName());
                ntd.setCustTelephone(examReportDTO.getCustTel());
                ntd.setVehicleClass(examReportDTO.getVehicleClass());
                ntd.setExamDate(examReportDTO.getExamDate());
                ntd.setAttemptTime(examReportDTO.getAttempt());
                Wdata.add(ntd);
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initTLog(){
         try {   
            tblTCustName.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("custName"));
            tblTTel.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("CustTelephone"));
            tblTVheicelclass.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("vehicleClass"));
            tblTDate.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("examDate"));
            tblTAttempt.setCellValueFactory(new PropertyValueFactory<ExamReportDTO, String>("attemptTime"));
            tableTrial.setItems(Tdata);
            ArrayList <ExamReportDTO> ExLogArray=null;
            ExLogArray=TrialExamDBController.getTrialExReport();
            for (ExamReportDTO examReportDTO : ExLogArray) {
                ExamReportTableModel ntd=new ExamReportTableModel();
                ntd.setCustName(examReportDTO.getCustName());
                ntd.setCustTelephone(examReportDTO.getCustTel());
                ntd.setVehicleClass(examReportDTO.getVehicleClass());
                ntd.setExamDate(examReportDTO.getExamDate());
                ntd.setAttemptTime(examReportDTO.getAttempt());
                Tdata.add(ntd);
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static class Distance{
        static double deltaX,deltaY;
    }
    @FXML
    void getCoordinates(MouseEvent event) {
        Distance.deltaX = - event.getX()+(-50);
        Distance.deltaY = - event.getY();
    }

    @FXML
    void setStage(MouseEvent event) {
        primaryStage.setX(event.getScreenX()+ Distance.deltaX);
        primaryStage.setY(event.getScreenY()+ Distance.deltaY);
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
