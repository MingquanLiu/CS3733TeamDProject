package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import edu.wpi.cs3733.programname.database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
    JFXListView<String> listEmployees = new JFXListView<String>();
    @FXML
    JFXButton btnAssignRequest;
    @FXML
    JFXButton btnDeleteUnassigned;

    @FXML
    JFXListView<String> listAssigned = new JFXListView<String>();
    @FXML
    JFXButton btnMarkCompleted;
    @FXML
    JFXButton btnDeleteAssigned;

    @FXML
    JFXListView<String> listCompleted = new JFXListView<String>();
    @FXML
    JFXButton btnDeleteCompleted;

    ManageController manager;
    private DBConnection dbConnection;
    List<ServiceRequest> currUnassigned = new ArrayList<ServiceRequest>();
    List<ServiceRequest> currAssigned = new ArrayList<ServiceRequest>();
    List<ServiceRequest> currCompleted = new ArrayList<ServiceRequest>();
    List<Employee> currEmployees = new ArrayList<Employee>();

    String currVisible = "unassigned";
    public void initData(DBConnection dbConnection){
        dbConnection = dbConnection;
        manager = new ManageController(dbConnection);
    }

    public void unassignedRequestButtonHandler() {
        unassignedRequests.setVisible(true);
        assignedRequests.setVisible(false);
        completedRequests.setVisible(false);
        currVisible = "unassigned";
        updateUnassignedView();
    }

    public void assignedRequestButtonHandler() {
        assignedRequests.setVisible(true);
        unassignedRequests.setVisible(false);
        completedRequests.setVisible(false);
        currVisible = "assigned";
        updateAssignedRequests();
    }

    public void completedRequestButtonHandler() {
        completedRequests.setVisible(true);
        assignedRequests.setVisible(false);
        unassignedRequests.setVisible(false);
        currVisible = "complete";
        updateCompletedRequests();
    }

    public void deleteRequestButtonHandler() {
        if (currVisible == "unassigned") {
            int index = listUnassigned.getEditingIndex();
            ServiceRequest requestToDelete = currUnassigned.get(index);
            //TODO delete from database

        }
        else if (currVisible == "assigned") {
            int index = listAssigned.getEditingIndex();
            ServiceRequest requestToDelete = currUnassigned.get(index);
        }
        else if (currVisible == "complete") {
            int index = listAssigned.getEditingIndex();
            ServiceRequest requestToDelete = currUnassigned.get(index);
        }

    }

    public void assignButtonHandler() {

    }

    public String createServiceRequestListString(ServiceRequest sr) {
        NodeData loc1 = manager.getNodeData(sr.getLocation1());
        if (sr.getLocation2() == null) {
            return sr.getServiceType() + " - " + loc1.getLongName();
        }
        else {
            NodeData loc2 = manager.getNodeData(sr.getLocation2());
            return sr.getServiceType() + " - Hallway between " + loc1.getShortName() +
                    " and " + loc2.getShortName();
        }
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
        listUnassigned = new JFXListView<String>();
        listEmployees = new JFXListView<String>();
        currUnassigned = manager.getUnassignedRequests();
        currEmployees = manager.getAllEmployees();

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
        listAssigned = new JFXListView<String>();
        currAssigned = manager.getAssignedRequests();
        for(ServiceRequest sr: currAssigned) {
            String requestDisplay = createServiceRequestListString(sr);
            listAssigned.getItems().add(requestDisplay);
        }
    }

    public void updateCompletedRequests() {
        listCompleted = new JFXListView<String>();
        currCompleted = manager.getCompletedRequests();

        for(ServiceRequest sr: currCompleted) {
            String requestDisplay = createServiceRequestListString(sr);
            listCompleted.getItems().add(requestDisplay);
        }
    }
}