package edu.wpi.cs3733.programname.commondata;

import edu.wpi.cs3733.programname.observer.MainUINodeDataObserver;
import edu.wpi.cs3733.programname.observer.MapAdminNodeDataObserver;
import edu.wpi.cs3733.programname.observer.NewMainPageNodeDataObserver;
import edu.wpi.cs3733.programname.observer.NewMapAdminNodeDataObserver;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.IOException;

import static edu.wpi.cs3733.programname.commondata.Constants.*;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class NodeData {

    private String nodeID;
    private Coordinate location;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;
    private String teamAssigned;
    private ImageView nodeImageView;
    private Circle circle;
    private boolean clicked;
    /**
     * NodeData constructor with location
     *
     * @param nodeID
     * @param location
     * @param floor
     * @param building
     * @param nodeType
     * @param longName
     * @param shortName
     * @param teamAssigned
     */
    public NodeData(String nodeID, Coordinate location, String floor, String building,
                    String nodeType, String longName, String shortName, String teamAssigned) {
        this.nodeID = nodeID;
        this.location = location;
        this.floor = floor;
        this.building = building;
        this.nodeType = nodeType;
        this.longName = longName;
        this.shortName = shortName;
        this.teamAssigned = teamAssigned;
        clicked = false;
    }


    /**
     * NodeData constructor with coordinates
     *
     * @param nodeID
     * @param xcoord
     * @param ycoord
     * @param floor
     * @param building
     * @param nodeType
     * @param longName
     * @param shortName
     * @param teamAssigned
     */
    public NodeData(String nodeID, int xcoord, int ycoord, String floor, String building,
                    String nodeType, String longName, String shortName, String teamAssigned) {
        this.nodeID = nodeID;
        this.location = new Coordinate(xcoord, ycoord);
        this.floor = floor;
        this.building = building;
        this.nodeType = nodeType;
        this.longName = longName;
        this.shortName = shortName;
        this.teamAssigned = teamAssigned;
        clicked = false;
    }


    /**
     * nodeID getter
     *
     * @return String nodeID
     */
    public String getNodeID() { return this.nodeID; }


    /**
     * nodeID setter
     *
     * @param nodeID
     */
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }


    /**
     * xcoord getter
     *
     * @return int xcoord
     */
    public int getXCoord() {
        return this.location.getXCoord();
    }


    /**
     * ycoord getter
     *
     * @return int ycoord
     */
    public int getYCoord() {
        return this.location.getYCoord();
    }


    /**
     * location getter
     *
     * @return Coordinate location
     */
    public Coordinate getLocation() {
        return location;
    }


    /**
     * location setter
     *
     * @param location
     */
    public void setLocation(Coordinate location) {
        this.location = location;
    }


    /**
     * floor getter
     *
     * @return String floor
     */
    public String getFloor() {
        return floor;
    }


    /**
     * floor setter
     *
     * @param floor
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }


    /**
     * building getter
     *
     * @return String building
     */
    public String getBuilding() {
        return building;
    }


    /**
     * building setter
     *
     * @param building
     */
    public void setBuilding(String building) {
        this.building = building;
    }


    /**
     * nodeType getter
     *
     * @return String nodeType
     */
    public String getNodeType() {
        return nodeType;
    }


    /**
     * nodeType setter
     *
     * @param type
     */
    public void setNodeType(String type) {
        this.nodeType = type;
    }


    /**
     * longName getter
     *
     * @return String longName
     */
    public String getLongName() {
        return longName;
    }


    /**
     * longName setter
     *
     * @param longName
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }


    /**
     * shortName getter
     *
     * @return String shortName
     */
    public String getShortName() {
        return shortName;
    }


    /**
     * shortName setter
     *
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    /**
     * teamAssigned getter
     *
     * @return String teamAssigned
     */
    public String getTeamAssigned() {
        return teamAssigned;
    }


    /**
     * teamAssigned setter
     *
     * @param teamAssigned
     */
    public void setTeamAssigned(String teamAssigned) {
        this.teamAssigned = teamAssigned;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeData nodeData = (NodeData) o;

        if (!nodeID.equals(nodeData.nodeID)) return false;
        if (!location.equals(nodeData.location)) return false;
        if (!floor.equals(nodeData.floor)) return false;
        if (!building.equals(nodeData.building)) return false;

        return shortName.equals(nodeData.shortName);
    }

    /**
     * gives hashcode using id, location, floor, and short name
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = nodeID.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + floor.hashCode();
        result = 31 * result + building.hashCode();
        result = 31 * result + nodeType.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + longName.hashCode();
        result = 31 * result + teamAssigned.hashCode();
        return result;
    }


    /******************************************************************************************************************************************************
     * Doing with Image View
     */
    public void initializeImageView(){
        final ImageView imv = new ImageView();
        Image image2 = new Image("img/NodeIcon/NodeIcon.png");
        imv.setImage(image2);

        imv.setFitWidth(ORIGINAL_NODE_WIDTH);
        imv.setFitHeight(ORIGINAL_NODE_HEIGHT);
        nodeImageView = imv;
    }

    public void changeImageView(String type){

        Image image2 = new Image("img/NodeIcon/NodeIcon.png");
        switch (type){
            case "REST":
                image2 = new Image("img/NodeIcon/BathroomIcon.png");
                break;
            case "INFO":
                image2 = new Image("img/NodeIcon/ServDeskIcon.png");
                break;
            case "RETL":
                image2 = new Image("img/NodeIcon/RetailIcon.png");
                break;
            case "DEPT":
                image2 = new Image("img/NodeIcon/WaitRoomIcon.png");
                break;
            case "EXIT":
                image2 = new Image("img/NodeIcon/ExitIcon.png");
                break;
            case "STAI":
                image2 = new Image("img/NodeIcon/StairsIcon.png");
                break;
            case "LABS":
                image2 = new Image("img/NodeIcon/LabIcon.png");
                break;
            case "SERV":
                image2 = new Image("img/NodeIcon/AddIcon.png");
                break;
        }
        if(!nodeImageView.getImage().impl_getUrl().equals(image2.impl_getUrl())) {
            nodeImageView.setImage(image2);
        }
    }
    public void setImageViewSizeAndLocation(double currentScale){
        nodeImageView.setFitWidth(ORIGINAL_NODE_WIDTH*currentScale);
        nodeImageView.setFitHeight(ORIGINAL_NODE_HEIGHT*currentScale);
        nodeImageView.setX(getXCoord()*currentScale-nodeImageView.getFitWidth()/2);
        nodeImageView.setY(getYCoord()*currentScale-nodeImageView.getFitHeight()/2);
    }
    public ImageView getNodeImageView(){
        return nodeImageView;
    }

    public void setImageViewOnClick(MainUINodeDataObserver mainUINodeDataObserver){
        mainUINodeDataObserver.setNodeData(this);
        nodeImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Image get clicked");
                try {
                    mainUINodeDataObserver.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setImageViewOnClick(NewMapAdminNodeDataObserver newMapAdminNodeDataObserver){
        newMapAdminNodeDataObserver.setNodeData(this);
        nodeImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Image get clicked");
                try {
                    newMapAdminNodeDataObserver.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setImageVisible(Boolean imageVisible){
        nodeImageView.setVisible(imageVisible);
    }


    /******************************************************************************************************************************************************
     * Doing with Shape
     */

    public void initializeCircle(){
        circle = new Circle(getXCoord(), getYCoord(), CIRCILE_RADIUS, NODE_COLOR) ;
        setBorder();
    }
    public void setBorder(){
        circle.setStroke(NODE_STROKE_COLOR);
    }

    public Circle getCircle(){
        return circle;
    }
    public void setCircleSizeAndLocation(double currentScale){
        circle.setCenterX(getXCoord()*currentScale);
        circle.setCenterY(getYCoord()*currentScale);
        circle.setRadius(CIRCILE_RADIUS*currentScale);
    }
    public void setCircleOnDragged(MapAdminNodeDataObserver mapAdminNodeDataObserver){
        mapAdminNodeDataObserver.setNodeData(this);
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapAdminNodeDataObserver.disableScroll();
                System.out.println("Image get clicked");
                try {
                    System.out.println("Scene X:"+event.getSceneX()+"Scene Y:"+event.getSceneY() +"Mouse X:"+event.getX()+"Mouse Y:"+event.getY());
                    System.out.println("image translate x:" + mapAdminNodeDataObserver.getMapAdminController().getFloorImage().getTranslateX());
                    circle.setCenterX(event.getX() + mapAdminNodeDataObserver.getMapAdminController().getFloorImage().getTranslateX());
                    circle.setCenterY(event.getY()  + mapAdminNodeDataObserver.getMapAdminController().getFloorImage().getTranslateY());
                    System.out.println("Node X: "+getXCoord()+"Node Y: "+getNodeID());
                    mapAdminNodeDataObserver.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setCircleOnDraggedExit(MapAdminNodeDataObserver mapAdminNodeDataObserver) {
        mapAdminNodeDataObserver.setNodeData(this);
        circle.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    circle.setCenterX(event.getSceneX());
                    circle.setCenterY(event.getSceneY());
                    int dbX = HelperFunction.UICToDBC((int) event.getSceneX(), mapAdminNodeDataObserver.getMapAdminController().currentScale);
                    int dbY = HelperFunction.UICToDBC((int) event.getSceneY(), mapAdminNodeDataObserver.getMapAdminController().currentScale);
                    Coordinate newLoc = new Coordinate(dbX, dbY);
                    mapAdminNodeDataObserver.getNodeData().setLocation(newLoc);
                   // mapAdminNodeDataObserver.enableScroll();
                    mapAdminNodeDataObserver.update();
                    mapAdminNodeDataObserver.updateNodeInDb();
                    mapAdminNodeDataObserver.showNodeAndPath();
                } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });
    }

    double mouseX;
    double mouseY;
    double imgX;
    double imgY;
    public void setCircleOnDragged(NewMapAdminNodeDataObserver newMapAdminNodeDataObserver){
        newMapAdminNodeDataObserver.setNodeData(this);

        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                imgX = ((Circle) (event.getSource())).getCenterX();
                imgY = ((Circle) (event.getSource())).getCenterY();
            }
        });
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newMapAdminNodeDataObserver.disableScroll();
                double offsetX = event.getSceneX() - mouseX;
                double offsetY = event.getSceneY() - mouseY;
                double newTranslateX = imgX + offsetX;
                double newTranslateY = imgY + offsetY;

                ((Circle) (event.getSource())).setCenterX(newTranslateX);
                ((Circle) (event.getSource())).setCenterY(newTranslateY);
            }
        });
