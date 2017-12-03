package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

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

    @FXML
    private Label removeerror;

    @FXML
    private Label changessaved;

    private ManageController manageController;

    private final ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    private SortedList<Employee> sortedEmployee;

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
                Toggle job = servicegroup.getSelectedToggle();
                String serviceType;
                if(job.equals(maintenanceBtn)) serviceType = "maintenance";
                else if(job.equals(transportBtn)) serviceType = "transportation";
                else serviceType = "interpreter";

                // I know this is ugly and I hate it but it was the most convenient way of doing things
                if(username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || email.isEmpty()
                        || newpassword.isEmpty() || serviceType.isEmpty()) throw new NullPointerException();

                Boolean adminAccess = admin.isSelected();
                dataerror.setVisible(false);

                Employee newuser = new Employee(username, newpassword, firstname, "", lastname, adminAccess,
                        serviceType, email);

                manageController.addEmployee(newuser);
                changessaved.setVisible(true);

                newusername.clear();
                newfirstname.clear();
                newlastname.clear();
                newemail.clear();
                password.clear();
                password1.clear();
                servicegroup.selectToggle(null);
                admin.setSelected(false);

                data.removeAll(data);
                data.addAll(manageController.getAllEmployees()); 
            }
        } catch (NullPointerException nullpointer) {
            dataerror.setVisible(true);
        }
    }

    @FXML
    void removeEmployee(ActionEvent event) {
        try {
            removeerror.setVisible(false);
            Employee employee = employeetable.getSelectionModel().getSelectedItem();
            manageController.deleteEmployee(employee.getUsername());
            changessaved.setVisible(true);
            data.removeAll(data);
            data.addAll(manageController.getAllEmployees());
        } catch (NullPointerException nullpointer) {
            removeerror.setVisible(true);
        }
    }

    @FXML
    private void hideChangesSaved(MouseEvent event) {
        changessaved.setVisible(false);
    }

    @FXML
    private void closeWindow(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) close.getScene().getWindow();
        changessaved.setVisible(false);
        stage.close();
    }

    @FXML
    void updateUsername(ActionEvent event) {
        // TODO: Verify that this is the right way to do this
    }

    public void initManager(ManageController manageController){
        this.manageController = manageController;
        this.data.addAll(manageController.getAllEmployees());
        sortedEmployee = new SortedList<Employee>(this.data);
        employeetable.setItems(this.sortedEmployee);
        sortedEmployee.comparatorProperty().bind(employeetable.comparatorProperty());
        firstname.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastname.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        username.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        service.setCellValueFactory(cellData -> cellData.getValue().serviceTypeProperty());
        administrator.setCellValueFactory(cellData -> cellData.getValue().sysAdminProperty());

    }
}
