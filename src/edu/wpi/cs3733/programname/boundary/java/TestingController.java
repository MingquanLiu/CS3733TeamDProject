package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.scene.Node;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class TestingController implements Initializable{

    //FXML objects
    @FXML
    private StackPane drawingStack;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private Canvas drawingCanvas;
    @FXML
    private ImageView imgMap;
    @FXML
    private AnchorPane panningPane;

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

    //login popup objects
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblLoginStatus;

    //hamburger pane and transitions
    @FXML
    private JFXHamburger burger;
    @FXML
    private AnchorPane paneControls;


    //location search
    @FXML
    private Button btnGo;
    @FXML
    private Button clear;
    @FXML
    private TextField txtStartLocation;
    @FXML
    private TextField txtEndLocation;


    //global variables, not FXML tied
    private ManageController manager;

    //locations search
    private List<Shape> drawings = new ArrayList<>();
    private GraphicsContext gc;
    private List<NodeData> currentPath;

    //hamburger transitions
    private HamburgerSlideCloseTransition burgerTransition;
    private boolean controlsVisible = false;
    private FadeTransition controlsTransition;

    //zooming/panning
    private double currentScale;
    final double minWidth = 1500;
    final double maxWidth = 5000;



    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb){
        burgerTransition = new HamburgerSlideCloseTransition(burger);
        burgerTransition.setRate(-1);

        controlsTransition = new FadeTransition(new Duration(500), paneControls);
        controlsTransition.setFromValue(0);
        controlsTransition.setToValue(1);
        paneControls.setVisible(controlsVisible);

        imgMap.setFitWidth(imgMap.getFitWidth()*0.3138);
        //panningPane.setPrefWidth(panningPane.getPrefWidth()*0.3138);
        currentScale = 0.3138;

        manager = new ManageController();

        //gc = drawingCanvas.getGraphicsContext2D();
    }

    //topmost methods are newest

    //pathing functions
    public void goButtonHandler(){
        System.out.println("drawing path");
        currentPath = manager.startPathfind(txtStartLocation.getText(), txtEndLocation.getText());
        displayPath(currentPath);
    }

    private void displayPath(List<NodeData> path){
        clearMain();
        System.out.println("drawing path");
        NodeData prev = path.get(0);
        int x = (int) (prev.getX()*currentScale);
        int y = (int) (prev.getY()*currentScale);
        System.out.println(x + ", " + y);
        ArrayList<Line> lines = new ArrayList<>();
        for(int i = 1; i < path.size(); i++){
            Line l = new Line();
            NodeData n = path.get(i);
            l.setStroke(Color.BLUE);
            l.setStrokeWidth(5.0);
            l.setStartX(prev.getX()*currentScale);
            l.setStartY(prev.getY()*currentScale);
            l.setEndX(n.getX()*currentScale);
            l.setEndY(n.getY()*currentScale);
            lines.add(l);
            prev = n;
        }
        drawings.addAll(lines);
        panningPane.getChildren().addAll(lines);
    }

    private void clearMain(){
        if(drawings.size() > 0){
            for(Shape shape:drawings){
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
    }


    //hamburger handling
    public void openMenu(MouseEvent e){
        burgerTransition.setRate(burgerTransition.getRate()*-1);
        burgerTransition.play();

        controlsVisible = !controlsVisible;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        controlsTransition.setToValue(Math.abs(controlsTransition.getToValue()-1));         //these two lines should make it fade out the next time you click
        controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue()-1));     // but they doent work the way I want them to for some reason
    }

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
            if(imgMap.getFitWidth() <= minWidth){
                return;
            }
            imgMap.setFitWidth(Math.max(imgMap.getFitWidth()*.9,minWidth));
            currentScale *= 0.9;
        }
        else{
            if(imgMap.getFitWidth() >= maxWidth){
                return;
            }
            imgMap.setFitWidth(Math.min(imgMap.getFitWidth()*1.1, maxWidth));
            currentScale *= 1.1;
        }
        if(!(currentPath == null)) {
            displayPath(currentPath);
        }
        System.out.println(currentScale);
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

}
