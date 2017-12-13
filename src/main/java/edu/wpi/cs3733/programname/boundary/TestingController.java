package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.boundary.observers.AbsObserver;
import edu.wpi.cs3733.programname.boundary.observers.MapObserver;
import edu.wpi.cs3733.programname.boundary.observers.RequestObserver;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.database.DbObservable;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static edu.wpi.cs3733.programname.commondata.Constants.*;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.convertFloor;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;
import static edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType.ASTAR;
import static edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType.BEAM;
import static javafx.scene.paint.Color.DARKBLUE;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;


public class TestingController extends UIController implements Initializable {


    //FXML objects
    //<editor-fold dsec="main panes">
    @FXML
    private StackPane drawingStack;
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private Canvas drawingCanvas;
    @FXML
    private AnchorPane panningPane;
    @FXML
    private AnchorPane serviceRequester;
    //</editor-fold>

    //<editor-fold desc="map changing">
    @FXML
    private JFXButton btnMapUp;
    @FXML
    private JFXButton btnMapDwn;
    @FXML
    private ComboBox comboFloors;
    @FXML
    private ComboBox comboBuilding;
    @FXML
    private ImageView imgMap;
    @FXML
    private Button btnFloorAdd;
    //</editor-fold>

    //<editor-fold desc="zoom and pan objects">
    @FXML
    private JFXButton btnZoomIn;
    @FXML
    private JFXButton btnZoomOut;
    @FXML
    private Slider slideZoom;
    //</editor-fold>

    //<editor-fold desc="Admin features">
    @FXML
    private GridPane paneAdminFeatures;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label lblLoginStatus;
    @FXML
    private JFXButton btnOpenAdmin;
    @FXML
    private JFXButton btnInterpreterReq;
    @FXML
    private JFXButton btnMaintenanceReq;
    @FXML
    private JFXButton btnTransportationReq;
    @FXML
    private JFXButton btnMapEdit;
    @FXML
    private JFXButton viewMyTasks;
    @FXML
    private JFXButton manageEmployees;
    //</editor-fold>

    //<editor-fold desc="key locations buttons">
    @FXML
    private ComboBox comboLocations;
    @FXML
    private JFXButton btnEditEmployees;

    @FXML
    private JFXButton btnLocateBR;
    @FXML
    private JFXButton btnLocateVM;
    @FXML
    private JFXButton btnLocateSD;
    @FXML
    private JFXButton btnLocateWR;
    @FXML
    private JFXButton btnLocateEV;
    @FXML
    private JFXButton btnLocateEX;
    @FXML
    private JFXButton btnLocateST;
    //</editor-fold>

    //<editor-fold desc="service request popup">
    @FXML
    private TextArea requestDescription;
    @FXML
    private JFXButton btnSelectMaintenanceLocation;
    @FXML
    private JFXButton btnCancelRequestAttempt;
    @FXML
    private JFXButton btnSubmitRequest;
    @FXML
    private Label lblServiceLocation;
    @FXML
    private Label lblServiceY;
    @FXML
    private Label lblServiceX;
    @FXML
    private Label lblReqType;
    //</editor-fold>

    //<editor-fold desc="hamburger and pane">
    @FXML
    private JFXHamburger burger;
    @FXML
    private AnchorPane paneControls;
    //</editor-fold>

    //<editor-fold desc="search box">
    @FXML
    private JFXButton btnGo;
    @FXML
    private JFXButton btnClear;
    @FXML
    private TextField txtStartLocation;
    @FXML
    private TextField txtEndLocation;
    //</editor-fold>

    //<editor-fold desc="node info pane">
    @FXML
    private DialogPane nodeInfoPane;
    @FXML
    private Label nodeInfoType;
    @FXML
    private Label nodeInfoShortName;
    @FXML
    private Label nodeInfoLongName;
    @FXML
    private Label lblNodeX;
    @FXML
    private Label lblNodeY;
    //</editor-fold>

    //<editor-fold desc="username">
    @FXML
    private JFXTextField txtUser;
    //</editor-fold>

    //<editor-fold desc="FAQ">
    @FXML
    private Button helpButton;
    //</editor-fold>

    //<editor-fold desc="email">
    @FXML
    private JFXButton emailDirections;
    //</editor-fold>

    //<editor-fold desc="directions pane">
    @FXML
    private TitledPane paneDirections;
    @FXML
    private Label txtAreaDirections;
    //</editor-fold>

    //about page stuff
    @FXML
    private JFXButton aboutBtn;

    //items for key locations fancy feature
    @FXML
    private TitledPane keyLocation;
    @FXML
    private JFXCheckBox locateBathrooms;
    @FXML
    private JFXCheckBox locateServiceDesks;
    @FXML
    private JFXCheckBox locateRetailServices;
    @FXML
    private JFXCheckBox locateWaitingRooms;
    @FXML
    private JFXCheckBox locateElevators;
    @FXML
    private JFXCheckBox locateExits;
    @FXML
    private JFXCheckBox locateStaircases;
    @FXML
    private JFXCheckBox locateLabs;
    @FXML
    private JFXCheckBox locateAdditionalServices;
    @FXML
    private JFXCheckBox locateAllLocations;

