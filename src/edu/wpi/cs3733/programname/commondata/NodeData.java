package edu.wpi.cs3733.programname.commondata;


public class NodeData {

    private String nodeID;
    private Coordinate location;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;
    private String teamAssigned;



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


    public String getNodeID() { return this.nodeID; }


    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }


    public int getXCoord() {
        return this.location.getXCoord();
    }


    public int getYCoord() {
        return this.location.getYCoord();
    }


    public Coordinate getLocation() {
        return location;
    }


    public void setLocation(Coordinate location) {
        this.location = location;
    }


    public String getFloor() {
        return floor;
    }


    public void setFloor(String floor) {
        this.floor = floor;
    }


    public String getBuilding() {
        return building;
    }


    public void setBuilding(String building) {
        this.building = building;
    }


    public String getNodeType() {
        return nodeType;
    }


    public void setNodeType(String type) {
        this.nodeType = type;
    }


    public String getLongName() {
        return longName;
    }


    public void setLongName(String longName) {
        this.longName = longName;
    }


    public String getShortName() {
        return shortName;
    }


    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getTeamAssigned() {
        return teamAssigned;
    }


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
}

