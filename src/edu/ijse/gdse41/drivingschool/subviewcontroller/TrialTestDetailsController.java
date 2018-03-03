/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.drivingschool.dbcontroller.ExamDetailsDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.TrialExamDBController;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
import edu.ijse.gdse41.drivingschooldto.TrialExamDTO;
import edu.ijse.gdse41.drivingschooldto.TrialLogDTO;
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
public class TrialTestDetailsController implements Initializable {
    @FXML
    private TextField txtAddmissionId;

    @FXML
    private TextField txtCustName;

    @FXML
    private JFXDatePicker txtTrialDate;
    @FXML
    private ComboBox<String> comboTrialAttempt;

    @FXML
    private JFXButton bttSaveDetails;

    @FXML
    void bttSaveDetailsClicked(ActionEvent event) {
        switch(comboTrialAttempt.getValue()){
            case "1":
                if(PrimaryValidate()){
                    try {
                        String exidi=SearchBoardController.mainDtoInSearch.getExdId();
                        String id=IdGenarateHelper.genarateTRId();
                        String date=txtTrialDate.getValue().toString();
                        int attempt=Integer.parseInt(comboTrialAttempt.getValue());
                        String trLogid=IdGenarateHelper.genaratTrLogId();
                        String rid=SearchBoardController.mainDtoInSearch.getrId();
                        ///----Dto---///
                        TrialLogDTO relog=new TrialLogDTO(trLogid,rid,date);
                        TrialExamDTO trdto=new TrialExamDTO(id, date, attempt);
                        //////
                        if(secondaryValidate(attempt)){
                            // Create the custom dialog.
                                Dialog<ButtonType> dialog = new Dialog<>();
                                dialog.setTitle("Details Confirmation");
                                dialog.setHeaderText("Please Make Sure That,"
                                                    + " These Details That You Have Entered Are Correct Before Saving "
                                                    + "To The System");

                                // Set the icon (must be included in the project).
                                //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

                                // Set the button types.
                                ButtonType okButt = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                ButtonType edit=new ButtonType("Let Me Edit", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().addAll(okButt,edit, ButtonType.CANCEL);

                                // Create the username and password labels and fields.
                                GridPane grid = new GridPane();
                                grid.setHgap(10);
                                grid.setVgap(10);
                                grid.setPadding(new Insets(20, 150, 10, 10));

                                TextField ExamDate = new TextField();
                                ExamDate.setText(date);
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
                                    
                                    boolean status=TrialExamDBController.addTrialDetails(trdto, exidi,relog);
                                    getTraditionalAlert(status);
                                }else if(result.get()==edit){
                                    System.out.println("edit");
                                }else{
                                    dialog.close();
                                }
                        }else{
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Saving Trial Details");
                            alert.setHeaderText("Error");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                            alert.setContentText("These Details Are Already Entered To The System, Please Make Sure That The Information Are Accurate");
                            alert.showAndWait(); 
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(TrialTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Saving Trial details");
                    alert.setHeaderText("Error");
                    alert.setContentText("This Customer is Already Applyed to His/Her Wrritten Examination"
                                        + "and This Can't be the First Attempt");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                    alert.showAndWait();
                    }
                    break;
                    
            case "2":            
                    try {
                        String trid=ExamDetailsDBController.getID(SearchBoardController.mainDtoInSearch.getExdId(), "trid");
                        System.out.println(trid);
                        /////
                        String trLogid=IdGenarateHelper.genaratTrLogId();
                        String rid=SearchBoardController.mainDtoInSearch.getrId();
                        /////
                        String trDate2=txtTrialDate.getValue().toString();
                        int attempt2=Integer.parseInt(comboTrialAttempt.getValue());
                        ///----Dto---///
                        TrialLogDTO relog=new TrialLogDTO(trLogid,rid,trDate2);
                        TrialExamDTO trdto=new TrialExamDTO(trid,trDate2,attempt2);
                        
                        if(secondaryValidate(attempt2)){
                            getSpecialAlert(trDate2, attempt2, relog, trdto);
                        }else{
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Saving Trial Details");
                            alert.setHeaderText("Error");
                            alert.setContentText("These Details Are Already Entered To The System, Please Make Sure That The Information Are Accurate");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                            alert.showAndWait(); 
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(WrittenTestDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                
            case "3":
                try {
                    String trid=ExamDetailsDBController.getID(SearchBoardController.mainDtoInSearch.getExdId(), "trid");
                    System.out.println(trid);
                    ////
                    String trLogid=IdGenarateHelper.genaratTrLogId();
                    String rid=mainDto.getrId();
                    ////
                    String trDate3=txtTrialDate.getValue().toString();
                    int attempt3=Integer.parseInt(comboTrialAttempt.getValue());
                    ///----Dto---///
                    TrialLogDTO trlog=new TrialLogDTO(trLogid, rid, trDate3);
                    TrialExamDTO trdto=new TrialExamDTO(trid, trDate3,attempt3);
                    ////
                    if(secondaryValidate(attempt3)){
                        getSpecialAlert(trDate3, attempt3, trlog, trdto);        
                    }else{
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Saving Trial Details");
                            alert.setHeaderText("Error");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
                            alert.setContentText("These Details Are Already Entered To The System, Please Make Sure That The Information Are Accurate");
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
        validateTrialDetailNodes();
        comboTrialAttempt.getItems().addAll("1","2","3");
        
        if(SearchBoardController.mainDtoInSearch!=null){
            mainDto=SearchBoardController.mainDtoInSearch;
            txtAddmissionId.setText(mainDto.getAddmissionId());
            txtCustName.setText(mainDto.getInitName());
        }else{
            mainDto=null;
        }
        // TODO
    }
    
    private boolean PrimaryValidate(){        
       try {
            String trid=ExamDetailsDBController.getID(SearchBoardController.mainDtoInSearch.getExdId(), "trid");
            if(trid!=null){
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
                int result=TrialExamDBController.getAttemptForValidate(mainDto.getExdId());
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
    
    private void validateTrialDetailNodes(){
        //----------------emptyValidate-----------------------//
        txtAddmissionId.setOnKeyReleased((KeyEvent event) -> {
            savebuttonEnable();
        });
        txtCustName.setOnKeyReleased((KeyEvent event) -> {
            savebuttonEnable();
        });
        txtTrialDate.setOnKeyReleased((KeyEvent event) -> {
            savebuttonEnable();
        });
        comboTrialAttempt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
               savebuttonEnable();
            }
        });
    }
    private void savebuttonEnable(){
        boolean act1=txtAddmissionId.getText().trim().equals("");
        boolean act2=txtCustName.getText().trim().equals("");
        //boolean act3=txtTrialDate.getValue().toString().trim().equals("");
        boolean act4=comboTrialAttempt.getValue().equals("");

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
            alert.setContentText("Update Successful ");
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
    private void getSpecialAlert(String date,int attemptT,TrialLogDTO trlog,TrialExamDTO trdto){
        // Create the custom dialog.
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Details Confirmation");
        dialog.setHeaderText("Please Make Sure That,"
                            + " These Details That You Have Entered Are Correct Before Saving "
                            + "To The System");

        // Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/Check File Filled-100.png").toString()));

        // Set the button types.
        ButtonType okButt = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType edit=new ButtonType("Let Me Change", ButtonBar.ButtonData.OK_DONE);
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
            boolean status=TrialExamDBController.updatTrEx(trdto,trlog);
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
        txtTrialDate.setValue(null);
        comboTrialAttempt.setValue("Trial Attempt");
    }
}
