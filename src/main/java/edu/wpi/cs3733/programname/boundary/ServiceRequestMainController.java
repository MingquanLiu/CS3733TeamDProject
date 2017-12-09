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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ServiceRequestMainController {

    @FXML
    JFXMasonryPane masonryPane;


    ManageController manager;

    public void initManager(ManageController manage) throws IOException {
        this.manager = manage;
        masonryPane = new JFXMasonryPane();
        List<ServiceRequest> allUnassigned = manager.getUnassignedRequests();
        for(ServiceRequest unassigned: allUnassigned) {
            AnchorPane requestView = (AnchorPane) FXMLLoader.load(new URL("service_request_obj.fxml"));
            updateRequestDetail(requestView, unassigned);
            masonryPane.getChildren().add(requestView);
        }
    }

    private void updateRequestDetail(AnchorPane requestView, ServiceRequest request) {
        Label titleLabel = (Label) requestView.lookup("#lblRequestTitle");
        Label detailLabel = (Label) requestView.lookup("#lblRequestDetail");
        Label locationLabel = (Label) requestView.lookup("#lblRequestLocation");
        NodeData locationNodeData = manager.queryNode
        if (request.getServiceType().equals(Constants.INTERPRETER_REQUEST)) {
            InterpreterRequest interpreterRequest = (InterpreterRequest) request;
            titleLabel.setText("Interpreter Request");
            detailLabel.setText(interpreterRequest.getLanguage() + "translation request at ");
            locationLabel.setText()
        } else if (request.getServiceType().equals(Constants.MAINTENANCE_REQUEST)) {
            MaintenanceRequest maintenanceRequest = (MaintenanceRequest) request;
            titleLabel.setText("Maintenance Request");
            detailLabel.setText(maintenanceRequest.getMaintenanceType() + " maintenance request at ");
        } else if (request.getServiceType().equals(Constants.TRANSPORTATION_REQUEST)) {
            TransportationRequest transportationRequest = (TransportationRequest) request;
            titleLabel.setText("Transportation Request");
            detailLabel.setText(transportationRequest.getTransportType() + " transport request from ");
        }
    }




}
