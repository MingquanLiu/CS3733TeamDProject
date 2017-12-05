package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

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
    private JFXButton edit;

    @FXML
    private JFXButton btnSkills;

    @FXML
    private JFXButton canceledit;

    @FXML
    private Label dataerror;

    @FXML
    private Label removeerror;

    @FXML
    private Label changessaved;

    @FXML
    private Label addlabel;

    //**************************************Skills window fields below***************************************//

    @FXML
    private Label labelUsername;

    @FXML
    private JFXListView<String> listAllSkills;

    @FXML
    private JFXListView<String> listMySkills;

    @FXML
    private JFXButton btnAddSkill;

    @FXML
    private JFXButton btnRmSkill;

    @FXML
    private JFXButton btnNewSkill;

    @FXML
    private Label labelSkillsSaved;

    //********************************************Additional vars*******************************************//

    private ManageController manageController;

    private final ObservableList<Employee> data = FXCollections.observableArrayList();

    private ObservableList<String> allSkills;

    @FXML
    private SortedList<Employee> sortedEmployee;


    @FXML
    void addNewEmployee(ActionEvent event) {
        boolean flag = false;
        try {
            if (!password.getText().equals(password1.getText())) {
                passerror.setVisible(true);
            }
            else {
                newusername.setDisable(false);
                canceledit.setVisible(false);
                btnSkills.setVisible(false);
                add.setText("Add employee");
                addlabel.setText("Add a new employee");
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

                for(Employee e: sortedEmployee) {
                    if(e.getUsername().equals(username)) {
                        manageController.editEmployee(newuser);
                        flag = true;
                    }
                }
                if(!flag) {
                    manageController.addEmployee(newuser);
                    changessaved.setVisible(true);
                }

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
            Employee employee = employeetable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deletion");
            alert.setHeaderText("Confirm action: delete employee");
            alert.setContentText("You are about to delete the employee " + employee.getUsername() +
                    " from the database. This cannot be undone. Are you sure you would like to proceed?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                removeerror.setVisible(false);
                manageController.deleteEmployee(employee.getUsername());
                changessaved.setVisible(true);
                data.removeAll(data);
                data.addAll(manageController.getAllEmployees());
            } else {
                // do nothing. Employee lives to work another day.
            }
        } catch (NullPointerException nullpointer) {
            removeerror.setVisible(true);
        }
    }

    @FXML
    private void updateRow(MouseEvent event) {
        this.hideChangesSaved(event);
        edit.setVisible(true);
    }

    @FXML
    private void hideChangesSaved(MouseEvent event) {
        changessaved.setVisible(false);
    }

    @FXML
    private void showedit(ActionEvent event) {
        edit.setVisible(false);
        canceledit.setVisible(true);
        btnSkills.setVisible(true);
        Employee employee = employeetable.getSelectionModel().getSelectedItem();
        newusername.setText(employee.getUsername());
        newusername.setDisable(true);
        newfirstname.setText(employee.getFirstName());
        newlastname.setText(employee.getLastName());
        newemail.setText(employee.getEmail());
        password.setText(employee.getPassword());
        password1.setText(employee.getPassword());
        admin.setSelected(employee.getSysAdmin());
        if(employee.getServiceType().equals("transportation")) servicegroup.selectToggle(transportBtn);
        else if(employee.getServiceType().equals("maintenance")) servicegroup.selectToggle(maintenanceBtn);
        else servicegroup.selectToggle(interpreterBtn);
        addlabel.setText("Edit employee " + employee.getUsername());
        add.setText("Update employee");
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        canceledit.setVisible(false);
        btnSkills.setVisible(false);
        employeetable.getSelectionModel().clearSelection();
        addlabel.setText("Add a new employee");
        add.setText("Add employee");
        newusername.clear();
        newusername.setDisable(false);
        newfirstname.clear();
        newlastname.clear();
        newemail.clear();
        password.clear();
        password1.clear();
        admin.setSelected(false);
        servicegroup.selectToggle(null);
    }

    @FXML
    private void closeWindow(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) close.getScene().getWindow();
        changessaved.setVisible(false);
        stage.close();
    }

    @FXML
    private void openSkillsWindow(ActionEvent Event) throws IOException {
//        labelUsername.setText(newusername.getText() + "'s Skills");
        // TODO: List out their skills
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/employeeSkillsPopup.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        stage.show();
    }

    //****************************************Skills methods******************************************//
    @FXML
    void addSkill(ActionEvent event) {
        String skill = listAllSkills.getSelectionModel().getSelectedItem();
        // TODO: Add the skill to the employee's skill list in DB and refresh list view
        labelSkillsSaved.setVisible(true);

    }

    @FXML
    void enableAddSkill(MouseEvent event) {
        btnAddSkill.setDisable(false);
        btnRmSkill.setDisable(true);
        labelSkillsSaved.setVisible(false);
    }

    @FXML
    void enableRmSkill(MouseEvent event) {
        btnRmSkill.setDisable(false);
        btnAddSkill.setDisable(true);
        labelSkillsSaved.setVisible(false);
    }

    @FXML
    void newSkill(ActionEvent event) {
        btnRmSkill.setDisable(true);
        btnAddSkill.setDisable(true);
        labelSkillsSaved.setVisible(false);
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New skill");
        dialog.setHeaderText("Add a new skill");
        dialog.setContentText("Please enter the name of the new skill:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String newSkill = result.get();
            allSkills.add(newSkill);

            // TODO: Add the new skill to the list
        }
    }

    @FXML
    void rmSkill(ActionEvent event) {
        btnRmSkill.setDisable(true);
        // TODO: Remove the skill
        labelSkillsSaved.setVisible(true);
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
        // TODO: Populate ListView of all skills
        allSkills = FXCollections.observableArrayList("First skill");
        listAllSkills = new JFXListView<String>();
        listAllSkills.setItems(allSkills);
    }
}
