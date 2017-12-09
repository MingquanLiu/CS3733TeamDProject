package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.wpi.cs3733.programname.ManageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NewMapController {
    //imageName - the submitted image name
    @FXML
    private Label imageName;
    //errUpload - hold error messages related to image selection
    @FXML
    private Label errUpload;
    //errFloor - holds error messages related to floor name choices
    @FXML
    private Label errFloor;
    //errBuilding - holds error messages related to building name choices
    @FXML
    private Label errBuilding;
    //addingType - states what the user is adding
    @FXML
    private Label addingType;

    @FXML
    private TextField buildingName;
    @FXML
    private TextField floorName;

    @FXML
    private JFXComboBox floorNum;

    @FXML
    private JFXButton btnUpload;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXButton btnCancel;

    //the file that will be copied over
    File selectedFile;
    //the filepath to be called later to change to it
    String filepath;
    //the manageController that does... something
    ManageController manager;
    //controls whether it adds a building or floor
    private boolean isBuilding;
    private Floor floor;
    private Building building;
    private boolean addedFile;

    public void onSubmit() {
        if (validSubmission()) {
            try {
                //relative path from main folder to the images folder where we store the floors
                String relPathMaps = "floorMaps/";

                //naming the new file based on the name given
                String fileName = floorName.getText() + "." + getFileExtension(selectedFile);
                //for later use pulling up the floor
                filepath = "file:floorMaps/" + fileName;

                //creating the two new files
                File fileMap = new File(relPathMaps + fileName);

                //copying them to the right locations
                copyFile(selectedFile, fileMap);

                //print to make sure, then hide the menu
                if (isBuilding) {
                    building = new Building(buildingName.getText());
                    floor = new Floor(floorName.getText(), buildingName.getText(), floorNum.getValue().toString(), "file:floorMaps/" + fileName);
                    building.addFloor(floor);
                } else {
                    floor = new Floor(floorName.getText(), buildingName.getText(), floorNum.getValue().toString(), "file:floorMaps/" + fileName);
                }
                addedFile = true;
                onCancelButton();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("some error happened");
            }

        }

    }

    public void onCancelButton() {
        btnCancel.getScene().getWindow().hide();
    }

    public Floor getFloor() {
        return floor;
    }

    public Building getBuilding() {
        return building;
    }

    public boolean addedMap() {
        return addedFile;
    }

    public void setUp(String type) {
        if (type.equals("Building")) {
            isBuilding = true;
            addingType.setText("Add a Building");
        } else {
            isBuilding = false;
            addingType.setText("Add a Floor");
        }
        ObservableList floors = FXCollections.observableArrayList(
                "L2",
                "L1",
                "G",
                "1",
                "2",
                "3");
        floorNum.setItems(floors);
        floorNum.setValue("L2");
        addedFile = false;
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
    }

    /**
     * Opens a FileChooser menu to let a user pick an image to use
     */
    public void setPath() {
        FileChooser fileChooser = new FileChooser();

        configureFileChooser(fileChooser);
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imageName.setText("File selected: " + selectedFile.getName());
        } else {
            imageName.setText("File selection cancelled.");
        }
    }

    private boolean validSubmission() {
        boolean haveFile = false, haveBuilding = false, haveFloor = false;
        String extension;
        errUpload.setText("");
        errBuilding.setText("");
        errFloor.setText("");

        if (selectedFile != null) {
            extension = getFileExtension(selectedFile);
            if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
                haveFile = true;
            } else
                errUpload.setText("You need to upload a valid image (.png or .jpg).");
        } else
            errUpload.setText("You need to upload an image.");


        if (!buildingName.getText().equals("")) {
            //ADD FORMATTING GUIDELINES
            haveBuilding = true;
        } else
            errBuilding.setText("You need to set a building name.");

        if (!floorName.getText().equals("")) {
            //ADD FORMATTING GUIDELINES
            haveFloor = true;
        } else
            errFloor.setText("You need to set a floor name.");

        return (haveBuilding && haveFile && haveFloor);
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    private void copyFile(File sourceFile, File destinationFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(
                    destinationFile);

            int bufferSize;
            byte[] bufffer = new byte[512];
            while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                fileOutputStream.write(bufffer, 0, bufferSize);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
