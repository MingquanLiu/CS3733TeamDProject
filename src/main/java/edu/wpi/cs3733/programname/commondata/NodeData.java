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

