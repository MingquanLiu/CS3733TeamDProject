package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeeRequestHandlerController {
        @FXML
        private JFXListView<?> listUnassigned;

        @FXML
        private JFXListView<?> listEmployeeRequests;

        @FXML
        private Label employeeName;

        @FXML
        private JFXButton btnAccept;

        @FXML
        private JFXButton btnComplete;

        @FXML
        private JFXButton btnRemove;

        @FXML
        void acceptButtonHandler(ActionEvent event) {

        }

        @FXML
        void markCompleteButtonHandler(ActionEvent event) {

        }

        @FXML
        void unhandleRequestButtonHandler(ActionEvent event) {

        }

}
