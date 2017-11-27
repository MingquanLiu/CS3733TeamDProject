package edu.wpi.cs3733.programname.boundary.java;

import com.sun.javafx.geom.Edge;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static javafx.scene.paint.Color.*;
import static javax.print.attribute.standard.Chromaticity.COLOR;

public class MainUI {
    //location search
    @FXML
    private Button go;
    @FXML
    private Button clear;
    @FXML
    private TextField startLocation;
    @FXML
    private TextField endLocation;

    //Additional services
    @FXML
    private Button restrooms;
    @FXML
    private Button vending;
    @FXML
    private Button serviceDesk;
    @FXML
    private Button transportation;
    @FXML
    private Button maintenance;
    @FXML
    private Button interpreter;
    @FXML
    private Button mapUpdate;
    @FXML
    private Button viewRequests;

    //login
    @FXML
    private Button login;
    @FXML
    private Button cancelLogin;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;

    // viewable panes
    @FXML
    private AnchorPane mainPane;
    @FXML
    private DialogPane serviceInfo;
    @FXML
    private AnchorPane admin;
    @FXML
    private TitledPane menu;
    @FXML
    private DialogPane serviceRequester;

    //requests viewer
    @FXML
    private Button closeRequestWindow;
    @FXML
    private Button closeRequest;
    @FXML
    private ListView requestsList;

    //requests handler
    @FXML
    private TextArea requestDescription;
    @FXML
    private Button selectMaintenanceLocation;
    @FXML
    private Button cancelRequestAttempt;
    @FXML
    private Button submitRequest;

    //requests confirmation popup
    @FXML
    private Label requestConfirmLabel;
    @FXML
    private DialogPane requestConfirmPane;

    //node information pane
    @FXML
    private DialogPane nodeInfoPane;
    @FXML
    private Label nodeInfoLongName;
    @FXML
    private Label nodeInfoShortName;
    @FXML
    private Label nodeInfoType;
    @FXML
    private Label nodeInfoLocation;
    @FXML
    private Button closeNodeInfo;

    //node addition pane
    @FXML
    private DialogPane nodeAdditionPane;
    @FXML
    private TextField nodeAddID;
    @FXML
    private TextField nodeAddName;
    @FXML
    private TextField nodeAddShortName;
    @FXML
    private TextField nodeAddType;
    @FXML
    private TextField nodeAddCoords;
    private Coordinate nodeAddCoordinates;
    @FXML
    private Button nodeAddSelectLocation;
    @FXML
    private Button nodeAddSubmit;
    @FXML
    private Button nodeAddCancel;

    //adding edges
    @FXML
    private Button addEdge;

    @FXML
    private Button btnCallPop;

    private boolean addingEdge = false;
    private boolean loggedOut = true;               //used to change the sign in/sign out button text
    private ManageController manager = new ManageController();               //global manage controller to call methods
    private boolean locationsSelected;              //used when submitting requests to ensure selection of locations
    private boolean mapBuilderOpened = false;
    //private Employee adminUser;                   //will be used in the future for logging in
    private String requestType;                     //used for submitting requests to keep method count down
    private String selectingLocation = "";          //string that determines if the user is currently selecting a location
    private boolean selectCoor = false;
    private int clickCount = 0;
    private String addEdgeN1 = "";
    private String addEdgeN2 = "";

    private List<Shape> drawings = new ArrayList<>();

    private int initX = 3315;
    private int initY = 280;
    private int prevSelectX = 0;
    private int prevSelectY = 0;
    private String foundNodeId = "";

    private HashMap<Coordinate, String> nodeIDs;

    public void goButtonHandler(){
        System.out.println("drawing path");
        List<NodeData> p = manager.startPathfind(startLocation.getText(), endLocation.getText());
        displayPath(p);
    }

    public void clearButtonHandler(){
        System.out.println("clearing search");
        startLocation.setText("");
        endLocation.setText("");
    }

