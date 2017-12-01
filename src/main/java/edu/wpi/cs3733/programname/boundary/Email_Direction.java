package edu.wpi.cs3733.programname.boundary;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.util.List;

public class Email_Direction {

    @FXML
    private TextField emailAddress;

    @FXML
    private Button sendEmail;

    @FXML
    private Label lblBadEmail;

    private ManageController manager;
    private List<NodeData> path;

    public void initialize(ManageController manager, List<NodeData> path) {
        this.manager = manager;
        this.path = path;
    }

    public void sendButtonHandler() {
        if (validEmail()) {
            this.manager.sendTextDirectionsEmail(path,emailAddress.getText());
            lblBadEmail.setVisible(false);
        }
        else{
            lblBadEmail.setVisible(true);
            return;
        }
        Stage stage = (Stage) sendEmail.getScene().getWindow();
        stage.close();
    }
    private boolean validEmail(){
        boolean check = true;
        String email = emailAddress.getText();

        if(!(email.contains("@") &&
                (email.contains(".com")||email.contains(".edu")||email.contains(".org")))) {
            check = false;
        }

        return check;
    }
}
