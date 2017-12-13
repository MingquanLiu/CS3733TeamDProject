package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.util.*;

import static edu.wpi.cs3733.programname.commondata.Constants.OPACITY_KEY_LOCATION_NOT_SHOWN;
import static edu.wpi.cs3733.programname.commondata.Constants.OPACITY_KEY_LOCATION_SHOWN;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;

public class NewMainPageController {
    //FXML objects
    //<editor-fold dsec="main panes">
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private AnchorPane panningPane;
    //</editor-fold>

    //<editor-fold desc="map changing">
//    @FXML
//    private JFXButton btnMapUp;
//    @FXML
//    private JFXButton btnMapDwn;
    @FXML
    private ImageView imgMap;
    //</editor-fold>

    //<editor-fold desc="zoom and pan objects">
    @FXML
    private JFXButton btnZoomIn;
    @FXML
    private JFXButton btnZoomOut;
    @FXML
    private Slider slideZoom;
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
    @FXML
    private CheckBox handicap;
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


    @FXML
    private JFXComboBox comboBuilding;
    @FXML
    private JFXComboBox comboFloors;


    @FXML
    private DialogPane nodeInfoPane;
    @FXML
    private AnchorPane nodeEditPane;
    @FXML
    private JFXButton nodeInfoSetLocation;
    @FXML
    private TextField textNodeId;
    @FXML
    private TextField textNodeLocation;
    @FXML
    private TextField textNodeFullName;
    @FXML
    private TextField textNodeShortName;
    @FXML
    private TextField textNodeTeamAssigned;
    @FXML
    private JFXButton nodeInfoAdd;
    @FXML
    private JFXButton nodeInfoEdit;
    @FXML
    private JFXButton nodeInfoDelete;
    @FXML
    private Label lblCurrentBuilding;
    @FXML
    private Label lblCurrentFloor;
    @FXML
    private JFXComboBox comboTypes;
    @FXML
    private TextField startLocation;
    @FXML
    private TextField endLocation;
    @FXML
    private JFXListView textDirections;
    @FXML
    private Label lblNodeX;
    @FXML
    private Label lblNodeY;
    @FXML
    private Label nodeInfoType;
    @FXML
    private Label nodeInfoShortName;
    @FXML
    private Label nodeInfoLongName;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnHelp;
    @FXML
    private JFXButton btnAbout;
    //animations stuff
    @FXML
    private JFXComboBox<Image> comboCharacter;
    private ArrayList<Transition> transitions = new ArrayList<>();
    private ArrayList<ImageView> drawnImages = new ArrayList<>();
    @FXML
    private JFXButton crossFloor;
    private ArrayList<Floor> floors;
    private ImageView currentChar;

    private Stage stage;

    private ManageController manager;
    private double currentScale;
    private final double MAX_UI_WIDTH = 5000;
    private boolean serviceRequestSubjectClicked = false;


    private Building curBuilding;
    private Floor curFloor;
    private ArrayList<Building> buildings = new ArrayList<>();

    private List<EdgeData> currentEdges = new ArrayList<>();
    private List<NodeData> currentNodes = new ArrayList<>();
    private List<Shape> drawings = new ArrayList<>();

    private String selectingLocation = "";
    private String nodeAction = "";

    private List<NodeData> currentPath;
    private List<Shape> pathDrawings = new ArrayList<>();
    private Group m_draggableNode;
    private String currentPathStartFloor = "";
    private String currentPathGoalFloor = "";
    private Coordinate currentStartFloorLoc;
    private Coordinate currentGoalFloorLoc;
    final double minWidth = 1500;
    final double maxWidth = 5000;

    private boolean logOffNext = false;
    private boolean loggedIn;
    private String userName = null;
    private Employee employeeLoggedIn;


