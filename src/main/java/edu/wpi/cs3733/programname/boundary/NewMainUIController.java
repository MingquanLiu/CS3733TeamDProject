package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.boundary.*;
import edu.wpi.cs3733.programname.boundary.observers.MapObserver;
import edu.wpi.cs3733.programname.boundary.observers.RequestObserver;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;


public class NewMainUIController implements Initializable {


    //FXML objects
    //<editor-fold dsec="main panes">
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private AnchorPane panningPane;
    //</editor-fold>

    //<editor-fold desc="map changing">
    @FXML
    private JFXButton btnMapUp;
    @FXML
    private JFXButton btnMapDwn;
    @FXML
    private ImageView imgMap;
    //</editor-fold>

    //<editor-fold desc="zoom and pan objects">
    @FXML
    private JFXButton btnZoomIn;
    @FXML
    private JFXButton btnZoomOut;
    @FXML
    private JFXSlider slideZoom;
    //</editor-fold>
    //<editor-fold desc="directions pane">
    //about page stuff
    @FXML
    private AnchorPane adminFeaturePane;
    @FXML
    private JFXButton adminFeatureSubject;
    @FXML
    private JFXButton mapEdit;
    @FXML
    private JFXButton employeeManager;
    @FXML
    private JFXButton serviceRequestSubject;
    @FXML
    private JFXButton maintenanceServiceRequest;
    @FXML
    private JFXButton interpreterServiceRequest;
    @FXML
    private JFXButton transportationServiceRequest;
    //<editor-fold desc="handicapped">
    @FXML
    private CheckBox handicap;
    //</editor-fold>
    @FXML
    private JFXButton keyLocationRetail;
    @FXML
    private JFXButton keyLocationBathroom;
    @FXML
    private JFXButton keyLocationWaitingroom;
    @FXML
    private JFXButton keyLocationElevator;
    @FXML
    private JFXButton keyLocationDestination;
    @FXML
    private JFXButton keyLocationExit;
    @FXML
    private JFXButton keyLocationSubject;
    @FXML
    private JFXButton keyLocationLab;
    @FXML
    private JFXButton keyLocationServiceDesk;
    @FXML
    private JFXButton keyLocationStairs;
    @FXML
    private AnchorPane keyLocationPane;

    /*
    *global variables, not FXML tied
    */

