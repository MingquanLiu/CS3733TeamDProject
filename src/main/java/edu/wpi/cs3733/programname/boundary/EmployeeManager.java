package edu.wpi.cs3733.programname.boundary;

import edu.wpi.cs3733.programname.commondata.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private TableColumn<Employee, String> admin;

    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private TextField newusername;

    @FXML
    private TextField newlastname;

    @FXML
    private TextField newfirstname;

    @FXML
    private TextField newemail;

    @FXML
    private MenuButton newservicetype;

    @FXML
    private CheckBox newadmin;

    @FXML
    private Button addnew;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private Label passerror;

    @FXML
    private Button remove;

    @FXML
    void addNewEmployee(ActionEvent event) {
        if(!password.getText().equals(password1.getText())) {
            passerror.setVisible(true);
        } else {
            passerror.setVisible(false);
            String username = newusername.
        }
    }

    @FXML
    void sortByService(ActionEvent event) {

    }

    @FXML
    void removeEmployee(ActionEvent event) {

    }
}
