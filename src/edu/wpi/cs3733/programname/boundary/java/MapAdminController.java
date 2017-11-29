package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class MapAdminController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private GridPane gridMapEdit;
    @FXML
    private TextField textNodeId;
    @FXML
    private TextField textNodeFloor;
    @FXML
    private TextField textNodeLocation;
    @FXML
    private TextField textNodeType;
    @FXML
    private TextField textNodeFullName;
    @FXML
    private TextField textNodeShortName;

    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnAddNode;
    @FXML
    private JFXButton btnAddEdge;
    @FXML
    private JFXButton btnEditNode;
    @FXML
    private JFXButton btnSubmitNodeEdit;    //TODO add this to UI in scenebuilder

    //FXML objects
    @FXML
    private StackPane drawingStack;
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
    private JFXButton btnZoomIn;
    @FXML
    private JFXButton btnZoomOut;

    @FXML
    private AnchorPane paneControls;
    @FXML
    private JFXHamburger burger;

    ManageController manager;
    private List<Shape> drawings = new ArrayList<>();

    private String addEdgeN1 = "";
    private String addEdgeN2 = "";

    private int prevClickX;
    private int prevClickY;
    //hamburger transitions
    private HamburgerSlideCloseTransition burgerTransition;
    private boolean controlsVisible = false;
    private FadeTransition controlsTransition;
    //zooming/panning
    private double currentScale;
    final double minWidth = 1500;
    final double maxWidth = 5000;

    //showing nodes
    private String selectingLocation = "";
    private boolean locationsSelected;
    private List<NodeData> currentPath;
    private List<NodeData> currentNodes = new ArrayList<>();
    private List<NodeData> floorNodes;
    private boolean addingEdge;

    private NodeData nodeToEdit;

    final private int originalMapRatioIndex = 3;

    ArrayList<Double> mapRatio = new ArrayList<>();
    private int currentMapRatioIndex;
    private DBConnection dbConnection;
    @Override
    public void initialize(URL url, ResourceBundle rb){


    }

    public void initData(DBConnection dbConnection){
        currentMapRatioIndex =originalMapRatioIndex;
//        mapRatio.add(0.24);
        manager = new ManageController(dbConnection);
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
        System.out.println("Scale: " + currentScale);
        imgMap.setFitWidth(maxWidth*currentScale);

        List<NodeData> nodes = manager.queryNodeByFloor(""+floor);
        floorNodes = nodes;
        List<EdgeData> edges = manager.getAllEdgeData();
        displayEdges(edges);
        showNodeList(nodes);
    }


    private void showNodeList (List<NodeData> nodeDataList){
        for(int i = 0;i <nodeDataList.size();i++){
            showNode(nodeDataList.get(i));
        }
    }
    private void showNode(NodeData n){
        currentNodes.add(n);
        System.out.println("x:"+DBCToUIC(n.getXCoord(),currentScale) +" y:"+DBCToUIC(n.getYCoord(),currentScale));
        System.out.println("X:"+n.getXCoord()+" Y:"+n.getYCoord());
        drawCircle(DBCToUIC(n.getXCoord(),currentScale),DBCToUIC(n.getYCoord(),currentScale));
    }

    @SuppressWarnings("Duplicates")
    private void displayPath(List<NodeData> path){
        clearMain();
        System.out.println("drawing path");
        NodeData prev = path.get(0);
        int x = (int) (prev.getXCoord()*currentScale);
        int y = (int) (prev.getYCoord()*currentScale);
        System.out.println(x + ", " + y);
        ArrayList<Line> lines = new ArrayList<>();
        for(int i = 1; i < path.size(); i++){
            Line l = new Line();
            NodeData n = path.get(i);
            l.setStroke(Color.BLUE);
            l.setStrokeWidth(5.0*currentScale);
            l.setStartX(prev.getXCoord()*currentScale);
            l.setStartY(prev.getYCoord()*currentScale);
            l.setEndX(n.getXCoord()*currentScale);
            l.setEndY(n.getYCoord()*currentScale);
            lines.add(l);
            prev = n;
        }
        drawings.addAll(lines);
        panningPane.getChildren().addAll(lines);
    }


    private void drawCircle(int x, int y){
        double radius = 7*currentScale;
        Circle c = new Circle(x, y, radius, RED);
        panningPane.getChildren().add(c);
        drawings.add(c);
    }

    private void displayEdge(NodeData n1, NodeData n2){
        Line line = new Line(n1.getXCoord()*currentScale,n1.getYCoord()*currentScale,n2.getXCoord()*currentScale,n2.getYCoord()*currentScale);
        line.setStrokeWidth(8*currentScale);
        line.setStroke(BLUE);
        panningPane.getChildren().add(line);
        drawings.add(line);
    }

    private void displayEdges(List<EdgeData> edges){
        for(EdgeData edge:edges){
            NodeData node1=getNode(edge.getStartNode());
            NodeData node2 = getNode(edge.getEndNode());
            if(node1!=null&&node2!=null){
                displayEdge(node1,node2);
            }

        }
    }

    private NodeData getNode(String nodeID){
        for(NodeData nodeData:floorNodes){
            if(nodeData.getNodeID().equals(nodeID)){
                if(nodeData.getFloor().equals(Integer.toString(floor))){
                    return nodeData;
                }else{
                    return null;
                }
            }
        }
        return null;
    }


    /**
     * reads different mouse click and executes appropraite steps
     * @param e the instance of a mouse click
     */
    public void onClickMap(MouseEvent e){
        System.out.println("Mouse Clicked");
        //clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        List<NodeData> nodes = manager.getAllNodeData();

        switch (selectingLocation) {
            case "":
                System.out.println("Get in findNodeData");
                NodeData mClickedNode= getClosestNode(nodes,x,y);
                mClickedNode = manager.getNodeData(mClickedNode.getNodeID());
                showNode(mClickedNode);
                break;
            case "nodeAdd":
                locationsSelected = true;
                prevClickX = x;
                prevClickY = y;
                textNodeLocation.setText(prevClickX + "," + prevClickY);
                drawCircle(prevClickX,prevClickY);
                gridMapEdit.setVisible(true);
                selectingLocation = "";
                break;
            case "addEdge":
                if (addEdgeN1.equals("")  || addEdgeN2.equals("")) {
                    nodes = manager.getAllNodeData();
                    mClickedNode = getClosestNode(nodes,x,y);
                    showNode(mClickedNode);
                    if (addEdgeN1.equals("")) {
                        addEdgeN1 = mClickedNode.getNodeID();
                    } else if (addEdgeN2.equals("")) {
                        addEdgeN2 = mClickedNode.getNodeID();
                    }
                    if (!addEdgeN1.equals("") && !addEdgeN2.equals("")) {
                        clearMain();
                        NodeData n1 = manager.getNodeData(addEdgeN1);
                        NodeData n2 = manager.getNodeData(addEdgeN2);
                        displayEdge(n1,n2);
                        manager.addEdge(addEdgeN1, addEdgeN2);
                        addEdgeN1 = "";
                        addEdgeN2 = "";
                        selectingLocation = "";
                    }
                }
            case "editNode":
                int editX = x;
                int editY = y;
                nodeToEdit = getClosestNode(nodes, editX, editY);
                textNodeId.setText(nodeToEdit.getNodeID());
                textNodeLocation.setText(nodeToEdit.getLocation().toString());
                textNodeFloor.setText(nodeToEdit.getFloor());
                textNodeType.setText(nodeToEdit.getNodeType());
                textNodeFullName.setText(nodeToEdit.getLongName());
                textNodeShortName.setText(nodeToEdit.getShortName());
                
                selectingLocation = "";
                gridMapEdit.setVisible(true);
                btnSubmitNodeEdit.setVisible(true);
            case "removeNode":
                int removeX = x;
                int removeY = y;
                NodeData nodeToRemove = getClosestNode(nodes, removeX, removeY);
                displayDeleteNodeConfirmation(nodeToRemove);
                break;
        }
    }
    @SuppressWarnings("Duplicates")
    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY){
        int dbX = UICToDBC(mouseX,currentScale);
        int dbY =UICToDBC(mouseY,currentScale);
        int resultX = 0;
        int resultY = 0;
        String resultNodeId = "";
        double d = 0;
        for (NodeData node : nodeDataList) {
            int nodeX = node.getXCoord();
            int nodeY = node.getYCoord();
            double temp = Math.sqrt(Math.pow(dbX - nodeX, 2) + Math.pow(dbY - nodeY, 2));
            if (temp < d||d==0) {
                d = temp;
                resultX = nodeX;
                resultY = nodeY;
                resultNodeId = node.getNodeID();
            }
        }
        return new NodeData(resultNodeId,new Coordinate(resultX,resultY),null,""+floor,null,null,null,null);
    }

    @SuppressWarnings("Duplicates")
    public void clearMain(){
        if(drawings.size() > 0){
            for(Shape shape:drawings){
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
    }
    private void clearPath(){
        currentPath = new ArrayList<>();

    }

    private void clearNodes(){
        currentNodes = new ArrayList<>();
    }

    @SuppressWarnings("Duplicates")
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

    @SuppressWarnings("Duplicates")
    private void setFloor(){
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


    private void newNodeLocation() {
        selectingLocation = "nodeAdd";
        locationsSelected = false;
        gridMapEdit.setVisible(false);
    }
    public void addNodeHandler() {
        String id = textNodeId.getText();
        String nodeType = textNodeType.getText();

        String location = textNodeLocation.getText();
        String[] locXY = location.split(",");
        Coordinate loc = new Coordinate(Integer.parseInt(locXY[0]), Integer.parseInt(locXY[1]));

        String longName = textNodeFullName.getText();
        String floor = textNodeFloor.getText();
        String shortName = textNodeShortName.getText();

        String building = "";               //figure out building based on Coordinate
        String teamAssigned = "";           //figure out what to do with this field for new nodes

        NodeData newNode = new NodeData(id,loc,floor,building,nodeType,longName,shortName,teamAssigned);
        manager.addNode(newNode);
        displayAddNodeConfirmation(id, longName, loc);
    }

    public void displayAddNodeConfirmation(String id, String name, Coordinate loc) {
        String message = "Node " + name + " (" + id + ")  was successfully added to" +
                " the map at location " + loc.toString();
    }

    public void addEdgeHandler() {
        if(addingEdge){
            selectingLocation = "";
            addingEdge = false;
        }else{
            selectingLocation = "addEdge";
            addingEdge = true;
        }
    }

    public void removeNodeHandler() {
        selectingLocation = "removeNode";
        gridMapEdit.setVisible(false);
    }

    public void editNodeHandler() {
        selectingLocation = "editNode";
        gridMapEdit.setVisible(false);
    }

    public void submitEditNodeHandler() {
        manager.deleteNode(nodeToEdit);

        String location = textNodeLocation.getText();
        location = location.replace('(', ' ');
        location = location.replace(')', ' ');
        location = location.trim();
        String[] locXY = location.split(",");
        Coordinate loc = new Coordinate(Integer.parseInt(locXY[0]), Integer.parseInt(locXY[0]));

        nodeToEdit.setNodeID(textNodeId.getText());
        nodeToEdit.setLocation(loc);
        nodeToEdit.setFloor(textNodeFloor.getText());
        nodeToEdit.setNodeType(textNodeType.getText());
        nodeToEdit.setLongName(textNodeFullName.getText());
        nodeToEdit.setShortName(textNodeShortName.getText());

        manager.addNode(nodeToEdit);
    }

    public void openMenuHandler(){
        burgerTransition.setRate(burgerTransition.getRate()*-1);
        burgerTransition.play();

        controlsVisible = !controlsVisible;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        controlsTransition.setToValue(Math.abs(controlsTransition.getToValue()-1));         //these two lines should make it fade out the next time you click
        controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue()-1));     // but they doent work the way I want them to for some reason
    }


    public void displayDeleteNodeConfirmation(NodeData nodeToRemove) {
        gridMapEdit.setVisible(true);
    }
    @SuppressWarnings("Duplicates")
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
        }
    }
    private int UICToDBC(int value, double scale){
        return (int)((double)value/scale);
    }
    private int DBCToUIC(int value, double scale){
        return (int)((double)value*scale);
    }
}
