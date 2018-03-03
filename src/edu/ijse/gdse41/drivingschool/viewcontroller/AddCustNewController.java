/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
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
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class AddCustNewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane topAnchor;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane overPane;

    @FXML
    private JFXButton bttPreReg;

    @FXML
    private JFXButton bttReg;

    @FXML
    private AnchorPane containtPane;

    @FXML
    private Label lblPrePoint;

    @FXML
    private Label lblRegPoint;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private Button bttBack;
    
    @FXML
    private Label lblSourse;
    
    @FXML
    void getCoordinates(MouseEvent event) {
        Distance.deltaX = - event.getX()+(-50);
        Distance.deltaY = - event.getY();
    }

    @FXML
    void setStage(MouseEvent event) {
        primaryStage.setX(event.getScreenX()+ Distance.deltaX);
        primaryStage.setY(event.getScreenY()+ Distance.deltaY);
    }
    
    @FXML
    void bttBackClicked(ActionEvent event) {
        try {
            backscene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/NavigationPage.fxml"));
            overPane.setVisible(false);
            rootPane.getChildren().add(backscene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void bttMaxClicked(ActionEvent event) {
        if(primaryStage.isFullScreen()){
            primaryStage.setFullScreen(false);
        }else{
            primaryStage.setFullScreen(true);
        }
    }

    @FXML
    void bttMiniClicked(ActionEvent event) {
        primaryStage.setIconified(true);
    }
    private Node loadScene1;
    private Node loadScene2;
    
    
    @FXML
    void bttPreRegClicked(ActionEvent event) {
       try { 
           loadScene1=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/pre_reg.fxml"));
           containtPane.getChildren().removeAll(loadScene2);
           containtPane.getChildren().add(loadScene1);
           lblPrePoint.setVisible(true);
           lblRegPoint.setVisible(false);
       } catch (IOException ex) {
           Logger.getLogger(AddCustNewController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    void bttRegClicked(ActionEvent event) {
         try {
           loadScene2=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/reg.fxml")); 
           containtPane.getChildren().removeAll(loadScene1);
           containtPane.getChildren().add(loadScene2);
           lblPrePoint.setVisible(false);
           lblRegPoint.setVisible(true);
           
          
       } catch (IOException ex) {
           Logger.getLogger(AddCustNewController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    private Node backscene;
    private  Stage primaryStage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttBack.setTooltip(new Tooltip("Back"));
        bttReg.setTooltip(new Tooltip("Customer Registration"));
        bttPreReg.setTooltip(new Tooltip("Customer Pre-Registration"));
        
        Platform.runLater(()->{
           primaryStage= (Stage) lblSourse.getScene().getWindow();
           ResizeHelper.addResizeListener(primaryStage);
        });
        
        try {
           loadScene1=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/pre_reg.fxml")); 
           containtPane.getChildren().add(loadScene1);
           lblRegPoint.setVisible(false);
           
       } catch (IOException ex) {
           Logger.getLogger(AddCustNewController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    } 
    public static class Distance{
        static double deltaX,deltaY;
    }
}
