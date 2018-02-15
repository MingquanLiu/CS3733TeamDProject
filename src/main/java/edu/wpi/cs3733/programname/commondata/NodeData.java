package edu.wpi.cs3733.programname.commondata;

import edu.wpi.cs3733.programname.observer.NewMainPageNodeDataObserver;
import edu.wpi.cs3733.programname.observer.NewMapAdminNodeDataObserver;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;

import static edu.wpi.cs3733.programname.commondata.Constants.*;

public class NodeData {

    private String nodeID;
    private Coordinate location;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;
    private String teamAssigned;
    private Circle circle;
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

    public void setCircleOnDragged(NewMapAdminNodeDataObserver newMapAdminNodeDataObserver){
        newMapAdminNodeDataObserver.setNodeData(this);
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ScrollPane scrollPane = newMapAdminNodeDataObserver.getScrollPane();
                AnchorPane anchorPane = newMapAdminNodeDataObserver.getAnchorPane();
                newMapAdminNodeDataObserver.disableScroll();
                System.out.println("Circle dragged");
                System.out.println("Scene X:" + event.getSceneX() + "Scene Y:" + event.getSceneY() + "Mouse X:" + event.getX() + "Mouse Y:" + event.getY());
//                System.out.println("image translate x:" + ((Circle) (event.getSource())).getCenterX());
                System.out.println("Get hVal :" + scrollPane.getHvalue()+" Get vVal:"+scrollPane.getVvalue());
                System.out.println("Height :" + scrollPane.viewportBoundsProperty().get().getHeight()+" Width:"+scrollPane.viewportBoundsProperty().get().getWidth());
                System.out.println("HeightA :" + anchorPane.getHeight()+" WidthA:"+anchorPane.getWidth());
                circle.setCenterX(event.getSceneX()+scrollPane.getHvalue()*(anchorPane.getWidth()-scrollPane.viewportBoundsProperty().get().getWidth()));
                circle.setCenterY(event.getSceneY()+scrollPane.getVvalue()*(anchorPane.getHeight()-scrollPane.viewportBoundsProperty().get().getHeight()));
                System.out.println("Node X: " + getXCoord() + "Node Y: " + getYCoord());
//                    newMapAdminNodeDataObserver.update();
            }
        });
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
                        System.out.println("Circle X: "+circle.getCenterX()+" Circle Y:"+circle.getCenterY());
//                        int dbX = HelperFunction.UICToDBC((int) event.getSceneX(), newMapAdminNodeDataObserver.getMainController().getScale());
//                        int dbY = HelperFunction.UICToDBC((int) event.getSceneY(), newMapAdminNodeDataObserver.getMainController().getScale());
                        int dbX = HelperFunction.UICToDBC((int) circle.getCenterX(), newMapAdminNodeDataObserver.getMainController().getScale());
                        int dbY = HelperFunction.UICToDBC((int) circle.getCenterY(), newMapAdminNodeDataObserver.getMainController().getScale());
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
        System.out.println("In changing circles");
    }
    public void changeBackCircleAndChangeColor(double currentScale){
        circle =new Circle(circle.getCenterX(), circle.getCenterY(), CIRCILE_RADIUS*currentScale, NODE_COLOR);
        setBorder();
    }

}