    @FXML
    private JFXComboBox<Image> comboCharacter;
    private ImageView character;

    @FXML
    private Label lblCrossFloor;
    @FXML
    private JFXButton crossFloor;


    private String previousDropDownState = "";
    // Handicapped checkbox
    //<editor-fold desc="handicapped">
    @FXML
    private CheckBox handicap;
    //</editor-fold>

    /*
    *global variables, not FXML tied
    */

    @FXML
    private JFXButton btnViewMyRequests;

    //global variables, not FXML tied
    private ManageController manager;

    //<editor-fold desc="locations search">
    private List<Shape> pathDrawings = new ArrayList<>();
    private GraphicsContext gc;
    private List<NodeData> currentPath;

    private List<NodeData> currentNodes = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="hamburger transition">
    private HamburgerSlideCloseTransition burgerTransition;
    private boolean controlsVisible = false;
    private FadeTransition controlsTransition;
    //</editor-fold>

    //<editor-fold desc="zooming/panning">
    private double currentScale;
    private final double MAX_UI_WIDTH = 5000;
    //</editor-fold>

    //<editor-fold desc="map change">
    private int floor = 2;
    private ArrayList<Floor> floors = new ArrayList<>();
    private Floor currentFloor;
    private String currentPathStartFloor = "";
    private String currentPathGoalFloor = "";
    private Coordinate currentStartFloorLoc;
    private Coordinate currentGoalFloorLoc;
    private ArrayList<Building> buildings = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="node info">
    private String selectingLocation = "";
    private ArrayList<Double> mapRatio = new ArrayList<>();
    private boolean loggedIn;
    private String userName = null;
    private Employee employeeLoggedIn;
    private List<Shape> shownNodes = new ArrayList<>();
    //</editor-fold>
    private NodeData lastShowNodeData = null;
    private boolean logOffNext = false;
    private String SRSelectType = null;

    int timesCalled = 0;

