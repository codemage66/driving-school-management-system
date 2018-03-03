/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbcontroller.CustomerDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.PreregistrationDBController;
import edu.ijse.gdse41.drivingschool.helpers.FieldValidator;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschool.viewcontroller.RegistrationController;
import edu.ijse.gdse41.drivingschooldto.Customer;
import edu.ijse.gdse41.drivingschooldto.RegistrationDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class RegController implements Initializable {
     @FXML
    private TextField txtAddmissionId;

    @FXML
    private TextField txtNIC;

    @FXML
    private ComboBox<String> comboLessonType;

    @FXML
    private TextField txtTel3;

    @FXML
    private TextField txTel2;

    @FXML
    private RadioButton rdoMale;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtInitialName;

    @FXML
    private JFXButton bttCancel;

    @FXML
    private JFXButton bttNext;


    @FXML
    private ComboBox<String> comboVehicalClass;

    @FXML
    private Label lblPreName;
    
    @FXML
    void FullName(KeyEvent event) {
        FieldValidator.onlyAlpha(event);
    }
    
    @FXML
    void initName(KeyEvent event) {
        FieldValidator.onlyAlpha(event);
    }
    @FXML
    void tel1KeyTyped(KeyEvent event) {
        FieldValidator.validateTelephoneNumber(txTel2);
        FieldValidator.onlyNumeric(event);
    }

    @FXML
    void tel2KeyTyped(KeyEvent event) {
        FieldValidator.validateTelephoneNumber(txtTel3);
        FieldValidator.onlyNumeric(event);
    }
    @FXML
    void NicKeyAction(KeyEvent event) {
        
    }
    
    @FXML
    void KeyEvent(KeyEvent event) {
//        txtNIC.textProperty().addListener(((observable, oldValue, newValue) -> {
//            if(newValue.){
//                FieldValidator.validateNIC(txtNIC);
//            }
//        }));
    }
   
    @FXML
    void bttCancelClicked(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Customer Registration");
        alert.setHeaderText("Canceling Registration");
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
    void addmissionIdKeyPressed(KeyEvent event) {
        txtAddmissionId.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            try {
            prId=PreregistrationDBController.getPrId(txtAddmissionId.getText());
            System.out.println(prId);
            
         } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(RegController.class.getName()).log(Level.SEVERE, null, ex);
         }
        });
    }

    @FXML
    void bttNextClicked(ActionEvent event) {
        if(admissionIdValidator()){
            String addmissionId=txtAddmissionId.getText();
            outerAddmissionId=txtAddmissionId.getText();
            String nic=txtNIC.getText();
            String fullName=txtFullName.getText();
            String initname=txtInitialName.getText();
            String tel2=txTel2.getText();
            String tel3;
            if(txtTel3.getText().equals("")){
                tel3="None";
            }else{
                tel3=txtTel3.getText();
            } 
            String gender=getGender();
            System.out.println(bttGroup.getSelectedToggle().toString());
            int lessonType=getLessonTtype();
            Customer c=new Customer(addmissionId,nic,fullName,initname,tel2,tel3,gender,lessonType);
            RegistrationDTO regDto=new RegistrationDTO();
            
            try {
                String rid=IdGenarateHelper.genarateRId();
                outerRid=rid;
                regDto=new RegistrationDTO(rid,prId,intakeClass());
            } catch (ClassNotFoundException ex) {
                 Logger.getLogger(RegController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                 Logger.getLogger(RegController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                boolean status=CustomerDBController.custFullReg(c,regDto);
                
                if (status){
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Customer Registration");
                    alert.setHeaderText("Customer Registered Successfully,Do you want to add Customer exsiting license Details");
                    alert.setContentText("Choose your option.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    
                    alert.getButtonTypes().setAll(yesButton,noButton,buttonTypeCancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get()==yesButton){
                        try {
                            Parent dlRoot=FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/DLDetails.fxml"));
                            Stage dlStage=new Stage(StageStyle.DECORATED);
                            dlStage.setScene(new Scene(dlRoot,976, 650));
                            dlStage.showAndWait();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(RegController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }else if(result.get()==noButton){
                        alert.close();
                        fieldRester();
                    }else{
                        alert.close();
                    }
                    
                    fieldRester();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Customer Registration");
                    alert.setHeaderText("Error While Saving");
                    alert.setContentText("Sorry, There is an error in Registration");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    alert.showAndWait();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Registration");
            alert.setHeaderText("Not Pre-registered");
            alert.setContentText("Customer Needs to Pre-Registered first to Register in the System");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait();
            fieldRester();
        }
        
        
    }
    Node backScene;
    final ToggleGroup bttGroup=new ToggleGroup();
    private String prId;
    private String [] simpleArray;
    private Pane pane;
    private Customer customer;
    public static String outerAddmissionId;
    public static String outerRid;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttNext.setDisable(true);
        
        
        initAddmissionIds();
        txtAddmissionId.requestFocus();
        /**ComboBox value Initialization*/
        comboLessonType.getItems().addAll("Group","Individual");
        comboVehicalClass.getItems().addAll("A","A1","B","B1","A/B/B1","A/B","A/B1");
        
        /**ToggelButton Initialization*/
        rdoMale.setToggleGroup(bttGroup);
        rdoMale.setSelected(true);
        rdoFemale.setToggleGroup(bttGroup);
        ///NicValidator//////////
        txtNIC.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("running");
            if(!newValue){
                FieldValidator.validateNIC(txtNIC);
            }
        }));
        
        
        //////txtAdmissionRedBand//////
        txtAddmissionId.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if(!newValue){
                    if(!admissionIdValidator()){
                       txtAddmissionId.setId("sandmanErrorText");
                    }else{
                        txtAddmissionId.setId("sandmanReverseErrorText");
                        String id=txtAddmissionId.getText();
                        try {
                            customer=CustomerDBController.getDetailsFromAddmission(id);
                            lblPreName.setText(customer.getCustName());
                        } catch (SQLException ex) {
                            Logger.getLogger(RegController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(RegController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
               }
            }
        });
        
        validateNodes();
        
    }  
    
    private int getLessonTtype(){
        String lessonType=comboLessonType.getValue();
        if(lessonType.equals("Individual")){
            return 1;
        }else{
            return 0;
        }
    }  
    private String getGender(){
        if(bttGroup.getSelectedToggle().equals(rdoMale)){
            return "male";
        }else{
            return "female";
        }
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
    
    private void initAddmissionIds(){
        try {
            ArrayList <String> addmssionList=PreregistrationDBController.getAllAddmissionIDs();
            simpleArray=new String[addmssionList.size()];
            addmssionList.toArray(simpleArray);
            TextFields.bindAutoCompletion(txtAddmissionId, simpleArray);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean admissionIdValidator(){
        if (Arrays.asList(simpleArray).contains(txtAddmissionId.getText())){
            return true;
        }else{
            return false;
        }
    }
    public Pane getContent(String a) throws IOException{
        pane = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/"+ a));
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        return pane;
    }
    private void fieldRester(){
        txtAddmissionId.setText("");
        txTel2.setText("");
        txtFullName.setText("");
        txtInitialName.setText("");
        txtNIC.setText("");
        txtTel3.setText("");   
    }
    
    private void validateNodes(){
        //-----------Value Validate-----------------------//
        txtAddmissionId.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyNumeric(event);
        }));
        
        txtFullName.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        }));
        txtInitialName.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        }));
        txTel2.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txTel2);
        }));
        txtTel3.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel3);
        }));
        
        //----------------emptyValidate-----------------------//
        txtAddmissionId.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtFullName.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtInitialName.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtNIC.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txtTel3.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable();
        });
        txTel2.setOnKeyReleased((KeyEvent event) -> {
            savebutEnable(); 
        });
        comboLessonType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                savebutEnable();
            }
        });
        comboVehicalClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                savebutEnable();
            }
        });
        
    }
    private void savebutEnable(){
        boolean act1=txtAddmissionId.getText().trim().equals("");
        boolean act2=txtFullName.getText().trim().equals("");
        boolean act3=txtInitialName.getText().trim().equals("");
        boolean act4=txtNIC.getText().trim().equals("");
        boolean act5=txTel2.getText().trim().equals("")||txtTel3.getText().trim().equals("");
        boolean act6=bttGroup.getSelectedToggle()==null;
        boolean act7=comboLessonType.getValue()==null;
        boolean act8=comboVehicalClass.getValue()==null;
        
        boolean finalE=act1 || act2 || act3 || act4 || act5 || act6 || act7 || act8;
        System.out.println(finalE);
        if(finalE){
            bttNext.setDisable(true);
            
        }else{
            bttNext.setDisable(false);
        }          
    }
}