//        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
////            @Override
////            public void handle(MouseEvent event) {
//////                if(clicked){
////                    newMapAdminNodeDataObserver.disableScroll();
////                    System.out.println("Circle dragged");
////                    System.out.println("Scene X:"+event.getSceneX()+"Scene Y:"+event.getSceneY() +"Mouse X:"+event.getX()+"Mouse Y:"+event.getY());
////                     System.out.println("image translate x:" + ((Circle)(event.getSource())).getCenterX());
////                    circle.setCenterX(event.getSceneX());
////                    circle.setCenterY(event.getSceneY());
////                    System.out.println("Node X: "+getXCoord()+"Node Y: "+getNodeID());
//////                }
//////                    newMapAdminNodeDataObserver.update();
////            }
//        });
    }
    public void setCircleOnDraggedExit(NewMapAdminNodeDataObserver newMapAdminNodeDataObserver) {
        newMapAdminNodeDataObserver.setNodeData(this);
        circle.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Circle dragged exit");
//                if(clicked){
                    try {
                        //circle.setCenterX(event.getSceneX());
                        //circle.setCenterY(event.getSceneY());
                        int dbX = HelperFunction.UICToDBC((int) event.getSceneX(), newMapAdminNodeDataObserver.getMainController().getScale());
                        int dbY = HelperFunction.UICToDBC((int) event.getSceneY(), newMapAdminNodeDataObserver.getMainController().getScale());
                        Coordinate newLoc = new Coordinate(dbX, dbY);
                        newMapAdminNodeDataObserver.getNodeData().setLocation(newLoc);
                        newMapAdminNodeDataObserver.enableScroll();

                        newMapAdminNodeDataObserver.updateNodeInDb();
                        newMapAdminNodeDataObserver.update();
//                    newMapAdminNodeDataObserver.showNodesOrEdges();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
////                }
            }
        });
    }
    public void setCircleMapAdminOnClick(NewMapAdminNodeDataObserver newMapAdminNodeDataObserver) {
        newMapAdminNodeDataObserver.setNodeData(this);
        circle.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                        newMapAdminNodeDataObserver.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setCircleMainPageOnClick(NewMainPageNodeDataObserver newMainPageNodeDataObserver) {
        newMainPageNodeDataObserver.setNodeData(this);
        circle.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    newMainPageNodeDataObserver.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void enlargeCircleAndChangeColor(double currentScale){
        circle =new Circle(circle.getCenterX(), circle.getCenterY(), EXPANDED_CIRCILE_RADIUS*currentScale, NODE_ENLARGED_COLOR);
        setBorder();
        clicked = true;
        System.out.println("In changing circles");
    }
    public void changeBackCircleAndChangeColor(double currentScale){
        circle =new Circle(circle.getCenterX(), circle.getCenterY(), CIRCILE_RADIUS*currentScale, NODE_COLOR);
        setBorder();
        clicked = false;
    }

}

