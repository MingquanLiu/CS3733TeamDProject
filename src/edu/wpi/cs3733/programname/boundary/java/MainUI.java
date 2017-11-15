package edu.wpi.cs3733.programname.boundary.java;

import com.sun.javafx.geom.Edge;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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


    private boolean loggedOut = true;               //used to change the sign in/sign out button text
    private ManageController manager = new ManageController();               //global manage controller to call methods
    private boolean locationsSelected;              //used when submitting requests to ensure selection of locations
    //private Employee adminUser;                   //will be used in the future for logging in
    private String requestType;                     //used for submitting requests to keep method count down
    private String selectingLocation = "";          //string that determines if the user is currently selecting a location
    private boolean selectCoor = false;

    public void buttonHandler(ActionEvent e){

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
            List<NodeData> nodes = manager.queryNodeByType("SERV");
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
            nodeAdditionPane.setVisible(true);
        }
        else if(e.getSource() == closeNodeInfo){
            nodeInfoPane.setVisible(false);
        }
        else if(e.getSource() == nodeAddCancel){
            nodeAdditionPane.setVisible(false);
        }
        else if(e.getSource() == nodeAddSubmit){
            NodeData n = new NodeData(nodeAddID.getText(), nodeAddCoordinates, "2", nodeAddType.getText(), nodeAddName.getText(), nodeAddShortName.getText());
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
    public void displayNodeInfo(MouseEvent e){
        int x = (int) e.getX();
        int y = (int) e.getY();
        System.out.println("This mouse clicked at X: "+x+" Y:"+y);
        int nodeX = 0;
        int nodeY = 0;
        int realX = x;
        int realY = y;
        double d = 1000;
        double temp;
        Coordinate loc = new Coordinate(x,y);
        if(selectCoor){
            nodeAddCoords.setText(x+","+y);
            selectCoor =false;
        }else{

        }

        if(selectingLocation.equals("")) {
            List<NodeData> nodes = manager.getAllNodeData();
            for(NodeData node:nodes){
                nodeX = node.getX();
                nodeY = node.getY();
                temp = Math.sqrt(Math.pow(x-nodeX,2)+Math.pow(y-nodeY,2));
                if (temp<d){
                    d = temp;
                    realX = nodeX;
                    realY = nodeY;
                }
            }
            loc.setX(realX);
            loc.setY(realY);

//            String id = nodeIDs.get(loc);
//            NodeData n = manager.getNodeData(id);

//            showNode(n);

//            nodeInfoPane.setLayoutX(nodeX + 3);
//            nodeInfoPane.setLayoutY(nodeY + 3);
//            nodeInfoPane.setVisible(true);
//            nodeInfoLocation.setText(x + ", " + y);
//
//            nodeInfoType.setText("" + n.getType());
//            nodeInfoLongName.setText("" + n.getLongName());
//            nodeInfoShortName.setText("" + n.getShortName());

        }
        else if(selectingLocation.equals("nodeAdd")){
            selectingLocation = "";
            locationsSelected = true;
            nodeAddCoordinates = loc;
            nodeAddCoords.setText(loc.getX() + ", " + loc.getY());
            nodeAdditionPane.setVisible(true);

        }
        else if(selectingLocation.equals("maintenance")){
            selectingLocation = "";
            locationsSelected = true;
            requestDescription.setText(requestDescription.getText() + "\n at " + loc.getX() + ", " + loc.getY());
            serviceRequester.setVisible(true);
        }
    }
    public void showNode(NodeData n){
        Circle c = new Circle(n.getX(), n.getY(), 5, Color.RED);
        c.setFill(Color.RED);
        mainPane.getChildren().addAll(c);
    }

    private void displayPath(List<NodeData> path){
        ArrayList<Line> lines = new ArrayList<Line>();
        NodeData prev = path.get(0);
        for(int i = 1; i < path.size(); i++){
            Line l = new Line();        //how do I get the start/end coords of an edge
            NodeData n = path.get(i);
            l.setStroke(Color.BLUE);
            l.setStrokeWidth(20.0);
            l.setStartX(prev.getX());
            l.setStartY(prev.getY());
            l.setEndX(n.getX());
            l.setEndY(n.getY());
            lines.add(l);
            prev = n;
        }
        mainPane.getChildren().addAll(lines);
    }


    public void displayServiceRequestStatus() {
        serviceInfo.setVisible(true);
        //requestsList.getItems().addAll(manager.getRequests());
    }

}
