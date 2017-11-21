package edu.wpi.cs3733.programname.boundary.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class TestingController {
    @FXML
    private ImageView imgMap;
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private Slider slideMapZoom;

    //map switching objects
    @FXML
    private Button btnMapUp;
    @FXML
    private Button btnMapDwn;
    @FXML
    private Label lblCurrentFloor;
    private int floor = 2;

    //zoom and pan objects
    @FXML
    private Button btnZoomIn;
    @FXML
    private Button btnZoomOut;
    private double currentScale = 1.0;

    //login popup objects
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblLoginStatus;

    /*
    private ChangeListener<Number> zoomChange = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            imgMap.setFitHeight((double)newValue * 1000.0);
            imgMap.setFitWidth((double)newValue * 1000.0);
        }
    };
    */

    //topmost methods are newest

    //popup methods
    public void popupHandler(ActionEvent e){
        if(e.getSource() == btnLogin){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("Login_Popup.fxml")));
            Scene newScene;
            try {
                newScene = new Scene(loader.load());
            } catch(IOException ex){
                //Todo add some sort of error handling
                return;
            }

            Stage loginStage = new Stage();
            loginStage.setScene(newScene);
            loginStage.showAndWait();

            boolean loggedIn = loader.<LoginPopup>getController().getLoggedIn();

            lblLoginStatus.setText("logged in");


        }
    }
    //map zooming method
    public void zoomHandler(ActionEvent e){
        if(e.getSource() == btnZoomOut){
            imgMap.setFitWidth(imgMap.getFitWidth()*.9);
            currentScale *= 0.9;
        }
        else{
            imgMap.setFitWidth(imgMap.getFitWidth()*1.1);
            currentScale *= 1.1;
        }
    }

    //map switching methods
    public void mapChange(ActionEvent e){
        if(e.getSource() == btnMapUp && floor < 3){
            floor ++;
            System.out.println("up to floor" + floor);
            setFloor();
        }
        else if (e.getSource() == btnMapDwn && floor > -2){
            floor --;
            System.out.println("down to floor" + floor);
            setFloor();
        }

    }
    public void setFloor(){
        Image oldImg = imgMap.getImage();
        String oldUrl = oldImg.impl_getUrl();  //using a deprecated method for lack of a better solution currently
        System.out.println("old image: " + oldUrl);

        String newUrl = oldUrl.substring(0,oldUrl.indexOf("Floor_")) + "Floor_" + floor + ".png";
        System.out.println("new image: " + newUrl);

        File file = new File(newUrl);
        System.out.println("current map: " + file.toString());
        Image newImg = new Image(file.toString());
        imgMap.setImage(newImg);

        lblCurrentFloor.setText("" + floor);
    }
    public void showMouseCoords(MouseEvent e){
        System.out.println(e.getX() + ", " + e.getY());
    }

    /*
    public void handleZoom(ActionEvent e) {
        System.out.println("zooming");
            double change = slideMapZoom.getValue();
            imgMap.setScaleX(imgMap.getScaleX()*change);
            imgMap.setScaleY(imgMap.getScaleY()*change);

    }
    */
}