    private boolean showStairs = false;
    private boolean showDestination = false;
    private boolean showWaitingRooms = false;
    private boolean showRetail = false;
    private boolean showServiceDesk = false;
    private boolean showLabs = false;
    private boolean showExits = false;
    private boolean showElevator = false;
    private boolean showBathrooms = false;
    public void StairsToggle() {
        resetKeyLocationShow();
        this.showStairs = !this.showStairs;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void DestinationToggle() {
        resetKeyLocationShow();
        this.showDestination = !this.showDestination;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void WaitingRoomToggle() {
        resetKeyLocationShow();
        this.showWaitingRooms = !this.showWaitingRooms;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void RetailToggle() {
        resetKeyLocationShow();
        this.showRetail = !this.showRetail;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void ServiceDeskToggle() {
        resetKeyLocationShow();
        this.showServiceDesk = !this.showServiceDesk;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void LabToggle() {
        resetKeyLocationShow();
        this.showLabs = !this.showLabs;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void ExitToggle() {
        resetKeyLocationShow();
        this.showExits = !this.showExits;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void ElevatorToggle() {
        resetKeyLocationShow();
        this.showElevator = !this.showElevator;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    public void BathroomToggle() {
        resetKeyLocationShow();
        this.showBathrooms = !this.showBathrooms;
        updateButtonVisibility();
        updateNodeVisibility();
    }
    private void resetKeyLocationShow(){
        showStairs = false;
        showDestination = false;
        showWaitingRooms = false;
        showRetail = false;
        showServiceDesk = false;
        showLabs = false;
        showExits = false;
        showElevator = false;
        showBathrooms = false;
    }
    private void updateButtonVisibility(){
        //if a nodegroup is toggled on, add it to the list of shown nodes
        if (showStairs) {
            keyLocationStairs.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationStairs.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showBathrooms) {
            keyLocationBathroom.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationBathroom.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showElevator) {
            keyLocationElevator.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationElevator.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showDestination) {
            keyLocationDestination.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationDestination.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showExits) {
            keyLocationExit.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationExit.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showLabs) {
            keyLocationLab.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationLab.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showRetail) {
            keyLocationRetail.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationRetail.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showServiceDesk) {
            keyLocationServiceDesk.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationServiceDesk.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
        if (showWaitingRooms) {
            keyLocationWaitingroom.setOpacity(OPACITY_KEY_LOCATION_SHOWN);
        } else {
            keyLocationWaitingroom.setOpacity(OPACITY_KEY_LOCATION_NOT_SHOWN);
        }
    }

    // This function displays all the nodes that are toggled on according to the ShowXXXX booleans
    private void updateNodeVisibility(){
        List<NodeData> visibleNodes = new LinkedList<NodeData>();
        //if a nodegroup is toggled on, add it to the list of shown nodes
        if (showBathrooms){} //visibleNodes.add(HelperFunction.getTypeNode(,"REST"));}
        if (showElevator){}
        if (showExits){}
        if (showLabs){}
        if (showServiceDesk){}
        if (showRetail){}
        if (showWaitingRooms){}
        if (showDestination){}
        if (showStairs){}
    }



    public void initManager(ManageController manageController) {
        manager = manageController;
        instantiateNodeList();
        currentScale = 0.4;
        slideZoom.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                currentScale = newVal.doubleValue() / 10;
                System.out.println("scale" + currentScale);
                setZoom();
            }
        });

        ////
        //MAP STUFF
        ////

        //get all buildings from the database: for each loops through and adds them to the list of buildings, and
        //picks "Main Hospital" as the starting building
        List<Building> dbBuildings = manager.getAllBuildings();
        for (Building b : dbBuildings) {
            buildings.add(b);
            if (b.getName().equals("Main Hospital"))
                curBuilding = b;
        }
        //sets up the comboBuilding ComboBox with the list of buildings, always starts with main hospital as the selected box
        ObservableList buildingList = FXCollections.observableList(new ArrayList<>());
        buildingList.addAll(buildings);
        System.out.println("buildinglist: " + buildingList);
        comboBuilding.setItems(buildingList);
        comboBuilding.setValue(curBuilding);
        //sets up the comboFloors ComboBox, gets the list of floors from curBuilding and picks the starting flor
        ObservableList floorList = FXCollections.observableList(new ArrayList<>());

        floorList.addAll(curBuilding.getFloors());

        comboFloors.setItems(floorList);
        System.out.println("3: " + curBuilding.getFloors().get(3) +
                " and 4: " + curBuilding.getFloors().get(4));
        curFloor = curBuilding.getFloors().get(4);
        comboFloors.setValue(curFloor);
        ObservableList typeList = FXCollections.observableList(new ArrayList<>());
        typeList.add("REST");
        typeList.add("INFO");
        typeList.add("RETL");
        typeList.add("DEPT");
        typeList.add("ELEV");
        typeList.add("EXIT");
        typeList.add("STAI");
        typeList.add("LABS");
        typeList.add("SERV");
        //sets the map, just in case we want it to start on another floor
        setMap();
        setZoom();

        Image walkingMan = new Image("img/walkingBlue1.gif");
        Image runningBatman = new Image("img/batmanRun1.gif");
        Image runningCat = new Image("img/catRun1.gif");
        currentChar = new ImageView("img/walkingBlue.gif");

        ObservableList characters = FXCollections.observableList(new ArrayList<>());
        characters.add(walkingMan);
        characters.add(runningBatman);
        characters.add(runningCat);

        comboCharacter.getItems().addAll(characters);
        comboCharacter.setButtonCell(new NewMainPageController.ImageListCell());
        comboCharacter.setCellFactory(listView -> new NewMainPageController.ImageListCell());
        comboCharacter.setValue(walkingMan);
        currentNodes = manageController.queryNodeByFloorAndBuilding(curBuilding.getName(),curFloor.getFloorNum());
    }
    //path display/animation
    private class ImageListCell extends ListCell<Image> {
        private final ImageView view;

        ImageListCell() {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            view = new ImageView();
        }

        @Override
        protected void updateItem(Image item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setGraphic(null);
            } else {
                view.setImage(item);
                setGraphic(view);
            }
        }
    }
    public void clearMain() {
        clearPath();
        //lblCrossFloor.setVisible(false);
        crossFloor.setVisible(false);
        closeNodeInfoHandler();
        clearPathFindLoc();
        //lastShowNodeData.setImageVisible(false);
    }
    private void clearPathFindLoc() {
        endLocation.setText("");
        startLocation.setText("");
    }
    private void clearPath() {
        //currentPath = new ArrayList<>();
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
        ImageView character = currentChar;
        character.setPreserveRatio(true);
        character.setFitWidth(200*currentScale);

        PathTransition pathTransition = new PathTransition();

        int distance = distanceBetweenNodes(nodes.get(0), nodes.get(nodes.size()-1));
        pathTransition.setDuration(Duration.millis(distance*10));
        pathTransition.setNode(character);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(100);
        panningPane.getChildren().add(character);
        character.toFront();
        pathTransition.setAutoReverse(false);
        pathTransition.orientationProperty().addListener(new ChangeListener<PathTransition.OrientationType>() {
            @Override
            public void changed(ObservableValue<? extends PathTransition.OrientationType> observable, PathTransition.OrientationType oldValue, PathTransition.OrientationType newValue) {
                System.out.println("orientation change");
            }
        });

        pathTransition.play();

        drawnImages.add(character);
        transitions.add(pathTransition);
    }

    private void displayPath(List<NodeData> path) {
        if (path != null && !path.isEmpty()) {
            clearPath();                    //first thing to do is clear any visible path and animations
            //currentPath = path;
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

                if(n.getFloor().equals(curFloor.getFloorNum())) {
                    System.out.println(n.getLongName());
                    if (prev.getFloor().equals(curFloor.getFloorNum())) {
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
                if(n.getFloor().equals(curFloor.getFloorNum())){
                    thisFloorPath.add(n);
                }
            }
            if(thisFloorPath.size() > 0){
                pathAnimation(thisFloorPath);
            }
            pathDrawings.addAll(lines);
            panningPane.getChildren().addAll(lines);

            //emailDirections.setVisible(true);
        }
    }
    public void crossFloor(){
        System.out.println("called crossFloor");
        System.out.println(curFloor.getFloorName());
        System.out.println(currentPathStartFloor);
        System.out.println(currentPathGoalFloor);

        if(curFloor.getFloorName().equals(currentPathGoalFloor)){
            for (Floor f:floors){
                if(f.getFloorName().equals(currentPathStartFloor)){
                    curFloor = f;
                    setMap();
                }
            }
        }
        else if(curFloor.getFloorName().equals(currentPathStartFloor)){
            for (Floor f:floors){
                if(f.getFloorName().equals(currentPathGoalFloor)){
                    curFloor = f;
                    setMap();
                }
            }
        }

    }
    public void selectCharacter(){
        Image selectedChar = comboCharacter.getValue();
        String newUrl = selectedChar.impl_getUrl();
        newUrl = newUrl.substring(0,newUrl.indexOf("1.gif")) + ".gif";
        currentChar = new ImageView(newUrl);
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

    public synchronized void reinitialize() {
        // Set the scale to default
        currentScale = 0.3;
        // Log off the admin
        if(logOffNext) try {
            loginButtonHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Go back to the default view
        clearMain();
    }

    public void mapEditHandler() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/map_admin.fxml"
                )
        );

        try {
            stage.setScene(
                    new Scene(
                            (Pane) loader.load()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
            loader.<NewMapAdminUI>getController().initAdminManager(manager);
        loader.<NewMapAdminUI>getController().passStage(stage);
        System.out.println("Changed to admin view");
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
        //TODO fix requests to use this controller
        //loader.<Transportation_Request>getController().initController(manager, this, employeeLoggedIn.getUsername());
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
        //TODO fix requests to use this controller
        //loader.<Interpreter_Request>getController().initController(manager, this, employeeLoggedIn.getUsername());
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
        //TODO fix requests to use this controller
        //loader.<Maintenance_Request>getController().initController(manager, this, employeeLoggedIn.getUsername());
        stage.show();
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

    public void aboutButtonHandler() throws IOException {
        System.out.print("In About Page");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/About_Popup.fxml"
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

    public void closeNodeInfoHandler() {
        nodeInfoPane.setVisible(false);
        nodeInfoLongName.setText("");
        nodeInfoShortName.setText("");
        nodeInfoType.setText("");
        lblNodeX.setText("");
        lblNodeY.setText("");
    }

    public void fuzzyStart() {
        String input = startLocation.getText();
        List<String> longNameIDS = manager.queryNodeByLongName(input);

        TextFields.bindAutoCompletion(startLocation, longNameIDS);
        TextFields.bindAutoCompletion(endLocation, longNameIDS);

    }

    public void setUserName(String userName) {        this.userName = userName; }

    public void goButtonHandler() {
        System.out.println("drawing path");
        try {
            //false needs to be changed to something that reflects if we need a handicap path
            System.out.println("start: " + startLocation.getText());
            System.out.println("end: " + endLocation.getText());
            currentPath = manager.startPathfind(startLocation.getText(), endLocation.getText(), handicap.isSelected());
        } catch (InvalidNodeException ine) {
            currentPath = new ArrayList<>();
        } catch (NoPathException np) {
            String id = np.startID;
            currentPath = new ArrayList<>();
        }
        TextDirections text = new TextDirections(currentPath);
        ObservableList directionsList = FXCollections.observableList(new ArrayList<>());
        directionsList.addAll(text.getTextDirections());
        textDirections.setItems(directionsList);
        textDirections.setCellFactory(param -> new ListCell<TextDirection>() {
            private Text text;
            @Override
            protected void updateItem(TextDirection item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getThisNode() == null) {
                    setText(null);
                } else {
                    text = new Text(item.getDirection());
                    text.setWrappingWidth(270.0);
                    setGraphic(text);
                }
            }
        });
        displayPath(currentPath);

        // TODO: Dan, sort these by floor
//        for (String s : Arrays.asList("L2", "L1", "G", "1", "2", "3")) {
//            try {
//                List<TextDirection> currentFloor = text.getByFloor(s);
//                directions += "\n\nFloor " + s;
//                for (TextDirection t : currentFloor) {
//                    directions += "\n\t" + t.getDirection();
//                }
//            } catch (NullPointerException npe) {
//                System.out.println("No text directions on this floor");
//            }
//        }

    }

    public void showNodesOrEdges() {
//        clearMain();
        System.out.println("Starting show node path for " + curFloor + " of building: " + curBuilding.getName()
                + "(" + curFloor.getFloorNum() + ")");
        currentNodes = manager.queryNodeByFloorAndBuilding(curFloor.getFloorNum(), curBuilding.getName());
//        showNodeList(currentNodes);
    }

    public void instantiateNodeList() {
        JFXNodesList nodesList = new JFXNodesList();
        JFXNodesList nodesList1 = new JFXNodesList();
        JFXNodesList keyLocationNodeList = new JFXNodesList();
        nodesList.addAnimatedNode(adminFeatureSubject, new Callback<Boolean, Collection<KeyValue>>() {
            @Override
            public Collection<KeyValue> call(Boolean expanded) {
                return new ArrayList<KeyValue>() {
                    {
                        add(new KeyValue(adminFeatureSubject.rotateProperty(), expanded ? 360 : 0, Interpolator.EASE_BOTH));
                    }
                };
            }
        });
        nodesList.addAnimatedNode(mapEdit);
        nodesList.addAnimatedNode(employeeManager);
        serviceRequestSubject.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            int i =serviceRequestSubject.getStyleClass().size();
            if (serviceRequestSubjectClicked) {
                serviceRequestSubject.getStyleClass().remove("color-button-serviceRequest");
                serviceRequestSubject.getStyleClass().add("color-button-adminFeature");
            } else {
                serviceRequestSubject.getStyleClass().remove("color-button-adminFeature");
                serviceRequestSubject.getStyleClass().add("color-button-serviceRequest");
            }
            serviceRequestSubjectClicked = !serviceRequestSubjectClicked;

        });
        nodesList1.addAnimatedNode(serviceRequestSubject
                , new Callback<Boolean, Collection<KeyValue>>() {
                    @Override
                    public Collection<KeyValue> call(Boolean expanded) {
                        return new ArrayList<KeyValue>() {
                            {
                                add(new KeyValue(serviceRequestSubject.rotateProperty(), expanded ? 0 : 270, Interpolator.EASE_BOTH));
                            }
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
                return new ArrayList<KeyValue>() {
                    {
                        add(new KeyValue(keyLocationSubject.rotateProperty(), expanded ? 360 : 0, Interpolator.EASE_BOTH));
                    }
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
        AnchorPane.setTopAnchor(keyLocationNodeList, 5.00);
        AnchorPane.setLeftAnchor(keyLocationNodeList, 10.0);
        AnchorPane.setTopAnchor(nodesList, 5.00);
        AnchorPane.setRightAnchor(nodesList, 10.0);
    }

    public void passStage(Stage s) {
        stage = s;
    }

    //map zooming method
    private void setZoom() {

        System.out.println("scale says: " + currentScale + " but slider says: " + slideZoom.getValue() / 10);
        imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        showNodesOrEdges();
        if(currentPath != null){
            displayPath(currentPath);
        }
    }


    //updates zoom slider to match current zoom scale
    private void updateZoomSlider() {
        slideZoom.setValue(currentScale * 10);
    }


    public void zoomHandler(ActionEvent e) {
        updateZoomSlider();
        int ratioIndex = AppSettings.getInstance().getMapRatioIndex();
        if (e.getSource() == btnZoomOut) {
//            if (AppSettings.getInstance().getMapRatioIndex() == 0) {
//                return;
//            }
            AppSettings.getInstance().setMapRatioIndex(ratioIndex - 1);
            currentScale = Math.max(currentScale - .08, .4);
            imgMap.setFitWidth(maxWidth * currentScale);
        } else {
//            if (AppSettings.getInstance().getMapRatioIndex() == (slideZoom.getValue() - 1)) {
//                return;
//            }
            AppSettings.getInstance().setMapRatioIndex(ratioIndex + 1);
            currentScale = Math.min(currentScale + .08, .9);
            imgMap.setFitWidth(maxWidth * currentScale);
        }
        updateZoomSlider();
        showNodesOrEdges();
    }

    public double getScale() {
        return currentScale;
    }


    public void setMap() {
        if (comboBuilding.getValue() != null && comboFloors.getValue() != null) {
            Building newBld = (Building) (comboBuilding.getValue());
            System.out.println("Old building and floor: " + curBuilding + "[" + curFloor + "]");
            if (newBld != curBuilding)
                System.out.println("New building and floor: " + newBld + "[" + newBld.getFloors().get(0) + "]");
            else
                System.out.println("New building and floor: " + newBld + "[" + comboFloors.getValue() + "]");


            if (newBld != curBuilding) {
                System.out.println("floors: " + newBld);
                floors = newBld.getFloors();
                ObservableList floorList = FXCollections.observableList(new ArrayList<>());

                floorList.addAll(floors);
                comboFloors.setItems(floorList);
                comboFloors.setValue(floorList.get(0));
                curBuilding = newBld;
            }
            if (curFloor == null || curFloor != comboFloors.getValue()) {
                System.out.println("Floors changing");
                System.out.println("comboFloors: " + comboFloors);
                Floor newFloor = (Floor) (comboFloors.getValue());
                curFloor = newFloor;
                String newUrl = newFloor.getImgUrl();

                Image newImg = new Image(newUrl);
                imgMap.setImage(newImg);

//                setNodeListImageVisibility(false, setNodeListController(setNodeListSizeAndLocation(initNodeListImage(currentNodes), currentScale), this));
                showNodesOrEdges();
            }
            if (lblCurrentBuilding != null) {
                lblCurrentBuilding.setText(curBuilding.getName());
                lblCurrentFloor.setText(curFloor.getFloorNum());
            }
            displayPath(currentPath);
        }
    }
    public void onClickMap(MouseEvent e) {
        System.out.println("Mouse Clicked");
        //clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        if (!selectingLocation.equals("selectSRLocation")) {

        } else {
            selectingLocation = "";
        }
        System.out.println("current floor: " + curFloor);
        List<NodeData> nodes = manager.queryNodeByFloorAndBuilding(curFloor.getFloorNum(), curFloor.getBuilding());
        switch (selectingLocation) {
            case "":
                System.out.println("Get in findNodeData");
                NodeData mClickedNode = getClosestNode(nodes, x, y);
                if (mClickedNode != null);
//                    showNodeInfo(mClickedNode);
                break;
            case "selectLocation":

                selectingLocation = "";
                break;
        }
    }

    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY) {
        int dbX = UICToDBC(mouseX, currentScale);
        int dbY = UICToDBC(mouseY, currentScale);
        int resultX = 0;
        int resultY = 0;
        String resultNodeId = "";
        NodeData mNode = null;
        double d = 0;
        for (NodeData node : nodeDataList) {
            int nodeX = node.getXCoord();
            int nodeY = node.getYCoord();
            double temp = Math.sqrt(Math.pow(dbX - nodeX, 2) + Math.pow(dbY - nodeY, 2));
            if (temp < d || d == 0) {
                d = temp;
                resultX = nodeX;
                resultY = nodeY;
                resultNodeId = node.getNodeID();
                mNode = node;
            }
        }
        return mNode;
    }

    @FXML
    private void goToDirection(MouseEvent event) {
        TextDirection direction = (TextDirection) textDirections.getSelectionModel().getSelectedItem();
        try {
            direction.getNodes();
            // TODO: Draw an actual path
        } catch (NullPointerException e) {
            System.out.println("User selected nothing");
        }
    }


    public void loginButtonHandler() throws IOException {
        if (logOffNext) {
            logOffNext = false;
            loggedIn = false;
            btnLogin.setText("Login");
            adminFeaturePane.setVisible(false);
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
        loader.<LoginPopup>getController().initManager(manager, this);
        stage.showAndWait();
        loggedIn = loader.<LoginPopup>getController().getLoggedIn();
        //loggedIn = true;
        if (loggedIn) {
            System.out.println("user name " + userName);
            employeeLoggedIn = manager.queryEmployeeByUsername(userName);
            logOffNext = true;
            btnLogin.setText("Logout");
            adminFeaturePane.setVisible(true);
        }
        //stage.show();
    }

    public void BathroomSweepHandler() throws IOException{
        System.out.println("Searching for nearest bathroom");
        String startID = AppSettings.getInstance().getDefaultLocation();
        String goalID = "REST";
        try {
            currentPath = manager.sweepPathfinder(startID,goalID, this.handicap.isSelected());
        } catch (InvalidNodeException ine) {
            currentPath = new ArrayList<>();
        } catch (NoPathException np) {
            String id = np.startID;
            currentPath = new ArrayList<>();
        }
        displayPath(currentPath);

    }

    public void ElevatorSweepHandler() throws IOException{
        System.out.println("Searching for nearest elevator");
        String startID = AppSettings.getInstance().getDefaultLocation();
        String goalID = "ELEV";
        try {
            currentPath = manager.sweepPathfinder(startID,goalID, this.handicap.isSelected());
        } catch (InvalidNodeException ine) {
            currentPath = new ArrayList<>();
        } catch (NoPathException np) {
            String id = np.startID;
            currentPath = new ArrayList<>();
        }
        displayPath(currentPath);

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
    public void clearPathHandler(){
        clearPath();
        clearPathFindLoc();
    }

    // End of controller
}
