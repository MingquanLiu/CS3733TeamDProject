package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.programname.ManageController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.*;

public class NewBuilding {


    @FXML
    private Label imageName;
    @FXML
    private Label errUpload;
    @FXML
    private Label errFloor;
    @FXML
    private Label errBuilding;

    @FXML
    private JFXButton btnUpload;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXButton btnCancel;

    @FXML
    private TextField buildingName;
    @FXML
    private TextField floorName;

    File selectedFile;
    String filepath;
    ManageController manager;

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

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

    public void onSubmit() {
        boolean haveFile = false, haveFloor = false, haveBuilding = false;
        String extension = "";
        errUpload.setText("");
        errBuilding.setText("");
        errFloor.setText("");
        if (selectedFile == null) {

        }
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

        if (haveFile && haveBuilding && haveFloor) {
            try {
                //relative path from main folder to the images folder where we store the floors
                String relPathMaps = "floorMaps/";

                //naming the new file based on the name given
                String fileName = floorName.getText() + "." + extension;
                //for later use pulling up the floor
                filepath = "file:floorMaps/" + fileName;

                //creating the two new files
                File fileMap = new File(relPathMaps + fileName);

                //copying them to the right locations
                copyFile(selectedFile, fileMap);

                //print to make sure, then hide the menu
                System.out.println("\n\n\nCreated new building!");

                initManager(manager);
                //manager.updateBuilding(buildingName.getText());
                onCancelButton();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("some error happened");
            }

        }

    }

    public void initManager(ManageController manageController){
        System.out.println("init For Login");
        manager = manageController;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public void copyFile(File sourceFile, File destinationFile) {
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

    public Building getBldg() {
        Building bldg = new Building(buildingName.getText());
        //Floor fl = new Floor(floorName.getText(), bldg.getName(), filepath);
        //bldg.addFloor(fl);

        return bldg;
    }

    public void onCancelButton() {
        btnCancel.getScene().getWindow().hide();
    }

}
