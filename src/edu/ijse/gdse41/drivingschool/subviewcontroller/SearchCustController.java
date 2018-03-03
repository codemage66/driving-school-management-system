/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.subviewcontroller;

import com.jfoenix.controls.JFXButton;
import edu.ijse.gdse41.drivingschool.dbcontroller.CustomerDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.DashBoardDBController;
import edu.ijse.gdse41.drivingschool.helpers.FieldValidator;
import edu.ijse.gdse41.drivingschool.viewcontroller.SearchBoardController;
import edu.ijse.gdse41.drivingschooldto.Customer;
import edu.ijse.gdse41.drivingschooldto.MainSearchDTO;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Sandman
 */
public class SearchCustController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtAddmission;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtCustName;

    @FXML
    private JFXButton bttSearch;

    @FXML
    private ImageView image;

    @FXML
    private TextField txtFillName;

    @FXML
    private TextField txtFillAddress;

    @FXML
    private TextField txtFillLicenseType;

    @FXML
    private TextField txtFillVehical;

    @FXML
    private TextField txtFillTel;

    @FXML
    private TextField txtFillAmount;

    @FXML
    private TextField txtDuePay;
    
    @FXML
    private JFXButton bttGetDetail;
    
    @FXML
    void txtAddmissionClicked(ActionEvent event) {

    }
    
    @FXML
    void bttGetDetailClicked(ActionEvent event) {
        if(SearchBoardController.mainDtoInSearch!=null){
            try {
                MainSearchDTO mainDto=SearchBoardController.mainDtoInSearch;
                Customer cust=CustomerDBController.getDetailsFromAddmission(mainDto.getAddmissionId());
                HashMap<String,Object> repoMap=new HashMap<>();
                repoMap.put("AddmissionId", cust.getAddmissionId());
                repoMap.put("CustFullName", cust.getCutFullName());
                repoMap.put("CustInitName", cust.getCustInitialName());
                repoMap.put("dob", cust.getDob());
                repoMap.put("Gender", cust.getGender());
                repoMap.put("Nic", cust.getNic());
                repoMap.put("Address", cust.getAddress());
                repoMap.put("Tel1", cust.getTel1());
                repoMap.put("Tel2", cust.getTel2());
                repoMap.put("Tel3", cust.getTel3());
                repoMap.put("VehicleClass", txtFillVehical.getText());
                repoMap.put("transType", cust.getTransType());
                repoMap.put("lessonType", cust.getLessonType());
                repoMap.put("date", date);
                
                viewJasper(SearchCustController.class.getResourceAsStream("/edu/ijse/gdse41/drivingschool/reports/CustDetailsReport.jasper"), repoMap);
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(SearchCustController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /*($F{status} ==1)? "Passed": (
	($F{status}==2)? "Failed" : "Absent"
)*/
    @FXML
    void bttSearchClicked(ActionEvent event) {
        System.out.println(idChecker);
        switch (idChecker){
            case 1:
                idType="addmissionId";
                outerId=txtAddmission.getText();
                searchRunner(txtAddmission.getText());
                break;
            case 2:
                idType="nic";
                outerId=txtAddmission.getText();
                searchRunner(txtNic.getText());
                break;
            case 3:
                idType="custName";
                outerId=txtAddmission.getText();
                searchRunner(txtCustName.getText());
                break; 
        }
        
         
    }
    @FXML
    void keyEntered(KeyEvent event) {
        txtAddmission.textProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);

            if(("").equalsIgnoreCase(newValue)){
                txtNic.setDisable(false);
                txtCustName.setDisable(false);               
            }else{
                idChecker=1;
                txtNic.setDisable(true);
                txtCustName.setDisable(true);
            }
        });
    }
    
    @FXML
    void nameEntered(KeyEvent event) {
        txtCustName.textProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            if(("").equalsIgnoreCase(newValue)){
                txtAddmission.setDisable(false);
                txtNic.setDisable(false);               
            }else{
                idChecker=3;
                txtAddmission.setDisable(true);
                txtNic.setDisable(true);
            }
        });
    }

    @FXML
    void nicEntered(KeyEvent event) {
        txtNic.textProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            if(("").equalsIgnoreCase(newValue)){
                txtAddmission.setDisable(false);
                txtCustName.setDisable(false);               
            }else{
                idChecker=2;
                txtAddmission.setDisable(true);
                txtCustName.setDisable(true);
            }
        });
    }
    private int idChecker=0;
    private static String idType="";
    public static String addmissionId;
    private String[]simpleArray;
    private static String outerId="";
    private String date;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initAddmissionIds();
        validateNodes();
        
        date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        
        if(SearchBoardController.mainDtoInSearch!=null){
            intitDefaultData();
            bttGetDetail.setDisable(false);
        }else{
            resetFields();
            bttGetDetail.setDisable(true);
        }     
