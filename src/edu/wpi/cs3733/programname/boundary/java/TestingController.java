package edu.wpi.cs3733.programname.boundary.java;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.sun.org.apache.xalan.internal.lib.NodeInfo;
import edu.wpi.cs3733.programname.commondata.Coordinate;
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
import javafx.scene.layout.GridPane;
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
    @FXML
    private DialogPane serviceRequester;

    //map switching objects
    @FXML
    private JFXButton btnMapUp;
    @FXML
    private JFXButton btnMapDwn;
    @FXML
    private Label lblCurrentFloor;
    private int floor = 2;

    //zoom and pan objects
    @FXML
    private JFXButton btnZoomIn;
    @FXML
    private JFXButton btnZoomOut;

    //Admin features
    @FXML
    private GridPane paneAdminFeatures;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label lblLoginStatus;
    @FXML
    private JFXButton btnOpenAdmin;
    @FXML
    private JFXButton btnInterpreterReq;
    @FXML
    private JFXButton btnMaintenanceReq;
    @FXML
    private JFXButton btnTransportationReq;

    @FXML
    private JFXButton btnLocateBR;
    @FXML
    private JFXButton btnLocateVM;
    @FXML
    private JFXButton btnLocateSD;
    @FXML
    private JFXButton btnLocateWR;
    @FXML
    private JFXButton btnMapEdit;

    //service request dialog
    @FXML
    private TextArea requestDescription;
    @FXML
    private JFXButton btnSelectMaintenanceLocation;
    @FXML
    private JFXButton btnCancelRequestAttempt;
    @FXML
    private JFXButton btnSubmitRequest;
    @FXML
    private Label lblServiceLovation;
    @FXML
    private Label lblServiceType;

    //hamburger pane and transitions
    @FXML
    private JFXHamburger burger;
    @FXML
    private AnchorPane paneControls;

    //location search
    @FXML
    private JFXButton btnGo;
    @FXML
    private JFXButton clear;
    @FXML
    private TextField txtStartLocation;
    @FXML
    private TextField txtEndLocation;

    //Node info panel
    @FXML
    private DialogPane nodeInfoPane;
    @FXML
    private Label nodeInfoType;
    @FXML
    private Label nodeInfoShortName;
    @FXML
    private Label nodeInfoLongName;
    @FXML
    private Label lblNodeX;
    @FXML
    private Label lblNodeY;

    //global variables, not FXML tied
    private ManageController manager;

    //locations search
    private List<Shape> drawings = new ArrayList<>();
    private GraphicsContext gc;
    private List<NodeData> currentPath;
    private List<NodeData> currentNodes = new ArrayList<>();

    //hamburger transitions
    private HamburgerSlideCloseTransition burgerTransition;
    private boolean controlsVisible = false;
    private FadeTransition controlsTransition;

    //zooming/panning
    private double currentScale;
    final double minWidth = 1500;
    final double maxWidth = 5000;
    final private int originalMapRatioIndex = 3;

    //showing nodes
    private String selectingLocation = "";
    ArrayList<Double> mapRatio = new ArrayList<>();
    private int currentMapRatioIndex;
    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb){
        currentMapRatioIndex =originalMapRatioIndex;
//        mapRatio.add(0.24);

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
        imgMap.setFitWidth(maxWidth*currentScale);
        manager = new ManageController();
    }

    //topmost methods are newest
    private void drawCycle(int x, int y){
        double radius = 10*currentScale;
        Circle c = new Circle(x, y, radius, RED);
        panningPane.getChildren().add(c);
        drawings.add(c);
    }

    private void showNodeList (List<NodeData> nodeDataList){
        for(int i = 0;i <nodeDataList.size();i++){
            showNode(nodeDataList.get(i));
        }
    }
    private void showNode(NodeData n){
        currentNodes.add(n);
        drawCycle(DBCToUIC(n.getX(),currentScale),DBCToUIC(n.getY(),currentScale));
    }

    private void showNodeInfo(NodeData nodeData){
        int dbX = nodeData.getX();
        int dbY = nodeData.getY();
        System.out.println("Node Coordinate: "+dbX+","+dbY+" Node Name: "+nodeData.getLongName());
        nodeInfoPane.setVisible(true);
        nodeInfoPane.setLayoutX(DBCToUIC(dbX,currentScale) + 3);
        nodeInfoPane.setLayoutY(DBCToUIC(dbY,currentScale) + 3);
        nodeInfoPane.setVisible(true);
        //nodeInfoLocation.setText(dbX + ", " + dbY);
        lblNodeX.setText(dbX + "");
        lblNodeY.setText(dbY + "");
        nodeInfoType.setText("" + nodeData.getType());
        nodeInfoLongName.setText("" + nodeData.getLongName());
        nodeInfoShortName.setText("" + nodeData.getShortName());
    }

    //displaying node info on click


    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY){
        int dbX = UICToDBC(mouseX,currentScale);
        int dbY =UICToDBC(mouseY,currentScale);
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

    public void clearMain(){
        if(drawings.size() > 0){
            for(Shape shape:drawings){
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            drawings = new ArrayList<>();
        }
        txtEndLocation.setText("");
        txtStartLocation.setText("");
    }

    private void clearPath(){
        currentPath = new ArrayList<>();

    }

    private void clearNodes(){
        currentNodes = new ArrayList<>();
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

    private int UICToDBC(int value, double scale){
        return (int)(value/scale);
    }
    private int DBCToUIC(int value, double scale){
        return (int)(value*scale);
    }
    public void mouseClickHandler(MouseEvent e){
        clearMain();
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (selectingLocation) {
            //case for displaying nearest node info when nothing is selected
            case "":
                System.out.println("Get in findNodeData X:"+x+" Y:"+y);

                List<NodeData> nodes = manager.getAllNodeData();
                NodeData mClickedNode= getClosestNode(nodes,x,y);
                mClickedNode = manager.getNodeData(mClickedNode.getId());
                showNode(mClickedNode);
                showNodeInfo(mClickedNode);
                break;
            case "selectLocation":
                Coordinate mCoordinate = new Coordinate(UICToDBC(x,currentScale),UICToDBC(y,currentScale));
                requestDescription.setText(mCoordinate.getX()+","+mCoordinate.getY());
                serviceRequester.setVisible(true);
                selectingLocation = "";
                break;
            // the rest of the situations when you click on the map
//            case "maintenance":
//                locationsSelected = true;
//                requestDescription.setText(requestDescription.getText() + "\n at " + x + ", " + y);
//                serviceRequester.setVisible(true);
//                selectingLocation = "";
//                break;

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
    public void loginButtonHandler(){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/edu/wpi/cs3733/programname/boundary/Login_Popup.fxml")));
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

    //Locate Bathroom/ Service desk/ VendingMachine JFXButton Handler
    public void locateHandler(ActionEvent event){
        Object mEvent = event.getSource();
        String nodeType = "";
        if(mEvent.equals(btnLocateBR)){
            nodeType = "REST";
        }
        if(mEvent.equals(btnLocateSD)){
            nodeType = "INFO";
        }
        if(mEvent.equals(btnLocateVM)){
            nodeType = "";
        }
        if(mEvent.equals(btnLocateWR)){
            nodeType = "";
        }
        if(!nodeType.equals("")){
            List<NodeData> mList = manager.queryNodeByTypeFloor(nodeType, Integer.toString(floor));
            if(mList!=null&&!mList.isEmpty())
            showNodeList(mList);
        }
    }
    
    //map zooming method
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
        relocateNodeInfo();
    }

    //relocate the node info panel
    public void relocateNodeInfo(){
        if(nodeInfoPane.isVisible()){
            int x = Integer.parseInt(lblNodeX.getText());
            int y = Integer.parseInt(lblNodeY.getText());
            nodeInfoPane.setLayoutX(x*currentScale);
            nodeInfoPane.setLayoutY(y*currentScale);
        }
    }

    public void goButtonHandler(){
        System.out.println("drawing path");
        currentPath = manager.startPathfind(txtStartLocation.getText(), txtEndLocation.getText());
        displayPath(currentPath);
    }

    public void SRHandler(ActionEvent e){
        Object mEvent = e.getSource();
        serviceRequester.setVisible(true);
        String SRType = "";
        if(mEvent == btnInterpreterReq){
            SRType = "I";
        }
        if(mEvent == btnMaintenanceReq){
            SRType = "M";
        }
        if(mEvent == btnTransportationReq){
            SRType = "T";
        }
        requestDescription.setText(SRType);
    }
    private void showScene(String url){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch(IOException ex){
            //Todo add some sort of error handling
            return;
        }
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.showAndWait();
    }
    public void mapEditHandler(){
        System.out.println("In map Edit handler");
        showScene("/edu/wpi/cs3733/programname/boundary/admin_screen.fxml");
    }

    public void openAdminHandler(){
        System.out.println("In open admin handler");
        showScene("/edu/wpi/cs3733/programname/boundary/serv_UI.fxml");
    }

    public void SRWindowHandler(ActionEvent e){
        Object mEvent = e.getSource();
        serviceRequester.setVisible(false);
        if(mEvent == btnSelectMaintenanceLocation){
            selectingLocation = "selectLocation";
        }
        if(mEvent == btnCancelRequestAttempt){
            //TODO clear the text
            requestDescription.setText("");

        }
        if(mEvent == btnSubmitRequest){
            //TODO clear the text and submit the SR
            manager.sendServiceRequest(requestDescription.getText());
        }
    }
    public void closeNodeInfoHandler(){
        nodeInfoPane.setVisible(false);
        nodeInfoLongName.setText("");
        nodeInfoShortName.setText("");
        nodeInfoType.setText("");
        lblNodeX.setText("");
        lblNodeY.setText("");
        clearMain();
    }

}
