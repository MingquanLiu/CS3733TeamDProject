package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.util.*;

import static edu.wpi.cs3733.programname.commondata.Constants.OPACITY_NOT_SHOWN;
import static edu.wpi.cs3733.programname.commondata.Constants.OPACITY_SHOWN;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;
import static javafx.scene.paint.Color.BLUE;


public class NewMapAdminUI extends UIController{


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
    private JFXButton addEdgeNodeInfoBox;
    @FXML
    private JFXButton btnSetDefaultLocation;

    @FXML
    private AnchorPane edgeAddPane;

    @FXML
    private AnchorPane mapAdminSettingPane;
    @FXML
    private JFXRadioButton AStar;
    @FXML
    private JFXRadioButton BestFirst;
    @FXML
    private JFXRadioButton Beam;
    @FXML
    private JFXRadioButton BFS;
    @FXML
    private JFXRadioButton DFS;
    @FXML
    private JFXRadioButton Scenic;
    @FXML
    private JFXRadioButton Dijkstra;


    /*
    *global variables, not FXML tied
    */

    private Stage stage;

    private ManageController manager;
    private double currentScale;
    private final double MAX_UI_WIDTH = 5000;
//    private boolean serviceRequestSubjectClicked = false;


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
    private boolean selectEdge = false;

