package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.AppSettings;
import edu.wpi.cs3733.programname.commondata.Constants;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.MaintenanceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.TransportationRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.List;

public class ServiceRequestMainController {

    private final int UNASSIGNED = 0;
    private final int ASSIGNED = 1;
    private final int COMPLETED = 2;

    @FXML
    JFXMasonryPane requestMasonryPane;
    @FXML
    ScrollPane requestMasonryScroll;

    @FXML
    TableView<Employee> employeeTableView;
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    private SortedList<Employee> sortedEmployee;

    @FXML
    private TableColumn<Employee, String> fullname;

    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private StackPane requestMasonry;

    @FXML
    private StackPane employeeMasonry;

    @FXML
    private JFXButton btnUnassigned;

    @FXML
    private JFXButton btnAssigned;

    @FXML
    private JFXButton btnCompleted;

    @FXML
    private JFXButton btnAll;

    @FXML
    private JFXButton btnInterpreter;

    @FXML
    private JFXButton btnMaintenance;

    @FXML
    private JFXButton btnTransportation;

    @FXML
    private JFXButton btnAssignSelected;

    ManageController manager;

    public void initManager(ManageController manage) throws IOException {
        this.manager = manage;
        updateRequestsUnassigned();
        updateEmployeeTable();
        AppSettings.getInstance().setCurrentSelectedRequestId(null);
    }

