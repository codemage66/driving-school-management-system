/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class UpdatePersonalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField xtFullName;

    @FXML
    private TextField txtInitialName;

    @FXML
    private TextField txtNIC;

    @FXML
    private JFXCheckBox checkMale;

    @FXML
    private JFXCheckBox chechFemale;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField tel1;

    @FXML
    private TextField tel2;

    @FXML
    private TextField txtLicenstype;

    @FXML
    private TextField txtVehicalClass;

    @FXML
    private TextField txtleession;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtBalance;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
