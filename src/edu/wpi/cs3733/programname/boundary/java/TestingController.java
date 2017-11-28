package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import javafx.scene.Node;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;


public class TestingController implements Initializable{

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
    private Button btnZoomIn;
    @FXML
    private Button btnZoomOut;

    //Admin features
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblLoginStatus;
    @FXML
    private Button btnOpenAdmin;
    @FXML
    private Button btnInterpreterReq;
    @FXML
    private Button btnMaintenanceReq;
    @FXML
    private Button btnTransportationReq;

    //service request dialog
    @FXML
    private TextArea requestDescription;


    //hamburger pane and transitions
    @FXML
    private JFXHamburger burger;
    @FXML
    private AnchorPane paneControls;

    //location search
    @FXML
    private Button btnGo;
    @FXML
    private Button clear;
    @FXML
    private TextField txtStartLocation;
    @FXML
    private TextField txtEndLocation;



    //global variables, not FXML tied
    private ManageController manager;

    //locations search
    private List<Shape> drawings = new ArrayList<>();
    private GraphicsContext gc;
    private List<NodeData> currentPath;

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


    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb){
        burgerTransition = new HamburgerSlideCloseTransition(burger);
        burgerTransition.setRate(-1);

        controlsTransition = new FadeTransition(new Duration(500), paneControls);
        controlsTransition.setFromValue(0);
        controlsTransition.setToValue(1);
        paneControls.setVisible(controlsVisible);

        imgMap.setFitWidth(imgMap.getFitWidth()*0.3138);
        currentScale = 0.3138;

        manager = new ManageController();

    }

    //topmost methods are newest
    private void drawCycle(int x, int y){
        Circle c = new Circle(x, y, 5, RED);
        panningPane.getChildren().add(c);
        drawings.add(c);
    }

    private void showNode(NodeData n){
        drawCycle((int)(n.getX()*currentScale),(int)(n.getY()*currentScale));
    }

    private void showNodeInfo(NodeData nodeData){
        int dbX = nodeData.getX();
        int dbY = nodeData.getY();
        System.out.println("Node Coordinate: "+dbX+","+dbY+" Node Name: "+nodeData.getLongName());
//        nodeInfoPane.setLayoutX(DBCoordinateToUICoordinate(dbX,initX) + 3);
//        nodeInfoPane.setLayoutY(DBCoordinateToUICoordinate(dbY,initY) + 3);
//        nodeInfoPane.setVisible(true);
//        nodeInfoLocation.setText(dbX + ", " + dbY);
//        nodeInfoType.setText("" + nodeData.getType());
//        nodeInfoLongName.setText("" + nodeData.getLongName());
//        nodeInfoShortName.setText("" + nodeData.getShortName());
    }

    //displaying node info on click

    public void mouseClickHandler(MouseEvent e){
        clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (selectingLocation) {
            //case for displaying nearest node info when nothing is selected
            case "":
                System.out.println("Get in findNodeData");
                List<NodeData> nodes = manager.getAllNodeData();
                NodeData mClickedNode= getClosestNode(nodes,x,y);
                mClickedNode = manager.getNodeData(mClickedNode.getId());
                showNode(mClickedNode);
                showNodeInfo(mClickedNode);
                break;
                // the rest of the situations when you click on the map
//            case "nodeAdd":
//                locationsSelected = true;
//                prevSelectX = UICoordinateToDBCoordinate(x,initX);
//                prevSelectY = UICoordinateToDBCoordinate(y,initY);
//                nodeAddCoords.setText(prevSelectX + ", " + prevSelectY);
//                drawCycle(prevSelectX,prevSelectY);
//                nodeAdditionPane.setVisible(true);
//                selectingLocation = "";
//                break;
//            case "maintenance":
//                locationsSelected = true;
//                requestDescription.setText(requestDescription.getText() + "\n at " + x + ", " + y);
//                serviceRequester.setVisible(true);
//                selectingLocation = "";
//                break;
//            case "addEdge":
//                if (addEdgeN1.equals("")  || addEdgeN2.equals("")) {
//                    nodes = manager.getAllNodeData();
//                    mClickedNode = getClosestNode(nodes,x,y);
//                    showNode(mClickedNode);
//                    if (addEdgeN1.equals("")) {
//                        addEdgeN1 = mClickedNode.getId();
//                    } else if (addEdgeN2.equals("")) {
//                        addEdgeN2 = mClickedNode.getId();
//                    }
//                    if (!addEdgeN1.equals("") && !addEdgeN2.equals("")) {
//                        clearMain();
//                        NodeData n1 = manager.getNodeData(addEdgeN1);
//                        NodeData n2 = manager.getNodeData(addEdgeN2);
//                        showEdge(n1,n2);
//                        manager.addEdge(addEdgeN1, addEdgeN2);
//                        addEdge.setText("Add Edge");
//                        addEdgeN1 = "";
//                        addEdgeN2 = "";
//                        selectingLocation = "";
//                    }
//                }
//                break;

        }
    }
    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY){
        int dbX = (int)(mouseX*(1/currentScale));
        int dbY = (int)(mouseY*(1/currentScale));
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

    //pathfinding functions
    public void goButtonHandler(){
        System.out.println("drawing path");
        currentPath = manager.startPathfind(txtStartLocation.getText(), txtEndLocation.getText());
        displayPath(currentPath);
    }

    private void displayPath(List<NodeData> path){
        clearMain();
        System.out.println("drawing path");
        NodeData prev = path.get(0);
        int x = (int) (prev.getX()*currentScale);
        int y = (int) (prev.getY()*currentScale);
        System.out.println(x + ", " + y);
        ArrayList<Line> lines = new ArrayList<>();
        for(int i = 1; i < path.size(); i++){
            Line l = new Line();
            NodeData n = path.get(i);
            l.setStroke(Color.BLUE);
            l.setStrokeWidth(5.0*currentScale);
            l.setStartX(prev.getX()*currentScale);
            l.setStartY(prev.getY()*currentScale);
            l.setEndX(n.getX()*currentScale);
            l.setEndY(n.getY()*currentScale);
            lines.add(l);
            prev = n;
        }
        drawings.addAll(lines);
        panningPane.getChildren().addAll(lines);
    }

    private void clearMain(){
        if(drawings.size() > 0){
            for(Shape shape:drawings){
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
    }


    //hamburger handling
    public void openMenu(MouseEvent e){
        burgerTransition.setRate(burgerTransition.getRate()*-1);
        burgerTransition.play();

        controlsVisible = !controlsVisible;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        controlsTransition.setToValue(Math.abs(controlsTransition.getToValue()-1));         //these two lines should make it fade out the next time you click
        controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue()-1));     // but they doent work the way I want them to for some reason
    }

    //popup methods
    public void popupHandler(ActionEvent e){
        if(e.getSource() == btnLogin){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("Login_Popup.fxml")));
            Scene newScene;
            try {
                newScene = new Scene(loader.load());
            } catch(IOException ex){
                //Todo add some sort of error handling
                return;
            }

            Stage loginStage = new Stage();
            loginStage.setScene(newScene);
            loginStage.showAndWait();
            boolean loggedIn = loader.<LoginPopup>getController().getLoggedIn();
            lblLoginStatus.setText("logged in");
        }
    }
    //map zooming method
    public void zoomHandler(ActionEvent e){
        clearMain();
        if(e.getSource() == btnZoomOut){
            if(imgMap.getFitWidth() <= minWidth){
                return;
            }
            imgMap.setFitWidth(Math.max(imgMap.getFitWidth()*.8,minWidth));
            currentScale *= 0.8;
        }
        else{
            if(imgMap.getFitWidth() >= maxWidth){
                return;
            }
            imgMap.setFitWidth(Math.min(imgMap.getFitWidth()*1.2, maxWidth));
            currentScale *= 1.2;
        }
        if(!(currentPath == null)) {
            displayPath(currentPath);
        }
        System.out.println(currentScale);
    }

    //map switching methods
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
    public void showMouseCoords(MouseEvent e){
        System.out.println(e.getX() + ", " + e.getY());
    }


}
