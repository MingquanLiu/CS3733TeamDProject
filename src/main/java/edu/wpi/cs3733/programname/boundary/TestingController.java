package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static edu.wpi.cs3733.programname.commondata.Constants.*;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.convertFloor;
import static edu.wpi.cs3733.programname.commondata.HelperFunction.*;
import static edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType.ASTAR;


public class TestingController extends UIController implements Initializable {


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
    private AnchorPane serviceRequester;

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
    private JFXButton btnEditEmployees;

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
    private Label lblServiceLocation;
    @FXML
    private Label lblServiceY;
    @FXML
    private Label lblServiceX;
    @FXML
    private Label lblReqType;

    //hamburger pane and transitions
    @FXML
    private JFXHamburger burger;
    @FXML
    private AnchorPane paneControls;

    //location search
    @FXML
    private JFXButton btnGo;
    @FXML
    private JFXButton btnClear;
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

    @FXML
    private JFXTextField txtUser;

    //FAQ
    @FXML
    private Button helpButton;

    //Email
    @FXML
    private JFXButton emailDirections;

    @FXML
    private JFXButton btnViewMyRequests;

    //global variables, not FXML tied
    private ManageController manager;

    //locations search
    private List<Shape> pathDrawings = new ArrayList<>();
    private List<Shape> shownNodes = new ArrayList<>();
    private GraphicsContext gc;
    private List<NodeData> currentPath;
    private List<NodeData> allNodes = new ArrayList<>();
    private List<NodeData> currentNodes = new ArrayList<>();

    //hamburger transitions
    private HamburgerSlideCloseTransition burgerTransition;
    private boolean controlsVisible = false;
    private FadeTransition controlsTransition;

    //zooming/panning
    private double currentScale;
    private final double MAX_UI_WIDTH = 5000;
    final private int originalMapRatioIndex = 3;

    //showing nodes
    private String selectingLocation = "";
    private ArrayList<Double> mapRatio = new ArrayList<>();
    private int currentMapRatioIndex;
    private boolean loggedIn;
    private Employee employeeLoggedIn;
    private NodeData lastShowNodeData = null;


    private PathfindingController.searchType mSearchType= ASTAR;

    //this runs on startup
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    public void initManager(ManageController manageController){
        currentMapRatioIndex =originalMapRatioIndex;
        manager = manageController;
//        mapRatio.add(0.24);
        paneAdminFeatures.setVisible(false);
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
        imgMap.setFitWidth(MAX_UI_WIDTH *currentScale);
//        allNodes = manageController.queryNodeByFloor(convertFloor(floor));
        allNodes = manageController.getAllNodeData();
        setNodeListImageVisibility(false,setNodeListController(setNodeListSizeAndLocation(initNodeListImage(allNodes),currentScale),this));  ;
        currentNodes = getNodeByFloor(allNodes,convertFloor(floor));
        showNodeList(allNodes);
//        panningPane.getChildren().add(imv);

    }
    public void setSearchType(PathfindingController.searchType searchType){
        System.out.println(currentMapRatioIndex);
        this.mSearchType = searchType;
    }
    //topmost methods are newest
//    private void drawCycle(int x, int y){
//        double radius = 10*currentScale;
//        Circle c = new Circle(x, y, radius, RED);
//        panningPane.getChildren().add(c);
//        shownNodes.add(c);
//    }

    private void showNodeList (List<NodeData> nodeDataList){
        for(int i = 0;i <nodeDataList.size();i++){
            showNode(nodeDataList.get(i));
        }
    }
    private void showNode(NodeData n){
        n.setImageViewSizeAndLocation(currentScale);
        panningPane.getChildren().add(n.getNodeImageView());
//        drawCycle(DBCToUIC(n.getXCoord(),currentScale),DBCToUIC(n.getYCoord(),currentScale));
    }

