package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static edu.wpi.cs3733.programname.commondata.Constants.INTERPRETER_REQUEST;
import static edu.wpi.cs3733.programname.commondata.Constants.MAINTENANCE_REQUEST;
import static edu.wpi.cs3733.programname.commondata.Constants.TRANSPORTATION_REQUEST;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.convertFloor;
import static edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType.ASTAR;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;


public class TestingController implements Initializable {


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
    //</editor-fold>

    //<editor-fold desc="zoom and pan objects">
    @FXML
    private JFXButton btnZoomIn;
    @FXML
    private JFXButton btnZoomOut;
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
    //</editor-fold>

    //<editor-fold desc="key locations buttons">
    @FXML
    private ComboBox comboLocations;
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



    // Handicapped checkbox
    //<editor-fold desc="handicapped">
    @FXML
    private CheckBox handicap;
    //</editor-fold>

    /*
    *global variables, not FXML tied
    */

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
    final double minWidth = 1500;
    final double maxWidth = 5000;
    final private int originalMapRatioIndex = 3;
    //</editor-fold>

    //<editor-fold desc="map change">
    private int floor = 2;
    private ArrayList<Floor> floors = new ArrayList<>();
    private Floor currentFloor;
    private ArrayList<Building> buildings = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="node info">
    private String selectingLocation = "";
    ArrayList<Double> mapRatio = new ArrayList<>();
    private int currentMapRatioIndex;
    private boolean loggedIn;
    private Employee employeeLoggedIn;
    private List<Shape> shownNodes = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="DP connectton">
    private DBConnection dbConnection;
    private PathfindingController.searchType mSearchType = ASTAR;
    //</editor-fold>

