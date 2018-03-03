/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.viewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.helpers.ResizeHelper;
import edu.ijse.gdse41.drivingschool.animation.FadeUp;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class SearchBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private AnchorPane overAnchor;
    
    @FXML
    private Button bttback;

    @FXML
    private JFXButton bttNewCust;

    @FXML
    private AnchorPane sildBar;
 
    @FXML
    private Label lblSourse;
  
    @FXML
    private ListView <String> listView;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Button bttMini;

    @FXML
    private Button bttMax;

    @FXML
    private Button bttClose;

    @FXML
    private AnchorPane containtPane;
    
    @FXML
    void bttNewCustClikcked(ActionEvent event) {
        try {
            listView.getSelectionModel().select(0);
            mainDtoInSearch=null;
            containtPane.getChildren().setAll(getContent("SearchCust.fxml"));
            new FadeUp(containtPane, 50);
            
        } catch (IOException ex) {
            Logger.getLogger(SearchBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void bttBackClicked(ActionEvent event) {
        try {
            backScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/NavigationPage.fxml"));
            overAnchor.setVisible(false);
            mainPane.getChildren().add(backScene);
            mainDtoInSearch=null;
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
    private Pane pane;
    private static Stage primaryStage;
    Node loadScene;
    Node backScene;
    public static MainSearchDTO mainDtoInSearch;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttback.setTooltip(new Tooltip("Back"));
        bttNewCust.setTooltip(new Tooltip("Search New Customer"));
         Platform.runLater(()->{
           primaryStage= (Stage) lblSourse.getScene().getWindow();
           ResizeHelper.addResizeListener(primaryStage);
        });
         
         
        try {
            listView.getItems().addAll("Search Customer","Update Customer","Written Test Details","Trial Test Details","Add Results","View Exam Dates","Exam Log","Add Payments","Show Payment Details");
            listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            
            loadScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/SearchCust.fxml"));
            containtPane.getChildren().add(loadScene); 
            new FadeUp(loadScene,50);
        } catch (IOException ex) {
            Logger.getLogger(SearchBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }   
    
    
    @FXML
    void listClickedEvent(MouseEvent event) {
        try {
            switch(listView.getSelectionModel().getSelectedItem()){
                case "Search Customer":                
                    containtPane.getChildren().setAll(getContent("SearchCust.fxml"));
                    new FadeUp(containtPane, 50);
                    break;
                case "Update Customer":      
                    containtPane.getChildren().setAll(getContent("UpdateDetails.fxml")); 
                    new FadeUp(containtPane, 50);
                    break;
                case "Written Test Details":
                    containtPane.getChildren().setAll(getContent("WrittenTestDetails.fxml"));
                    new FadeUp(containtPane,50);
                    break;
                case "Trial Test Details":
                    containtPane.getChildren().setAll(getContent("TrialTestDetails.fxml"));
                    new FadeUp(containtPane,50);
                    break;
                case "Add Results":
                    containtPane.getChildren().setAll(getContent("AddExamResult.fxml"));
                    new FadeUp(containtPane,50);
                    break;
                case "View Exam Dates":
                    containtPane.getChildren().setAll(getContent("ViewExamDates.fxml"));
                    new FadeUp(containtPane,50);
                    break;
                case "Exam Log":
                    containtPane.getChildren().setAll(getContent("ExamLog.fxml"));
                    new FadeUp(containtPane,50);
                    break;
                case "Add Payments":
                    containtPane.getChildren().setAll(getContent("AddPayment.fxml"));
                    new FadeUp(containtPane, 50);
                    break;
                case "Show Payment Details":
                    containtPane.getChildren().setAll(getContent("ShowPaymentDetails.fxml"));
                    new FadeUp(containtPane,50);
                    break;
            }
        } catch (IOException ex) {
                Logger.getLogger(SearchBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public Pane getContent(String a) throws IOException{
        pane = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/"+ a));
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        return pane;
    }
    
    public static class Distance{
        static double deltaX,deltaY;
    }
    
    @FXML
    void getCoordinates(MouseEvent event) {
        Distance.deltaX = - event.getX()+(-210);
        Distance.deltaY = - event.getY();
    }

    @FXML
    void settinStage(MouseEvent event) {
        primaryStage.setX(event.getScreenX()+ Distance.deltaX);
        primaryStage.setY(event.getScreenY()+ Distance.deltaY);
    }
}
