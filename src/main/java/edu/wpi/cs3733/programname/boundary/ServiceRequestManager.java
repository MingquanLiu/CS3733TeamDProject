package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequestManager {

    @FXML
    AnchorPane mainServicePane;

    @FXML
    JFXButton btnUnassigned;
    @FXML
    JFXButton btnAssigned;
    @FXML
    JFXButton btnCompleted;
    @FXML
    JFXButton btnBack;

    @FXML
    StackPane stackLists;
    @FXML
    GridPane unassignedRequests;
    @FXML
    GridPane assignedRequests;
    @FXML
    GridPane completedRequests;

    @FXML
    JFXListView<String> listUnassigned = new JFXListView<String>();
    @FXML
    JFXListView<String> listEmployees;
    @FXML
    JFXButton btnAssignRequest;
    @FXML
    JFXButton btnDeleteUnassigned;

    @FXML
    JFXListView<String> listAssigned;
    @FXML
    JFXButton btnMarkCompleted;
    @FXML
    JFXButton btnDeleteAssigned;

    @FXML
    JFXListView<String> listCompleted;
    @FXML
    JFXButton btnDeleteCompleted;

    ManageController manager;
    List<ServiceRequest> currUnassigned = new ArrayList<ServiceRequest>();
    List<ServiceRequest> currAssigned = new ArrayList<ServiceRequest>();
    List<ServiceRequest> currCompleted = new ArrayList<ServiceRequest>();
    List<Employee> currEmployees = new ArrayList<Employee>();
    String currVisible = "unassigned";

    int requestIndex = -1;
    int employeeIndex = -1;

    public void initManager(ManageController manageController){
        manager = manageController;
        unassignedRequestButtonHandler();
        btnMarkCompleted.setVisible(false);
        btnAssignRequest.toFront();
        btnDeleteAssigned.toFront();
        btnDeleteUnassigned.toFront();
    }

    public void unassignedRequestButtonHandler() {
        unassignedRequests.setVisible(true);
        assignedRequests.setVisible(false);
        completedRequests.setVisible(false);
        btnMarkCompleted.setVisible(false);
        btnAssignRequest.toFront();
        btnDeleteAssigned.toFront();
        currVisible = "unassigned";
        updateUnassignedView();
    }

    public void assignedRequestButtonHandler() {
        assignedRequests.setVisible(true);
        unassignedRequests.setVisible(false);
        completedRequests.setVisible(false);
        btnMarkCompleted.setVisible(true);
        btnMarkCompleted.toFront();
        btnDeleteCompleted.toFront();
        currVisible = "assigned";
        updateAssignedRequests();
    }

    public void completedRequestButtonHandler() {
        completedRequests.setVisible(true);
        assignedRequests.setVisible(false);
        unassignedRequests.setVisible(false);
        btnMarkCompleted.setVisible(false);
        currVisible = "complete";
        updateCompletedRequests();
    }

    public void deleteRequestButtonHandler() {
        if (currVisible == "unassigned") {
            int index = listUnassigned.getSelectionModel().getSelectedIndex();
            ServiceRequest requestToDelete = currUnassigned.get(index);
            manager.deleteServiceRequest(requestToDelete);
        }
        else if (currVisible == "assigned") {
            int index = listAssigned.getSelectionModel().getSelectedIndex();
            ServiceRequest requestToDelete = currUnassigned.get(index);
            manager.deleteServiceRequest(requestToDelete);
        }
        else if (currVisible == "complete") {
            int index = listAssigned.getSelectionModel().getSelectedIndex();
            ServiceRequest requestToDelete = currUnassigned.get(index);
            manager.deleteServiceRequest(requestToDelete);
        }
        updateCurrentView();
    }

    public void assignButtonHandler() {
        int reqIndex = listUnassigned.getSelectionModel().getSelectedIndex();
        int emplIndex = listEmployees.getSelectionModel().getSelectedIndex();
        if (reqIndex > -1 && requestIndex < 0) {
            requestIndex = reqIndex;
        }
        else if (emplIndex > -1 && employeeIndex < 0) {
            employeeIndex = emplIndex;
        }
        if (requestIndex > -1 && employeeIndex > -1) {
            executeAssign();
        }
    }

    public void executeAssign() {
        System.out.println("Assign Button Pressed");
        ServiceRequest request = currUnassigned.get(requestIndex);
        Employee employee = currEmployees.get(employeeIndex);
        this.manager.assignServiceRequest(request, employee.getUsername());
        updateCurrentView();
        requestIndex = -1;
        employeeIndex = -1;
    }

    public void markCompletedHandler() {
        int index = listAssigned.getSelectionModel().getSelectedIndex();
        ServiceRequest request = currAssigned.get(index);
        this.manager.completeServiceRequest(request);
        updateCurrentView();
    }

    public String createServiceRequestListString(ServiceRequest sr) {
        NodeData loc1 = manager.getNodeData(sr.getLocation1());
        System.out.println("Service Request String == " + sr.getLocation2());
        System.out.println("Char test = " + sr.getLocation2().charAt(0));
        System.out.println("String length = " + sr.getLocation2().length());
        return sr.getServiceType() + " - " + loc1.getLongName();
//        if (sr.getLocation2() == null || sr.getLocation2() == "null") {
//            return sr.getServiceType() + " - " + loc1.getLongName();
//        }
//        else if (sr.getLocation2().trim() == "") {
//            return sr.getServiceType() + " - " + loc1.getLongName();
//        }
//        else {
//            NodeData loc2 = manager.getNodeData(sr.getLocation2());
//            return sr.getServiceType() + " - Hallway between " + loc1.getShortName() +
//                    " and " + loc2.getShortName();
//        }
    }

    public String createEmployeeListString(Employee e) {
        return e.getUsername() + " - " + e.getFirstName() + " " + e.getLastName();
    }

    public void updateCurrentView() {
        if (currVisible == "unassigned") {
            updateUnassignedView();
        }
        else if (currVisible == "assigned") {
            updateAssignedRequests();
        }
        else if (currVisible == "complete") {
            updateCompletedRequests();
        }
    }

    public void updateUnassignedView() {
        currUnassigned = manager.getUnassignedRequests();
        currEmployees = manager.getAllEmployees();

        listUnassigned.getItems().clear();
        listEmployees.getItems().clear();

        for(ServiceRequest sr: currUnassigned) {
            String requestDisplay = createServiceRequestListString(sr);
            listUnassigned.getItems().add(requestDisplay);
        }
        for(Employee emp: currEmployees) {
            String employeeDisplay = createEmployeeListString(emp);
            listEmployees.getItems().add(employeeDisplay);
        }
    }

    public void updateAssignedRequests() {
        currAssigned = manager.getAssignedRequests();
        listAssigned.getItems().clear();
        for(ServiceRequest sr: currAssigned) {
            String requestDisplay = createServiceRequestListString(sr);
            listAssigned.getItems().add(requestDisplay);
        }
    }

    public void updateCompletedRequests() {
        currCompleted = manager.getCompletedRequests();
        listCompleted.getItems().clear();
        for(ServiceRequest sr: currCompleted) {
            String requestDisplay = createServiceRequestListString(sr);
            listCompleted.getItems().add(requestDisplay);
        }
    }

    public void backButtonHandler() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}