package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalTime;
public class Interpreter_Request {


    @FXML
    private Button SubmitBtn;
    @FXML
    private Button CancelBtn;
    @FXML
    private Button CurrentTimeBtn;
    @FXML
    private JFXTimePicker TimeTextField;
    @FXML
    private JFXComboBox LanguageDropDown;
    @FXML
    private JFXComboBox SeverityDropDown;
    @FXML
    private JFXTextField DestinationField;
    @FXML
    private Button btnSelectLocation;

    private ManageController manager;
    private TestingController testingController;
    private NodeData nodeData;
    private String userName;
    private String type = "interpreter";
//    public void buttonHandler(ActionEvent e){
//        System.out.println("a button was clicked");
//        if(e.getSource() == SubmitBtn){
//            succesfulLogin = manager.login(txtUser.getText(), txtPass.getText());
//            if(succesfulLogin) {
//                System.out.println("Submitting Request");
//                SubmitBtn.getScene().getWindow().hide();
//            }
//            else{
//                System.out.println("Request Failed");
//                txtPass.setText("");
//                txtUser.setText("");
//            }
//        }
//        else{
//            SubmitBtn.getScene().getWindow().hide();
//        }
//    }

    public void SetCurrentTime(){
        TimeTextField.setValue(LocalTime.now());
    }
    public void SeverityDropDownHandler(){}
    public void LanguageDropDownHandler(){}
    public void DestinationHandler(){}

    public void SubmitHandler(){
        System.out.println("Interpreter Request:");
        System.out.println("Langauge: " + LanguageDropDown.getSelectionModel().getSelectedItem());
        System.out.println("Severity: " + SeverityDropDown.getSelectionModel().getSelectedItem());
        System.out.println("Location: " + DestinationField.getText() );
        System.out.println("Time: "     + TimeTextField.getValue());
        manager.createInterpreterRequest(userName,type,nodeData.getNodeID(),null,"",Integer.parseInt(SeverityDropDown.getSelectionModel().getSelectedItem().toString())
                ,LanguageDropDown.getSelectionModel().getSelectedItem().toString(),TimeTextField.getValue().toString());
        Stage stage = (Stage) SubmitBtn.getScene().getWindow();
        stage.close();
    }

    public void backButtonHandler() {
        Stage stage = (Stage) CancelBtn.getScene().getWindow();
        stage.close();
    }

    void initController(ManageController manageController,TestingController testingController,String userName){
        this.manager = manageController;
        this.testingController = testingController;
        this.userName = userName;
    }
    void initController(ManageController manageController,TestingController testingController, NodeData nodeData,String userName){
        this.manager = manageController;
        this.userName = userName;
        this.testingController = testingController;
        this.nodeData = nodeData;
        DestinationField.setText(nodeData.getLocation().toString());
    }
    public void selectLocationHandler(){
        testingController.setSelectingLocationState(type);
        Stage stage = (Stage) btnSelectLocation.getScene().getWindow();
        stage.close();
    }
    // End of class
}