    private MapObserver mapObserver;
    private RequestObserver requestObserver;
    private ArrayList<Transition> transitions = new ArrayList<>();
    private ArrayList<ImageView> drawnImages = new ArrayList<>();

    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initManager(ManageController manageController) {
        manager = manageController;
//        mapRatio.add(0.24);
        paneAdminFeatures.setVisible(false);
        mapRatio.add(0.318);
        mapRatio.add(0.35);
        mapRatio.add(0.39);
        mapRatio.add(0.43);
        mapRatio.add(0.48);
        mapRatio.add(0.55);
        mapRatio.add(0.60);
        burgerTransition = new HamburgerSlideCloseTransition(burger);
        burgerTransition.setRate(-1);

        controlsTransition = new FadeTransition(new Duration(500), paneControls);
        controlsTransition.setFromValue(0);
        controlsTransition.setToValue(1);
        paneControls.setVisible(controlsVisible);
        //currentScale = mapRatio.get(AppSettings.getInstance().getMapRatioIndex());
        currentScale = 0.3;
        setZoom();
//        allNodes = manageController.queryNodeByFloor(convertFloor(floor));
//        allNodes = manageController.getAllNodeData();
        currentNodes = manager.queryNodeByFloor(convertFloor(floor));
        setNodeListImageVisibility(false,setNodeListController(setNodeListSizeAndLocation(initNodeListImage(currentNodes),currentScale),this));  ;
        showNodeList(currentNodes);
//        panningPane.getChildren().add(imv);

        ObservableList locations = FXCollections.observableArrayList(
                "None",
                "Bathrooms",
                "Service Desks",
                "Retail Services",
                "Waiting Rooms",
                "Elevators",
                "Exits",
                "Staircases",
                "Labs",
                "Additional Services",
                "All Locations");
        comboLocations.setItems(locations);
        comboLocations.setValue("None");

        Floor basement2 = new Floor("L2", "45 Francis", "L2", "file:floorMaps/Floor_-2.png");
        Floor basement1 = new Floor("L1", "45 Francis", "L1", "file:floorMaps/Floor_-1.png");
        Floor ground = new Floor("G", "45 Francis", "G", "file:floorMaps/Floor_0.png");
        Floor floor1 = new Floor("1", "45 Francis", "1", "file:floorMaps/Floor_1.png");
        Floor floor2 = new Floor("2", "45 Francis", "2", "file:floorMaps/Floor_2.png");
        Floor floor3 = new Floor("3", "45 Francis", "3", "file:floorMaps/Floor_3.png");

        ArrayList<Floor> basicFloors = new ArrayList<>();
        basicFloors.add(basement2);
        basicFloors.add(basement1);
        basicFloors.add(ground);
        basicFloors.add(floor1);
        basicFloors.add(floor2);
        basicFloors.add(floor3);

        Building hospital = new Building("Hospital");
        hospital.addAllFloors(basicFloors);

        floors.addAll(hospital.getFloors());
        buildings.add(hospital);

        floor = 4;

        ObservableList floorList = FXCollections.observableList(new ArrayList<>());
        floorList.addAll(floors);
        comboFloors.setItems(floorList);

        ObservableList buildingList = FXCollections.observableList(new ArrayList<>());
        buildingList.addAll(buildings);
        comboBuilding.setItems(buildingList);

        setBuilding(hospital);
        //setFloor(floor2);


        //create big grid
        GridPane bigGrid = new GridPane();


        //create titled pane
        VBox content = new VBox();

        bigGrid.add(content, 0, 0);

        //create grid
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));


        //add checkboxes to grid
        grid.add(locateBathrooms, 0, 0);
        grid.add(locateServiceDesks, 0, 1);
        grid.add(locateRetailServices, 0, 2);
        grid.add(locateWaitingRooms, 0, 3);
        grid.add(locateElevators, 0, 4);
        grid.add(locateExits, 0, 5);
        grid.add(locateStaircases, 0, 6);
        grid.add(locateLabs, 0, 7);
        grid.add(locateAdditionalServices, 0, 8);
        grid.add(locateAllLocations, 0, 9);
        keyLocation.setContent(content);
        keyLocation.setText("TRIALTRIAL");
        keyLocation.setCollapsible(false);
        keyLocation.setExpanded(false);
        System.out.println("Expandable:" + keyLocation.isExpanded());
        System.out.println("Animated:" + keyLocation.isAnimated());
        System.out.println("Collapse:" + keyLocation.isCollapsible());
        keyLocation.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                keyLocation.setLayoutY(1000);
                System.out.println("Clicked");
            }
        });
        //add grid to titled pane
        grid.setLayoutX(10);
        grid.setLayoutY(10);
        content.getChildren().add(grid);

        ArrayList<AbsObserver> observerList = new ArrayList<>();
        requestObserver = new RequestObserver(this);
        observerList.add(requestObserver);
        mapObserver = new MapObserver(this);
        observerList.add(mapObserver);
        manager.initializeObservable(observerList);

        //lblCrossFloor.setVisible(false);
        crossFloor.setVisible(false);

        paneControls.setPickOnBounds(false);

        slideZoom.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {

                if (changing) {
                    //pauseTransitions();
                } else {
                    resumeTransitions();
                }
            }
        });
        slideZoom.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal){
                currentScale = newVal.doubleValue()/10;
                pauseTransitions();
                setZoom();
            }
        });

        Image walkingMan = new Image("img/walkingBlue.gif");
        Image runningBatman = new Image("img/batmanRun.gif");
        Image wong = new Image("img/wong.gif");

        ObservableList characters = FXCollections.observableList(new ArrayList<>());
        characters.add(walkingMan);
        characters.add(runningBatman);
        characters.add(wong);

        comboCharacter.getItems().addAll(characters);
        //comboCharacter.setButtonCell(new ImageListCell());
        //comboCharacter.setCellFactory(listView -> new ImageListCell());
        comboCharacter.setValue(walkingMan);
//        comboCharacter.getSelectionModel().select(0);

    }
//    private class ImageListCell extends ListCell<Image> {
//        private final ImageView view;
//
//        ImageListCell() {
//            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//            view = new ImageView();
//        }
//
//        @Override
//        protected void updateItem(Image item, boolean empty) {
//            super.updateItem(item, empty);
//
//            if (item == null || empty) {
//                setGraphic(null);
//            } else {
//                view.setImage(item);
//                setGraphic(view);
//            }
//        }
//    }

    public void selectCharacter(){
        displayPath(currentPath);
    }
    private void pauseTransitions() {
        if (transitions.size() > 0) {
            for (Transition t : transitions) {
                t.pause();
            }
        }
    }
    private void resumeTransitions(){
        if(transitions.size() > 0){
            for(Transition t:transitions){
                t.play();
            }
        }
    }


    public void setSearchType(PathfindingController.searchType searchType) {
        AppSettings.getInstance().setSearchType(searchType);
    }

    //topmost methods are newest
