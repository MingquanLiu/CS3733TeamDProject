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
    //locatiosn search
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
    private TextField nodeAddNodeID;
    @FXML
    private TextField nodeAddCoords;
    private Coordinate nodeAddCoordinates;
    @FXML
    private TextField nodeAddFloor;
    @FXML
    private TextField nodeAddBuilding;
    @FXML
    private TextField nodeAddNodeType;
    @FXML
    private TextField nodeAddLongName;
    @FXML
    private TextField nodeAddShortName;
    @FXML
    private TextField nodeAddTeamAssigned;


    @FXML
    private Button nodeAddSelectLocation;
    @FXML
    private Button nodeAddSubmit;
    @FXML
    private Button nodeAddCancel;

    //adding edges
    @FXML
    private Button addEdge;

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

    int initX = 1501;
    int initY = 1021;
    int prevSelectX = 0;
    int prevSelectY = 0;

    private HashMap<Coordinate, String> nodeIDs;

    /**
     * reads different button clicks and executes appropriate steps
     * @param e the instance of a button being clicked
     */
    public void buttonHandler(ActionEvent e){
        clearMain();
        //pathfinding button handling
        if(e.getSource() == go){
            System.out.println("drawing path");
            List<NodeData> p = manager.startPathfind(startLocation.getText(), endLocation.getText());
            displayPath(p);
        }
        else if(e.getSource() == clear){
            System.out.println("clearing search");
            startLocation.setText("");
            endLocation.setText("");
        }


        //basic requests button handling
        else if(e.getSource() == restrooms){
            System.out.println("locating restrooms");
            List<NodeData> nodes = manager.queryNodeByType("REST");
            for(NodeData n:nodes){
                showNode(n);
            }
        }
        else if(e.getSource() == vending){
            System.out.println("locating vending machines");
            List<NodeData> nodes = manager.queryNodeByType("VEND");
            for(NodeData n:nodes){
                showNode(n);
            }

        }
        else if(e.getSource() == serviceDesk) {
            System.out.println("locating service desks");
            List<NodeData> nodes = manager.queryNodeByType("INFO");
            for (NodeData n : nodes) {
                showNode(n);
            }
        }

        //admin button handling
        //  log in, check the logged on boolean and change the sign in button
        else if(e.getSource() == login && loggedOut){
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

        }
        //  log off, check the logged on boolean and change the sign in button
        else if(e.getSource() == login && !loggedOut){
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
        else if(e.getSource() == addEdge && !addingEdge){
            addEdge.setText("cancel");
            selectingLocation = "addEdge";
            addingEdge = true;
        }
        //  log off, check the logged on boolean and change the sign in button
        else if(e.getSource() == addEdge && addingEdge){
            addEdge.setText("Add Edge");
            selectingLocation = "";
            addingEdge = false;
        }
        //  clear the username/pass text
        else if(e.getSource() == cancelLogin){
            user.setText("");
            pass.setText("");
        }
        //admin requests
        else if(e.getSource() == transportation) {
            System.out.println("proccessing transportation request");
            menu.setExpanded(false);
            serviceRequester.setVisible((true));

            requestType = "transport";
            requestDescription.setText("Transportation request for");
        }
        else if(e.getSource() == interpreter){
            System.out.println("proccessing interpreter request");
            menu.setExpanded(false);
            serviceRequester.setVisible((true));

            requestType = "interpreter";
            requestDescription.setText("Interpreter request for");
        }
        else if(e.getSource() == maintenance){
            System.out.println("proccessing maintenance request");
            menu.setExpanded(false);
            serviceRequester.setVisible((true));

            requestType = "maintenance";
        }
        else if(e.getSource() == submitRequest){
            System.out.println("submitting " + requestType + " request");
            //manager.sendServiceRequest(adminUser, requestType);
            locationsSelected = false;
            requestType = "";
            menu.setExpanded(true);
            serviceRequester.setVisible(false);
            requestDescription.setText("");
        }

        else if(e.getSource() == mapUpdate){
            mapBuilderOpened = true;
            nodeAddNodeID.setText("");
            nodeAddCoords.setText("");
            nodeAddFloor.setText("");
            nodeAddBuilding.setText("");
            nodeAddNodeType.setText("");
            nodeAddLongName.setText("");
            nodeAddShortName.setText("");
            nodeAddTeamAssigned.setText("");
            nodeAdditionPane.setVisible(true);
        }
        else if(e.getSource() == closeNodeInfo){
            nodeInfoPane.setVisible(false);
            clearMain();
        }
        else if(e.getSource() == nodeAddCancel){
            mapBuilderOpened = false;
            nodeAdditionPane.setVisible(false);
        }
        else if(e.getSource() == nodeAddSubmit){
            nodeAddCoordinates = new Coordinate(prevSelectX,prevSelectY);
            NodeData n = new NodeData(nodeAddNodeID.getText(), nodeAddCoordinates, nodeAddFloor.getText(), nodeAddBuilding.getText(),
                                        nodeAddNodeType.getText(), nodeAddLongName.getText(), nodeAddShortName.getText(), nodeAddTeamAssigned.getText());
            mapBuilderOpened = false;
            nodeAdditionPane.setVisible(false);
            manager.addNode(n);
        }
        else if(e.getSource() == nodeAddSelectLocation){
            System.out.println("selecting locations");
            selectCoor = true;
            nodeAdditionPane.setVisible(false);
            selectingLocation = "nodeAdd";
        }
        else if(e.getSource() == selectMaintenanceLocation){
            serviceRequester.setVisible(false);
            selectingLocation = "maintenance";
        }
        else if(e.getSource() == cancelRequestAttempt){
            System.out.println("canceling request attempt");
            menu.setExpanded(true);
            requestDescription.setText("");
            serviceRequester.setVisible(false);
        }
        //maintenance request modification handling
        else if(e.getSource() == viewRequests){
            System.out.println("showing requests");
            displayServiceRequestStatus();
        }
        else if(e.getSource() == closeRequestWindow){
            System.out.println("closing request window");
            serviceInfo.setVisible(false);
        }
    }


    //mouseclick handling

    /**
     * reads different mouse click and executes appropraite steps
     * @param e the instance of a mouse click
     */
    public void displayNodeInfo(MouseEvent e){
        clearMain();
        int x = (int) e.getX();
        int movedX = makeX(x);
        int y = (int) e.getY();
        int movedY = makeY(y);
        System.out.println("This mouse clicked at X: "+x+" Y:"+y);
        int nodeX = 0;
        int nodeY = 0;
        int realX = x;
        int realY = y;
        String foundNodeId = "";
        double d = 1000;
        double temp;
        Coordinate loc = new Coordinate(x,y);
        if(selectingLocation.equals("")) {
//            System.out.println("mouse x,y: " + x + ", " + y + "  moved x,y: " + movedX +", " +movedY);
            List<NodeData> nodes = manager.getAllNodeData();
            for(NodeData node:nodes){
                nodeX = node.getXCoord();
                nodeY = node.getYCoord();
//                System.out.println("node x,y: " + nodeX + ", " + nodeY + "  real x,y: " +realX + ", " +realY);
                temp = Math.sqrt(Math.pow(movedX-nodeX,2)+Math.pow(movedY-nodeY,2));
                if (temp<d){
                    d = temp;
                    realX = nodeX;
                    realY = nodeY;
                    foundNodeId = node.getNodeID();
                }
            }
            loc.setXCoord(convertX(realX));
            loc.setYCoord(convertY(realY));
            NodeData n = manager.getNodeData(foundNodeId);
            showNode(n);
            nodeInfoPane.setLayoutX(convertX(realX) + 3);
            nodeInfoPane.setLayoutY(convertY(realY) + 3);
            nodeInfoPane.setVisible(true);
            nodeInfoLocation.setText(realX + ", " + realY);

            nodeInfoType.setText("" + n.getNodeType());
            nodeInfoLongName.setText("" + n.getLongName());
            nodeInfoShortName.setText("" + n.getShortName());
        }
        else if(selectingLocation.equals("nodeAdd")){
            selectingLocation = "";
            locationsSelected = true;
            nodeAddCoordinates = loc;
            prevSelectX = makeX(loc.getXCoord());
            prevSelectY = makeY(loc.getYCoord());
            nodeAddCoords.setText(prevSelectX + ", " + prevSelectY);
            Circle c = new Circle(prevSelectX, prevSelectY, 5, RED);
            mainPane.getChildren().addAll(c);
            drawings.add(c);
            nodeAdditionPane.setVisible(true);

        }
        else if(selectingLocation.equals("maintenance")){
            selectingLocation = "";
            locationsSelected = true;
            requestDescription.setText(requestDescription.getText() + "\n at " + loc.getXCoord() + ", " + loc.getYCoord());
            serviceRequester.setVisible(true);
        }
        else if(selectingLocation.equals("addEdge")){

            if (addEdgeN1 ==""||addEdgeN2==""){
                List<NodeData> nodes = manager.getAllNodeData();
                for(NodeData node:nodes){
                    nodeX = node.getXCoord();
                    nodeY = node.getYCoord();
//                System.out.println("node x,y: " + nodeX + ", " + nodeY + "  real x,y: " +realX + ", " +realY);
                    temp = Math.sqrt(Math.pow(movedX-nodeX,2)+Math.pow(movedY-nodeY,2));
                    if (temp<d){
                        d = temp;
                        realX = nodeX;
                        realY = nodeY;
                        foundNodeId = node.getNodeID();
                    }
                }
                Circle c = new Circle(convertX(realX), convertY(realY), 5, RED);
                mainPane.getChildren().addAll((c));
                drawings.add(c);
                loc.setXCoord(convertX(realX));
                loc.setYCoord(convertY(realY));
                if(addEdgeN1==""){
                    addEdgeN1 = foundNodeId;
                }else if(addEdgeN2 == ""){
                    addEdgeN2 = foundNodeId;
                }
                if (addEdgeN1 !=""&&addEdgeN2!=""){
                    NodeData n1 = manager.getNodeData(addEdgeN1);
                    NodeData n2 = manager.getNodeData(addEdgeN2);
                    clearMain();
                    Line l = new Line(convertX(n1.getXCoord()), convertY(n1.getYCoord()), convertX(n2.getXCoord()), convertY(n2.getYCoord()));
                    l.setStrokeWidth(8);
                    l.setStroke(BLUE);
                    Circle c1 = new Circle(convertX(n1.getXCoord()), convertY(n1.getYCoord()), 5, RED);
                    Circle c2 = new Circle(convertX(n2.getXCoord()), convertY(n2.getYCoord()), 5, RED);
                    mainPane.getChildren().addAll(l, c1, c2);
                    drawings.add(l);
                    drawings.add(c1);
                    drawings.add(c2);
                    manager.addEdge(addEdgeN1,addEdgeN2);
                    addEdgeN1 = "";
                    addEdgeN2 = "";
                    selectingLocation = "";
                    addEdge.setText("Add Edge");
                }
            }

        }
    }

    /**
     * shows the basic information of a node that was clicked
     * @param n a node that was clicked
     */
    public void showNode(NodeData n){
        Circle c = new Circle(convertX(n.getXCoord()), convertY(n.getYCoord()), 5, Color.RED);
        c.setFill(Color.RED);
        drawings.add(c);
        mainPane.getChildren().addAll(c);
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
            Line l = new Line();        //how do I get the start/end coords of an edge
            NodeData n = path.get(i);
            l.setStroke(Color.BLUE);
            l.setStrokeWidth(5.0);
            l.setStartX(convertX(prev.getXCoord()));
            l.setStartY(convertY(prev.getYCoord()));
            l.setEndX(convertX(n.getXCoord()));
            l.setEndY(convertY(n.getYCoord()));
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
    public void displayServiceRequestStatus() {
        serviceInfo.setVisible(true);
        //requestsList.getItems().addAll(manager.getRequests());
    }

    /**
     * converts actual coordinates and scales it to map on UI
     * @param x the x coordinate
     * @return the new x coordinate (that works for the UI)
     */
    public int convertX(int x) {
        return x - 3315;
    }

    /**
     * oonverts the actual coordinates and scales it to map on UI
     * @param y the y coordinate
     * @return the new y coordinate (that works for the UI)
     */
    public int convertY(int y) {
        return y - 280;
    }

    /**
     * converts the map UI coordinate to the actual coordinate
     * @param x the x coordinate
     * @return the new x coordinate (that works for the initial map)
     */
    public int makeX(int x){
        return x + 3315;
    }

    /**
     * converts the map UI coordinate to the actual coordinate
     * @param y the y coordinate
     * @return the new y coordinate (that works for the initial map)
     */
    public int makeY(int y){
        return y + 280;
    }
    public void clearMain(){
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
            if(mCoord.getXCoord() == coordinate.getXCoord()&&mCoord.getYCoord() == coordinate.getYCoord()){
                return mNodeData.getNodeID();
            }
        }
        return "Not Found";
    }

}
