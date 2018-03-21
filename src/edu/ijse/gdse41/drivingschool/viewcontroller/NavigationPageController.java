/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbcontroller.NotoficationDBController;
import edu.ijse.gdse41.drivingschool.tablemodels.NotificationTableModel;
import edu.ijse.gdse41.drivingschooldto.NotificationDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sandman
 */

public class NavigationPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private AnchorPane topAnchor;
    
    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private AnchorPane overAnchor;
    
    @FXML
    private Label lblSourse;
    
    @FXML
    private JFXButton bttNewCust;

    @FXML
    private JFXButton bttSearch;

    @FXML
    private JFXButton bttUpComing;

    @FXML
    private JFXButton bttSettings;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;
    
    @FXML
    private Button bttBack;
    
    @FXML
    private TableView notiTable;

    @FXML
    private TableColumn  tblCustName;

    @FXML
    private TableColumn  tblTel;

    @FXML
    private TableColumn tblCata;

    @FXML
    private TableColumn tblClass;

    
    
    @FXML
    void bttBackClicked(ActionEvent event) {
        try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/Login.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(backScene);
            
            Notifications notificationBuilder=Notifications.create()
                    .title("Loged Out")
                    .text("You have Loged-out from the system")
                    .darkStyle()
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.showInformation();
//            
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

    @FXML
    void bttNewCustClicked(ActionEvent event) {
        try {
            newScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/AddCustNew.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(newScene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttSearchClicked(ActionEvent event) {
        try {
            searchScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/SearchBoard.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(searchScene);
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttSettClicked(ActionEvent event) {
        try {
            settingsScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/Settings.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(settingsScene);
            
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void bttUpComingClicked(ActionEvent event) {
        try {
            searchScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/Upcomming.fxml"));
            overAnchor.setVisible(false);
            rootAnchor.getChildren().add(searchScene);
            
        } catch (IOException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Node newScene;
    Node backScene;
    private Node searchScene;
    private Node settingsScene;
    private static Stage primaryStage;
    
    private final ObservableList<NotificationTableModel> data
            = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttSearch.setTooltip(new Tooltip("Search Registered Customers"));
        bttNewCust.setTooltip(new Tooltip("Add New Customers to the System"));
        bttUpComing.setTooltip(new Tooltip("Get Reports about pending customers for exams and trial tests"));
        bttBack.setTooltip(new Tooltip("Log-Out"));

            Platform.runLater(()->{
                primaryStage= (Stage) lblSourse.getScene().getWindow();
                ResizeHelper.addResizeListener(primaryStage);
            });
        initNotificationTbl();
    }    
    
    @FXML
    void getCoordinates(MouseEvent event) {
        SearchBoardController.Distance.deltaX = - event.getX()+(-50);
        SearchBoardController.Distance.deltaY = - event.getY();
    }

    @FXML
    void setStage(MouseEvent event) {
        primaryStage.setX(event.getScreenX()+ SearchBoardController.Distance.deltaX);
        primaryStage.setY(event.getScreenY()+ SearchBoardController.Distance.deltaY);
    }

    private void initNotificationTbl(){
        try {
            tblCustName.setCellValueFactory(new PropertyValueFactory<NotificationTableModel, String>("custName"));
            tblTel.setCellValueFactory(new PropertyValueFactory<NotificationTableModel, String>("tel1"));
            tblCata.setCellValueFactory(new PropertyValueFactory<NotificationTableModel, String>("cata"));
            tblClass.setCellValueFactory(new PropertyValueFactory<NotificationTableModel, String>("lClass"));
            notiTable.setItems(data);
            ArrayList <NotificationDTO> notiArray=null;
            notiArray=NotoficationDBController.getCustNotification();
            if (notiArray != null){
                for (NotificationDTO notificationDTO : notiArray) {
                    NotificationTableModel ntd=new NotificationTableModel();
                    ntd.setCustName(notificationDTO.getCustName());
                    ntd.setTel1(notificationDTO.getTel());
                    ntd.setCata(notificationDTO.getCatName());
                    ntd.setLClass(notificationDTO.getClassType());
                    data.add(ntd);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NavigationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