    private boolean logOffNext = false;

    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initData(DBConnection dbConnection) {
        currentMapRatioIndex = originalMapRatioIndex;
        manager = new ManageController(dbConnection);
        this.dbConnection = dbConnection;
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
        currentScale = mapRatio.get(currentMapRatioIndex);
        imgMap.setFitWidth(maxWidth * currentScale);

        ObservableList locations = FXCollections.observableArrayList(
                "Bathrooms",
                "Service Desks",
                "Retail Services",
                "Waiting Rooms",
                "Elevators",
                "Exits",
                "Staircases",
                "Labs",
                "Additional Services");
        comboLocations.setItems(locations);
        comboLocations.setValue("Bathrooms");

        Floor basement2 = new Floor("Basement 2", "main", "img/Floor_-2.png");
        Floor basement1 = new Floor("Basement 1", "main", "img/Floor_-1.png");
        Floor ground = new Floor("Ground", "main", "img/Floor_0.png");
        Floor floor1 = new Floor("Floor 1", "main", "img/Floor_1.png");
        Floor floor2 = new Floor("Floor 2", "main", "img/Floor_2.png");
        Floor floor3 = new Floor("Floor 3", "Shapiro", "img/Floor_3.png");

        ArrayList<Floor> basicFloors = new ArrayList<>();
        basicFloors.add(basement2);
        basicFloors.add(basement1);
        basicFloors.add(ground);
        basicFloors.add(floor1);
        basicFloors.add(floor2);
        basicFloors.add(floor3);

        Building hospital = new Building("hospital");
        hospital.addAllFloors(basicFloors);

        floors.addAll(hospital.getFloors());
        buildings.add(hospital);

        floor = 2;

        ObservableList floorList = FXCollections.observableList(new ArrayList<>());
        floorList.addAll(floors);
        comboFloors.setItems(floorList);

        ObservableList buildingList = FXCollections.observableList(new ArrayList<>());
        buildingList.addAll(buildings);
        comboBuilding.setItems(buildingList);

        setBuilding(hospital);
        setFloor(ground);


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
        keyLocation.setContent(content);
        keyLocation.setText("TRIALTRIAL");
        keyLocation.setCollapsible(true);
        keyLocation.setExpanded(true);
        System.out.println("Expandable:"+keyLocation.isExpanded());
        System.out.println("Animated:"+keyLocation.isAnimated());
        System.out.println("Collapse:"+keyLocation.isCollapsible());
        keyLocation.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                keyLocation.setLayoutY(1000);
                System.out.println("Clicked");            }
        });
        //add grid to titled pane
        grid.setLayoutX(10);
        grid.setLayoutY(10);
        content.getChildren().add(grid);



    }

    public void setSearchType(PathfindingController.searchType searchType) {
        System.out.println(currentMapRatioIndex);
        this.mSearchType = searchType;
    }

    //topmost methods are newest
    private void drawCycle(int x, int y, Color col) {
        double radius = 10 * currentScale;
        Circle c = new Circle(x, y, radius, col);
        panningPane.getChildren().add(c);
        shownNodes.add(c);
    }

    private void showNodeList(List<NodeData> nodeDataList) {
        for (int i = 0; i < nodeDataList.size(); i++) {
            showNode(nodeDataList.get(i));
        }
    }

    private void showNode(NodeData n) {
        currentNodes.add(n);
        drawCycle(DBCToUIC(n.getXCoord(), currentScale), DBCToUIC(n.getYCoord(), currentScale), RED);
    }

    private void showNode(NodeData n, String type) {
        currentNodes.add(n);
        if (type.equals("start")) {
            drawCycle(DBCToUIC(n.getXCoord(), currentScale), DBCToUIC(n.getYCoord(), currentScale), GREEN);
        } else if (type.equals("end")) {
            drawCycle(DBCToUIC(n.getXCoord(), currentScale), DBCToUIC(n.getYCoord(), currentScale), RED);
        }
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
        int resultX = 0;
        int resultY = 0;
        String resultNodeId = "";
        double d = 0;
        for (NodeData node : nodeDataList) {
            int nodeX = node.getXCoord();
            int nodeY = node.getYCoord();
//                System.out.println("node x,y: " + nodeX + ", " + nodeY + "  real x,y: " +realX + ", " +realY);
            double temp = Math.sqrt(Math.pow(dbX - nodeX, 2) + Math.pow(dbY - nodeY, 2));
            if (temp < d || d == 0) {
                d = temp;
                resultX = nodeX;
                resultY = nodeY;
                resultNodeId = node.getNodeID();
            }
        }
        return new NodeData(resultNodeId, new Coordinate(resultX, resultY), floor + "", null, null, null, null, null);
    }

    private void displayPath(List<NodeData> path) {
        currentPath = path;
        clearMain();
        System.out.println("drawing path");
        NodeData prev = path.get(0);
        NodeData last = path.get(path.size() - 1);
        showNode(prev, "end");
        showNode(last, "start");
        int x = (int) (prev.getXCoord() * currentScale);
        int y = (int) (prev.getYCoord() * currentScale);
        System.out.println(x + ", " + y);
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 1; i < path.size(); i++) {
            Line l = new Line();
            NodeData n = path.get(i);
            if (n.getFloor().equals(convertFloor(floor)) && prev.getFloor().equals(convertFloor(floor))) {
                l.setStroke(Color.BLUE);
                l.setStrokeWidth(5.0 * currentScale);
                l.setStartX(prev.getXCoord() * currentScale);
                l.setStartY(prev.getYCoord() * currentScale);
                l.setEndX(n.getXCoord() * currentScale);
                l.setEndY(n.getYCoord() * currentScale);
                lines.add(l);
            }
            prev = n;
        }
        pathDrawings.addAll(lines);
        panningPane.getChildren().addAll(lines);
        emailDirections.setVisible(true);
    }

    public void clearMain() {
        clearPath();
        closeNodeInfoHandler();
        clearPathFindLoc();
    }

    public void clearPathFindLoc() {
        txtEndLocation.setText("");
        txtStartLocation.setText("");
    }

    private void clearPath() {
        //currentPath = new ArrayList<>();
        if (pathDrawings.size() > 0) {
            for (Shape shape : pathDrawings) {
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            pathDrawings = new ArrayList<>();
        }
    }

    private void clearNodes() {
        currentNodes = new ArrayList<>();
        if (shownNodes.size() > 0) {
            for (Shape shape : shownNodes) {
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            shownNodes = new ArrayList<>();
        }
    }

    //map switching methods
    public void addBuilding() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/newBuilding.fxml"
                ));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        stage.showAndWait();
        Building newBld = loader.<NewBuilding>getController().getBldg();
        buildings.add(newBld);
        ObservableList bldgs = comboBuilding.getItems();
        bldgs.add(newBld);
        comboBuilding.setItems(bldgs);
        setBuilding(newBld);
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
        System.out.println("floors: " + floors);
        ObservableList floorList = FXCollections.observableList(new ArrayList<>());
        floorList.addAll(floors);
        System.out.println("floorslist: " + floorList);