    public void locateRestroomButtonHandler(){
        System.out.println("locating restrooms");
        List<NodeData> nodes = manager.queryNodeByType("REST");
        for(NodeData n:nodes){
            showNode(n);
        }
    }

    public void locateVendingMachineButtonHandler(){
        System.out.println("locating vending machines");
        List<NodeData> nodes = manager.queryNodeByType("VEND");
        for(NodeData n:nodes){
            showNode(n);
        }
    }

    public void locateServiceDeskButtonHandler(){
        System.out.println("locating service desks");
        List<NodeData> nodes = manager.queryNodeByType("INFO");
        for (NodeData n : nodes) {
            showNode(n);
        }
    }

    public void loginButtonHandler(){
        if(loggedOut){
            System.out.println("logging in");
            //adminUser = manager.login(user.getText(), pass.getText());
            //if(adminUser != null){
            login.setText("Sign out");
            loggedOut = false;
            admin.setVisible(true);
            //if (adminUser.isSysAdmin()){
            mapUpdate.setVisible(true);
            //}
            cancelLogin.setVisible(false);
            //}
        }else{
            System.out.println("logging out");
            /*
            manager.logout(adminUser);
            adminUser = null;
            */
            loggedOut = true;
            login.setText("Sign in");
            admin.setVisible(false);
            mapUpdate.setVisible(true);
            cancelLogin.setVisible(true);
        }
    }

    public void addEdgeButtonHandler(){
        if(addingEdge){
            addEdge.setText("Add Edge");
            selectingLocation = "";
            addingEdge = false;
        }else{
            addEdge.setText("cancel");
            selectingLocation = "addEdge";
            addingEdge = true;
        }
    }

    public void cancelLoginButtonHandler(){
        user.setText("");
        pass.setText("");
    }
    public void transportationSRButtonHandler(){
        System.out.println("proccessing transportation request");
        menu.setExpanded(false);
        serviceRequester.setVisible((true));
        requestType = "transport";
        requestDescription.setText("Transportation request for");
    }
    public void interpreterSRButtonHandler(){
        System.out.println("proccessing interpreter request");
        menu.setExpanded(false);
        serviceRequester.setVisible((true));

        requestType = "interpreter";
        requestDescription.setText("Interpreter request for");
    }
    public void maintenanceSRButtonHandler(){
        System.out.println("proccessing maintenance request");
        menu.setExpanded(false);
        serviceRequester.setVisible((true));
        requestType = "maintenance";
    }
    public void submitRequestButtonHandler(){
        System.out.println("submitting " + requestType + " request");
        //manager.sendServiceRequest(adminUser, requestType);
        locationsSelected = false;
        requestType = "";
        menu.setExpanded(true);
        serviceRequester.setVisible(false);
        requestDescription.setText("");
    }
    public void mapUpdateButtonHandler(){
        mapBuilderOpened = true;
        nodeAddCoords.setText("");
        nodeAddID.setText("");
        nodeAddName.setText("");
        nodeAddShortName.setText("");
        nodeAddType.setText("");
        nodeAdditionPane.setVisible(true);
    }
    public void closeNodeInfoButtonHandler(){
        nodeInfoPane.setVisible(false);
        clearMain();
    }

    public void nodeAddCancelButtonHandler(){
        mapBuilderOpened = false;
        nodeAdditionPane.setVisible(false);
    }

    public void nodeAddSubmitButtonHandler(){
        Coordinate mCoordinate = new Coordinate(prevSelectX,prevSelectY);
        NodeData n = new NodeData(nodeAddID.getText(), mCoordinate, "2", nodeAddType.getText(), nodeAddName.getText(), nodeAddShortName.getText());
        mapBuilderOpened = false;
        nodeAdditionPane.setVisible(false);
        manager.addNode(n);
    }
    public void nodeAddSelectLocationButtonHandler(){
        System.out.println("selecting locations");
        selectCoor = true;
        nodeAdditionPane.setVisible(false);
        selectingLocation = "nodeAdd";
    }

