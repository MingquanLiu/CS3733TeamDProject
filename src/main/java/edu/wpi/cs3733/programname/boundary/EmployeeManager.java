package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class EmployeeManager {

    @FXML
    private AnchorPane mainServicePane;

    @FXML
    private TableView<Employee> employeetable;

    @FXML
    private TableColumn<Employee, String> username;

    @FXML
    private TableColumn<Employee, String> lastname;

    @FXML
    private TableColumn<Employee, String> firstname;

    @FXML
    private TableColumn<Employee, String> service;

    @FXML
    private JFXCheckBox admin;

    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private Label passerror;

    @FXML
    private JFXTextField newusername;

    @FXML
    private JFXTextField newfirstname;

    @FXML
    private JFXTextField newlastname;

    @FXML
    private JFXTextField newemail;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXButton add;

    @FXML
    private JFXRadioButton interpreterBtn;

    @FXML
    private ToggleGroup servicegroup;

    @FXML
    private JFXRadioButton maintenanceBtn;

    @FXML
    private JFXRadioButton transportBtn;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton remove;

    private ManageContoller manager;

    @FXML
    void addNewEmployee(ActionEvent event) {
        if(!password.getText().equals(password1.getText())) {
            passerror.setVisible(true);
        } else {
            // Add validation for all fields, none of them can be empty!!!
            passerror.setVisible(false);
            String username = newusername.getText();
            String firstname = newfirstname.getText();
            String lastname = newlastname.getText();
            String email = newemail.getText();
            String newpassword = password1.getText();
            String job = servicegroup.getSelectedToggle().toString();
            Boolean adminAccess = admin.isSelected();

            // Do some stuff to add the user to the database and update the table

            newusername.clear();
            newfirstname.clear();
            newlastname.clear();
            newemail.clear();
            password.clear();
            password1.clear();
            servicegroup.selectToggle(null);
            admin.setSelected(false);
        }

    }

    @FXML
    void removeEmployee(ActionEvent event) {

    }

    @FXML
    void sortByService(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

}
