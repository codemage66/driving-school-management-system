/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import edu.ijse.gdse41.drivingschool.dbcontroller.CustomerDBController;
import edu.ijse.gdse41.drivingschooldto.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.drivingschool.helpers.FieldValidator;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschooldto.CustAmount;
import edu.ijse.gdse41.drivingschooldto.PreRegistrationDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class Pre_regController implements Initializable {

    @FXML
    private TextField txtnName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtMobileNum;
    
    @FXML
    private JFXDatePicker dobPicker;
    @FXML
    private ComboBox<String> comboLicenseType;
    @FXML 
    private ComboBox<String> comboVehicalClass;
    @FXML
    private ComboBox<String> comboTransmission;
    @FXML
    private JFXButton bttPayment;
    @FXML
    private JFXButton bttSave;
    @FXML
    private TextField txtFee;
    /**
     * Initializes the controller class.
     */
    
    private String date;
    public static String addmissionId;
    public static String courseFee;
    public static String customerName;
    public static String OuterVclass;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldRester();
        date = new SimpleDateFormat("ddMMyy").format(new Date());
        System.out.println(date);
        
        comboTransmission.getItems().addAll("Auto","Manual");
        comboLicenseType.getItems().addAll("New","extend");
        comboVehicalClass.getItems().addAll("A","A1","B","B1","A/B/B1","A/B","A/B1");
//        
//        try {
//         ValidationSupport vds=new ValidationSupport();
//        vds.registerValidator(txtAddress, Validator.createEmptyValidator("Text is required"));
//        vds.registerValidator(comboVehicalClass, Validator.createEmptyValidator("Text is required"));
//        vds.setErrorDecorationEnabled(false);
//System.out.println(IdGenarateHelper.genarateAddmissionId());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Pre_regController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Pre_regController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        bttSave.setDisable(true);
        validateNodes();
    } 

    @FXML
    private void bttPaymentClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Customer Pre-Registration");
        alert.setHeaderText("Canceling Pre-Registration");
        alert.setContentText("Do you want to cancel the Registration?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
            fieldRester();
        } else {
            alert.close();
        }
    }

    @FXML
    private void bttSaveClicked(ActionEvent event) {
        try {
            String addmissionID=IdGenarateHelper.genarateAddmissionId();
            addmissionId=addmissionID;
            String custName= txtnName.getText();
            String address=txtAddress.getText();
            String tel1=txtMobileNum.getText();
            String dob=dobPicker.getValue().toString();
            String licenseType=intakeLicenseId();
            String vclass=intakeClass();
            String transType=comboTransmission.getValue().toString();
            OuterVclass=vclass;
            String aid=IdGenarateHelper.genarateAID();
            double amount=Double.parseDouble(txtFee.getText());
            courseFee=Double.toString(amount);
            customerName=custName;
            
            Customer customer=new Customer(addmissionID,custName,address,tel1,dob,transType);
            CustAmount custAmo=new CustAmount(aid, amount, amount,date);
            PreRegistrationDTO prDto= new PreRegistrationDTO(IdGenarateHelper.genaratePrId(), addmissionID, licenseType, vclass, aid);
            
            boolean status= CustomerDBController.custPreReg(customer,prDto,custAmo);
            
            if (status) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Customer Pre-Registration");
                    alert.setHeaderText("Customer Pre-Registered Successfully,Do you want to make a payment?");
                    alert.setContentText("Choose your option.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    
                    alert.getButtonTypes().setAll(yesButton,noButton,buttonTypeCancel);
                    Optional<ButtonType> result = alert.showAndWait();  
                    if(result.get()==yesButton){    
                        try {
                            Parent root;
                            root = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/Pre_payment.fxml"));
                            Stage payStage=new Stage(StageStyle.UTILITY);
                            payStage.setScene(new Scene(root, 1095, 521));
                            payStage.showAndWait();
                            fieldRester();
                        } catch (IOException ex) {
                            Logger.getLogger(Pre_regController.class.getName()).log(Level.SEVERE, null, ex);
                        }                       
                    }else if(result.get()==noButton){
                        fieldRester();
                        alert.close();
                    }else{
                        fieldRester();
                        alert.close();
                    }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Pre-Registration");
                alert.setHeaderText("Error While Saving");
                alert.setContentText("Sorry, There is an error in Registration");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                alert.showAndWait();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Pre_regController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String intakeLicenseId(){
        String lessonId="";
        switch(comboLicenseType.getValue()){
            case "New":
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
    private void  fieldRester(){
        txtAddress.setText("");
        txtFee.setText("");
        txtMobileNum.setText("");
        txtnName.setText("");
    }
    private void validateNodes(){
        //-----------field Validation--------//
        txtMobileNum.setOnKeyTyped((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtMobileNum);
        });
        txtnName.setOnKeyTyped((event) -> {
            FieldValidator.onlyAlpha(event);
        });
        //---------Empty Validation---------//
        txtAddress.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtFee.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtMobileNum.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtnName.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        
        
    }
    private void savebutEnable(){
        boolean act1=txtnName.getText().trim().equals("");
        boolean act2=txtAddress.getText().trim().equals("");
        boolean act3=txtFee.getText().trim().equals("");
        boolean act4=txtMobileNum.getText().trim().equals("");
        boolean act5=comboLicenseType.getValue() == null;
        boolean act6=comboVehicalClass.getValue()==null;
        boolean act7=comboTransmission.getValue()==null;
        boolean act8=dobPicker.getValue()==null;
        
        boolean finalE=act1||act2||act3||act4||act5||act6||act7||act8;
        
        if(finalE){
            bttSave.setDisable(true);
        }else{
            bttSave.setDisable(false);
        }
    }
}
