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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;
import static javafx.scene.paint.Color.BLUE;


public class NewMainUIController {


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
    private JFXComboBox comboBuildingAdmin;
    @FXML
    private JFXComboBox comboFloorsAdmin;

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
    private JFXButton btnDeleteNode;
    @FXML
    private JFXButton btnEditNode;

    @FXML
    private JFXButton btnAddEdge;
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
    @FXML
    private AnchorPane nodeInfoBox;
    @FXML
    private Label edgeNode1;
    @FXML
    private Label edgeNode2;
    @FXML
    private JFXButton setEdge1;
    @FXML
    private JFXButton setEdge2;


    /*
    *global variables, not FXML tied
    */

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
    private String edgeAction = "";
    private NodeData selectEdgeN1 = null;
    private NodeData selectEdgeN2 = null;

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


    public void initManager(ManageController manageController) {
        manager = manageController;
        instantiateNodeList();

        currentScale = 0.3;

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

        comboTypes.setItems(typeList);
        comboTypes.setValue("REST");
        //sets the map, just in case we want it to start on another floor
        setMap();
        setNodeListImageVisibility(false, setNodeListController(setNodeListSizeAndLocation(initNodeListImage(currentNodes), currentScale), this));
        setZoom();
    }

    public void onClickMap(MouseEvent e) {
        System.out.println("Mouse Clicked");
        //clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();

        ///////////////////

        if (!selectingLocation.equals("selectSRLocation")) {

        } else {
            //System.out.println("In selectSRLocation" + SRSelectType);
            //popupSRWithCoord(getClosestNode(currentNodes, x, y), SRSelectType);
            selectingLocation = "";
        }

        ///////////////////////
        System.out.println("current floor: " + curFloor);
        List<NodeData> nodes = manager.queryNodeByFloorAndBuilding(curFloor.getFloorNum(), curFloor.getBuilding());
        switch (selectingLocation) {
            case "":
                System.out.println("Get in findNodeData");
                NodeData mClickedNode = getClosestNode(nodes, x, y);
                if (mClickedNode != null)
                    showNodeInfo(mClickedNode);
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
                nodeEditPane.setVisible(true);
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

    public synchronized void reinitialize() {
        // Set the scale to default
        currentScale = 0.3;
        // Log off the admin
        if(logOffNext) try {
            loginButtonHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Clear all drawings on the map
        clearMain();
        clearPath();
        closeNodeInfoHandler();
        // Go back to the default view
        returnToMain();
    }

    public void adminClickMap(MouseEvent e) {
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
                setNodeDataToInfoPane(mClickedNode);
                btnAddNode.setVisible(false);
                btnDeleteNode.setVisible(true);
                btnEditNode.setVisible(true);
                if (e.getClickCount() == 2) {
                    clearNodeInfoText();
                    lblCurrentBuilding.setText(mClickedNode.getBuilding());
                    lblCurrentFloor.setText(curFloor.getFloorNum());
                    textNodeLocation.setText(UICToDBC(x, currentScale) +
                            "," + UICToDBC(y, currentScale));
                    btnAddNode.setVisible(true);
                    btnDeleteNode.setVisible(false);
                    btnEditNode.setVisible(false);

                }
                break;
            case "selectLocation":
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
                break;
            case "selectEdge":
                NodeData mNode = getClosestNode(nodes, x, y);
                if (selectEdgeN1 == null) {
                    selectEdgeN1 = mNode;
                } else if (selectEdgeN2 == null) {
                    selectEdgeN2 = mNode;
                }
                if (selectEdgeN2 != null && selectEdgeN1 != null) {
                    if (edgeAction.equals("addEdge")) {
                        manager.addEdge(selectEdgeN1.getNodeID(), selectEdgeN2.getNodeID());
                    }
                    if (edgeAction.equals("deleteEdge")) {
                        /*String edgeId = getEdge(currentEdge, selectEdgeN1.getNodeID(), selectEdgeN2.getNodeID());
                        if (!edgeId.equals("")) {
                            manager.deleteEdge(edgeId);
                            System.out.println("Edge Exists");
                        }*/
                    }
                    selectingLocation = "";
                    showNodesOrEdges();
                    selectEdgeN2 = selectEdgeN1 = null;
//                    setupBurger();
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

        System.out.println("scale says: " + currentScale + " but slider says: " + slideZoom.getValue() / 10);
        imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        if (!(currentPath == null) && !currentPath.isEmpty()) {
            List<NodeData> mPath = currentPath;
            clearPath();
            displayPath(mPath);
        }

        if (allNodesBox != null)
            showNodesOrEdges();

    }


    //updates zoom slider to match current zoom scale
    private void updateZoomSlider() {
        slideZoom.setValue(currentScale * 10);
    }


    public void zoomHandler(ActionEvent e) {
        updateZoomSlider();
        if (e.getSource() == btnZoomOut) {
            if (AppSettings.getInstance().getMapRatioIndex() == 0) {
                return;
            }
            AppSettings.getInstance().setMapRatioIndex(AppSettings.getInstance().getMapRatioIndex() - 1);
            currentScale = Math.max(currentScale - .08, .3);
            imgMap.setFitWidth(maxWidth * currentScale);
        } else {
            if (AppSettings.getInstance().getMapRatioIndex() == (slideZoom.getValue() - 1)) {
                return;
            }
            AppSettings.getInstance().setMapRatioIndex(AppSettings.getInstance().getMapRatioIndex() + 1);
            currentScale = Math.min(currentScale + .08, .6);
            imgMap.setFitWidth(maxWidth * currentScale);
        }
//        clearMain();
//        if (!(currentEdge == null) && !currentEdge.isEmpty()) {
//            System.out.println("case edge 1");
//            List<EdgeData> mEdges = currentEdge;
//            clearEdge();
//            displayEdges(mEdges);
//        }
//        if (!(currentNodes == null) && !currentNodes.isEmpty()) {
//            List<NodeData> mNodes = manager.queryNodeByFloorAndBuilding(convertFloor(floor), "45 Francis");
//            System.out.println("case node main, floor = " + floor);
//            clearNodes();
//            showNodeList(mNodes);
//            System.out.println(currentScale);
//        }
        updateZoomSlider();
        showNodesOrEdges();
    }

    public double getScale() {
        return currentScale;
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
                ArrayList<Floor> floors = newBld.getFloors();
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

                setNodeListImageVisibility(false, setNodeListController(setNodeListSizeAndLocation(initNodeListImage(currentNodes), currentScale), this));
                showNodesOrEdges();

            }
            if (lblCurrentBuilding != null) {
                lblCurrentBuilding.setText(curBuilding.getName());
                lblCurrentFloor.setText(curFloor.getFloorNum());
            }
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
            nodeEditPane.setVisible(true);
            nodeInfoAdd.setVisible(true);
            nodeInfoDelete.setVisible(false);
            nodeInfoEdit.setVisible(false);
            nodeAction = "addNode";
        } else {
            selectingLocation = "selectEdge";
            edgeAction = "addEdge";
            btnAddEdge.getScene().setCursor(Cursor.CROSSHAIR);
        }
    }

    public void nodeInfoHandler(ActionEvent event) {
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
        if (event.getSource() == btnAddNode) {
            NodeData newNode = new NodeData(id, loc, floor, building, nodeType, longName, shortName, teamAssigned);
            manager.addNode(newNode);
            clearNodeInfoText();
            clearMain();
            showNodesOrEdges();
        }
        if (event.getSource() == btnDeleteNode) {
            NodeData newNode = new NodeData(id, loc, floor, building, nodeType, longName, shortName, teamAssigned);
            manager.deleteNode(newNode);
            clearNodeInfoText();
            clearMain();
            showNodesOrEdges();
        }
        if (event.getSource() == btnEditNode) {
            NodeData newNode = new NodeData(id, loc, floor, building, nodeType, longName, shortName, teamAssigned);
            manager.editNode(newNode);
            clearNodeInfoText();
            clearMain();
            showNodesOrEdges();
        }
    }

    private void setNodeDataToInfoPane(NodeData nodeData) {
        nodeInfoBox.setOpacity(75);
        textNodeId.setText(nodeData.getNodeID());
        lblCurrentBuilding.setText(nodeData.getBuilding());
        lblCurrentFloor.setText(nodeData.getFloor());
        textNodeFullName.setText(nodeData.getLongName());
        textNodeLocation.setText(nodeData.getLocation().toString());
        textNodeShortName.setText(nodeData.getShortName());
        textNodeTeamAssigned.setText(nodeData.getTeamAssigned());
        comboTypes.setValue(nodeData.getNodeType());
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

    public void addEdge() {
        if (!edgeNode1.equals("None") && !edgeNode2.equals("None")) {
            manager.addEdge(edgeNode1.getText(), edgeNode2.getText());
            clearMain();
            showNodesOrEdges();
        }
    }

    public void addEdgeToList(ActionEvent event) {
        if (event.getSource() == setEdge1)
            edgeNode1.setText(textNodeId.getText());
        else
            edgeNode2.setText(textNodeId.getText());
    }

    public void clearEdges() {
        edgeNode1.setText("None");
        edgeNode2.setText("None");
    }


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
        //displayPath(currentPath);
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

    private void displayPath(List<NodeData> path) {
        if (path != null && !path.isEmpty()) {
            currentPath = path;
            clearPath();
            System.out.println("drawing path");
            NodeData prev = path.get(0);
            int x = (int) (prev.getXCoord() * currentScale);
            int y = (int) (prev.getYCoord() * currentScale);
            System.out.println(x + ", " + y);

            ArrayList<Line> lines = new ArrayList<>();
            for (int i = 1; i < path.size(); i++) {
                Line l = new Line();
                NodeData n = path.get(i);

                if (i <= path.size() - 2) {     //has to be minus 2, so that you dont go to path.get(path.size()) since that wouldn't work
                    NodeData nextNode = path.get(i + 1);
                    String printFloor = "";
                    if ((n.getNodeType().equals("ELEV") && nextNode.getNodeType().equals("ELEV")) ||
                            (n.getNodeType().equals("STAI") && nextNode.getNodeType().equals("STAI"))) {
                        for (int j = 1; j < path.size() - i; j++) {
                            nextNode = path.get(i + j);
                            if (!nextNode.getNodeType().equals(n.getNodeType())) {
                                printFloor = n.getFloor();
                                currentPathStartFloor = printFloor;
                                currentStartFloorLoc = new Coordinate(n.getXCoord(), n.getYCoord());
                                currentPathGoalFloor = nextNode.getFloor();
                                currentGoalFloorLoc = new Coordinate(nextNode.getXCoord(), n.getYCoord());
                            } else if (nextNode.equals(n)) {
                                printFloor = n.getFloor();
                                currentPathStartFloor = printFloor;
                                currentStartFloorLoc = new Coordinate(n.getXCoord(), n.getYCoord());
                                currentPathGoalFloor = nextNode.getFloor();
                                currentGoalFloorLoc = new Coordinate(nextNode.getXCoord(), n.getYCoord());
                            }
                        }
                        /*lblCrossFloor.setText("Proceed to Floor " + printFloor + "!");
                        lblCrossFloor.setLayoutX(DBCToUIC(n.getXCoord(), currentScale));
                        lblCrossFloor.setLayoutY(DBCToUIC(n.getYCoord(), currentScale));
                        lblCrossFloor.setVisible(true);
                        lblCrossFloor.toFront();*/
                    }
                }

                if (n.getFloor().equals(curFloor.getFloorNum()) && prev.getFloor().equals(curFloor.getFloorNum())) {
                    l.setStroke(Color.BLUE);
                    l.setStrokeWidth(10.0 * currentScale);
                    l.setStartX(prev.getXCoord() * currentScale);
                    l.setStartY(prev.getYCoord() * currentScale);
                    l.setEndX(n.getXCoord() * currentScale);
                    l.setEndY(n.getYCoord() * currentScale);
                    lines.add(l);
                }
                prev = n;
            }
            pathDrawings.addAll(lines);
            m_draggableNode.getChildren().addAll(lines);
            panningPane.getChildren().add(m_draggableNode);
            //panningPane.getChildren().addAll(lines);
            //emailDirections.setVisible(true);
        }
    }

    ///
    // helper functions
    ///

    private void clearPathFindLoc() {
        endLocation.setText("");
        startLocation.setText("");
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

    private void clearPath() {
        //currentPath = new ArrayList<>();
        if (pathDrawings.size() > 0) {
            panningPane.getChildren().remove(m_draggableNode);
            for (Shape shape : pathDrawings) {
                System.out.println("success remove");
                m_draggableNode.getChildren().remove(shape);
            }
            currentPathStartFloor = "";
            currentPathGoalFloor = "";
            pathDrawings = new ArrayList<>();
        }
    }

    public void relocateNodeInfo() {
        if (nodeInfoPane.isVisible()) {
            int x = Integer.parseInt(lblNodeX.getText());
            int y = Integer.parseInt(lblNodeY.getText());
            nodeInfoPane.setLayoutX(x * currentScale);
            nodeInfoPane.setLayoutY(y * currentScale);
        }
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
        nodeEditPane.setVisible(false);
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

    private void showNodeInfo(NodeData nodeData) {
        int dbX = nodeData.getXCoord();
        int dbY = nodeData.getYCoord();
        System.out.println("Node Coordinate: " + dbX + "," + dbY + " Node Name: " + nodeData.getLongName());
        //nodeInfoPane.setVisible(true);
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

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void returnToMain() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/test.fxml"
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
        loader.<NewMainUIController>getController().initManager(manager);
        loader.<NewMainUIController>getController().passStage(stage);

        System.out.println("Returned to normal view");
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
        loader.<NewMainUIController>getController().initAdminManager(manager);
        loader.<NewMainUIController>getController().passStage(stage);
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

    public void passStage(Stage s) {
        stage = s;
    }

    public void initAdminManager(ManageController manageController) {

        manager = manageController;

        currentScale = 0.3;

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
        comboBuilding.setItems(buildingList);
        comboBuilding.setValue(curBuilding);

        //sets up the comboFloors ComboBox, gets the list of floors from curBuilding and picks the starting flor
        ObservableList floorList = FXCollections.observableList(new ArrayList<>());
        floorList.addAll(curBuilding.getFloors());
        comboFloors.setItems(floorList);
        curFloor = curBuilding.getFloors().get(4);
        comboFloors.setValue(curFloor);

        System.out.println("Value: " + comboFloors.getValue());


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
        lblCurrentBuilding.setText(curBuilding.getName());
        lblCurrentFloor.setText(curFloor.getFloorNum());
        selectingLocation = "";
        //sets the map, just in case we want it to start on another floor
        setMap();
        setZoom();
    }


}