/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.drivingschool.dbcontroller.ExamDetailsDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.WrittenExamDBController;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.ExamLogDTO;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
import edu.ijse.gdse41.drivingschooldto.WrittenExamDTO;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class WrittenTestDetailsController implements Initializable {
   @FXML
    private TextField txtAddmissionId;

    @FXML
    private TextField txtCustName;

    @FXML
    private JFXDatePicker txtExamDate;
    @FXML
    private ComboBox <String> comboExamAttempt;

    @FXML
    private JFXButton bttSaveDetails;

    @FXML
    void bttSaveDetailsClicked(ActionEvent event) {
            switch(comboExamAttempt.getValue()){
                case "1":
                    if(PrimaryValidate()){
                        try {
                            String rid=SearchBoardController.mainDtoInSearch.getrId();
                            String id=IdGenarateHelper.genarateWrId();
                            String wrLogId=IdGenarateHelper.genarateExLogId();
                            String eDetailId=IdGenarateHelper.genarateExDetailId();
                            String exDate1 =txtExamDate.getValue().toString();
                            int attempt=Integer.parseInt(comboExamAttempt.getValue());
                            ///----Dto----///
                            WrittenExamDTO exdto=new WrittenExamDTO(id,exDate1, attempt);                        
                            ExamLogDTO exlog=new ExamLogDTO(wrLogId, rid, exDate1);
                            /////
                            if(secondaryValidate(attempt)){
                                // Create the custom dialog.
                                Dialog<ButtonType> dialog = new Dialog<>();
                                dialog.setTitle("Details Confirmation");
                                dialog.setHeaderText("Please make sure that, these details that you have entered are correct before saving to the System");

                                // Set the icon (must be included in the project).
                                dialog.setGraphic(new ImageView(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/Check File Filled-100.png").toString()));

                                // Set the button types.
                                ButtonType okButt = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                ButtonType edit=new ButtonType("Let me change", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().addAll(okButt,edit, ButtonType.CANCEL);

                                // Create the labels and fields.
                                GridPane grid = new GridPane();
                                grid.setHgap(10);
                                grid.setVgap(10);
                                grid.setPadding(new Insets(20, 150, 10, 10));

                                TextField ExamDate = new TextField();
                                ExamDate.setText(exDate1);
                                ExamDate.setEditable(false);

                                TextField attempt1 = new TextField();
                                attempt1.setText(Integer.toString(attempt));

                                grid.add(new Label("Exam Date:"), 0, 0);
                                grid.add(ExamDate, 1, 0);
                                grid.add(new Label("Attempt Time:"), 0, 1);
                                grid.add(attempt1, 1, 1);

                                dialog.getDialogPane().setContent(grid);
                                
                                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                                
                                Optional<ButtonType> result = dialog.showAndWait();
                                if(result.get()==okButt){
                                    System.out.println("loginButt");
                                    
                                    boolean status=WrittenExamDBController.addExamDetails(exdto, eDetailId, rid,exlog);
                                    getTraditionalAlert(status);
                                }else if(result.get()==edit){
                                    System.out.println("edit");
                                }else{
                                    dialog.close();
                                }
                            }
                            
                            
                      } catch (SQLException | ClassNotFoundException ex) {
                          Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                      }  
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Saving Exam details");
                                alert.setHeaderText("Error");
                                alert.setContentText("This customer already applyed to his/her wrritten examination"
                                        + " and this can't be the first Attempt");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                                alert.showAndWait();
                    }
                    break;
                    
                
                case "2": 
                    
                    try {
                        String wrid=ExamDetailsDBController.getID(SearchBoardController.mainDtoInSearch.getExdId(), "wrid");
                        String rid=SearchBoardController.mainDtoInSearch.getrId();
                        String wrLogId=IdGenarateHelper.genarateExLogId();
                        int attemptTime=Integer.parseInt(comboExamAttempt.getValue());
                        String exDate2=txtExamDate.getValue().toString();
                        System.out.println(wrid);
                        ///----Dto----///
                        ExamLogDTO exlog=new ExamLogDTO(wrLogId, rid, txtExamDate.getValue().toString());
                        WrittenExamDTO wrdto=new WrittenExamDTO(wrid,exDate2,attemptTime);
                        
                        if(secondaryValidate(attemptTime)){
                            getSpecialAlert(exDate2, attemptTime, exlog, wrdto);
                        }else{
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Saving Exam details");
                            alert.setHeaderText("Error");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                            alert.setContentText("These details are already entered to the system, please make sure that the information are accurate");
                            alert.showAndWait(); 
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                
                case "3":
                    try {
                        String wrid=ExamDetailsDBController.getID(SearchBoardController.mainDtoInSearch.getExdId(), "wrid");
                        String rid=SearchBoardController.mainDtoInSearch.getrId();
                        String wrLogId=IdGenarateHelper.genarateExLogId();
                        int attemptTime=Integer.parseInt(comboExamAttempt.getValue());
                        String examDate3=txtExamDate.getValue().toString();
                        
                        System.out.println(wrid);
                        ///----Dto----///
                        ExamLogDTO exlog=new ExamLogDTO(wrLogId, rid, examDate3);
                        WrittenExamDTO wrdto=new WrittenExamDTO(wrid,examDate3,attemptTime);
                        
                        if(secondaryValidate(attemptTime)){
                            getSpecialAlert(examDate3, attemptTime, exlog, wrdto);
                        }else{
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Saving Exam details");
                            alert.setHeaderText("Error");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                            alert.setContentText("These details are already entered to the system, please make sure that the information are accurate");
                            alert.showAndWait(); 
                        }

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                     
           }
           
    }
    private MainSearchDTO mainDto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bttSaveDetails.setDisable(true);
        
        validateExamDetailNodes();
        comboExamAttempt.getItems().addAll("1","2","3");
        
        if(SearchBoardController.mainDtoInSearch!=null){
            mainDto=SearchBoardController.mainDtoInSearch;
            txtAddmissionId.setText(mainDto.getAddmissionId());
            txtCustName.setText(mainDto.getInitName());
        }else{
            mainDto=null;
        }
    // TODO
    }  
    //---primaraly looking, if there is already a 1 attempt detail----//
    private boolean PrimaryValidate(){
       try {
            String wrid1=ExamDetailsDBController.getID(mainDto.getExdId(), "wrid");
            if(wrid1!=null){
               return false;
            }
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
       }
       return true;
    }
    
    //---deciding if the entered attempt time already entered or not----//
    private boolean secondaryValidate(int attempt){
            try {
                int result=WrittenExamDBController.getAttemptForValidate(mainDto.getExdId());
                System.out.println(result);
                if(result==0&& attempt==1){
                    return true;
                }else if(attempt==(result+1)){
                    return true;
                }
                 
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
    
    private void validateExamDetailNodes(){
        //----------------emptyValidate-----------------------//
        txtAddmissionId.setOnKeyReleased((KeyEvent event) -> {
            savebuttonEnable();
        });
        txtCustName.setOnKeyReleased((KeyEvent event) -> {
            savebuttonEnable();
        });
        txtExamDate.setOnKeyReleased((KeyEvent event) -> {
            savebuttonEnable();
        });
        comboExamAttempt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
               savebuttonEnable();
            }
        });
    }
    private void savebuttonEnable(){
        boolean act1=txtAddmissionId.getText().trim().equals("");
        boolean act2=txtCustName.getText().trim().equals("");
//        boolean act3=txtExamDate.getValue().toString().equals("");
        boolean act4=comboExamAttempt.getValue().equals("");

        boolean finalE=act1 || act2 || act4;
        System.out.println(finalE);
        if(finalE){
            bttSaveDetails.setDisable(true);
            
        }else{
            bttSaveDetails.setDisable(false);
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
    private void getSpecialAlert(String date,int attemptT,ExamLogDTO exlog,WrittenExamDTO wrdto){
        // Create the custom dialog.
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Details Confirmation");
        dialog.setHeaderText("Please make sure that, these details that you have entered are correct before saving to the System");

        // Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/Check File Filled-100.png").toString()));

        // Set the button types.
        ButtonType okButt = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType edit=new ButtonType("Let me Change", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButt,edit, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField ExamDate = new TextField();
        ExamDate.setText(date);
        ExamDate.setEditable(false);
                            
        TextField attempt = new TextField();
        attempt.setText(Integer.toString(attemptT));

        grid.add(new Label("Exam Date:"), 0, 0);
        grid.add(ExamDate, 1, 0);
        grid.add(new Label("Attempt Time:"), 0, 1);
        grid.add(attempt, 1, 1);

        dialog.getDialogPane().setContent(grid);
        
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
        
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.get()==okButt){
            try {
            System.out.println("loginButt");
            boolean status=WrittenExamDBController.updatWrEx(wrdto,exlog);
            getTraditionalAlert(status);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(result.get()==edit){
                System.out.println("edit");
        }else{
            fieldReseter();
            dialog.close();
        }                        
    }
    private void fieldReseter(){
        txtExamDate.setValue(null);
        comboExamAttempt.setValue("Exam Attempt");
    }
}
