package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.*;
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
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;
import static javafx.scene.paint.Color.BLUE;


public class MapEditController implements Initializable {

    @FXML
    private AnchorPane panningPane;
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
    private Label lblCurrentBuilding;
    @FXML
    private Label lblCurrentFloor;
    @FXML
    private JFXComboBox comboTypes;

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

    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initManager(ManageController manageController) {
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
                break;
            case "selectLocation":
                btnAddNode.getScene().setCursor(Cursor.DEFAULT);
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
                    btnAddNode.getScene().setCursor(Cursor.DEFAULT);
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

    //map zooming method
    private void setZoom() {
        imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        if (!(currentPath == null) && !currentPath.isEmpty()) {
            List<NodeData> mPath = currentPath;
            clearPath();
            displayPath(mPath);
        }
        setNodeListSizeAndLocation(currentNodes, currentScale);
        showNodesOrEdges();

    }


    //updates zoom slider to match current zoom scale
    private void updateZoomSlider() {
        slideZoom.setValue(currentScale * 10);
    }


    public void zoomHandler(ActionEvent e) {
        updateZoomSlider();
        if (e.getSource() == btnZoomOut) {
//            if(imgMap.getFitWidth() <= minWidth){
//                return;
//            }
            if (AppSettings.getInstance().getMapRatioIndex() == 0) {
                return;
            }
            AppSettings.getInstance().setMapRatioIndex(AppSettings.getInstance().getMapRatioIndex() - 1);
            currentScale = Math.max(currentScale - .08, .3);
            imgMap.setFitWidth(maxWidth * currentScale);
        } else {
//            if(imgMap.getFitWidth() >= MAX_UI_WIDTH){
//                return;
//            }
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

    public double getScale(){return currentScale;}

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

           // setNodeListImageVisibility(false,setNodeListController(setNodeListSizeAndLocation(initNodeListImage(currentNodes),currentScale),this));  ;
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
            nodeAction = "addNode";
            lblCurrentBuilding.setText(curBuilding.getName());
            lblCurrentFloor.setText(curFloor.getFloorNum());
        } else {
            selectingLocation = "selectEdge";
            edgeAction = "addEdge";
            btnAddEdge.getScene().setCursor(Cursor.CROSSHAIR);
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

                if(i <= path.size()-2){     //has to be minus 2, so that you dont go to path.get(path.size()) since that wouldn't work
                    NodeData nextNode = path.get(i+1);
                    String printFloor = "";
                    if((n.getNodeType().equals("ELEV") && nextNode.getNodeType().equals("ELEV")) ||
                            (n.getNodeType().equals("STAI") && nextNode.getNodeType().equals("STAI"))){
                        for(int j = 1; j < path.size() - i; j++) {
                            nextNode = path.get(i+j);
                            if (!nextNode.getNodeType().equals(n.getNodeType())){
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

                if(n.getFloor().equals(curFloor.getFloorNum())&&prev.getFloor().equals(curFloor.getFloorNum())) {
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

    public void passStage(Stage s){
        stage = s;
    }


}
