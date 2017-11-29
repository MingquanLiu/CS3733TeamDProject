package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.servicerequest.entity.Employee;
import edu.wpi.cs3733.programname.servicerequest.entity.ServiceRequest;
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

    ManageController manager = new ManageController();

    List<ServiceRequest> currUnassigned = new ArrayList<ServiceRequest>();
    List<ServiceRequest> currAssigned = new ArrayList<ServiceRequest>();
    List<ServiceRequest> currCompleted = new ArrayList<ServiceRequest>();
    List<Employee> currEmployees = new ArrayList<Employee>();

    String currVisible = "unassigned";


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
        if (sr.getLocation2() == null) {
            return sr.getServiceType() + " - " + sr.getLocation1().getLongName();
        }
        else return sr.getServiceType() + " - Hallway between " + sr.getLocation1().getShortName() +
                " and " + sr.getLocation2().getShortName();
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
      //      String requestDisplay = createServiceRequestListString(sr);
            String requestDisplay = "test passed!!!!!!!!";
            listAssigned.getItems().add(requestDisplay);
        }
    }

    public void updateCompletedRequests() {
        listCompleted = new JFXListView<String>();
        currCompleted = manager.getCompletedRequests();

        for(ServiceRequest sr: currCompleted) {
         //   String requestDisplay = createServiceRequestListString(sr);
            String requestDisplay = "test passed!!!!!!!!";
            listCompleted.getItems().add(requestDisplay);
        }
    }
}