/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbcontroller.TrialExamDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.TrialLogDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.WrittenExamDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.WrittenLogDBController;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.ExamLogDTO;
import edu.ijse.gdse41.drivingschooldto.TrialExamDTO;
import edu.ijse.gdse41.drivingschooldto.TrialLogDTO;
import edu.ijse.gdse41.drivingschooldto.WrittenExamDTO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.SegmentedButton;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class AddExamResultController implements Initializable {
  
    
    @FXML
    private TextField txtWAddmissionId;

    @FXML
    private TextField txtWCustName;

    @FXML
    private SegmentedButton Wsegmented;

    @FXML
    private TextField txtWDate;

    @FXML
    private RadioButton rdoWpass;

    @FXML
    private RadioButton rdoWFail;

    @FXML
    private RadioButton rdoWAbsant;


    @FXML
    private TextArea txtWRemarks;

    @FXML
    private JFXButton bttWSave;

    @FXML
    private ToggleButton togW1;

    @FXML
    private ToggleButton togW2;

    @FXML
    private ToggleButton togW3;

    @FXML
    private TextField txtTAddmssionId;

    @FXML
    private TextField txtTCustName;

    @FXML
    private SegmentedButton Tsegmented;

    @FXML
    private TextField txtTDate;
    
    @FXML
    private RadioButton rdoTpass;

    @FXML
    private RadioButton rdoTFail;

    @FXML
    private RadioButton rdoTAbsant;
    

    

    @FXML
    private TextArea txtTRemarks;

    @FXML
    private JFXButton btttSave;

    @FXML
    private ToggleButton togT1;

    @FXML
    private ToggleButton togT2;

    @FXML
    private ToggleButton togT3;

    @FXML
    void bttWSaveClicked(ActionEvent event) {
        try {
            String wrlogid=WrittenLogDBController.getLogId(SearchBoardController.mainDtoInSearch.getrId(),wrdto.getExDate());
            int status=getWRdo();
            String remarks=txtWRemarks.getText();
            ExamLogDTO exlog=new ExamLogDTO(wrlogid,status, remarks);
            
            boolean state=WrittenLogDBController.updateExamLog(exlog);
            getTraditionalAlert(state);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddExamResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    void btttSaveClicked(ActionEvent event) {
        try {
            String trlogid=TrialLogDBController.getLogId(SearchBoardController.mainDtoInSearch.getrId(),trdto.getTrDate());
            String exdid=SearchBoardController.mainDtoInSearch.getExdId();
            int status=getTRdo();
            String remarks=txtTRemarks.getText();
            TrialLogDTO exlog=new TrialLogDTO(trlogid,status, remarks);
            boolean state;
            switch(getTRdo()){
                case 1:
                    state=TrialLogDBController.updateTrialLogState(exlog,exdid);
                default:
                    state=TrialLogDBController.updateTrialLog(exlog);
            }
            getTraditionalAlert(state);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddExamResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    private WrittenExamDTO wrdto;
    private TrialExamDTO trdto;
    
    final ToggleGroup rdoGroup=new ToggleGroup();
    final ToggleGroup rdoGroup2=new ToggleGroup();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bttWSave.setDisable(true);
        btttSave.setDisable(true);
        
        validateWNodes();
        
        System.out.println("initialized");
        rdoTAbsant.setToggleGroup(rdoGroup);
        rdoTpass.setToggleGroup(rdoGroup);
        rdoTFail.setToggleGroup(rdoGroup);
        
        rdoWAbsant.setToggleGroup(rdoGroup2);
        rdoWpass.setToggleGroup(rdoGroup2);
        rdoWFail.setToggleGroup(rdoGroup2);
        
        SearchCustController.refreshMainDto();
        initValues();
        
        Wsegmented.getButtons().addAll(togW1,togW2,togW3);
        Tsegmented.getButtons().addAll(togT1,togT2,togT3);
        
        
        Wsegmented.getStyleClass().add(Wsegmented.STYLE_CLASS_DARK);
        Tsegmented.getStyleClass().add(Wsegmented.STYLE_CLASS_DARK);

    }    
    private void setWTogs(){
        switch(wrdto.getAttemptNo()){
            case 1:
                togW1.setSelected(true);
                break;
            case 2:
                togW2.setSelected(true);
                break;
            case 3:
                togW3.setSelected(true);
                break;
        }
    }
    private void setTTogs(){
        switch(trdto.getAttemptNo()){
            case 1:
                togT1.setSelected(true);
                break;
            case 2:
                togT2.setSelected(true);
                break;
            case 3:
                togT3.setSelected(true);
                break;
                
        }
    }
    private int getWRdo(){
        if(rdoGroup2.getSelectedToggle().equals(rdoWpass)){
            return 1;
        }else if(rdoGroup2.equals(rdoWFail)){
            return 2;
        }else{
            return 3;
        }
    }
    private int getTRdo(){
        if(rdoGroup.getSelectedToggle().equals(rdoTpass)){
            return 1;
        }else if(rdoGroup.equals(rdoTFail)){
            return 2;
        }else{
            return 3;
        }
    }
    private void fieldReseter(){
        txtWRemarks.setText("");
        txtTRemarks.setText("");
    }
    private void validateWNodes(){ 
        //----------------emptyValidate-----------------------//
        txtTRemarks.setOnKeyReleased((KeyEvent event) -> {
            saveTEnable();
        });
        txtWRemarks.setOnKeyReleased((KeyEvent event) -> {
            saveWEnable();
        });  
    }
    private void saveWEnable(){
        boolean act1=txtWRemarks.getText().trim().equals("");
        boolean act2=txtWAddmissionId.getText().trim().equals("");
        boolean act3=txtWCustName.getText().trim().equals("");
        
        boolean finalE=act1||act2||act3;
        
        System.out.println(finalE);
        if(finalE){
            bttWSave.setDisable(true);
            
        }else{
            bttWSave.setDisable(false);
        }          
    }
    private void saveTEnable(){
        boolean act1=txtTRemarks.getText().trim().equals("");
        boolean act2=txtTAddmssionId.getText().trim().equals("");
        boolean act3=txtTCustName.getText().trim().equals("");
        
        boolean finalE=act1||act2||act3;
        
        System.out.println(finalE);
        if(finalE){
            btttSave.setDisable(true);
            
        }else{
            btttSave.setDisable(false);
        }          
    }
    private void initValues(){
        if(SearchBoardController.mainDtoInSearch!=null){
            try {
                wrdto=WrittenExamDBController.getWrittenEx(SearchBoardController.mainDtoInSearch.getExdId());
                trdto=TrialExamDBController.getTrialEx(SearchBoardController.mainDtoInSearch.getExdId());

                if(wrdto!=null){
                    txtWAddmissionId.setText(SearchBoardController.mainDtoInSearch.getAddmissionId());
                    txtWCustName.setText(SearchBoardController.mainDtoInSearch.getInitName());
                    setWTogs();
                    txtWDate.setText(wrdto.getExDate());
                }else{
                    Wsegmented.setDisable(true);
                    bttWSave.setDisable(true);
                }
                if(trdto!=null){
                    txtTAddmssionId.setText(SearchBoardController.mainDtoInSearch.getAddmissionId());
                    txtTCustName.setText(SearchBoardController.mainDtoInSearch.getInitName());  
                    setTTogs();
                    txtTDate.setText(trdto.getTrDate());
                }else{
                    Tsegmented.setDisable(true);
                    btttSave.setDisable(true);
                }  
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AddExamResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            bttWSave.setDisable(true);
            btttSave.setDisable(true);
        }
    }
    private void getTraditionalAlert(boolean state){
        if(state){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving Exam details");
            alert.setHeaderText("Success");
            alert.setContentText("Update successful ");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait(); 
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saving Exam details");
            alert.setHeaderText("Updating Error");
            alert.setContentText("There is an error while updating");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait();
        }
    }
}
