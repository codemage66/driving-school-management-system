/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.drivingschool.dbcontroller.DLDetailsDBController;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschooldto.DLDetailsDTO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class DLDetailsController implements Initializable {

    @FXML
    private TextField txtDlNumber;

    @FXML
    private JFXDatePicker issueDate;

    @FXML
    private JFXDatePicker exDate;

    @FXML
    private ComboBox <String> comboVClass;

    @FXML
    private JFXButton bttSave;

    @FXML
    private JFXButton bttCancel;

    @FXML
    void bttCancelClicked(ActionEvent event) {
        txtDlNumber.setText("");   
    }

    @FXML
    void bttSaveClicked(ActionEvent event) {
        try {
            String addmissionid=RegController.outerAddmissionId;
            String id=IdGenarateHelper.genarateDLDId();
            String vcid=intakeClass();
            String dlNo=txtDlNumber.getText();
            String issueD=issueDate.getValue().toString();
            String exD=exDate.getValue().toString();
            String rid=RegController.outerRid;
            DLDetailsDTO dlDto=new DLDetailsDTO(id, addmissionid, vcid, dlNo, issueD, exD);
            
            boolean state=DLDetailsDBController.addDLDetails(dlDto,rid);
            
            if(state){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Update");
                alert.setHeaderText("Success");
                alert.setContentText("Successfully added Customer License Details");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Update");
                alert.setHeaderText("Error");
                alert.setContentText("Error in adding customer license details");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DLDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DLDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboVClass.getItems().setAll("A","A1","B","B1","A/B/B1","A/B","A/B1");
    }    
    private String intakeClass(){
        String Vclass="";
        switch(comboVClass.getValue()){
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
