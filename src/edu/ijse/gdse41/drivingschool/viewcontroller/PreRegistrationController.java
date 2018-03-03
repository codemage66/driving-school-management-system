/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import edu.ijse.gdse41.drivingschool.dbcontroller.CustomerDBController;
import edu.ijse.gdse41.drivingschooldto.Customer;
import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class PreRegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane overPane; 
    
    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;
    
    @FXML
    private Label lblSourse;
        
    @FXML
    private TextField txtnName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMobileNum;

    @FXML
    private ComboBox <String> comboLicenseType;

    @FXML
    private ComboBox<String> comboVehicalClass;

    @FXML
    private ComboBox<String> comboTransmission;
    
    @FXML
    private Button bttBack;

    @FXML
    private JFXButton bttPayment;

    @FXML
    private JFXButton bttSave;
    
    
    @FXML
    private JFXDatePicker dobPicker;
    
    @FXML
    void bbtMiniClicked(ActionEvent event) {
       primaryStage.setIconified(true);
    }
        
    @FXML
    void bttBackClicked(ActionEvent event) {
         try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/View/NewCustomer.fxml"));
            overPane.setVisible(false);
            rootPane.getChildren().add(backScene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttPaymentClicked(ActionEvent event) {
        
        
    }

    @FXML
    void bttSaveClicked(ActionEvent event) {
//        try {
//            String addmissionID=IdGenarateHelper.genarateAddmissionId();
//            String custName= txtnName.getText();
//            String address=txtAddress.getText();
//            String tel1=txtMobileNum.getText();
//            String dob=dobPicker.getValue().toString();
//            String licenseType=intakeLicenseId();
//            String vclass=intakeClass();
//            Customer c=new Customer(addmissionID,custName,address,tel1,dob);
//        
////            boolean status= CustomerDBController.custPreReg (c,addmissionID,licenseType,vclass);
////            System.out.println(CustomerDBController.genarateAddmissionId());
//             if (status) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Customer Add");
//                alert.setHeaderText("Done");
//                alert.setContentText("Customer has Pre-Registered sucessfully!!");
//                alert.showAndWait();                
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Customer Add");
//                alert.setHeaderText("OPPS!!");
//                alert.setContentText("there is an error adding customer");
//                alert.showAndWait();
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(PreRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(PreRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
              
    }

    @FXML
    void bttcloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void bttmaxclicked(ActionEvent event) {
        if(primaryStage.isFullScreen()){
            primaryStage.setFullScreen(false);         
        }else{
        primaryStage.setFullScreen(true);            
        }
    }
    Node backScene;
    String date;
    private static Stage primaryStage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
           primaryStage= (Stage) lblSourse.getScene().getWindow();
           ResizeHelper.addResizeListener(primaryStage);
        });
        date = new SimpleDateFormat("ddMMyy").format(new Date());
        System.out.println(date);
        
        comboLicenseType.getItems().addAll("new type","extend");
        comboVehicalClass.getItems().addAll("A","A1","B","B1","A/B/B1","A/B","A/B1");
        
        try {
            System.out.println(IdGenarateHelper.genarateAddmissionId());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PreRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PreRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private String intakeLicenseId(){
        String lessonId="";
        switch(comboLicenseType.getValue()){
            case "new type":
                lessonId="lc1";
                break;
            case "extend":
                lessonId="lc2";
                break;
        }
        return lessonId;
    
    }
    private String intakeClass(){
        String Vclass="";
        switch(comboVehicalClass.getValue()){
            case "A":
                Vclass="vc1";
                break;
            case "A1":
                Vclass="vc2";
                break;
            case "B":
                Vclass="vc3";
                break; 
            case "B1":
                Vclass="vc5";
                break;
            case "A/B/B1":
                Vclass="vc7";
                break; 
            case "A/B":
                Vclass="vc8";
                break;
            case "A/B1":
                Vclass="vc9";
                break;    
        }
        return Vclass;
    }
    
}
