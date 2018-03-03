/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import edu.ijse.gdse41.drivingschool.dbcontroller.UserLoginDBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private JFXButton bttLogin;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private Label lblSourse;
    
    @FXML
    private Label lblAdminLog;
  
    @FXML
    void lblAdminLogClicked(MouseEvent event) {
        try {
            Parent adminLog = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/AdminLogin.fxml"));
            Scene adminloginScene = new Scene(adminLog);
            Stage next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            next_Stage.setScene(adminloginScene);
            next_Stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
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
    void bttMiniClicked(ActionEvent event) {
        primaryStage.setIconified(true);
    }

    @FXML
    void passKeyLogin(ActionEvent event) {
        bttloginPressed(event);
    }
    @FXML
    void bttloginPressed(ActionEvent event) {
        bttLogin.setTooltip(new Tooltip("Log-in to the System"));
        
        String username=txtUser.getText();
        String password=txtPass.getText();
        if(!username.equals("")&&!password.equals("")){
            try {
            String pass=UserLoginDBConnection.getLogPass(username, password);
            if (pass.equals(password)) {
                Parent dashBoard = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/NavigationPage.fxml"));
                Scene dashBoardScene = new Scene(dashBoard);
                Stage next_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                next_Stage.setScene(dashBoardScene);
                next_Stage.show();
                
                Notifications notificationBuilder = Notifications.create()
                        .title("Loged in")
                        .text("Successfully loged into the system")
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.BOTTOM_RIGHT)
                        .darkStyle()
                        .graphic(null);
                notificationBuilder.showInformation();
            } else {
                txtPass.setText("");
                txtUser.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("OPPS!!");
                alert.setContentText("Username or Password incorrect, please try again");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                alert.showAndWait();
            }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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

//    private boolean isValid() {
//        String username=txtUser.getText();
//        String password=txtPass.getText();
//        if(password.equals("")&&username.equals("")){
//            try {
//                String pass=UserLoginDBConnection.getLogPass(username, password);
//                if(pass.equals(password)){
//                    return true;
//                }
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return false;
//        
//    }

    private static Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttLogin.setTooltip(new Tooltip("Login to the System"));
        Platform.runLater(() -> {
            primaryStage = (Stage) lblSourse.getScene().getWindow();
            ResizeHelper.addResizeListener(primaryStage);
        });
        RequiredFieldValidator validator=new RequiredFieldValidator();
        txtUser.getValidators().add(validator);
        validator.setMessage("No Username Found");
        validator.setOpacity(0.5);
        
        
        txtUser.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if(!newValue){
                    txtUser.validate();
                }
            }
        });
    }

}