    public void selectMaintenanceLocationButtonHandler(){
        serviceRequester.setVisible(false);
        selectingLocation = "maintenance";
    }

    public void cancelRequestAttemptButtonHandler(){
        System.out.println("canceling request attempt");
        menu.setExpanded(true);
        requestDescription.setText("");
        serviceRequester.setVisible(false);
    }

    public void viewRequestsButtonHandler(){
        System.out.println("showing requests");
        displayServiceRequestStatus();
    }

    public void closeRequestWindowButtonHandler(){
        System.out.println("closing request window");
        serviceInfo.setVisible(false);
    }

    /**
     * reads different button clicks and executes appropriate steps
     */

    /**
     * reads different mouse click and executes appropraite steps
     * @param e the instance of a mouse click
     */
    public void displayNodeInfo(MouseEvent e){
        clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (selectingLocation) {
            case "":
                System.out.println("Get in findNodeData");
                List<NodeData> nodes = manager.getAllNodeData();
                NodeData mClickedNode= getClosestNode(nodes,x,y);
                mClickedNode = manager.getNodeData(mClickedNode.getId());
                showNode(mClickedNode);
                showNodeInfo(mClickedNode);
                break;
            case "nodeAdd":
                locationsSelected = true;
                prevSelectX = UICoordinateToDBCoordinate(x,initX);
                prevSelectY = UICoordinateToDBCoordinate(y,initY);
                nodeAddCoords.setText(prevSelectX + ", " + prevSelectY);
                drawCycle(prevSelectX,prevSelectY);
                nodeAdditionPane.setVisible(true);
                selectingLocation = "";
                break;
            case "maintenance":
                locationsSelected = true;
                requestDescription.setText(requestDescription.getText() + "\n at " + x + ", " + y);
                serviceRequester.setVisible(true);
                selectingLocation = "";
                break;
            case "addEdge":
                if (addEdgeN1.equals("")  || addEdgeN2.equals("")) {
                    nodes = manager.getAllNodeData();
                    mClickedNode = getClosestNode(nodes,x,y);
                    showNode(mClickedNode);
                    if (addEdgeN1.equals("")) {
                        addEdgeN1 = mClickedNode.getId();
                    } else if (addEdgeN2.equals("")) {
                        addEdgeN2 = mClickedNode.getId();
                    }
                    if (!addEdgeN1.equals("") && !addEdgeN2.equals("")) {
                        clearMain();
                        NodeData n1 = manager.getNodeData(addEdgeN1);
                        NodeData n2 = manager.getNodeData(addEdgeN2);
                        showEdge(n1,n2);
                        manager.addEdge(addEdgeN1, addEdgeN2);
                        addEdge.setText("Add Edge");
                        addEdgeN1 = "";
                        addEdgeN2 = "";
                        selectingLocation = "";
                    }
                }
                break;
        }
    }


