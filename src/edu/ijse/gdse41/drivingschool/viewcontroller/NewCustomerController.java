/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class NewCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private AnchorPane overAnchor;
    
    @FXML
    private JFXButton bttPreReg;

    @FXML
    private JFXButton bttReg;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;
    
    @FXML
    private Label lblRoot;

        
    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0); 
                
    }

    @FXML
    void bttMiniClicked(ActionEvent event) {
        primaryStage.setIconified(true);
    }

    @FXML
    void bttPreRegClicked(ActionEvent event) {
        try {
            Scene1=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/PreRegistration.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(Scene1);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttRegClicked(ActionEvent event) {
       try {
            Scene2=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/Registration.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(Scene2);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    void bttmaxClicked(ActionEvent event) {
        if(primaryStage.isFullScreen()){
            primaryStage.setFullScreen(false);
        }else{
            primaryStage.setFullScreen(true);
        }
    }
    @FXML
    void bttbackClicked(ActionEvent event) {
       try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/NavigationPage.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(backScene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   Node Scene1;
   Node Scene2;
   Node backScene;
   private static Stage primaryStage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Platform.runLater(()->{
           primaryStage= (Stage) lblRoot.getScene().getWindow();
           ResizeHelper.addResizeListener(primaryStage);
       });
        // TODO
    }    
    
}
