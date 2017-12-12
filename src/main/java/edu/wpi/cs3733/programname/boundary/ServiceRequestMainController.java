package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXMasonryPane;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Constants;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.MaintenanceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.TransportationRequest;
import edu.wpi.cs3733.programname.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequestMainController {

    @FXML
    StackPane root;
    @FXML
    JFXMasonryPane masonryPane;
    @FXML
    ScrollPane masonryScroll;

    ManageController manager;

    public void initManager(ManageController manage) throws IOException {
        this.manager = manage;
        masonryPane.setVisible(true);
        ArrayList<Node> children = new ArrayList<>();
        List<ServiceRequest> allUnassigned = manager.getUnassignedRequests();
        for(ServiceRequest unassigned: allUnassigned) {
            AnchorPane requestFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(
                    "/fxml/service_request_obj2.fxml"
            ));
            AnchorPane requestView = (AnchorPane) requestFXML.lookup("#serviceObj");
            requestView.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            updateRequestDetail(requestView, unassigned);
            requestView.setVisible(true);
            requestView.toFront();
            masonryPane.getChildren().add(requestView);
        }

    }

    private void updateRequestDetail(AnchorPane requestView, ServiceRequest request) {
        String details = "";
        Label titleLabel = (Label) requestView.lookup("#lblRequestTitle");
        Label typeLocationLabel = (Label) requestView.lookup("#lblTypeLocation");
        Label descriptionLabel = (Label) requestView.lookup("#lblDescription");
        Label severityLabel = (Label) requestView.lookup("#lblSeverity");
        Label assignedToLabel = (Label) requestView.lookup("#lblAssignedTo");
        Label idLabel = (Label) requestView.lookup("#lblRequestId");

        NodeData locationNodeData = manager.getNodeData(request.getLocation2());
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
        descriptionLabel.setText(request.getDescription());
        descriptionLabel.setWrapText(true);
        severityLabel.setText("Severity: " + request.getSeverity());
        assignedToLabel.setText("");
        assignedToLabel.setWrapText(true);
        idLabel.setText("ID#: " + request.getServiceID());
    }




}
