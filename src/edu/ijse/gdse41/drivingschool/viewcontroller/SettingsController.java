/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class SettingsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane overPane;

    @FXML
    private JFXButton bttBackup;

    @FXML
    private JFXButton bttRestore;
    
    @FXML
    private Button bttBack;
    
    @FXML
    void bttBackupClicked(ActionEvent event) {

    }

    @FXML
    void bttRestore(ActionEvent event) {

    }
     @FXML
    void bttBackClicked(ActionEvent event) {
        try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/NavigationPage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        overPane.setVisible(false);
        rootPane.getChildren().add(backScene);
    }
    private Node backScene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