//        try {
            System.out.println("setting items");
            //comboFloors.setItems(floorList);
            System.out.println("just set items: " + comboFloors.getItems() + "\nand about to set value");
            comboFloors.setValue(floorList.get(0));
            System.out.println("just set value: " + comboFloors.getValue());
            //setFloor(newBld.getFloors().get(0));
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
        System.out.println("setFloor() started, comboFloors value: " + comboFloors.getValue());
        currentFloor = (Floor) (comboFloors.getValue());
        System.out.println("currentFloor: " + currentFloor);
        floor = floors.indexOf(currentFloor) - 2;
        System.out.println("floor: " + floor);
        System.out.println("about to get URL");

        String newUrl = currentFloor.getImgUrl();
        System.out.println("new image: " + newUrl);

        //File file = new File(newUrl);
        //System.out.println("current map: " + file.toString());
        Image newImg = new Image(newUrl);
        System.out.println("about to be: " + newImg.getWidth());
        imgMap.setImage(newImg);
    }

    public void showMouseCoords(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
    }

    private int UICToDBC(int value, double scale) {
        return (int) (value / scale);
    }

    private int DBCToUIC(int value, double scale) {
        return (int) (value * scale);
    }

    public void mouseClickHandler(MouseEvent e) {
        //clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        List<NodeData> nodes = manager.queryNodeByFloor(convertFloor(floor));
        NodeData mClickedNode = getClosestNode(nodes, x, y);
        switch (selectingLocation) {
            //case for displaying nearest node info when nothing is selected
            case "":
                //clearMain();
                clearNodes();
                System.out.println("Get in findNodeData X:" + x + " Y:" + y);
                mClickedNode = manager.getNodeData(mClickedNode.getNodeID());
                showNode(mClickedNode);
                showNodeInfo(mClickedNode);
                break;
            case "selectLocation":
                System.out.println("In selectLocation");
                Coordinate mCoordinate = new Coordinate(UICToDBC(x, currentScale), UICToDBC(y, currentScale));
                lblServiceX.setText("" + mCoordinate.getXCoord());
                lblServiceY.setText("" + mCoordinate.getYCoord());
                serviceRequester.setVisible(true);
                selectingLocation = "";
                break;
            case "selectStart":
                clearNodes();
                String startId = mClickedNode.getNodeID();
                mClickedNode = manager.getNodeData(mClickedNode.getNodeID());
                showNode(mClickedNode);
                showNodeInfo(mClickedNode);
                txtStartLocation.setText(startId);
                selectingLocation = "";
                break;
            case "selectEnd":
                clearNodes();
                String endId = mClickedNode.getNodeID();
                mClickedNode = manager.getNodeData(mClickedNode.getNodeID());
                showNode(mClickedNode);
                showNodeInfo(mClickedNode);
                txtEndLocation.setText(endId);
                selectingLocation = "";
                break;
            // the rest of the situations when you click on the map
//            case "maintenance":
//                locationsSelected = true;
//                requestDescription.setText(requestDescription.getText() + "\n at " + x + ", " + y);
//                serviceRequester.setVisible(true);
//                selectingLocation = "";
//                break;

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
            }
        }
        clearNodes();
        clearMain();
        if (!nodeType.equals("")) {
            List<NodeData> mList = manager.queryNodeByTypeFloor(nodeType, Integer.toString(floor));
            if (mList != null && !mList.isEmpty())
                showNodeList(mList);
        }
    }

    //map zooming method
    public void zoomHandler(ActionEvent e) {
//        clearMain();

        if (e.getSource() == btnZoomOut) {
//            if(imgMap.getFitWidth() <= minWidth){
//                return;
//            }
            if (currentMapRatioIndex == 0) {
                return;
            }
            currentMapRatioIndex -= 1;
            currentScale = mapRatio.get(currentMapRatioIndex);
            imgMap.setFitWidth(maxWidth * currentScale);
        } else {
//            if(imgMap.getFitWidth() >= maxWidth){
//                return;
//            }
            if (currentMapRatioIndex == (mapRatio.size() - 1)) {
                return;
            }
            currentMapRatioIndex += 1;
            currentScale = mapRatio.get(currentMapRatioIndex);
            imgMap.setFitWidth(maxWidth * currentScale);
        }
        clearMain();

        if (!(currentPath == null) && !currentPath.isEmpty()) {
            List<NodeData> mPath = currentPath;
            clearPath();
            displayPath(mPath);
        }
        if (!(currentNodes == null) && !currentNodes.isEmpty()) {
            List<NodeData> mNodes = currentNodes;
            clearNodes();
            showNodeList(mNodes);
            System.out.println(currentScale);
            relocateNodeInfo();
        }

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
            System.out.println(mSearchType);
            currentPath = manager.startPathfind(txtStartLocation.getText(), txtEndLocation.getText(), mSearchType, this.handicap.isSelected());
        } catch (InvalidNodeException ine) {
            currentPath = new ArrayList<>();
        } catch (NoPathException np) {
            String id = np.startID;
            currentPath = new ArrayList<>();
        }
        displayPath(currentPath);
        clearPathFindLoc();
        TextDirections textDirections = new TextDirections(currentPath);
        String directions = textDirections.getTextDirections();
        txtAreaDirections.setText(directions);
        paneDirections.setExpanded(true);
    }

    //select location when clicking on the text field
    public void selectStartField() {
        selectingLocation = "selectStart";
    }

    public void selectEndField() {
        selectingLocation = "selectEnd";
    }

    public void SRHandler(ActionEvent e) {
        Object mEvent = e.getSource();
        serviceRequester.setVisible(true);
        String SRType = "";
        if (mEvent == btnInterpreterReq) {
            lblReqType.setText("Interpreter Request");
            SRType = "Language to: \nLanguage from:";
        }
        if (mEvent == btnMaintenanceReq) {
            lblReqType.setText("Maintenance Request");
            SRType = "Maintenance type: \nMaintenance urgency(1-5): ";
        }
        if (mEvent == btnTransportationReq) {
            lblReqType.setText("Transportation Request");
            SRType = "Transportation type: \nTransportation urgency: ";
        }
        requestDescription.setText(SRType);
    }

    //    //popup methods
    private FXMLLoader showScene(String url) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            //Todo add some sort of error handling
            return loader;
        }
        loader.<LoginPopup>getController().initData(dbConnection);
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

        String username = "admin";
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
        loader.<LoginPopup>getController().initData(dbConnection);
        stage.show();
        //loggedIn = loader.<LoginPopup>getController().getLoggedIn();
        loggedIn = true;
        if (loggedIn) {
            logOffNext = true;
            //username = loader.<LoginPopup>getController().getUsername();
            username = "admin";
            employeeLoggedIn = manager.queryEmployeeByUsername(username);
            loggedIn = true;
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
        loader.<MapAdminController>getController().initData(dbConnection);
        loader.<MapAdminController>getController().setmTestController(this);
        stage.show();
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
        loader.<ServiceRequestManager>getController().initData(dbConnection);
        stage.show();
    }

    public void SRWindowHandler(ActionEvent e) {
        Object mEvent = e.getSource();
        serviceRequester.setVisible(false);
        if (mEvent == btnSelectMaintenanceLocation) {
            selectingLocation = "selectLocation";
            setBurger();
        }
        if (mEvent == btnCancelRequestAttempt) {
            //TODO clear the text
            requestDescription.setText("");
            lblServiceX.setText("");
            lblServiceY.setText("");
        }
        if (mEvent == btnSubmitRequest) {
            //TODO clear the text and submit the SR
            String typeText = lblReqType.getText();
            String type = "";

            if (typeText == "Interpreter Request") {
                type = INTERPRETER_REQUEST;
            } else if (typeText == "Transportation Request") {
                type = TRANSPORTATION_REQUEST;
            } else if (typeText == "Maintenance Request") {
                type = MAINTENANCE_REQUEST;
            }

            int locX = Integer.parseInt(lblServiceX.getText());
            int locY = Integer.parseInt(lblServiceX.getText());
            String locationId = getClosestNode(manager.getAllNodeData(), locX, locY).getNodeID();
            String description = requestDescription.getText().replaceAll("\n", " ");
            //   String senderUsername = employeeLoggedIn.getUsername();
            manager.createServiceRequest("admin", type, locationId, null, description);
            lblServiceX.setText("");
            lblServiceY.setText("");
        }
    }

    public void closeNodeInfoHandler() {
        clearNodes();
        nodeInfoPane.setVisible(false);
//        nodeInfoLongName.setText("");
//        nodeInfoShortName.setText("");
//        nodeInfoType.setText("");
//        lblNodeX.setText("");
//        lblNodeY.setText("");
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
        this.goButtonHandler();
    }

}