    private void showNodeInfo(NodeData nodeData){
        int dbX = nodeData.getXCoord();
        int dbY = nodeData.getYCoord();
        System.out.println("Node Coordinate: "+dbX+","+dbY+" Node Name: "+nodeData.getLongName());
        nodeInfoPane.setVisible(true);
        nodeInfoPane.setLayoutX(DBCToUIC(dbX,currentScale) + 3);
        nodeInfoPane.setLayoutY(DBCToUIC(dbY,currentScale) + 3);
        nodeInfoPane.setVisible(true);
        //nodeInfoLocation.setText(dbX + ", " + dbY);
        lblNodeX.setText(dbX + "");
        lblNodeY.setText(dbY + "");
        nodeInfoType.setText("" + nodeData.getNodeType());
        nodeInfoLongName.setText("" + nodeData.getLongName());
        nodeInfoShortName.setText("" + nodeData.getShortName());
    }

    //displaying node info on click

    private NodeData getClosestNode(List<NodeData> nodeDataList, int mouseX, int mouseY){
        int dbX = UICToDBC(mouseX,currentScale);
        int dbY =UICToDBC(mouseY,currentScale);
        NodeData mNodeData = null;
        double d = 0;
        for (NodeData node : nodeDataList) {
            int nodeX = node.getXCoord();
            int nodeY = node.getYCoord();
            double temp = Math.sqrt(Math.pow(dbX - nodeX, 2) + Math.pow(dbY - nodeY, 2));
            if (temp < d||d==0) {
                d = temp;
                mNodeData = node;
            }
        }
        return mNodeData;
    }

