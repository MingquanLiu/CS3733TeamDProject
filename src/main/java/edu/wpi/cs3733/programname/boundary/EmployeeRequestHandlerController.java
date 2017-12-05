package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class EmployeeRequestHandlerController {
        @FXML
        private JFXListView<String> listUnassigned = new JFXListView<>();

        @FXML
        private JFXListView<String> listEmployeeRequests = new JFXListView<>();

        @FXML
        private Label employeeName;

        @FXML
        private JFXButton btnAccept;

        @FXML
        private JFXButton btnComplete;

        @FXML
        private JFXButton btnRemove;

        ManageController manager;
        Employee loggedIn;
        List<ServiceRequest> unassigned;
        List<ServiceRequest> assignedToMe;


        public void initialize(ManageController manager, Employee loggedIn) {
                this.manager = manager;
                this.loggedIn = loggedIn;
                updateView();
        }

        @FXML
        public void acceptButtonHandler(ActionEvent event) {
                int reqIndex = listUnassigned.getSelectionModel().getSelectedIndex();
                ServiceRequest selected = unassigned.get(reqIndex);
                manager.assignServiceRequest(selected, loggedIn.getUsername());
                updateView();
        }

        @FXML
        public void markCompleteButtonHandler(ActionEvent event) {
                int reqIndex = listEmployeeRequests.getSelectionModel().getSelectedIndex();
                ServiceRequest selected = assignedToMe.get(reqIndex);
                manager.completeServiceRequest(selected);
                updateView();
        }

        @FXML
        public void unhandleRequestButtonHandler(ActionEvent event) {
            int reqIndex = listEmployeeRequests.getSelectionModel().getSelectedIndex();
            ServiceRequest selected = assignedToMe.get(reqIndex);
            manager.unhandleServiceRequest(selected);
            updateView();

        }

        public void updateView() {
            this.unassigned = manager.queryUnassignedRequestsByType(loggedIn.getServiceType());
            this.assignedToMe = manager.queryRequestsByEmployee(loggedIn);
            listEmployeeRequests.getItems().clear();
            listUnassigned.getItems().clear();

            for(ServiceRequest req: this.unassigned) {
                NodeData loc1 = manager.getNodeData(req.getLocation1());
                String display = req.getServiceType() + " - " + loc1.getLongName();
                listUnassigned.getItems().add(display);
            }

            for(ServiceRequest req: this.assignedToMe) {
                NodeData loc1 = manager.getNodeData(req.getLocation1());
                String display = req.getServiceType() + " - " + loc1.getLongName();
                listEmployeeRequests.getItems().add(display);
            }
        }
}
