package edu.wpi.cs3733.programname.boundary.java;

import com.sun.javafx.geom.Edge;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;

import static javafx.scene.paint.Color.*;

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
    private Button selectLocations;
    @FXML
    private Button cancelRequestAttempt;
    @FXML
    private Button submitRequest;

    //requests confirmation popup
    @FXML
    private Label requestConfirmLabel;
    @FXML
    private DialogPane requestConfirmPane;

    private boolean success;

    private boolean loggedOut = true;
    private ManageController manager;
    private HashMap<Coordinate, String> nodeIDs;
    private boolean locationsSelected;
    //private Employee adminUser;
    private String requestType;

    public void buttonHandler(ActionEvent e){

        //pathfinding button handling
        if(e.getSource() == go){
            System.out.println("drawing path");
            //ArrayList<Node> p = manager.startPathfind(startLocation.getText(), endLocation.getText());
            //displayPath(p);
        }
        else if(e.getSource() == clear){
            System.out.println("clearing search");
            startLocation.setText("");
            endLocation.setText("");
        }


        //basic requests button handling
        else if(e.getSource() == restrooms){
            System.out.println("locating restrooms");
            /*
            ArrayList<NodeData> nodes = manager.queryNodeByType("REST");
            for(NodeData n:nodes){
                showNode(n);
            }
            */
        }
        else if(e.getSource() == vending){
            System.out.println("locating vending machines");
            /*
            ArrayList<NodeData> nodes = manager.queryNodeByType("VEND");
            for(NodeData n:nodes){
                showNode(n);
            }
            */
        }
        else if(e.getSource() == serviceDesk) {
            System.out.println("locating service desks");
            /*
            ArrayList<NodeData> nodes = manager.queryNodeByType("SERVE");
            for (NodeData n : nodes) {
                showNode(n);
            }
            */
        }

        //admin button handling
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
        else if(e.getSource() == cancelLogin){
            user.setText("");
            pass.setText("");
        }
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
            if(!locationsSelected){
                System.out.println("no locations selected");
            }
            else if(locationsSelected && (requestType.equals("transport") || requestType.equals("maintenance") || requestType.equals("interpreter"))) {
                System.out.println("submitting " + requestType + " request");
                //manager.sendServiceRequest(adminUser, requestType);
                locationsSelected = false;
                requestType = "";
                menu.setExpanded(true);
                serviceRequester.setVisible(false);
            }
        }
        else if(e.getSource() == selectLocations){
            System.out.println("selecting locations");
            serviceRequester.setVisible(false);
            //awaitLocationSelect();
            serviceRequester.setVisible(true);
            locationsSelected = true;
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

    public void displayNodeInfo(NodeData n){

    }
    /*
    public void showNode(NodeData n){
        Circle c = new Circle(n.coordinate.getX(), n.coordinate.getY(), 10, Color.RED);
        mainPane.getChildren().addAll(c);
    }

    private void displayPath(ArrayList<Node> path){
        ArrayList<Line> lines = new ArrayList<Line>();
        Node prev = path.get(0);
        for(int i = 1; i < path.size(); i++){
            Line l = new Line();        //how do I get the star/end coords of an edge
            l.setStroke(Color.BLUE);
            l.setStartX(prev.getX());
            l.setStartY(prev.getY());
            l.setEndX(n.getX()):
            l.setEndY(n.getY());
            lines.add(l);
            prev = n;
        }
        mainPane.getChildren().addAll(lines);
    }
    */
    public void refreshDisplayMap(){

    }

    public void displayServiceRequestStatus() {
        serviceInfo.setVisible(true);
        //requestsList.getItems().addAll(requests.keySet());
    }

}