    private int UICoordinateToDBCoordinate(int originalValue, int changingConstant){
        return originalValue+changingConstant;
    }
    private int DBCoordinateToUICoordinate(int originalValue, int changingConstant){
        return originalValue-changingConstant;
    }

    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY){
        int dbX = UICoordinateToDBCoordinate(mouseX,initX);
        int dbY = UICoordinateToDBCoordinate(mouseY,initY);
        int resultX = 0;
        int resultY = 0;
        String resultNodeId = "";
        double d = 0;
        for (NodeData node : nodeDataList) {
            int nodeX = node.getX();
            int nodeY = node.getY();
//                System.out.println("node x,y: " + nodeX + ", " + nodeY + "  real x,y: " +realX + ", " +realY);
            double temp = Math.sqrt(Math.pow(dbX - nodeX, 2) + Math.pow(dbY - nodeY, 2));
            if (temp < d||d==0) {
                d = temp;
                resultX = nodeX;
                resultY = nodeY;
                resultNodeId = node.getId();
            }
        }
        return new NodeData(resultNodeId,new Coordinate(resultX,resultY),null,null,null,null);
    }

    private void showNodeInfo(NodeData nodeData){
        int dbX = nodeData.getX();
        int dbY = nodeData.getY();
        nodeInfoPane.setLayoutX(DBCoordinateToUICoordinate(dbX,initX) + 3);
        nodeInfoPane.setLayoutY(DBCoordinateToUICoordinate(dbY,initY) + 3);
        nodeInfoPane.setVisible(true);
        nodeInfoLocation.setText(dbX + ", " + dbY);
        nodeInfoType.setText("" + nodeData.getType());
        nodeInfoLongName.setText("" + nodeData.getLongName());
        nodeInfoShortName.setText("" + nodeData.getShortName());
    }
    //mouseclick handling

    private void drawCycle(int x, int y){
        Circle c = new Circle(x, y, 5, RED);
        mainPane.getChildren().add(c);
        drawings.add(c);
    }

    private void drawLine(int x1, int y1, int x2, int y2){
        Line line = new Line(x1,y1,x2,y2);
        line.setStrokeWidth(8);
        line.setStroke(BLUE);
        mainPane.getChildren().add(line);
        drawings.add(line);
    }
    /**
     * shows the basic information of a node that was clicked
     * @param n a node that was clicked
     */
    private void showNode(NodeData n){
        drawCycle(DBCoordinateToUICoordinate(n.getX(),initX),DBCoordinateToUICoordinate(n.getY(),initY));
    }

    private void showEdge(NodeData n1, NodeData n2){
        int DBX1 = DBCoordinateToUICoordinate(n1.getX(),initX);
        int DBY1 = DBCoordinateToUICoordinate(n1.getY(),initY);
        int DBX2 = DBCoordinateToUICoordinate(n2.getX(),initX);
        int DBY2 = DBCoordinateToUICoordinate(n2.getY(),initY);
        drawLine(DBX1,DBY1,DBX2,DBY2);
        drawCycle(DBX1,DBY1);
        drawCycle(DBX2,DBY2);
    }

    /**
     * displays a highlighted path on the map
     * @param path a list of nodes that make up a path
     */
    private void displayPath(List<NodeData> path){
        clearMain();
        System.out.println("Inside displayPath");
        ArrayList<Line> lines = new ArrayList<Line>();
        NodeData prev = path.get(0);
        for(int i = 1; i < path.size(); i++){
            Line l = new Line();
            NodeData n = path.get(i);
            l.setStroke(Color.BLUE);
            l.setStrokeWidth(5.0);
            l.setStartX(DBCoordinateToUICoordinate(prev.getX(),initX));
            l.setStartX(DBCoordinateToUICoordinate(prev.getX(),initX));
            l.setStartY(DBCoordinateToUICoordinate(prev.getY(),initY));
            l.setEndX(DBCoordinateToUICoordinate(n.getX(),initX));
            l.setEndY(DBCoordinateToUICoordinate(n.getY(),initY));
            lines.add(l);
            prev = n;
        }
        mainPane.getChildren().addAll(lines);
        drawings.addAll(lines);
    }


    /**
     * displays the status of a service request
     * not fully finished, will complete in future iteration
     */
    private void displayServiceRequestStatus() {
        serviceInfo.setVisible(true);
        //requestsList.getItems().addAll(manager.getRequests());
    }

    private void clearMain(){
        if(drawings.size() > 0){
            for(Shape shape:drawings){
                System.out.println("success remove");
                mainPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
    }

    /**
     * given the coordinates of a Node, the corresponding node ID is returned
     * @param mList
     * @param coordinate
     * @return corresponding ID given node  coordinate
     */
    public String getNodeIdWithCoor(List<NodeData> mList, Coordinate coordinate){
        for(NodeData mNodeData : mList){
            Coordinate mCoord = mNodeData.getLocation();
            if(mCoord.getX() == coordinate.getX()&&mCoord.getY() == coordinate.getY()){
                return mNodeData.getId();
            }
        }
        return "Not Found";
    }

    public void examplePopup(ActionEvent e){

    }
}