    private void displayPath(List<NodeData> path){
        if(path!=null&&!path.isEmpty()){
            currentPath= path;
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
                if(n.getFloor().equals(convertFloor(floor))&&prev.getFloor().equals(convertFloor(floor))){
                    l.setStroke(Color.BLUE);
                    l.setStrokeWidth(5.0*currentScale);
                    l.setStartX(prev.getXCoord()*currentScale);
                    l.setStartY(prev.getYCoord()*currentScale);
                    l.setEndX(n.getXCoord()*currentScale);
                    l.setEndY(n.getYCoord()*currentScale);
                    lines.add(l);
                }
                prev = n;
            }
            pathDrawings.addAll(lines);
            panningPane.getChildren().addAll(lines);
            emailDirections.setVisible(true);
        }
    }

    public void clearMain(){
        clearPath();
        closeNodeInfoHandler();
        clearPathFindLoc();
        lastShowNodeData.setImageVisible(false);
    }
    public void clearPathFindLoc(){
        txtEndLocation.setText("");
        txtStartLocation.setText("");
    }

    private void clearPath(){
        currentPath = new ArrayList<>();
        if(pathDrawings.size() > 0){
            for(Shape shape: pathDrawings){
                System.out.println("success remove");
                panningPane.getChildren().remove(shape);
            }
            pathDrawings = new ArrayList<>();
        }
    }

    private void clearNodes(){
    }

    //map switching methods
    public void mapChange(ActionEvent e){
        clearMain();
        if(e.getSource() == btnMapUp && floor < 3){
            floor ++;
            System.out.println("up to floor" + floor);
            setFloor();
            displayPath(currentPath);
            setNodeListImageVisibility(false,currentNodes);
            currentNodes = getNodeByFloor(allNodes,convertFloor(floor));
            nodeInfoPane.setVisible(false);
        }
        else if (e.getSource() == btnMapDwn && floor > -2){
            floor --;
            System.out.println("down to floor" + floor);
            setFloor();
            displayPath(currentPath);
            setNodeListImageVisibility(false,currentNodes);
            currentNodes = getNodeByFloor(allNodes,convertFloor(floor));
            nodeInfoPane.setVisible(false);
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

        lblCurrentFloor.setText(convertFloor(floor));
    }

    private int UICToDBC(int value, double scale){
        return (int)(value/scale);
    }
    private int DBCToUIC(int value, double scale){
        return (int)(value*scale);
    }
    public void mouseClickHandler(MouseEvent e){
        int x = (int)e.getX();
        int y =(int)e.getY();
        List<NodeData> mList = getNodeByVisibility(currentNodes,true);
        NodeData mNode=getClosestNode(mList,x,y);
//        if(lastShowNodeData!=null)lastShowNodeData.setImageVisible(false);
//        mNode.setImageVisible(true);
//        lastShowNodeData = mNode;
        showNodeInfo(mNode);
    }
    //hamburger handling
    public void openMenu(MouseEvent e){
        setBurger();
    }
    public void setBurger(){
        burgerTransition.setRate(burgerTransition.getRate()*-1);
        burgerTransition.play();

        controlsVisible = !controlsVisible;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        controlsTransition.setToValue(Math.abs(controlsTransition.getToValue()-1));         //these two lines should make it fade out the next time you click
        controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue()-1));     // but they doent work the way I want them to for some reason
    }
    private void setBurgerFalse(){
        burgerTransition.setRate(burgerTransition.getRate()*-1);
        burgerTransition.play();

        controlsVisible = false;
        controlsTransition.play();
        paneControls.setVisible(controlsVisible);

        controlsTransition.setToValue(Math.abs(controlsTransition.getToValue()-1));         //these two lines should make it fade out the next time you click
        controlsTransition.setFromValue(Math.abs(controlsTransition.getFromValue()-1));     // but they doent work the way I want them to for some reason
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
            nodeType = "RETL";
            setNodeListImageVisibility(true, currentNodes);
        }
        if(mEvent.equals(btnLocateWR)){
            nodeType = "DEPT";
        }
        if(!nodeType.equals("")){
            List<NodeData> mList = getTypeNode(currentNodes,nodeType);
            for(NodeData nodeData:mList){
                nodeData.changeImageView(nodeType);
            }
            setNodeListImageVisibility(true,mList);
        }
    }
    
    //map zooming method
    public void zoomHandler(ActionEvent e) {
        if (e.getSource() == btnZoomOut) {
            if (currentMapRatioIndex == 0) {
                return;
            }
            currentMapRatioIndex -= 1;
            currentScale = mapRatio.get(currentMapRatioIndex);
            imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        } else {
            if (currentMapRatioIndex == (mapRatio.size() - 1)) {
                return;
            }
            currentMapRatioIndex += 1;
            currentScale = mapRatio.get(currentMapRatioIndex);
            imgMap.setFitWidth(MAX_UI_WIDTH * currentScale);
        }

        if (!(currentPath == null) && !currentPath.isEmpty()) {
            List<NodeData> mPath = currentPath;
            clearPath();
            displayPath(mPath);
        }
        setNodeListSizeAndLocation(currentNodes,currentScale);
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
        try {
            System.out.println(mSearchType);
            currentPath = manager.startPathfind(txtStartLocation.getText(), txtEndLocation.getText(), mSearchType);
        }
        catch(InvalidNodeException ine) {
            currentPath = new ArrayList<>();
        }
        displayPath(currentPath);
        clearPathFindLoc();
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

    //select location when clicking on the text field
    public void selectStartField(){
        selectingLocation = "selectStart";
    }
    public void selectEndField(){
        selectingLocation = "selectEnd";
    }

    public void SRHandler(ActionEvent e){
        Object mEvent = e.getSource();
        serviceRequester.setVisible(true);
        String SRType = "";
        if(mEvent == btnInterpreterReq){
            lblReqType.setText("Interpreter Request");
            SRType = "Language to: \nLanguage from:";
        }
        if(mEvent == btnMaintenanceReq){
            lblReqType.setText("Maintenance Request");
            SRType = "Maintenance type: \nMaintenance urgency(1-5): ";
        }
        if(mEvent == btnTransportationReq){
            lblReqType.setText("Transportation Request");
            SRType = "Transportation type: \nTransportation urgency: ";
        }
        requestDescription.setText(SRType);
    }

//    //popup methods
    private FXMLLoader showScene(String url){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch(IOException ex){
            //Todo add some sort of error handling
            return loader;
        }
        loader.<LoginPopup>getController().initManager(manager);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.showAndWait();
        return loader;
    }

    public void loginButtonHandler() throws IOException {
        String username = "admin";
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
        loader.<LoginPopup>getController().initManager(manager);
//        loggedIn = loader.<LoginPopup>getController().getLoggedIn();
        loggedIn = true;
        if(loggedIn) {
            employeeLoggedIn = manager.queryEmployeeByUsername(username);
//            lblLoginStatus.setText("logged in");
            loggedIn = true;
            showAdminControls();
        }
        stage.show();
    }
    private void showAdminControls(){
        paneAdminFeatures.setVisible(loggedIn);
    }

    public void mapEditHandler() throws IOException {
        System.out.println("In map Edit handler");
//        showScene("/edu/wpi/cs3733/programname/boundary/admin_screen.fxml");
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin_screen.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<MapAdminController>getController().initManager(manager);
        loader.<MapAdminController>getController().setmTestController(this);
        stage.show();
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

    public void SRWindowHandler(ActionEvent e){
        Object mEvent = e.getSource();
        serviceRequester.setVisible(false);
        if(mEvent == btnSelectMaintenanceLocation){
            selectingLocation = "selectLocation";
            setBurger();
        }
        if(mEvent == btnCancelRequestAttempt){
            //TODO clear the text
            requestDescription.setText("");
            lblServiceX.setText("");
            lblServiceY.setText("");
        }
        if(mEvent == btnSubmitRequest){
            //TODO clear the text and submit the SR
            String typeText = lblReqType.getText();
            String type = "";

            if (typeText == "Interpreter Request") {
                type = INTERPRETER_REQUEST;
            } else if (typeText == "Transportation Request") {
                type = TRANSPORTATION_REQUEST;
            } else if (typeText == "Maintenance Request") {
                type = MAINTENANCE_REQUEST;

            }

            int locX = Integer.parseInt(lblServiceX.getText());
            int locY = Integer.parseInt(lblServiceX.getText());
            String locationId = getClosestNode(manager.getAllNodeData(), locX, locY).getNodeID();
            String description = requestDescription.getText().replaceAll("\n", " ");
         //   String senderUsername = employeeLoggedIn.getUsername();
            manager.createServiceRequest("admin", type, locationId, null, description, 1);
            lblServiceX.setText("");
            lblServiceY.setText("");
        }
    }

    public void closeNodeInfoHandler(){
        nodeInfoPane.setVisible(false);
        nodeInfoLongName.setText("");
        nodeInfoShortName.setText("");
        nodeInfoType.setText("");
        lblNodeX.setText("");
        lblNodeY.setText("");
    }

    public void helpButtonHandler()throws IOException {
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

    public void aboutButtonHandler()throws IOException {
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

    public void TransportRequestHandler()throws IOException {
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
        stage.show();
    }

    public void InterpreterRequestHandler()throws IOException {
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
        stage.show();
    }

    public void MaintenanceRequestHandler()throws IOException {
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
        stage.show();
    }

    public void handleEmailButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/email_Direction.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<Email_Direction>getController().initialize(manager, currentPath);
        stage.show();
        emailDirections.setVisible(false);
    }

    @Override
    public void passNodeData(NodeData nodeData) {
        switch (selectingLocation){
            case "":
                clearNodes();
//                showNode(nodeData);
                showNodeInfo(nodeData);
                break;
            case "selectLocation":
                System.out.println("In selectLocation");
                lblServiceX.setText(""+nodeData.getXCoord());
                lblServiceY.setText(""+nodeData.getYCoord());
                serviceRequester.setVisible(true);
                selectingLocation = "";
                break;
            case "selectStart":
                clearNodes();
//                showNode(nodeData);
                showNodeInfo(nodeData);
                txtStartLocation.setText(nodeData.getNodeID());
                selectingLocation = "";
                break;
            case "selectEnd":
                clearNodes();
//                showNode(nodeData);
                showNodeInfo(nodeData);
                txtEndLocation.setText(nodeData.getNodeID());
                selectingLocation = "";
                break;
        }
    }

    @Override
    public void passEdgeData(EdgeData edgeData) {

    }


}
