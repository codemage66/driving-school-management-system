/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import edu.ijse.gdse41.drivingschool.dbcontroller.CustomerDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.DLDetailsDBController;
import edu.ijse.gdse41.drivingschool.helpers.FieldValidator;
import edu.ijse.gdse41.drivingschool.helpers.IdGenarateHelper;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.Customer;
import edu.ijse.gdse41.drivingschooldto.DLDetailsDTO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class UpdateDetailsController implements Initializable {
  @FXML
    private TextField txtAddmissionNum;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtInitname;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtDob;

    @FXML
    private RadioButton rdoMale;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private JFXButton bttSavePersonal;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtTel1;

    @FXML
    private TextField txtTel2;

    @FXML
    private TextField txtTel3;

    @FXML
    private JFXButton bttSaveContact;

    @FXML
    private JFXButton btttSaveLicDetails;

    @FXML
    private TextField licNumber;
    
    @FXML
    private JFXDatePicker expireDate;
    
    @FXML
    private JFXDatePicker issueDate;

    @FXML
    private ComboBox<String> liVehicelClass;

    @FXML
    void bttContactClicked(ActionEvent event) {
        if(SearchBoardController.mainDtoInSearch!=null){
            String addmission=txtAddmissionNum.getText();
            String address=txtAddress.getText();
            String tel1=txtTel1.getText();
            String tel2=txtTel2.getText();
            String tel3=txtTel3.getText();
            Customer cust=new Customer();
            cust.setAddmissionId(addmission);
            cust.setAddress(address);
            cust.setTel1(tel1);
            cust.setTel2(tel2);
            cust.setTel3(tel3);
            try {
                boolean state=CustomerDBController.updateCust(2, cust);
                getTraditionalAlert(state);               
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Update");
            alert.setHeaderText("Customer Not found!!");
            alert.setContentText("Plaese select and search an customer to update customer details");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait();       
        }
    }

    @FXML
    void bttSavePersonalClicked(ActionEvent event) {
        if(SearchBoardController.mainDtoInSearch!=null){
            String addmission=txtAddmissionNum.getText();
            String fullName=txtFullName.getText();
            String initname=txtInitname.getText();
            String nic=txtNic.getText();
            String dob=txtDob.getText();
            String gender=getGender();
            System.out.println(gender);
            
            Customer cust=new Customer();
            cust.setAddmissionId(addmission);
            cust.setCustInitialName(initname);
            cust.setCutFullName(fullName);
            cust.setDob(dob);
            cust.setGender(gender);
            cust.setNic(nic);
            try {
                boolean state=CustomerDBController.updateCust(1, cust);
                getTraditionalAlert(state);                
          } catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Update");
            alert.setHeaderText("Customer Not found!!");
            alert.setContentText("Plaese select and search an customer to update customer details");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait();
        }  
    }

    @FXML
    void btttSaveLicDetailsClicked(ActionEvent event) {
        if(dlto!=null){
            String cldid=dlto.getCldId();
            String liNo=licNumber.getText();
            String issueD=issueDate.getValue().toString();
            String exD=expireDate.getValue().toString();
            String vcid=intakeClass();

            DLDetailsDTO dlDto=new DLDetailsDTO(cldid, vcid, liNo, issueD, exD);
            try {
                boolean status=DLDetailsDBController.updateData(dlDto);
                getTraditionalAlert(status);
            } catch (ClassNotFoundException | SQLException ex) {
              Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
            try {
                String cldid=IdGenarateHelper.genarateDLDId();
                String rid=SearchBoardController.mainDtoInSearch.getrId();
                String admissionId=SearchBoardController.mainDtoInSearch.getAddmissionId();
                String liNo=licNumber.getText(); 
                String issueD=issueDate.getValue().toString();
                String exD=expireDate.getValue().toString();
                String vcid=intakeClass();
                
                DLDetailsDTO dlDto=new DLDetailsDTO(cldid, admissionId, vcid, liNo, issueD, exD);
                
                boolean state=DLDetailsDBController.addDLDetails(dlDto, rid);
                getTraditionalAlert(state);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    final ToggleGroup rdoGroup=new ToggleGroup();
    private DLDetailsDTO dlto;
    
    /**
     * Initializes the controller class.
     */
    private String vcid;
//    private DLDetailsDTO slsdto;
//    private Customer cust;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdoMale.setToggleGroup(rdoGroup);
        rdoFemale.setToggleGroup(rdoGroup);
        liVehicelClass.getItems().addAll("A","A1","B","B1","A/B/B1","A/B","A/B1");
        
        bttSavePersonal.setDisable(true);
        bttSaveContact.setDisable(true);
        btttSaveLicDetails.setDisable(true);
        
        SearchCustController.refreshMainDto();
        if(SearchBoardController.mainDtoInSearch!=null){
            try {
                String addmissionId=SearchBoardController.mainDtoInSearch.getAddmissionId();
                Customer cust=CustomerDBController.getDetailsFromAddmission(addmissionId);
                vcid=CustomerDBController.getVehicalClass(SearchBoardController.mainDtoInSearch.getrId());
                dataUpdater(cust);
                setDLDetails();
//                slsdto=RegistrationDBController.getDLDetails(addmissionId);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtNic.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("running");
            if(!newValue){
                FieldValidator.validateNIC(txtNic);
            }
        }));
       // TODO
       validateContactNodes();
       validatePersonalNodes();
       validateLicenseNodes();
    } 
    private void dataUpdater(Customer customer){
        txtFullName.setText(customer.getCutFullName());
        txtAddmissionNum.setText(customer.getAddmissionId());
        txtAddress.setText(customer.getAddress());
        txtDob.setText(customer.getDob());
        if(customer.getGender().equalsIgnoreCase("male")){
            rdoMale.setSelected(true);
        }else{
            rdoMale.setSelected(true);
        }
        System.out.println(customer.getCustInitialName());
        txtInitname.setText(customer.getCustInitialName());
        txtNic.setText(customer.getNic());
        txtTel1.setText(customer.getTel1());
        txtTel2.setText(customer.getTel2());
        txtTel3.setText(customer.getTel3());
        
    }
    private String getGender(){
        if(rdoGroup.getSelectedToggle().equals(rdoMale)){
            return "male";
        }else{
            return "female";
        }
    }
    private void setDLDetails(){
        if(SearchBoardController.mainDtoInSearch.getCldId() != null){
            try {
                dlto=DLDetailsDBController.getDLDetails(SearchBoardController.mainDtoInSearch.getCldId());
                System.out.println(SearchBoardController.mainDtoInSearch.getCldId());
                licNumber.setText(dlto.getDlNo());
                LocalDate issueD=LocalDate.parse(dlto.getIssueDate());
                LocalDate exD=LocalDate.parse(dlto.getExpierDate());
                issueDate.setValue(issueD);
                expireDate.setValue(exD);
                liVehicelClass.setValue(intakeClass(dlto.getVcId()));
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            dlto=null;
        }
    }
    private String intakeClass(){
        String Vclass="";
        switch(liVehicelClass.getValue()){
            case "A":
                Vclass="vc1";
                break;
            case "A1":
                Vclass="vc2";
                break;
            case "B":
                Vclass="vc3";
                break; 
            case "B1":
                Vclass="vc5";
                break;
            case "A/B/B1":
                Vclass="vc7";
                break; 
            case "A/B":
                Vclass="vc8";
                break;
            case "A/B1":
                Vclass="vc9";
                break;    
        }
        return Vclass;
    }
    private String intakeClass(String vcid){
        String Vclass="";
        switch(vcid){
            case "vc1":
                Vclass="A";
                break;
            case "vc2":
                Vclass="A1";
                break;
            case "vc3":
                Vclass="B";
                break; 
            case "vc5":
                Vclass="B1";
                break;
            case "vc7":
                Vclass="A/B/B1";
                break; 
            case "vc8":
                Vclass="A/B";
                break;
            case "vc9":
                Vclass="A/B1";
                break;    
        }
        return Vclass;
    }
    private void validatePersonalNodes(){ 
        //-----------Value Validate-----------------------//
        txtFullName.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        }));
        txtInitname.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        }));
        txtNic.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateNIC(txtNic);
        }));
        //----------------emptyValidate-----------------------// 
        txtFullName.setOnKeyReleased((KeyEvent event) -> {
            savePersonalEnable();
        });
        txtInitname.setOnKeyReleased((KeyEvent event) -> {
            savePersonalEnable();
        });
        txtNic.setOnKeyReleased((KeyEvent event) -> {
            savePersonalEnable();
        });
        txtDob.setOnKeyReleased((KeyEvent event) -> {
            savePersonalEnable();
        });
        
        txtTel1.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel1);
        }));
        txtTel2.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel2);
        }));
        txtTel3.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel3);
        }));
        
        
    }
    private void validateContactNodes(){
        //-----------Value Validate-----------------------//
        txtTel1.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel1);
        }));
        txtTel2.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel2);
        }));
        txtTel3.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateTelephoneNumber(txtTel3);
        }));
        //----------------emptyValidate-----------------------//
        txtAddress.setOnKeyReleased((KeyEvent event) -> {
            saveContactEnable();
        });
        txtTel1.setOnKeyReleased((KeyEvent event) -> {
            saveContactEnable();
        });
        txtTel2.setOnKeyReleased((KeyEvent event) -> {
            saveContactEnable();
        });
        txtTel3.setOnKeyReleased((KeyEvent event) -> {
            saveContactEnable();
        });  
    }
    private void validateLicenseNodes(){
        //-----------Value Validate-----------------------//
        licNumber.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyNumeric(event);
        }));
        //----------------emptyValidate-----------------------//
        licNumber.setOnKeyReleased((KeyEvent event) -> {
            saveLicenseEnable();
        });
        issueDate.setOnKeyReleased((KeyEvent event) -> {
            saveLicenseEnable();
        });
        expireDate.setOnKeyReleased((KeyEvent event) -> {
            saveLicenseEnable();
        });
        liVehicelClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
               saveLicenseEnable();
            }
        });
        
        
    }
    private void savePersonalEnable(){
        boolean act1=txtFullName.getText().trim().equals("");
        boolean act2=txtInitname.getText().trim().equals("");
        boolean act3=txtNic.getText().trim().equals("");
        boolean act4=txtDob.getText().trim().equals("");
        
        
        boolean finalE=act1 || act2 || act3 || act4;
        System.out.println(finalE);
        if(finalE){
            bttSavePersonal.setDisable(true);
            
        }else{
            bttSavePersonal.setDisable(false);
        }          
    }
    
    private void saveContactEnable(){
        boolean act1=txtAddress.getText().trim().equals("");
        boolean act2=txtTel1.getText().trim().equals("");
        boolean act3=txtTel2.getText().trim().equals("");
        boolean act4=txtTel3.getText().trim().equals("");

        boolean finalE=act1 || act2 || act3 || act4;
        System.out.println(finalE);
        if(finalE){
            bttSaveContact.setDisable(true);
            
        }else{
            bttSaveContact.setDisable(false);
        }          
    }
    private void saveLicenseEnable(){
        boolean act1=licNumber.getText().trim().equals("");
    //    boolean act2=issueDate.getValue().toString().equals("");
    //    boolean act3=expireDate.getValue().toString().equals("");
        boolean act4=liVehicelClass.getValue()==null;

        boolean finalE=act1 || act4;
        System.out.println(finalE);
        if(finalE){
            btttSaveLicDetails.setDisable(true);
            
        }else{
            btttSaveLicDetails.setDisable(false);
        }          
    }
    private void getTraditionalAlert(boolean state){
        if(state){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Update");
            alert.setHeaderText("Success");
            alert.setContentText("Successfully Updated Customer Details");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait(); 
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Update");
            alert.setHeaderText("Updating Error");
            alert.setContentText("there is an error adding customer");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png").toString()));
            alert.showAndWait();
        }
    }
}
