/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.gdse41.drivingschool.dbcontroller.UserLoginDBConnection;
import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class AdminLoginController implements Initializable {


     @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane overPane;
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton bttLogin;

    @FXML
    private Button bttmini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private Button bttBack;

    @FXML
    private Label lblSourse;
    
    @FXML
    void passLoginKey(ActionEvent event) {
        bttLoginClicked(event);
    }
    
    @FXML
    void bttBackClicked(ActionEvent event) {
         try {
             backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/Login.fxml"));
             overPane.setVisible(false);
             rootPane.getChildren().add(backScene);
         } catch (IOException ex) {
             Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void bttLoginClicked(ActionEvent event) {
        String user=txtUsername.getText();
        String pass=txtPassword.getText();
        if(!user.equals("")&&!pass.equals("")){
            try {
            String getPass=UserLoginDBConnection.getLogPass(user, pass);
                if(getPass.equals(pass)){
                    Parent dashBoard = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/adminAdduser.fxml"));
                    Scene dashBoardScene = new Scene(dashBoard);
                    Stage next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    next_Stage.setScene(dashBoardScene);
                    next_Stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Error");
                    alert.setHeaderText("OPPS!!");
                    alert.setContentText("Username or Password incorrect, please try again");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    alert.showAndWait();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Error");
                    alert.setHeaderText("OPPS!!");
                    alert.setContentText("Username or Password can't be empty!!");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    alert.showAndWait();
        }
        
        
    }

    @FXML
    void bttMaxClicked(ActionEvent event) {
        if (primaryStage.isFullScreen()) {
            primaryStage.setFullScreen(false);
        } else {
            primaryStage.setFullScreen(true);
        }
    }

    @FXML
    void bttminiClicked(ActionEvent event) {
        primaryStage.setIconified(true);
    }
    
    
    private static Stage primaryStage;
    private Node backScene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            primaryStage = (Stage) lblSourse.getScene().getWindow();
            ResizeHelper.addResizeListener(primaryStage);
        });
    }    
    
}