    @SuppressWarnings("Duplicates")
    private void updateRequestsUnassigned() throws IOException {
        requestMasonryPane.getChildren().clear();
        requestMasonryPane.setVisible(true);
        List<ServiceRequest> allUnassigned = manager.getUnassignedRequests();
        for(ServiceRequest unassigned: allUnassigned) {
            AnchorPane requestFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(
                    "/fxml/service_request_obj2.fxml"
            ));
            AnchorPane requestView = (AnchorPane) requestFXML.lookup("#serviceObj");
            requestView.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
            updateRequestDetail(requestView, unassigned, UNASSIGNED);
            requestView.setVisible(true);
            requestMasonryPane.getChildren().add(requestView);
        }
    }

    public void unassignedButtonHandler() throws IOException {
        updateRequestsUnassigned();
    }

    public void assignedButtonHandler() throws IOException {
        updateRequestsAssigned();
    }

    public void completedButtonHandler() throws IOException {
        updateRequestsCompleted();
    }

    public void allTypesButtonHandler() {

    }

    public void interpreterButtonHandler() {

    }

    public void maintenanceButtonHandler() {

    }

    public void transportationButtonHandler() {

    }

    @SuppressWarnings("Duplicates")
    private void updateRequestsAssigned() throws IOException {
        requestMasonryPane.getChildren().clear();
        requestMasonryPane.setVisible(true);
        List<ServiceRequest> allAssigned = manager.getAssignedRequests();
        for(ServiceRequest assigned: allAssigned) {
            AnchorPane requestFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(
                    "/fxml/service_request_obj2.fxml"
            ));
            AnchorPane requestView = (AnchorPane) requestFXML.lookup("#serviceObj");
            requestView.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
            updateRequestDetail(requestView, assigned, ASSIGNED);
            requestView.setVisible(true);
            requestMasonryPane.getChildren().add(requestView);
        }
    }

    @SuppressWarnings("Duplicates")
    private void updateRequestsCompleted() throws IOException {
        requestMasonryPane.getChildren().clear();
        List<ServiceRequest> allCompleted = manager.getCompletedRequests();
        for(ServiceRequest completed: allCompleted) {
            AnchorPane requestFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(
                    "/fxml/service_request_obj2.fxml"
            ));
            AnchorPane completedRequestView = (AnchorPane) requestFXML.lookup("#serviceObj");
            completedRequestView.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
            updateRequestDetail(completedRequestView, completed, COMPLETED);
            completedRequestView.setVisible(true);
            requestMasonryPane.getChildren().add(completedRequestView);
        }
    }

    private void updateEmployeeTable() {
        employees.addAll(manager.getAllEmployees());
        sortedEmployee = new SortedList<Employee>(this.employees);
        employeeTableView.setItems(this.sortedEmployee);
        sortedEmployee.comparatorProperty().bind(employeeTableView.comparatorProperty());
        fullname.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }

    private void updateRequestDetail(AnchorPane requestView, ServiceRequest request, int reqStatus) {
        String details = "";
        Label titleLabel = (Label) requestView.lookup("#lblRequestTitle");
        Label typeLocationLabel = (Label) requestView.lookup("#lblTypeLocation");
        Label severityLabel = (Label) requestView.lookup("#lblSeverity");
        Label assignedToLabel = (Label) requestView.lookup("#lblAssignedTo");
        Label requestIdLabel = (Label) requestView.lookup("#lblRequestId");
        requestIdLabel.setText(request.getServiceID());
        requestIdLabel.setVisible(false);

        NodeData locationNodeData = manager.getNodeData(request.getLocation1());
        if (request.getServiceType().equals(Constants.INTERPRETER_REQUEST)) {
            InterpreterRequest interpreterRequest = (InterpreterRequest) request;
            titleLabel.setText("Interpreter Request");
            details = interpreterRequest.getLanguage() + " translation request at " +
                    locationNodeData.getLongName() + "!";
            typeLocationLabel.setText(details);
            typeLocationLabel.setWrapText(true);
        } else if (request.getServiceType().equals(Constants.MAINTENANCE_REQUEST)) {
            MaintenanceRequest maintenanceRequest = (MaintenanceRequest) request;
            titleLabel.setText("Maintenance Request");
            details = maintenanceRequest.getMaintenanceType() + " maintenance request at " +
                    locationNodeData.getLongName() + "!";

        } else if (request.getServiceType().equals(Constants.TRANSPORTATION_REQUEST)) {
            TransportationRequest transportationRequest = (TransportationRequest) request;
            NodeData destination = manager.getNodeData(transportationRequest.getDestination());
            titleLabel.setText("Transportation Request");
            details = transportationRequest.getTransportType() + " transport request from " +
                    locationNodeData.getLongName() + " to " + destination.getLongName() + "!";
        }
        typeLocationLabel.setText(details);
        typeLocationLabel.setWrapText(true);
        severityLabel.setText("Severity: " + request.getSeverity());
        if (reqStatus == UNASSIGNED) {
            assignedToLabel.setText("Request not assigned!");
        } else {
            Employee handler = manager.queryEmployeeByUsername(request.getReceiver());
            assignedToLabel.setText("Assigned to: " + handler.getFirstName() + " " + handler.getLastName());
        }
        assignedToLabel.setWrapText(true);
    }

    public void assignToEmployeeButtonHandler(ActionEvent ae) {
        JFXButton aeSource = (JFXButton) ae.getSource();
        AnchorPane requestAnchor = (AnchorPane) aeSource.getParent();
        String requestId = ((Label) requestAnchor.lookup("#lblRequestId")).getText();
        AppSettings.getInstance().setCurrentSelectedRequestId(requestId);
        System.out.println("### " + AppSettings.getInstance().getCurrentSelectedRequestId());
    }

    public void assignSelectedButtonHandler() {
        System.out.println("### Assigned Request #" + AppSettings.getInstance().getCurrentSelectedRequestId());
        if (validateSubmission()) {
            ServiceRequest selected = manager.queryRequestsById(AppSettings.getInstance().getCurrentSelectedRequestId());
            Employee assignee = employeeTableView.getSelectionModel().getSelectedItem();
            manager.assignServiceRequest(selected, assignee.getUsername());
        }
    }

    private boolean validateSubmission() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error assigning request!");
        if (AppSettings.getInstance().getCurrentSelectedRequestId() == null) {
            alert.setContentText("Please select a service request to assign!");
            alert.showAndWait();
            return false;
        }
        if (employeeTableView.getSelectionModel().getSelectedItem() == null) {
            alert.setContentText("Please select an employee to receive the request!");
            alert.showAndWait();
            return false;
        }

        return true;
    }
}
