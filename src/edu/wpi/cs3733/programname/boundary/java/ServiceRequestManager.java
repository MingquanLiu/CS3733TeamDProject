package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
    JFXListView<String> listCompleted;
    @FXML
    JFXButton btnDeleteCompleted;


}