    private ManageController manager;
    private double currentScale;
    private final double MAX_UI_WIDTH = 5000;
    private boolean serviceRequestSubjectClicked =false;
    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initManager(ManageController manageController) {
        manager = manageController;
        instantiateNodeList();

        currentScale = 0.3;
        setZoom();

        slideZoom.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal){
                currentScale = newVal.doubleValue()/10;
                System.out.println("scale" + currentScale);
                setZoom();
            }
        });
    }

    //Locate Bathroom/ Service desk/ VendingMachine JFXButton Handler
    public void locateHandler(ActionEvent event) {
        Object mEvent = event.getSource();
        String nodeType = "";

            String keyLocationString = event.getClass().toString();
            switch (keyLocationString) {
                case "Bathrooms":
                    nodeType = "REST";
                    break;
                case "Service Desks":
                    nodeType = "INFO";
                    break;
                case "Retail Services":
                    nodeType = "RETL";
                    break;
                case "Waiting Rooms":
                    nodeType = "DEPT";
                    break;
                case "Elevators":
                    nodeType = "ELEV";
                    break;
                case "Exits":
                    nodeType = "EXIT";
                    break;
                case "Staircases":
                    nodeType = "STAI";
                    break;
                case "Labs":
                    nodeType = "LABS";
                    break;
                case "Additional Services":
                    nodeType = "SERV";
                    break;
                case "All Locations":
                    //THIS IS NOT A REAL NODE TYPE ITS JUST TO ALLOW IT WORK
                    nodeType = "ALL";
                    break;
                case "None":
                    nodeType = "None";
                    break;
            }
    }
    //map zooming method
    private void setZoom(){
        imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        /*
        if (!(currentPath == null) && !currentPath.isEmpty()) {
            List<NodeData> mPath = currentPath;
            clearPath();
            displayPath(mPath);
        }
        setNodeListSizeAndLocation(currentNodes, currentScale);
        relocateNodeInfo();
        */
    }


    //updates zoom slider to match current zoom scale
    private void updateZoomSlider(){
        slideZoom.setValue(currentScale*10);
    }

    public void zoomHandler(ActionEvent e) {
        if (e.getSource() == btnZoomOut){
            currentScale = Math.max(currentScale-.08, .3);
        }
        else if (e.getSource() == btnZoomIn){
            currentScale = Math.min(currentScale+.08, .6);
        }

        setZoom();
        updateZoomSlider();
    }

    public void instantiateNodeList(){
        JFXNodesList nodesList = new JFXNodesList();
        JFXNodesList nodesList1 = new JFXNodesList();
        JFXNodesList keyLocationNodeList = new JFXNodesList();
        nodesList.addAnimatedNode(adminFeatureSubject, new Callback<Boolean, Collection<KeyValue>>() {
            @Override
            public Collection<KeyValue> call(Boolean expanded) {
                return new ArrayList<KeyValue>(){
                    {add(new KeyValue(adminFeatureSubject.rotateProperty(),expanded ? 360:0, Interpolator.EASE_BOTH) );}
                };
            }
        });
        nodesList.addAnimatedNode(mapEdit);
        nodesList.addAnimatedNode(employeeManager);
        serviceRequestSubject.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
//            int i =serviceRequestSubject.getStyleClass().size();
            if(serviceRequestSubjectClicked){
                serviceRequestSubject.getStyleClass().remove("color-button-serviceRequest");
                serviceRequestSubject.getStyleClass().add("color-button-adminFeature");
            }else{
                serviceRequestSubject.getStyleClass().remove("color-button-adminFeature");
                serviceRequestSubject.getStyleClass().add("color-button-serviceRequest");
            }
            serviceRequestSubjectClicked = !serviceRequestSubjectClicked;

    });
        nodesList1.addAnimatedNode(serviceRequestSubject
                ,new Callback<Boolean, Collection<KeyValue>>() {
            @Override
            public Collection<KeyValue> call(Boolean expanded) {
                return new ArrayList<KeyValue>(){
                    {add(new KeyValue(serviceRequestSubject.rotateProperty(),expanded ? 0:270, Interpolator.EASE_BOTH) );}
                };
            }
        });
        nodesList1.addAnimatedNode(interpreterServiceRequest);
        nodesList1.addAnimatedNode(maintenanceServiceRequest);
        nodesList1.addAnimatedNode(transportationServiceRequest);
        nodesList1.setSpacing(10);
//        nodesList1.getTransforms().add(new Rotate(serviceRequestSubject.getLayoutX(),serviceRequestSubject.getLayoutY(),90));
        nodesList1.setRotate(90);
        nodesList.addAnimatedNode(nodesList1);
        nodesList.setSpacing(10);
        adminFeaturePane.getChildren().add(nodesList);
        keyLocationNodeList.addAnimatedNode(keyLocationSubject, new Callback<Boolean, Collection<KeyValue>>() {
            @Override
            public Collection<KeyValue> call(Boolean expanded) {
                return new ArrayList<KeyValue>(){
                    {add(new KeyValue(keyLocationSubject.rotateProperty(),expanded ? 360:0, Interpolator.EASE_BOTH) );}
                };
            }
        });
        keyLocationNodeList.addAnimatedNode(keyLocationDestination);
        keyLocationNodeList.addAnimatedNode(keyLocationBathroom);
        keyLocationNodeList.addAnimatedNode(keyLocationElevator);
        keyLocationNodeList.addAnimatedNode(keyLocationExit);
        keyLocationNodeList.addAnimatedNode(keyLocationLab);
        keyLocationNodeList.addAnimatedNode(keyLocationRetail);
        keyLocationNodeList.addAnimatedNode(keyLocationStairs);
        keyLocationNodeList.addAnimatedNode(keyLocationWaitingroom);
        keyLocationNodeList.addAnimatedNode(keyLocationServiceDesk);
        keyLocationNodeList.setSpacing(10);
        keyLocationPane.getChildren().add(keyLocationNodeList);
        AnchorPane.setTopAnchor(keyLocationNodeList,5.00);
        AnchorPane.setLeftAnchor(keyLocationNodeList,10.0);
        AnchorPane.setTopAnchor(nodesList,5.00);
        AnchorPane.setRightAnchor(nodesList,10.0);
    }

}
