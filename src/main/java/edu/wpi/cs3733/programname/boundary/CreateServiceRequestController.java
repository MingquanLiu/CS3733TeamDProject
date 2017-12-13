package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Constants;
import edu.wpi.cs3733.programname.commondata.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.time.LocalTime;
import java.util.List;

public class CreateServiceRequestController {

    @FXML
    private JFXComboBox<?> comboSeverity;

    @FXML
    private JFXComboBox<?> comboMaintenanceType;

    @FXML
    private JFXComboBox<?> comboTransportType;

    @FXML
    private JFXComboBox<?> comboLanguages;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXTimePicker timePicker;

    @FXML
    private JFXButton btnCurrentTime;

    @FXML
    private Label lblNewRequestTitle;

    @FXML
    private Label lblReserveTime;

    @FXML
    private JFXButton btnNewInterpreter;

    @FXML
    private JFXButton btnNewMaintenance;

    @FXML
    private JFXButton btnNewTransportation;

    @FXML
    private JFXTextField txtSelectLocation;

    @FXML
    private JFXTextField txtTransportDestination;

    @FXML
    private JFXTextArea txtDescription;

    ManageController manager;
    String submissionType;
    Employee loggedIn;
    List<String> allLongNames;

    private AutoCompletionBinding<String> autoCompletionBindingLocation;
    private AutoCompletionBinding<String> autoCompletionBindingTransportDestination;

    public void initManager(ManageController manage, String requestType, Employee loggedIn) {
        manager = manage;
        this.loggedIn = loggedIn;
        allLongNames = manager.fuzzyQueryNodesByLongName("");
        autoCompletionBindingLocation = TextFields.bindAutoCompletion(txtSelectLocation, allLongNames);
        autoCompletionBindingTransportDestination = TextFields.bindAutoCompletion(txtTransportDestination,allLongNames);
        if (requestType == Constants.TRANSPORTATION_REQUEST) {
            initializeTransportation();
        } else if (requestType == Constants.MAINTENANCE_REQUEST) {
            initializeMaintenance();
        } else {
            initializeInterpreter();
        }
    }

    public void initializeInterpreter() {
        comboMaintenanceType.setVisible(false);
        comboTransportType.setVisible(false);
        comboLanguages.setVisible(true);
        txtTransportDestination.setVisible(false);
        timePicker.setVisible(true);
        btnCurrentTime.setVisible(true);
        lblReserveTime.setVisible(true);
        submissionType = Constants.INTERPRETER_REQUEST;
    }

    public void initializeMaintenance() {
        comboMaintenanceType.setVisible(true);
        comboTransportType.setVisible(false);
        comboLanguages.setVisible(false);
        txtTransportDestination.setVisible(false);
        timePicker.setVisible(false);
        btnCurrentTime.setVisible(false);
        lblReserveTime.setVisible(false);
        submissionType = Constants.MAINTENANCE_REQUEST;
    }

    public void initializeTransportation() {
        comboMaintenanceType.setVisible(false);
        comboTransportType.setVisible(true);
        comboLanguages.setVisible(false);
        txtTransportDestination.setVisible(true);
        timePicker.setVisible(true);
        btnCurrentTime.setVisible(true);
        lblReserveTime.setVisible(true);
        submissionType = Constants.TRANSPORTATION_REQUEST;
    }

    public void interpreterButtonHandler() {
        initializeInterpreter();
    }
    public void maintenanceButtonHandler() {
        initializeMaintenance();
    }
    public void transportationButtonHandler() {
        initializeTransportation();
    }

    public void currentTimeButtonHandler() {
        timePicker.setValue(LocalTime.now());
    }

    public void fuzzyRequestLocation() {
        String input = txtSelectLocation.getText();
        autoCompletionBindingLocation.setUserInput(input);
    }

    public void fuzzyTransportDestination() {
        String input = txtTransportDestination.getText();
        autoCompletionBindingTransportDestination.setUserInput(input);
    }

