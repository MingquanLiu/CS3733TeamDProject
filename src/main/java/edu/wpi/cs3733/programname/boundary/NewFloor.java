package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NewFloor {


    @FXML
    private TextField buildingName;
    @FXML
    private TextField floorName;

    @FXML
    private Label imageName;
    @FXML
    private Label errorMessage;

    @FXML
    private JFXButton btnUpload;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXButton btnCancel;

    File selectedFile;

    private Floor floor;

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
        errorMessage.setText("");
        if (selectedFile != null) {
            String extension = getFileExtension(selectedFile);
            if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
                if (!buildingName.getText().equals("")) {
                    try {
                        String relPath = "src\\main\\resources\\img\\";
                        String buildName = buildingName.getText() + floorName.getText() + "." + extension;
                        File file = new File(relPath + buildName);
                        copyFile(selectedFile, file);
                        System.out.println("");
                        floor = new Floor(floorName.getText(), buildingName.getText(), "img/"+buildName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                    errorMessage.setText("You need to select the building.");
            }
        } else {
            if (buildingName.getText().equals("")) {
                errorMessage.setText("You need to upload an image and select the building.");
            }
            else
                errorMessage.setText("You need to upload an image.");
        }

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

    public Floor getFloor(){
        return floor;
    }
}
