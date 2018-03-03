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
import edu.ijse.gdse41.drivingschooldto.RegistrationDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class RegistrationController implements Initializable {
    
    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private AnchorPane overAnchor;
    
    @FXML
    private TextField txtAddmissionId;
        
    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtInitialName;

    @FXML
    private TextField txtNIC;

    @FXML
    private ComboBox<String> comboVehicalClass;

    @FXML
    private ComboBox<String> comboLessonType;
        
      @FXML
    private TextField txtTel3;

    @FXML
    private TextField txTel2;

    @FXML
    private JFXButton bttCancel;

    @FXML
    private JFXButton bttNext;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private Button bttback;

    @FXML
    private Label lblSourse;
    
    @FXML
    private RadioButton rdoMale;

    @FXML
    private RadioButton rdoFemale;

    
    @FXML
    void bttCancelClicked(ActionEvent event) {

    }

    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void bttMaxClicked(ActionEvent event) {
     
    }

    @FXML
    void bttMiniClicked(ActionEvent event) {
       
    }

    @FXML
    void bttNextClicked(ActionEvent event) {
        String addmissionId=txtAddmissionId.getText();
        String nic=txtNIC.getText();
        String fullName=txtFullName.getText();
        String initname=txtInitialName.getText();
        String tel2=txTel2.getText();
        String tel3=txtTel3.getText();
        String gender=getGender();
        System.out.println(bttGroup.getSelectedToggle().toString());
        int lessonType=getLessonTtype();
        Customer c=new Customer(addmissionId,nic,fullName,initname,tel2,tel3,gender,lessonType);
        RegistrationDTO regDto=new RegistrationDTO(nic, nic, nic);
        
        //Customer c=new Customer("18041702","3258963144","oshan me","sandman","2589633","325896","male",0);
//        try {
//            boolean status=CustomerDBController.custFullReg(c);
//            if (status) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Customer Add");
//                alert.setHeaderText("Done");
//                alert.setContentText("Customer has Pre-Registered sucessfully!!");
//                alert.showAndWait();
//                fieldReseter();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Customer Add");
//                alert.setHeaderText("OPPS!!");
//                alert.setContentText("there is an error adding customer");
//                alert.showAndWait();
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
    }
    @FXML
    void bttBackClicked(ActionEvent event) {
       try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/View/NewCustomer.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(backScene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
     * Initializes the controller class.
     */
    Node backScene;
    final ToggleGroup bttGroup=new ToggleGroup();
    
    //private static Stage primaryStage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Platform.runLater(()->{
           primaryStage= (Stage) lblSourse.getScene().getWindow();
           ResizeHelper.addResizeListener(primaryStage);
        });*/
        comboVehicalClass.getItems().addAll("A","A1","B","B1","A/B/B1","A/B","A/B1");
        comboLessonType.getItems().addAll("Group","Individual");
        rdoMale.setToggleGroup(bttGroup);
        rdoMale.setSelected(true);
        rdoFemale.setToggleGroup(bttGroup);
        // TODO
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
    private void fieldReseter(){
        txtAddmissionId.setText("");
        txtFullName.setText("");
        txtInitialName.setText("");
        txtInitialName.setText("");
        txtTel3.setText("");
        txTel2.setText("");
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
//    private void initAddmissionIds(){
//        try {
//            ArrayList <String> addmssionList=CustomerDBController.getAllAddmissionIDs();
//            String [] simpleArray=new String[addmssionList.size()];
//            addmssionList.toArray(simpleArray);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//    }
}