    public void submitButtonHandler() {
        if (checkSubmission()) {
            int severity = Integer.parseInt(comboSeverity.getSelectionModel().getSelectedItem().toString());
            String requestLocationId = manager.queryNodeByLongName(txtSelectLocation.getText()).getNodeID();

            if (submissionType == Constants.INTERPRETER_REQUEST) {
                String language = comboLanguages.getSelectionModel().getSelectedItem().toString();
                String reservationTime = timePicker.getValue().toString();
                manager.createInterpreterRequest(loggedIn.getUsername(),
                        submissionType,
                        requestLocationId,
                        null,
                        txtDescription.getText(),
                        severity,
                        language,
                        reservationTime);

            } else if (submissionType == Constants.MAINTENANCE_REQUEST) {
                String maintenanceType = comboMaintenanceType.getSelectionModel().getSelectedItem().toString();
                manager.createMaintenanceRequest(loggedIn.getUsername(), submissionType, requestLocationId, null,
                        txtDescription.getText(), severity, maintenanceType);

            } else if (submissionType == Constants.TRANSPORTATION_REQUEST) {
                String transportType = comboTransportType.getSelectionModel().getSelectedItem().toString();
                String reservationTime = timePicker.getValue().toString();
                String destinationId = manager.queryNodeByLongName(txtTransportDestination.getText()).getNodeID();
                manager.createTransportationRequest(loggedIn.getUsername(), submissionType, requestLocationId, null,
                        txtDescription.getText(), severity, transportType, destinationId, reservationTime);
            }
            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();
        }
    }

    public boolean checkSubmission() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        String capital = submissionType.substring(0,1).toUpperCase() + submissionType.substring(1);
        alert.setHeaderText("Error creating " + capital + " Request!");

        // checks that a request type has been selected
        if (comboLanguages.getSelectionModel().isEmpty() && submissionType == Constants.INTERPRETER_REQUEST) {
            alert.setContentText("Please select a language!");
            alert.showAndWait();
            return false;
        } else if (comboMaintenanceType.getSelectionModel().isEmpty()
                && submissionType == Constants.MAINTENANCE_REQUEST) {
            alert.setContentText("Please specify a maintenance type!");
            alert.showAndWait();
            return false;
        } else if (comboTransportType.getSelectionModel().isEmpty()
                && submissionType == Constants.TRANSPORTATION_REQUEST) {
            alert.setContentText("Please specify a transportation type!");
            alert.showAndWait();
            return false;
        }

        // checks that a valid location has been entered
        String requestLocationId = manager.queryNodeByLongName(txtSelectLocation.getText()).getNodeID();
        if (requestLocationId == null || manager.getNodeData(requestLocationId) == null ||
                txtSelectLocation.getText() == "") {
            alert.setContentText("Please enter a valid location!");
            alert.showAndWait();
            return false;
        }

        //checks that a severity has been selected
        if (comboSeverity.getSelectionModel().isEmpty()) {
            alert.setContentText("Please specify the severity of your request!");
            alert.showAndWait();
            return false;
        }

        //checks if destination is valid for transport requests
        String destinationId = manager.queryNodeByLongName(txtTransportDestination.getText()).getNodeID();
        boolean invalidDest = (destinationId == null || manager.getNodeData(destinationId) == null);
        if (invalidDest && submissionType == Constants.TRANSPORTATION_REQUEST) {
            alert.setContentText("Please enter a valid destination for your transport!");
            alert.showAndWait();
            return false;
        }

        //checks if time is valid for relevant request types
        if (timePicker.getValue() == null && (submissionType == Constants.INTERPRETER_REQUEST
                || submissionType == Constants.TRANSPORTATION_REQUEST)) {
            alert.setContentText("Please enter a valid time for your request!");
            alert.showAndWait();
            return false;
        }
        return true;
    }



}

