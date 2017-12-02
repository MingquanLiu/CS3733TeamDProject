package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<Employee, String> administrator;

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

    @FXML
    private Label dataerror;

    private ManageController manageController;

    private final ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    void addNewEmployee(ActionEvent event) {
        try {
            if (!password.getText().equals(password1.getText())) {
                passerror.setVisible(true);
            }
            else {
                passerror.setVisible(false);
                String username = newusername.getText();
                String firstname = newfirstname.getText();
                String lastname = newlastname.getText();
                String email = newemail.getText();
                String newpassword = password1.getText();
                String job = servicegroup.getSelectedToggle().toString();

                // I know this is ugly and I hate it but it was the most convenient way of doing things
                if(username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || email.isEmpty()
                        || newpassword.isEmpty() || job.isEmpty()) throw new NullPointerException();

                Boolean adminAccess = admin.isSelected();
                dataerror.setVisible(false);

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
        } catch (NullPointerException nullpointer) {
            dataerror.setVisible(true);
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
    public void initManager(ManageController manageController){
        this.manageController = manageController;
        this.data.addAll(manageController.getAllEmployees());
        employeetable.setItems(this.data);
        firstname.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastname.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        username.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        service.setCellValueFactory(cellData -> cellData.getValue().serviceTypeProperty());
        administrator.setCellValueFactory(cellData -> cellData.getValue().sysAdminProperty());
    }
}
