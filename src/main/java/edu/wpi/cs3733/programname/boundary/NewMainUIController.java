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
import javafx.scene.Cursor;
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
import static javafx.scene.paint.Color.BLUE;


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

    @FXML
    private JFXComboBox comboBuilding;
    @FXML
    private JFXComboBox comboFloors;

    @FXML
    private JFXCheckBox allEdgesBox;
    @FXML
    private JFXCheckBox allNodesBox;
    @FXML
    private JFXButton btnNewBuilding;
    @FXML
    private JFXButton btnNewFloor;
    @FXML
    private JFXButton btnAddNode;
    @FXML
    private JFXButton btnAddEdge;
    @FXML
    private AnchorPane nodeInfoPane;
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
    /*
    *global variables, not FXML tied
    */

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
    private String edgeAction = "";
    private NodeData selectEdgeN1 = null;
    private NodeData selectEdgeN2 = null;

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

        comboTypes.setItems(typeList);
        comboTypes.setValue("REST");
        //sets the map, just in case we want it to start on another floor
        setMap();
    }

    public void onClickMap(MouseEvent e) {
        System.out.println("Mouse Clicked");
        //clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        System.out.println("current floor: " + curFloor);
        List<NodeData> nodes = manager.queryNodeByFloorAndBuilding(curFloor.getFloorNum(), curFloor.getBuilding());
        switch (selectingLocation) {
            case "":
                System.out.println("Get in findNodeData");
                NodeData mClickedNode = getClosestNode(nodes, x, y);
                break;
            case "selectLocation":
                nodeInfoSetLocation.getScene().setCursor(Cursor.DEFAULT);
                switch (nodeAction) {
                    case "addNode":
                        textNodeLocation.setText(UICToDBC(x, currentScale) + "," + UICToDBC(y, currentScale));
                        break;
                    case "deleteNode":
                        mClickedNode = getClosestNode(nodes, x, y);
                        setNodeDataToInfoPane(mClickedNode);
                        break;
                    case "editNode":
                        mClickedNode = getClosestNode(nodes, x, y);
                        setNodeDataToInfoPane(mClickedNode);
                        break;
                }
                nodeInfoPane.setVisible(true);
                selectingLocation = "";
                break;
            case "selectEdge":
                NodeData mNode = getClosestNode(nodes, x, y);
                if (selectEdgeN1 == null) {
                    selectEdgeN1 = mNode;
                    //showNode2(selectEdgeN1);
                } else if (selectEdgeN2 == null) {
                    selectEdgeN2 = mNode;
                    //showNode2(selectEdgeN2);
                }
                if (selectEdgeN2 != null && selectEdgeN1 != null) {
                    //displayEdge2(selectEdgeN1, selectEdgeN2);
                    if (edgeAction.equals("addEdge")) {
                        manager.addEdge(selectEdgeN1.getNodeID(), selectEdgeN2.getNodeID());
                    }
                    if (edgeAction.equals("deleteEdge")) {
                        String edgeId = getEdge(currentEdges, selectEdgeN1.getNodeID(), selectEdgeN2.getNodeID());
                        if (!edgeId.equals("")) {
                            manager.deleteEdge(edgeId);
                            System.out.println("Edge Exists");
                        }
                    }
                    selectingLocation = "";
                    nodeInfoSetLocation.getScene().setCursor(Cursor.DEFAULT);
                    showNodesOrEdges();
                    selectEdgeN2 = selectEdgeN1 = null;
                }
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
    private void setZoom() {
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
    private void updateZoomSlider() {
        slideZoom.setValue(currentScale * 10);
    }

    public void zoomHandler(ActionEvent e) {
        if (e.getSource() == btnZoomOut) {
            currentScale = Math.max(currentScale - .08, .3);
        } else if (e.getSource() == btnZoomIn) {
            currentScale = Math.min(currentScale + .08, .6);
        }

        setZoom();
        updateZoomSlider();
    }

    public double getScale(){return currentScale;}

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

    public void setMap() {
        Building newBld = (Building) (comboBuilding.getValue());
        System.out.println("Old building and floor: " + curBuilding + "[" + curFloor + "]");
        if (newBld != curBuilding)
            System.out.println("New building and floor: " + newBld + "[" + newBld.getFloors().get(0) + "]");
        else
            System.out.println("New building and floor: " + newBld + "[" + comboFloors.getValue() + "]");


        if (newBld != curBuilding) {
            System.out.println("floors: " + newBld);
            ArrayList<Floor> floors = newBld.getFloors();
            ObservableList floorList = FXCollections.observableList(new ArrayList<>());
            floorList.addAll(floors);
            comboFloors.setItems(floorList);
            comboFloors.setValue(floorList.get(0));
            curBuilding = newBld;
        }
        if (curFloor == null || curFloor != comboFloors.getValue()) {
            System.out.println("Floors changing");
            Floor newFloor = (Floor) (comboFloors.getValue());
            curFloor = newFloor;
            String newUrl = newFloor.getImgUrl();

            Image newImg = new Image(newUrl);
            imgMap.setImage(newImg);
            showNodesOrEdges();

        }


    }

    public void addMap(ActionEvent event) {
        String typeToAdd = "";
        if (event.getSource() == btnNewBuilding)
            typeToAdd = "Building";
        if (event.getSource() == btnNewFloor)
            typeToAdd = "Floor";
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/newMap.fxml"
                ));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(
                            loader.load()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        loader.<NewMapController>getController().setUp(typeToAdd, buildings);
        loader.<NewMapController>getController().initManager(manager);
        stage.showAndWait();
        if (loader.<NewMapController>getController().addedMap()) {
            if (typeToAdd.equals("Building")) {
                Building newBld = loader.<NewMapController>getController().getBuilding();
                buildings.add(newBld);
                ObservableList bldgs = comboBuilding.getItems();
                bldgs.add(newBld);
                comboBuilding.setItems(bldgs);
            }
            if (typeToAdd.equals("Floor")) {
                Floor newFloor = loader.<NewMapController>getController().getFloor();
                for (Building b : buildings) {
                    if (b.getName().equals(newFloor.getBuilding())) {
                        b.addFloor(newFloor);
                    }
                }
                if (curBuilding.getName().equals(newFloor.getBuilding())) {
                    ObservableList fls = comboFloors.getItems();
                    fls.add(newFloor);
                    comboFloors.setItems(fls);
                }
            }
            setMap();
        }
    }

    public void addButtonHandler(ActionEvent event) {
        if (event.getSource() == btnAddNode) {
            nodeInfoPane.setVisible(true);
            nodeInfoAdd.setVisible(true);
            nodeInfoDelete.setVisible(false);
            nodeInfoEdit.setVisible(false);
            nodeAction = "addNode";
            lblCurrentBuilding.setText(curBuilding.getName());
            lblCurrentFloor.setText(curFloor.getFloorNum());
        } else {
            selectingLocation = "selectEdge";
            edgeAction = "addEdge";
            btnAddEdge.getScene().setCursor(Cursor.CROSSHAIR);
        }
    }

    public void nodeInfoHandler(ActionEvent event) {
        nodeInfoPane.setVisible(false);
        if (event.getSource() == nodeInfoSetLocation) {
            selectingLocation = "selectLocation";
            nodeInfoSetLocation.getScene().setCursor(Cursor.CROSSHAIR);
        } else {
            String id = textNodeId.getText();
            String nodeType = comboTypes.getValue().toString();
            String location = textNodeLocation.getText();
            String[] locXY = location.split(",");
            Coordinate loc = new Coordinate(Integer.parseInt(locXY[0]), Integer.parseInt(locXY[1]));
            String longName = textNodeFullName.getText();
            String floor = lblCurrentFloor.getText();
            String shortName = textNodeShortName.getText();
            String building = lblCurrentBuilding.getText();               //figure out building based on Coordinate
            String teamAssigned = textNodeTeamAssigned.getText();           //figure out what to do with this field for new nodes
            if (event.getSource() == nodeInfoAdd) {
                NodeData newNode = new NodeData(id, loc, floor, building, nodeType, longName, shortName, teamAssigned);
                manager.addNode(newNode);
                //displayAddNodeConfirmation(id, longName, loc);
                clearNodeInfoText();
                showNodesOrEdges();
            }
            if (event.getSource() == nodeInfoDelete) {
                NodeData newNode = new NodeData(id, loc, floor, building, nodeType, longName, shortName, teamAssigned);
                manager.deleteNode(newNode);
                clearNodeInfoText();
                showNodesOrEdges();
            }
            if (event.getSource() == nodeInfoEdit) {
                NodeData newNode = new NodeData(id, loc, floor, building, nodeType, longName, shortName, teamAssigned);
                manager.editNode(newNode);
                clearNodeInfoText();
                showNodesOrEdges();
            }
        }

    }

    private void setNodeDataToInfoPane(NodeData nodeData) {
        textNodeId.setText(nodeData.getNodeID());
        lblCurrentBuilding.setText(nodeData.getBuilding());
        lblCurrentFloor.setText(nodeData.getFloor());
        textNodeFullName.setText(nodeData.getLongName());
        textNodeLocation.setText(nodeData.getLocation().toString());
        textNodeShortName.setText(nodeData.getShortName());
        textNodeTeamAssigned.setText(nodeData.getTeamAssigned());
    }

    public void showNodesOrEdges() {
        clearMain();
        System.out.println("Starting show node path for " + curFloor + " of building: " + curBuilding.getName()
                + "(" + curFloor.getFloorNum() + ")");
        currentNodes = manager.queryNodeByFloorAndBuilding(curFloor.getFloorNum(), curBuilding.getName());

        if (allEdgesBox.isSelected()) {
            currentEdges = manager.getAllEdgeData();
            System.out.println("Doing edges");
            displayEdges(currentEdges);
        }
        if (allNodesBox.isSelected()) {
            setCircleNodeListSizeAndLocation(setCircleNodeListController(initNodeListCircle(currentNodes), this), currentScale);
            showNodeList(currentNodes);
        }
    }

    private void showNodeList(List<NodeData> nodeDataList) {
        for (int i = 0; i < nodeDataList.size(); i++) {
            showNode(nodeDataList.get(i));
        }
    }

    private void showNode(NodeData n) {
        panningPane.getChildren().add(n.getCircle());
    }

    private void displayEdges(List<EdgeData> edges) {
        for (EdgeData edge : edges) {
            NodeData node1 = getNode(edge.getStartNode());
            NodeData node2 = getNode(edge.getEndNode());
            if (node1 != null && node2 != null) {
                displayEdge(node1, node2);
            }
        }
    }

    private void displayEdge(NodeData n1, NodeData n2) {
        Line line = new Line(n1.getXCoord() * currentScale, n1.getYCoord() * currentScale, n2.getXCoord() * currentScale, n2.getYCoord() * currentScale);
        line.setStrokeWidth(8 * currentScale);
        line.setStroke(BLUE);
        panningPane.getChildren().add(line);
        drawings.add(line);
    }

    private NodeData getNode(String nodeID) {
        for (NodeData nodeData : currentNodes) {
            if (nodeData.getNodeID().equals(nodeID)) {
                //System.out.println(nodeData.getBuilding());
                boolean rightBuilding = false;
                if ((curFloor.getBuilding()).matches("Main Hospital"))
                    rightBuilding = partOfMainB(nodeData.getBuilding(), (curFloor.getBuilding()));
                else if ((curFloor.getBuilding().equals(curBuilding.getName())))
                    rightBuilding = true;
                if (nodeData.getFloor().equals(curFloor.getFloorNum()) && rightBuilding) {
                    return nodeData;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    private String getEdge(List<EdgeData> edgeDataList, String node1, String node2) {
        for (EdgeData edgeData : edgeDataList) {
            if (edgeData.getStartNode().equals(node1) && edgeData.getEndNode().equals(node2)) {
                return edgeData.getEdgeID();
            }
            if (edgeData.getStartNode().equals(node2) && edgeData.getEndNode().equals(node1)) {
                return edgeData.getEdgeID();
            }
        }
        return "";
    }

    private boolean partOfMainB(String nodeBuilding, String curBuilding) {
        boolean nodeBuild = nodeBuilding.matches("Main Hospital|BTM|(15|25|45) Francis|Tower|Shapiro");
        boolean curBuild = curBuilding.matches("Main Hospital");
        return nodeBuild && curBuild;
    }

    public void nodeInfoXHandler() {
        nodeInfoPane.setVisible(false);
        clearNodeInfoText();
    }

    private void clearNodeInfoText() {
        textNodeId.setText("");
        lblCurrentFloor.setText("");
        lblCurrentBuilding.setText("");
        textNodeFullName.setText("");
        textNodeLocation.setText("");
        textNodeShortName.setText("");
        textNodeTeamAssigned.setText("");
    }

    public void clearMain() {
        if (drawings.size() > 0) {
            for (Shape shape : drawings) {
                panningPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
        for (NodeData nodeData : currentNodes) {
            panningPane.getChildren().remove(nodeData.getCircle());
        }
    }

}
