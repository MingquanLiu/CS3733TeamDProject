package edu.wpi.cs3733.programname.boundary.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import edu.wpi.cs3733.programname.ManageController;

public class LoginPopup {
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;

    private ManageController manager;
    boolean succesfulLogin;

    public void buttonHandler(ActionEvent e){
        System.out.println("a button was clicked");
        if(e.getSource() == btnSubmit){
            succesfulLogin = true;
            //succesfulLogin = manager.Login();
            System.out.println("logging in");
            btnSubmit.getScene().getWindow().hide();
        }
        else{

        }
    }
    public boolean getLoggedIn(){
        return succesfulLogin;
    }
}
