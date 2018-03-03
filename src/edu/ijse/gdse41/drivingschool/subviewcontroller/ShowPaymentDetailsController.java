/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbconnection.DBConnection;
import edu.ijse.gdse41.drivingschool.dbcontroller.CustAmountDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.PaymentDBController;
import edu.ijse.gdse41.drivingschool.tablemodels.PaymentLogTableModel;
import edu.ijse.gdse41.drivingschool.viewcontroller.NavigationPageController;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschool.viewcontroller.UpcommingController;
import edu.ijse.gdse41.drivingschooldto.CustAmount;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
import edu.ijse.gdse41.drivingschooldto.PaymentDTO;
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
import javafx.scene.control.TextField;
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
public class ShowPaymentDetailsController implements Initializable {

    @FXML
    private TextField txtAddmissionId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtCourseAmount;

    @FXML
    private TextField txtBalance;

    @FXML
    private TableView payLogTable;

    @FXML
    private TableColumn tblPayId;

    @FXML
    private TableColumn tblAmount;

    @FXML
    private TableColumn tblPayDate;

    @FXML
    private JFXButton bttPayReport;


   
    
    @FXML
    void bttPayReportClicked(ActionEvent event) {
        HashMap<String,Object> repoMap=new HashMap<>();
        repoMap.put("AddmissionId", mainDetailsDto.getAddmissionId());
        repoMap.put("CustName", mainDetailsDto.getInitName());
        repoMap.put("Tel", Integer.toString(mainDetailsDto.getTel1()));
        repoMap.put("CustAddress", mainDetailsDto.getAddress());
        repoMap.put("Vclass", mainDetailsDto.getVcId());
        repoMap.put("date", date);
        repoMap.put("CoursePay", custA.getTotalAmount());
        repoMap.put("Balance", custA.getBalance());
        viewJasper(ShowPaymentDetailsController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/FullPaymentsReport.jasper"), repoMap);
    }
    //----variables----//
    
    private CustAmount custA;
    private final ObservableList<PaymentLogTableModel> data
            = FXCollections.observableArrayList();
    private MainSearchDTO mainDetailsDto;
    private String date;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(SearchBoardController.mainDtoInSearch!=null){
            try {
                txtAddmissionId.setText(SearchBoardController.mainDtoInSearch.getAddmissionId());
                txtCustName.setText(SearchBoardController.mainDtoInSearch.getInitName());
                custA=CustAmountDBController.getCustAmount(SearchBoardController.mainDtoInSearch.getAddmissionId());
                txtCourseAmount.setText(Double.toString(custA.getTotalAmount()));
                txtBalance.setText(Double.toString(custA.getBalance()));
                mainDetailsDto=SearchBoardController.mainDtoInSearch;
            } catch (SQLException ex) {
                Logger.getLogger(ShowPaymentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ShowPaymentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            intiLog();
            payLogTable.refresh();
            bttPayReport.setDisable(false);
        }else{
            mainDetailsDto=null;
            custA=null;
            bttPayReport.setDisable(true);
        }
        date=new SimpleDateFormat("dd-MM-YYYY").format(new Date());
    }    
    private void intiLog(){
       if(SearchBoardController.mainDtoInSearch!=null){
            try {   
                tblPayId.setCellValueFactory(new PropertyValueFactory<PaymentLogTableModel, String>("paymentId"));
                tblAmount.setCellValueFactory(new PropertyValueFactory<PaymentLogTableModel, String>("amount"));
                tblPayDate.setCellValueFactory(new PropertyValueFactory<PaymentLogTableModel, String>("date"));

                payLogTable.setItems(data);
                ArrayList <PaymentDTO> payLogArray=null;
                payLogArray=PaymentDBController.getPayLog(SearchBoardController.mainDtoInSearch.getAddmissionId());
                for (PaymentDTO payDto : payLogArray) {
                    PaymentLogTableModel ntd=new PaymentLogTableModel();
                    ntd.setPaymentId(payDto.getAddmissionId());
                    ntd.setAmount(Double.toString(payDto.getAmount()));
                    ntd.setDate(payDto.getDate());
                    
                    data.add(ntd);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    private void viewJasper(InputStream st,HashMap<String,Object>map){
        try {
            JasperReport loadReport=(JasperReport) JRLoader.loadObject(st);
            JasperPrint printJasper=JasperFillManager.fillReport(loadReport, map, DBConnection.getDBConnection().getConnection());
            JasperViewer.viewReport(printJasper,false);
        } catch (JRException ex) {
            Logger.getLogger(Pre_paymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpcommingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpcommingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   
}