// TODO
    }    
  
    private void searchRunner(String id){

            try {
                MainSearchDTO mainSearch= DashBoardDBController.getMainSearch(idType, id);
                SearchBoardController.mainDtoInSearch=mainSearch;
                if(mainSearch != null){
                    txtFillName.setText(mainSearch.getInitName());
                    txtFillAddress.setText(mainSearch.getAddress());
                    txtFillLicenseType.setText(intakeLicenseId(mainSearch.getLcId()));
                    txtFillVehical.setText(mainSearch.getVcId());
                    txtFillTel.setText(Integer.toString(mainSearch.getTel1()));
                    txtFillAmount.setText(Double.toString(mainSearch.getCourseAmount()));
                    txtDuePay.setText(Double.toString(mainSearch.getBalance()));
                    txtFillVehical.setText(mainSearch.getVcId());
                    
                    bttGetDetail.setDisable(false);
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Customer");
                    alert.setHeaderText("Somthing went wrong");
                    alert.setContentText("Please make sure that the customer is Registeed or not!! ");
                    alert.showAndWait();               
                }
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SearchCustController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
    private String intakeLicenseId(String cata){
        String lcId="";
        switch(cata){
            case "lc1":
                lcId="New";
                break;
            case "lc2":
                lcId="Extend";
                break;
        }
        return lcId;
    }
    private void initAddmissionIds(){
        try {
            ArrayList<String> addmissionIdList=CustomerDBController.getRegisteredAddmissionIds();
            simpleArray=new String[addmissionIdList.size()];
            addmissionIdList.toArray(simpleArray);
            TextFields.bindAutoCompletion(txtAddmission, simpleArray);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean admissionIdValidator(){
        if (Arrays.asList(simpleArray).contains(txtAddmission.getText())){
            return true;
        }else{
            return false;
        }
    }
    private void intitDefaultData(){
        MainSearchDTO tempMain=SearchBoardController.mainDtoInSearch;
        txtAddmission.setText(tempMain.getAddmissionId());
        txtFillAddress.setText(tempMain.getAddress());
        txtFillAmount.setText(Double.toString(tempMain.getCourseAmount()));
        txtFillLicenseType.setText(intakeLicenseId(tempMain.getLcId()));
        txtFillName.setText(tempMain.getInitName());
        txtFillTel.setText(Integer.toString(tempMain.getTel1()));
        txtFillVehical.setText(tempMain.getVcId());
        txtDuePay.setText(Double.toString(tempMain.getBalance()));
    }
    public void resetFields(){
        txtAddmission.setText("");
        txtCustName.setText("");
        txtDuePay.setText("");
        txtFillAddress.setText("");
        txtFillAmount.setText("");
        txtFillLicenseType.setText("");
        txtFillName.setText("");
        txtFillTel.setText("");
        txtNic.setText("");
        txtFillVehical.setText("");
        
    }
    public static void refreshMainDto(){
        if(!idType.equals("")&&!outerId.equals("")){
            try {
            MainSearchDTO mainSearch= DashBoardDBController.getMainSearch(idType, outerId);
            SearchBoardController.mainDtoInSearch=mainSearch;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SearchCustController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private void viewJasper(InputStream st,HashMap<String,Object>map){
        try {
            JasperReport loadReport=(JasperReport) JRLoader.loadObject(st);
            JasperPrint printJasper=JasperFillManager.fillReport(loadReport, map, new JREmptyDataSource());
            JasperViewer.viewReport(printJasper,false);
        } catch (JRException ex) {
            Logger.getLogger(Pre_paymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void validateNodes(){
        //-----------Value Validate-----------------------//
        txtAddmission.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyNumeric(event);
        }));
        
        txtCustName.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.onlyAlpha(event);
        }));
        
        txtNic.setOnKeyTyped(((KeyEvent event) -> {
            FieldValidator.validateNIC(txtNic);
        }));
 
    }
}
