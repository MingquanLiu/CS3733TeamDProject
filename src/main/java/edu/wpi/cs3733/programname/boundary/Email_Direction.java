package edu.wpi.cs3733.programname.boundary.java;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class Email_Direction {

    @FXML
    private TextField emailAddress;

    @FXML
    private Button sendEmail;

    private ManageController manager;
    private List<NodeData> path;

    public void initialize(ManageController manager, List<NodeData> path) {
        this.manager = manager;
        this.path = path;
    }

    public void sendButtonHandler() {
        if (emailAddress.getText() != null && emailAddress.getText() != "") {
            this.manager.sendTextDirectionsEmail(path,emailAddress.getText());
        }
        Stage stage = (Stage) sendEmail.getScene().getWindow();
        stage.close();
    }
}