    private NodeData prevShowNode;

//    private boolean logOffNext = false;
//    private boolean loggedIn;
//    private String userName = null;
//    private Employee employeeLoggedIn;
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
                nodeInfoBox.setOpacity(OPACITY_NOT_SHOWN);
                if(prevShowNode!=null) {
                    shrinkNode(prevShowNode);
                }
//                setNodeDataToInfoPane(mClickedNode);
                if (e.getClickCount() == 2) {
                    clearNodeInfoText();
                    NodeData mClickedNode = getClosestNode(nodes, x, y);
                    lblCurrentBuilding.setText(mClickedNode.getBuilding());
                    lblCurrentFloor.setText(curFloor.getFloorNum());
                    textNodeLocation.setText(UICToDBC(x, currentScale) +
                            "," + UICToDBC(y, currentScale));
                    setButtonVisibilityForAdd(true);
                    nodeInfoBox.setOpacity(OPACITY_SHOWN);
                }
                break;
        }
    }

    public void setButtonVisibilityForAdd(Boolean addNode){
        if(addNode){
            btnAddNode.setVisible(true);
            btnDeleteNode.setVisible(false);
            btnEditNode.setVisible(false);
            addEdgeNodeInfoBox.setVisible(false);
            btnSetDefaultLocation.setVisible(false);
        }
        else{
            btnAddNode.setVisible(false);
            btnDeleteNode.setVisible(true);
            btnEditNode.setVisible(true);
            addEdgeNodeInfoBox.setVisible(true);
            btnSetDefaultLocation.setVisible(true);
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

//        if (allNodesBox != null)
//            setCircleNodeListSizeAndLocation(currentNodes,currentScale);
//        if(allEdgesBox!=null){
//            if(currentEdges!=null&&!currentEdges.isEmpty()){
//                displayEdges(currentEdges);
//            }
//        }
            showNodesOrEdges();

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
//        showNodesOrEdges();
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

//    public void addButtonHandler(ActionEvent event) {
//        if (event.getSource() == btnAddNode) {
//            nodeEditPane.setVisible(true);
//            nodeInfoAdd.setVisible(true);
//            nodeInfoDelete.setVisible(false);
//            nodeInfoEdit.setVisible(false);
//            nodeAction = "addNode";
//        } else {
//            selectingLocation = "selectEdge";
//            edgeAction = "addEdge";
//            btnAddEdge.getScene().setCursor(Cursor.CROSSHAIR);
//        }
//    }

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
        nodeInfoBox.setOpacity(OPACITY_SHOWN);
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
        if (drawings.size() > 0) {
            for (Shape shape : drawings) {
                panningPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
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
            edgeAddPane.setOpacity(OPACITY_NOT_SHOWN);
        }
    }

    public void addEdgeToList(ActionEvent event) {
        edgeNode1.setText(textNodeId.getText());
        edgeAddPane.setOpacity(OPACITY_SHOWN);
        selectEdge = true;
    }

    public void clearEdges() {
        edgeNode1.setText("None");
        edgeNode2.setText("None");
        edgeAddPane.setOpacity(OPACITY_NOT_SHOWN);
    }

    public void clearMain() {
        if(prevShowNode!=null)
        panningPane.getChildren().remove(prevShowNode.getCircle());
        prevShowNode = null;
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
//            panningPane.getChildren().remove(m_draggableNode);
            for (Shape shape : pathDrawings) {
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
//                m_draggableNode.getChildren().remove(shape);
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
        loader.<NewMainPageController>getController().initManager(manager);
        loader.<NewMainPageController>getController().passStage(stage);

        System.out.println("Returned to normal view");
    }


    public void passStage(Stage s) {
        stage = s;
    }

    public void initAdminManager(ManageController manageController) {

        manager = manageController;

        currentScale = 0.4;

        slideZoom.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                currentScale = newVal.doubleValue() / 10;
                System.out.println("scale" + currentScale);
                setZoom();
            }
        });
        //MAP STUFF
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
        nodeInfoBox.setOpacity(OPACITY_NOT_SHOWN);
        edgeAddPane.setOpacity(OPACITY_NOT_SHOWN);
//        showNodesOrEdges();
//        currentNodes = manager.queryNodeByFloorAndBuilding(curFloor.getFloorNum(), "Hospital");
//        currentEdges = manager.getAllEdgeData();
//        setCircleNodeListSizeAndLocation(setCircleNodeListController(initNodeListCircle(currentNodes), this), currentScale);

    }

    public ManageController getManager(){
        return manager;
    }

    @Override
    public void passNodeData(NodeData nodeData) throws IOException {

        if(prevShowNode==null){
            prevShowNode = nodeData;
        }else if(!nodeData.equals(prevShowNode)){
            shrinkNode(prevShowNode);
            prevShowNode = nodeData;
        }else{
            System.out.println("Equals");
        }
        enlargeNode(nodeData);
        setButtonVisibilityForAdd(false);
        setNodeDataToInfoPane(nodeData);
        if(selectEdge){
            edgeNode2.setText(textNodeId.getText());
            selectEdge=false;
        }
    }
    public void shrinkNode(NodeData nodeData){
        panningPane.getChildren().remove(nodeData.getCircle());
        nodeData.changeBackCircleAndChangeColor(currentScale);
        setCircleNodeController(nodeData,this);
        panningPane.getChildren().add(nodeData.getCircle());
    }

    public void enlargeNode(NodeData nodeData){
        panningPane.getChildren().remove(nodeData.getCircle());
        nodeData.enlargeCircleAndChangeColor(currentScale);
        setCircleNodeController(nodeData,this);
        panningPane.getChildren().add(nodeData.getCircle());
    }
    @Override
    public void passEdgeData(EdgeData edgeData) {

    }

    public void disablePaneScroll() {
        this.paneScroll.setPannable(false);
        this.paneScroll.setFitToWidth(true);
    }

    public void enablePaneScroll() {
        this.paneScroll.setPannable(true);
        this.paneScroll.setFitToWidth(false);
    }

    public void mapAdminSettingHandler(){
        if(mapAdminSettingPane.visibleProperty().getValue())
            mapAdminSettingPane.setVisible(false);
        else{
            mapAdminSettingPane.setVisible(true);
        }
    }

    public void pfAlgorithmHandler(ActionEvent e){
        Object mEvent = e.getSource();
        PathfindingController.searchType mType = PathfindingController.searchType.ASTAR;
        if(mEvent.equals(AStar)){
            mType = PathfindingController.searchType.ASTAR;
        }
        if(mEvent.equals(BestFirst)){
            mType = PathfindingController.searchType.BEST;
        }
        if(mEvent.equals(Beam)){
            mType = PathfindingController.searchType.BEAM;
        }

        if(mEvent.equals(BFS)){
            mType = PathfindingController.searchType.BFS;
        }

        if(mEvent.equals(DFS)){
            mType = PathfindingController.searchType.DFS;
        }
        if(mEvent.equals(Dijkstra)){
            mType = PathfindingController.searchType.DIJKSTRA;
        }
        if(mEvent.equals(Scenic)){
            mType = PathfindingController.searchType.SCENIC;
        }
        AppSettings.getInstance().setSearchType(mType);
    }
    public void setDefaultLocationHandler(ActionEvent e){
        if(textNodeId.getText()!=null&&!textNodeId.getText().equals("")){
            AppSettings.getInstance().setDefaultLocation(textNodeId.getText());
        }else{
            System.out.println("Node Id is empty");
        }
    }

    public ImageView getFloorImage() {
        return imgMap;
    }

    public ScrollPane getPaneScroll() {
        return paneScroll;
    }
}