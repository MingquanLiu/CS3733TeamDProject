package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import edu.wpi.cs3733.programname.ManageController;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    ManageController manager;

    public void initManager(ManageController manage) throws IOException {
        this.manager = manage;
        updateRequestsUnassigned();
        updateEmployeeTable();
    }

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
        fullname.setCellValueFactory(
                cellData -> cellData.getValue().
                        fullNameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }

    public String createEmployeeListString(Employee e) {
        return e.getUsername() + " - " + e.getFirstName() + " " + e.getLastName();
    }

    private void updateRequestDetail(AnchorPane requestView, ServiceRequest request, int reqStatus) {
        String details = "";
        Label titleLabel = (Label) requestView.lookup("#lblRequestTitle");
        Label typeLocationLabel = (Label) requestView.lookup("#lblTypeLocation");
        Label severityLabel = (Label) requestView.lookup("#lblSeverity");
        Label assignedToLabel = (Label) requestView.lookup("#lblAssignedTo");

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




}
