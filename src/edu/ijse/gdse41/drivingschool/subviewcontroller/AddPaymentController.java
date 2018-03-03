/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbcontroller.CustAmountDBController;
import edu.ijse.gdse41.drivingschool.helpers.FieldValidator;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschool.helpers.PaymentBalanceHelper;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.CustAmount;
import edu.ijse.gdse41.drivingschooldto.PaymentDTO;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
public class AddPaymentController implements Initializable {
  @FXML
    private TextField txtAddmissionId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtAmount;

    @FXML
    private JFXButton bttpay;

    @FXML
    private Label lblBalance;

   
    
    @FXML
    void bttPayClicked(ActionEvent event) {
        if(txtAmount.getText()!=""){
            try {
                String pid=IdGenarateHelper.genaratePID();
                double amount=Double.parseDouble(txtAmount.getText());
                String addmissionId=SearchBoardController.mainDtoInSearch.getAddmissionId();
                PaymentDTO paydto=new PaymentDTO(pid, addmissionId, amount, date);
              
                boolean state=PaymentBalanceHelper.runBalanceUpdater(paydto);
                balanceRefresher();
                if(state){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Customer Payment");
                    alert.setHeaderText("Success");
                    alert.setContentText("Customer payment Done Successfully");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    alert.showAndWait();
                    
                    HashMap<String,Object> payMap=new HashMap<>();
                    payMap.put("AddmissionId", txtAddmissionId.getText());
                    payMap.put("CustName", txtCustName.getText());
                    payMap.put("PaymentType", "Cash");
                    payMap.put("CustAddress", SearchBoardController.mainDtoInSearch.getAddress());
                    payMap.put("Description", "Payment For Driving Course");
                    payMap.put("Payment", amount);
                    payMap.put("Balance", custA.getBalance());
                    payMap.put("Total", amount);
                    payMap.put("Vclass", SearchBoardController.mainDtoInSearch.getVcId());
                    payMap.put("Date", date);
                    viewJasper(AddPaymentController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/Payment.jasper"), payMap);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Customer Payment");
                    alert.setHeaderText("OPPS!!");
                    alert.setContentText("there is an error making Payment");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Payment");
            alert.setHeaderText("OPPS!!");
            alert.setContentText("please make sure Amount field is not empty");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait();
        }
    }
    
    @FXML
    void bttPayDetailsClicked(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    CustAmount custA;
    String date;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttpay.setDisable(true);
        
        date=new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        
        if(SearchBoardController.mainDtoInSearch!=null){
            txtAddmissionId.setText(SearchBoardController.mainDtoInSearch.getAddmissionId());
            txtCustName.setText(SearchBoardController.mainDtoInSearch.getInitName());
            balanceRefresher();
        }
        validateNodes();
        // TODO
    }    
    private void balanceRefresher(){
        try {
                custA=CustAmountDBController.getCustAmount(SearchBoardController.mainDtoInSearch.getAddmissionId());
                lblBalance.setText("Rs: "+Double.toString(custA.getBalance())+"0");
            } catch (SQLException ex) {
                Logger.getLogger(AddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void viewJasper(InputStream st,HashMap<String,Object>map){
        try {
            JasperReport loadReport=(JasperReport) JRLoader.loadObject(st);
            JasperPrint printJasper=JasperFillManager.fillReport(loadReport, map, new JREmptyDataSource());
            JasperViewer.viewReport(printJasper,false);
        } catch (JRException ex) {
            Logger.getLogger(Pre_paymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    private void validateNodes(){
        //-----------Value Validate-----------------------//
        txtAmount.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyNumeric(event);
        }));
        
        
        //----------------emptyValidate-----------------------//
        txtAddmissionId.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtCustName.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtAmount.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
    }
    private void savebutEnable(){
        boolean act1=txtAddmissionId.getText().trim().equals("");
        boolean act2=txtCustName.getText().trim().equals("");
        boolean act3=txtAmount.getText().trim().equals("");
        
        
        boolean finalE=act1 || act2 || act3 ;
        System.out.println(finalE);
        if(finalE){
            bttpay.setDisable(true);
            
        }else{
            bttpay.setDisable(false);
        }          
    }
}
