/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbcontroller.UsersDBController;
import edu.ijse.gdse41.drivingschool.helpers.FieldValidator;
import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class AdminAdduserController implements Initializable {
    @FXML
    private AnchorPane topPane;
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane overPane;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPass;

    @FXML
    private PasswordField txtconPass;

    @FXML
    private JFXButton bttSave;

    @FXML
    private JFXButton bttCancel;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private Button bttBack;

    @FXML
    private Label lblSource;
    
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
    void nameTyped(KeyEvent event) {

    }

    @FXML
    void usernameTyped(KeyEvent event) {

    }

    @FXML
    void bttBackClicked(ActionEvent event) {
        try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/AdminLogin.fxml"));
            overPane.setVisible(false);
            rootPane.getChildren().add(backScene);
        } catch (IOException ex) {
            Logger.getLogger(AdminAdduserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttCancelClicked(ActionEvent event) {
        txtName.setText("");
        txtPass.setText("");
        txtUsername.setText("");
        txtconPass.setText("");
    }

    @FXML
    void bttCloseClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void bttMaxClicked(ActionEvent event) {
        if (primaryStage.isFullScreen()){
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
    void bttSaveClicked(ActionEvent event) {
        String pass;
        if(emptyValidater()){
            if((txtPass.getText()).equals(txtconPass.getText())){
                pass=txtPass.getText();
                try {
                    boolean state=UsersDBController.addUser(txtUsername.getText(), pass, txtName.getText());
                    if(state){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Customer Update");
                        alert.setHeaderText("Success");
                        alert.setContentText("Successfully added user");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                        alert.showAndWait();
                    }else{
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Customer Adding");
                        alert.setHeaderText("OPPS!!");
                        alert.setContentText("there is an error adding user");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                        alert.showAndWait();
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(AdminAdduserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("User Adding");
                        alert.setHeaderText("OPPS!!");
                        alert.setContentText("passwords dosen't match");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                        alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("User Adding");
                        alert.setHeaderText("OPPS!!");
                        alert.setContentText("Enter the appropriate data to complete the process");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                        alert.showAndWait();
        }
        
        
    }

    private Node backScene;
    private Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater(() -> {
            primaryStage = (Stage) lblSource.getScene().getWindow();
            ResizeHelper.addResizeListener(primaryStage);
        });
         
        
        
        txtUsername.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                if(txtUsername.getText().equals("")){
                    txtUsername.setId("sandmanErrorText");
                }
            }
         });
        txtName.requestFocus();
        validate();
    }    

    private void validate(){
        txtName.setOnKeyTyped((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        });
        txtUsername.setOnKeyTyped((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        });
    }
    private boolean emptyValidater(){
        if(txtName.getText()!=""&&txtPass.getText()!=""&&txtUsername.getText()!=""&&txtconPass.getText()!=""){
            return false;
        }else{
            return true;
        }
    }
     public static class Distance{
        static double deltaX,deltaY;
    }
}