//    private void drawCycle(int x, int y){
//        double radius = 10*currentScale;
//        Circle c = new Circle(x, y, radius, RED);
//        panningPane.getChildren().add(c);
//        shownNodes.add(c);
//    }

    private void showNodeList(List<NodeData> nodeDataList) {
        for (int i = 0; i < nodeDataList.size(); i++) {
            showNode(nodeDataList.get(i));
        }
    }

    private void showNode(NodeData n) {
        n.setImageViewSizeAndLocation(currentScale);
        panningPane.getChildren().add(n.getNodeImageView());
    }

    private void showNodeInfo(NodeData nodeData) {
        int dbX = nodeData.getXCoord();
        int dbY = nodeData.getYCoord();
        System.out.println("Node Coordinate: " + dbX + "," + dbY + " Node Name: " + nodeData.getLongName());
        nodeInfoPane.setVisible(true);
        nodeInfoPane.setLayoutX(DBCToUIC(dbX, currentScale) + 3);
        nodeInfoPane.setLayoutY(DBCToUIC(dbY, currentScale) + 3);
        nodeInfoPane.setVisible(true);
        //nodeInfoLocation.setText(dbX + ", " + dbY);
        lblNodeX.setText(dbX + "");
        lblNodeY.setText(dbY + "");
        nodeInfoType.setText("" + nodeData.getNodeType());
        nodeInfoLongName.setText("" + nodeData.getLongName());
        nodeInfoShortName.setText("" + nodeData.getShortName());
        nodeInfoPane.toFront();
    }

    //displaying node info on click

    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY) {
        int dbX = UICToDBC(mouseX, currentScale);
        int dbY = UICToDBC(mouseY, currentScale);
        NodeData mNodeData = null;
        double d = 0;
        for (NodeData node : nodeDataList) {
            int nodeX = node.getXCoord();
            int nodeY = node.getYCoord();
            double temp = Math.sqrt(Math.pow(dbX - nodeX, 2) + Math.pow(dbY - nodeY, 2));
            if (temp < d || d == 0) {
                d = temp;
                mNodeData = node;
            }
        }
        return mNodeData;
    }
    //clearing functions
    public void clearMain() {
        clearPath();
        //lblCrossFloor.setVisible(false);
        crossFloor.setVisible(false);
        closeNodeInfoHandler();
        clearPathFindLoc();
        //lastShowNodeData.setImageVisible(false);
    }
    private void clearPath() {
        currentPath = new ArrayList<>();
        if (pathDrawings.size() > 0) {
            for (Shape shape : pathDrawings) {
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            currentPathStartFloor = "";
            currentPathGoalFloor = "";
            pathDrawings = new ArrayList<>();

        }
        clearAnimations();
    }
    public void clearAnimations(){
        if(!transitions.isEmpty()) {
            for (Transition t : transitions) {
                t = new PathTransition();
                t.stop();
            }
        }
        transitions = new ArrayList<>();
        if (drawnImages.size() > 0){
            for(ImageView img:drawnImages){
                panningPane.getChildren().remove(img);
            }
        }
    }
    private void pathAnimation(List<NodeData> nodes){
        clearAnimations();
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(DBCToUIC(nodes.get(0).getXCoord(),currentScale));
        moveTo.setY(DBCToUIC(nodes.get(0).getYCoord(),currentScale));
        path.getElements().add(moveTo);
        for (int i = 1; i < nodes.size(); i++){
            LineTo lineTo = new LineTo();
            lineTo.setX(DBCToUIC(nodes.get(i).getXCoord(),currentScale));
            lineTo.setY(DBCToUIC(nodes.get(i).getYCoord(),currentScale));
            path.getElements().add(lineTo);

        }
        //panningPane.getChildren().addAll(path);

        String imgUrl = ((Image)(comboCharacter.getValue())).impl_getUrl();
        System.out.println(imgUrl);
        ImageView walkingMan = new ImageView(imgUrl);
        walkingMan.setPreserveRatio(true);
        walkingMan.setFitWidth(200*currentScale);

        PathTransition pathTransition = new PathTransition();

        int distance = distanceBetweenNodes(nodes.get(0), nodes.get(nodes.size()-1));
        pathTransition.setDuration(Duration.millis(distance*10));
        pathTransition.setNode(walkingMan);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(100);
        panningPane.getChildren().add(walkingMan);
        walkingMan.toFront();
        pathTransition.setAutoReverse(false);
        pathTransition.orientationProperty().addListener(new ChangeListener<PathTransition.OrientationType>() {
            @Override
            public void changed(ObservableValue<? extends PathTransition.OrientationType> observable, PathTransition.OrientationType oldValue, PathTransition.OrientationType newValue) {
                System.out.println("orientation change");
            }
        });

        pathTransition.play();

        drawnImages.add(walkingMan);
        transitions.add(pathTransition);
    }

    private void displayPath(List<NodeData> path) {
        if (path != null && !path.isEmpty()) {
            clearPath();                    //first thing to do is clear any visible path and animations
            currentPath = path;
            System.out.println("drawing path");
            NodeData prev = path.get(0);
            int x = (int) (prev.getXCoord() * currentScale);
            int y = (int) (prev.getYCoord() * currentScale);
            System.out.println(x + ", " + y);

            ArrayList<NodeData> thisFloorPath = new ArrayList<>();
            ArrayList<Line> lines = new ArrayList<>();
            int startIndex = -1;
            for (int i = 1; i < path.size(); i++) {
                Line l = new Line();
                NodeData n = path.get(i);
//                if(i==1 || i == path.size()-1){
//                    n.
//                }

                if(i <= path.size()-2){     //has to be minus 2, so that you dont go to path.get(path.size()) since that wouldn't work
                    NodeData nextNode = path.get(i+1);
                    if(!n.getFloor().equals(nextNode.getFloor())){
                                currentPathStartFloor = n.getFloor();
                                currentStartFloorLoc = new Coordinate(n.getXCoord(), n.getYCoord());
                                currentPathGoalFloor = nextNode.getFloor();
                                currentGoalFloorLoc = new Coordinate(nextNode.getXCoord(), n.getYCoord());
//                        lblCrossFloor.setText("Proceed to Floor " + currentPathGoalFloor + System.lineSeparator()+ "From Floor " + currentPathStartFloor);
//                        lblCrossFloor.setLayoutX(DBCToUIC(n.getXCoord(), currentScale));
//                        lblCrossFloor.setLayoutY(DBCToUIC(n.getYCoord(), currentScale));
//                        lblCrossFloor.setVisible(true);
//                        lblCrossFloor.toFront();
                        crossFloor.setText("From floor " + currentPathGoalFloor + System.lineSeparator()+ "To Floor " + currentPathStartFloor);
                        crossFloor.setLayoutX(DBCToUIC(n.getXCoord(), currentScale));
                        crossFloor.setLayoutY(DBCToUIC(n.getYCoord(), currentScale));
                        crossFloor.setVisible(true);
                        crossFloor.toFront();
                    }
                }

//                Path path = new Path();
//                PathElement start = new moveTo(l.getStartX(), l.getStartY());
                if(n.getFloor().equals(convertFloor(floor))) {
                    System.out.println(n.getLongName());
                    if (prev.getFloor().equals(convertFloor(floor))) {
                        l.setStroke(Color.LIGHTSKYBLUE);
                        l.setStrokeWidth(10.0 * currentScale);
                        l.setStartX(prev.getXCoord() * currentScale);
                        l.setStartY(prev.getYCoord() * currentScale);
                        l.setEndX(n.getXCoord() * currentScale);
                        l.setEndY(n.getYCoord() * currentScale);
                        lines.add(l);
                    }
                }
                prev = n;
            }
            for(NodeData n:path){
                if(n.getFloor().equals(convertFloor(floor))){
                    thisFloorPath.add(n);
                }
            }
            if(thisFloorPath.size() > 0){
                pathAnimation(thisFloorPath);
            }
            pathDrawings.addAll(lines);
            panningPane.getChildren().addAll(lines);

            emailDirections.setVisible(true);
        }
    }

    public void crossFloor(){
        System.out.println("called crossFloor");
        System.out.println(currentFloor.getFloorName());
        System.out.println(currentPathStartFloor);
        System.out.println(currentPathGoalFloor);
        if(currentFloor.getFloorName().equals(currentPathGoalFloor)){
            for (Floor f:floors){
                if(f.getFloorName().equals(currentPathStartFloor)){
                    setFloor(f);
                }
            }
        }
        else if(currentFloor.getFloorName().equals(currentPathStartFloor)){
            for (Floor f:floors){
                if(f.getFloorName().equals(currentPathGoalFloor)){
                    setFloor(f);
                }
            }
        }
    }

    private void clearPathFindLoc() {
        txtEndLocation.setText("");
        txtStartLocation.setText("");
    }

    public void mapChange(ActionEvent e) {
        setFloor();
    }

    public void setBuilding(Building newBld) {
        System.out.println("building: " + newBld);
        comboBuilding.setValue(newBld);
        setBuilding();
    }

    public void setBuilding() {
        Building newBld = (Building) (comboBuilding.getValue());
        floors = newBld.getFloors();
        ObservableList floorList = FXCollections.observableList(new ArrayList<>());
        floorList.addAll(floors);

//        try {
        comboFloors.setItems(floorList);
        comboFloors.setValue(floorList.get(0));
        setFloor((Floor) floorList.get(0));
//        } catch (Exception e) {
//            System.out.println("SCREAM");
//        }
    }

    public void setFloor(Floor newFloor) {
        comboFloors.setValue(newFloor);
        setFloor();
    }

    public void setFloor() {
        //TODO add changing of displayed nodes
        //lblCrossFloor.setVisible(false);
        currentFloor = (Floor) (comboFloors.getValue());
        floor = floors.indexOf(currentFloor) - 2;
        System.out.println("floor: " + floor);

        String newUrl = currentFloor.getImgUrl();
        System.out.println("new image: " + newUrl);

        Image newImg = new Image(newUrl);
        System.out.println("about to be: " + newImg.getWidth());
        imgMap.setImage(newImg);
        //For changing the node
        clearCurrentNodeIMV(currentNodes);
        currentNodes = manager.queryNodeByFloor(convertFloor(floor));
        setNodeListImageVisibility(false,setNodeListController(setNodeListSizeAndLocation(initNodeListImage(currentNodes),currentScale),this));
        showNodeList(currentNodes);
//        clearPath();
        displayPath(currentPath);

        nodeInfoPane.setVisible(false);
//        if (currentPathStartFloor.equals("") || !currentPathGoalFloor.equals(currentFloor.getFloorName()) ||
//                currentPathGoalFloor.equals(currentFloor.getFloorName())) {
//            lblCrossFloor.setVisible(false);
//        }
//
//        if (currentFloor.getFloorName().equals(currentPathGoalFloor)) {
//            lblCrossFloor.setText("Proceed to Floor " + currentPathStartFloor + "!");
//            lblCrossFloor.setLayoutX(DBCToUIC(currentStartFloorLoc.getXCoord(), currentScale));
//            lblCrossFloor.setLayoutY(DBCToUIC(currentStartFloorLoc.getYCoord(), currentScale));
//            lblCrossFloor.setVisible(true);
//            lblCrossFloor.toFront();
//        } else if (currentFloor.getFloorName().equals(currentPathStartFloor)) {
//            lblCrossFloor.setText("Proceed to Floor " + currentPathGoalFloor + "!");
//            lblCrossFloor.setLayoutX(DBCToUIC(currentGoalFloorLoc.getXCoord(), currentScale));
//            lblCrossFloor.setLayoutY(DBCToUIC(currentGoalFloorLoc.getYCoord(), currentScale));
//            lblCrossFloor.setVisible(true);
//            lblCrossFloor.toFront();
//        }
        comboLocations.setValue("None");
        previousDropDownState = "";
        timesCalled++;
        System.out.println(timesCalled);
    }

    public void mouseClickHandler(MouseEvent e) throws IOException {
        int x = (int) e.getX();
        int y = (int) e.getY();
        if (!selectingLocation.equals("selectSRLocation")) {
            List<NodeData> mList = getNodeByVisibility(currentNodes, true);
            NodeData mNode = getClosestNode(mList, x, y);
//        if(lastShowNodeData!=null)lastShowNodeData.setImageVisible(false);
//        mNode.setImageVisible(true);
//        lastShowNodeData = mNode;
            if (mNode != null)
                showNodeInfo(mNode);
        } else {
            System.out.println("In selectSRLocation" + SRSelectType);
            popupSRWithCoord(getClosestNode(currentNodes, x, y), SRSelectType);
            selectingLocation = "";
        }
    }

    //hamburger handling
    public void openMenu(MouseEvent e) {
        setBurger();
    }

    public void setBurger() {
        burgerTransition.setRate(burgerTransition.getRate() * -1);
        burgerTransition.play();

        controlsVisible = !controlsVisible;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        //controlsTransition.setToValue(Math.abs(controlsTransition.getToValue()-1));         //these two lines should make it fade out the next time you click
        //controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue()-1));     // but they doent work the way I want them to for some reason
    }

    private void setBurgerFalse() {
        burgerTransition.setRate(burgerTransition.getRate() * -1);
        burgerTransition.play();

        controlsVisible = false;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        controlsTransition.setToValue(Math.abs(controlsTransition.getToValue() - 1));         //these two lines should make it fade out the next time you click
        controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue() - 1));     // but they doent work the way I want them to for some reason
    }

    //Locate Bathroom/ Service desk/ VendingMachine JFXButton Handler
    public void locateHandler(ActionEvent event) {
        Object mEvent = event.getSource();
        String nodeType = "";

        if (mEvent == comboLocations) {
            String keyLocationString = comboLocations.getValue().toString();
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
                case "Departments":
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

        if(!previousDropDownState.equals("All")){
            List<NodeData> ListA = getNodeByVisibility(currentNodes,true);
            for(NodeData nodeData:ListA){
                nodeData.setImageVisible(false);
                nodeData.changeImageView("");
            }
        }else{
            setNodeListImageVisibility(false,currentNodes);
        }
        if(nodeType.equals("None")){
            List<NodeData> mList = getNodeByVisibility(currentNodes,true);
            setNodeListImageVisibility(false,mList);
        }
        if (nodeType.equals("ALL")) {
            //ADD CODE HERE THANK YOU MINGQUANNNNNN
//            for(NodeData nodeData:currentNodes){
//                nodeData.changeImageView(nodeType);
//            }
            setNodeListImageVisibility(true,currentNodes);
        }
        if ((!nodeType.equals("")) && (!nodeType.equals("ALL"))) {

            List<NodeData> mList = getTypeNode(currentNodes, nodeType);
            for (NodeData nodeData : mList) {
                nodeData.changeImageView(nodeType);
            }
            setNodeListImageVisibility(true, mList);
        }
        previousDropDownState = nodeType;
    }


//    //THIs doesnt link its not EVEN THE DROP DOWN ITS THE TITLED PANE BUT YEA GL MING-MING
    public void locateDropdownHandler(ActionEvent event) {
        Object mEvent = event.getSource();
        String nodeType = "";

        if (mEvent == keyLocation) {
            String keyLocationString = keyLocation.getChildrenUnmodifiable().toString();
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
            }
        }
        if (nodeType.equals("ALL")) {
            //ADD CODE HERE THANK YOU MINGQUANNNNNN

        }
        if ((!nodeType.equals("")) && (!nodeType.equals("ALL"))) {
            List<NodeData> mList = getTypeNode(currentNodes, nodeType);
            for (NodeData nodeData : mList) {
                nodeData.changeImageView(nodeType);
            }
            setNodeListImageVisibility(true, mList);
        }
    }

    //map zooming method
    private void setZoom(){
        imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        if (!(currentPath == null) && !currentPath.isEmpty()) {
            List<NodeData> mPath = currentPath;
            displayPath(mPath);
        }
        setNodeListSizeAndLocation(currentNodes, currentScale);
        relocateNodeInfo();
    }
    public void zoomHandler(ActionEvent e) {
        if (e.getSource() == btnZoomOut){
            currentScale = Math.max(currentScale-.08, .3);
        } else if (e.getSource() == btnZoomIn){
            currentScale = Math.min(currentScale+.08, .6);
        }

        setZoom();
        updateZoomSlider();
    }

    //updates zoom slider to match current zoom scale
    private void updateZoomSlider(){
        slideZoom.setValue(currentScale*10);
    }

    //relocate the node info panel
    public void relocateNodeInfo() {
        if (nodeInfoPane.isVisible()) {
            int x = Integer.parseInt(lblNodeX.getText());
            int y = Integer.parseInt(lblNodeY.getText());
            nodeInfoPane.setLayoutX(x * currentScale);
            nodeInfoPane.setLayoutY(y * currentScale);
        }
    }

    public void goButtonHandler() {
        System.out.println("drawing path");
        try {
            currentPath = manager.startPathfind(txtStartLocation.getText(), txtEndLocation.getText(), this.handicap.isSelected());
        } catch (InvalidNodeException ine) {
            currentPath = new ArrayList<>();
        } catch (NoPathException np) {
            String id = np.startID;
            currentPath = new ArrayList<>();
        }
        displayPath(currentPath);
        clearPathFindLoc();
        //
        TextDirections textDirections = new TextDirections(currentPath);
        //String directions = textDirections.getTextDirections();
        //shitty fix for null problem
        //directions = directions.replaceAll("null", "");
        //txtAreaDirections.setText(directions);
        paneDirections.setExpanded(true);
        /*ScrollPane sp = new ScrollPane();
        sp.setContent(txtAreaDirections);*/

    }

    public void employeeButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/employee_manager_UI.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<EmployeeManager>getController().initManager(this.manager);
        stage.show();
    }

    public void employeeRequestHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/employee_request_handler.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<EmployeeRequestHandlerController>getController().initialize(this.manager,employeeLoggedIn);
        stage.show();
    }

    //select location when clicking on the text field
    public void selectStartField() {
        selectingLocation = "selectStart";
    }

    public void selectEndField() {
        selectingLocation = "selectEnd";
    }


    //popup methods
    private FXMLLoader showScene(String url) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            //Todo add some sort of error handling
            return loader;
        }
        loader.<LoginPopup>getController().initManager(manager,this);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.showAndWait();
        return loader;
    }

    public void loginButtonHandler() throws IOException {
        if (logOffNext) {
            logOffNext = false;
            loggedIn = false;
            btnLogin.setText("Log in");
            showAdminControls();
            System.out.println("logging out");
            return;
        }
        String username = "wwong2";
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/Login_Popup.fxml"
                ));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<LoginPopup>getController().initManager(manager,this);
        stage.showAndWait();
        loggedIn = loader.<LoginPopup>getController().getLoggedIn();
        //loggedIn = true;
        if (loggedIn) {
            System.out.println("user name "+userName);
            employeeLoggedIn = manager.queryEmployeeByUsername(userName);
            logOffNext = true;
            btnLogin.setText("Logout");
            showAdminControls();
        }
        //stage.show();
    }

    private void showAdminControls() {
        paneAdminFeatures.setVisible(loggedIn);
    }

    public void mapEditHandler() throws IOException {
        System.out.println("In map Edit handler");
//        showScene("/edu/wpi/cs3733/programname/boundary/admin_screen.fxml");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin_screen.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<MapAdminController>getController().initManager(manager);
        //loader.<MapAdminController>getController().sendBuildings(buildings);
        loader.<MapAdminController>getController().setmTestController(this);
        stage.showAndWait();
        buildings = loader.<MapAdminController>getController().getBuildings();
        System.out.println(buildings);
        ObservableList bldgs = comboBuilding.getItems();
        for (Building b : buildings) {
            if (!bldgs.contains(b))
            bldgs.add(b);
        }
        comboBuilding.setItems(bldgs);
    }

    public void openAdminHandler() throws IOException {
        System.out.println("In open admin handler");
        //showScene("/edu/wpi/cs3733/programname/boundary/serv_UI.fxml");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/serv_UI.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<ServiceRequestManager>getController().initManager(manager);
        stage.show();
    }

    public void viewMyRequestsHandler() throws IOException {
        System.out.println("In open admin handler");
        //showScene("/edu/wpi/cs3733/programname/boundary/serv_UI.fxml");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/employee_request_handler.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<EmployeeRequestHandlerController>getController().initialize(manager, employeeLoggedIn);
        stage.show();
    }


    public void closeNodeInfoHandler() {
        nodeInfoPane.setVisible(false);
        nodeInfoLongName.setText("");
        nodeInfoShortName.setText("");
        nodeInfoType.setText("");
        lblNodeX.setText("");
        lblNodeY.setText("");
    }

    public void helpButtonHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/FAQ_Popup.fxml"
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

    public void aboutButtonHandler() throws IOException{
        System.out.print("In About Page");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/About_Popup.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        loader.load()
                )
        );
        stage.show();
    }


    public void transportRequestHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/Transportation_Request_UI.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<Transportation_Request>getController().initController(manager, this, employeeLoggedIn.getUsername());
        stage.show();
    }

    public void interpreterRequestHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/Interpreter_Request_UI.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<Interpreter_Request>getController().initController(manager, this, employeeLoggedIn.getUsername());
        stage.show();
    }

    public void maintenanceRequestHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/Maintenance_Request_UI.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<Maintenance_Request>getController().initController(manager, this, employeeLoggedIn.getUsername());
        stage.show();
    }

    public void handleEmailButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/email_Direction.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<Email_Direction>getController().initialize(manager, currentPath);
        stage.show();
        emailDirections.setVisible(false);
    }

    // Turn the handicapped path restriction on or off
    public void toggleHandicap() {
        if (handicap.isSelected()) {
            handicap.setSelected(false);
            AppSettings.getInstance().setHandicapPath(false);
        } else {
            handicap.setSelected(true);
            AppSettings.getInstance().setHandicapPath(true);
        }
    }

    @Override
    public void passNodeData(NodeData nodeData) throws IOException {
        switch (selectingLocation) {
            case "":

//                showNode(nodeData);
                showNodeInfo(nodeData);
                break;
            case "selectLocation":
                System.out.println("In selectLocation");
                lblServiceX.setText("" + nodeData.getXCoord());
                lblServiceY.setText("" + nodeData.getYCoord());
                serviceRequester.setVisible(true);
                selectingLocation = "";
                break;
            case "selectStart":
//                showNode(nodeData);
                showNodeInfo(nodeData);
                txtStartLocation.setText(nodeData.getLongName());
                selectingLocation = "";
                break;
            case "selectEnd":
//                showNode(nodeData);
                showNodeInfo(nodeData);
                txtEndLocation.setText(nodeData.getLongName());
                selectingLocation = "";
                break;
            case "selectSRLocation":
                popupSRWithCoord(nodeData, SRSelectType);
                selectingLocation = "";
                break;
        }
    }

    @Override
    public void passEdgeData(EdgeData edgeData) {

    }

    public void setSelectingLocationState(String SRType) {
        selectingLocation = "selectSRLocation";
        this.SRSelectType = SRType;
    }

    public void popupSRWithCoord(NodeData nodeData, String SRType) throws IOException {
        System.out.println("If in here" + SRType);
        switch (SRType) {
            case "interpreter":
                System.out.println("In Interpreter");
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/Interpreter_Request_UI.fxml"
                        )
                );
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(
                        new Scene(
                                (Pane) loader.load()
                        )
                );
                loader.<Interpreter_Request>getController().initController(manager, this, nodeData, employeeLoggedIn.getUsername());
                stage.show();
                break;
            case "maintenance":
                loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/Maintenance_Request_UI.fxml"
                        )
                );
                stage = new Stage(StageStyle.DECORATED);
                stage.setScene(
                        new Scene(
                                (Pane) loader.load()
                        )
                );
                loader.<Maintenance_Request>getController().initController(manager, this, nodeData, employeeLoggedIn.getUsername());
                stage.show();
                break;
            case "transportation":
                loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/Transportation_Request_UI.fxml"
                        )
                );
                stage = new Stage(StageStyle.DECORATED);
                stage.setScene(
                        new Scene(
                                (Pane) loader.load()
                        )
                );
                loader.<Transportation_Request>getController().initController(manager, this, nodeData, employeeLoggedIn.getUsername());
                stage.show();
                break;
        }
    }
    private void clearCurrentNodeIMV(List<NodeData> mList){
        for(NodeData nodeData:mList){
            panningPane.getChildren().remove(nodeData.getNodeImageView());
        }

    }

    public void setUserName(String userName){
        this.userName = userName;
    }


}
