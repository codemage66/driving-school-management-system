/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.animation.FadeUp;
import edu.ijse.gdse41.drivingschool.dbcontroller.TrialExamDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.WrittenExamDBController;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.TrialExamDTO;
import edu.ijse.gdse41.drivingschooldto.WrittenExamDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class ViewExamDatesController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane overPane;
    
    @FXML
    private TextField txtAddmissionId;

    @FXML
    private TextField txtCustName;

    @FXML
    private JFXButton bttExamLog;

    @FXML
    private Label lblExamDate;

    @FXML
    private Label lblExamTime;

    @FXML
    private Label lblExamRemain;

    @FXML
    private Label lblTrialDate;

    @FXML
    private Label lblTriTime;

    @FXML
    private Label lblTrialRemain;

    @FXML
    void bttExamLogClicked(ActionEvent event) {
        try {
            examLogScene=(Node)FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/subview/ExamLog.fxml"));
            overPane.setVisible(false);
            rootPane.getChildren().add(examLogScene);
            new FadeUp(rootPane,10);
            
        } catch (IOException ex) {
            Logger.getLogger(ViewExamDatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    private Node examLogScene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SearchCustController.refreshMainDto();
        
        if(SearchBoardController.mainDtoInSearch!=null){
            txtAddmissionId.setText(SearchBoardController.mainDtoInSearch.getAddmissionId());
            txtCustName.setText(SearchBoardController.mainDtoInSearch.getInitName());
        }
        initExam();
        initTrial();
    }    
    private void initExam(){
        if(SearchBoardController.mainDtoInSearch!=null){
            try {
            WrittenExamDTO exdto=WrittenExamDBController.getWrittenEx(SearchBoardController.mainDtoInSearch.getExdId());
            if(exdto!=null){
                lblExamDate.setText(exdto.getExDate());
                lblExamTime.setText(Integer.toString(exdto.getAttemptNo()));
                switch(exdto.getAttemptNo()){
                    case 1:
                        lblExamRemain.setText("2");
                        break;
                    case 2:
                        lblExamRemain.setText("1");
                        break;
                    default:
                        lblExamRemain.setText("None");
                        break;
                }
            }
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ViewExamDatesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    private void initTrial(){
        if(SearchBoardController.mainDtoInSearch!=null){
            try {
                TrialExamDTO trdto=TrialExamDBController.getTrialEx(SearchBoardController.mainDtoInSearch.getExdId());
                if(trdto!=null){
                    lblTrialDate.setText(trdto.getTrDate());
                    lblTriTime.setText(Integer.toString(trdto.getAttemptNo()));
                    switch(trdto.getAttemptNo()){
                    case 1:
                        lblTrialRemain.setText("2");
                        break;
                    case 2:
                        lblTrialRemain.setText("1");
                        break;
                    default:
                        lblTrialRemain.setText("None");
                        break;
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ViewExamDatesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
