package edu.wpi.cs3733.programname.commondata;

public class NodeData {

    private String id;
    private Coordinate location;
    private String floor;
    private String type;
    private String longName;
    private String shortName;

    /**
     * Constructor for NodeData using input of coordinate as one 'coordinate' type
     * @param id ID of the node
     * @param location the coordinate of the node
     * @param floor the floor the node is on
     * @param type the type of the node
     * @param longName the long name of the node
     * @param shortName the short name of the node
     */
    public NodeData(String id, Coordinate location, String floor,
                    String type, String longName, String shortName) {
        this.id = id;
        this.location = location;
        this.floor = floor;
        this.type = type;
        this.longName = longName;
        this.shortName = shortName;
    }

    /**
     * Constructor for NodeData with coordinate inputs split up as x and y
     * @param id
     * @param x
     * @param y
     * @param floor
     * @param type
     * @param longName
     * @param shortName
     */
    public NodeData(String id, int x, int y, String floor,
                    String type, String longName, String shortName) {
        this.id = id;
        this.location = new Coordinate(x,y);
        this.floor = floor;
        this.type = type;
        this.longName = longName;
        this.shortName = shortName;
    }

    /**
     * getter for NodeID
     * @return the ID of the node
     */
    public String getId() { return this.id; }

    /**
     * setter for NodeID
     * @param newId the ID of the node you want to set it to
     */
    public void setId(String newId) {
        this.id = newId;
    }

    /**
     * getter for x coordinate of node
     * @return x coordinate of node
     */
    public int getX() {
        return this.location.getX();
    }

    /**
     * getter for y coordinate of node
     * @return y coordinate of node
     */
    public int getY() {
        return this.location.getY();
    }

    /**
     * getter for coordinate of node (in Coordinate form)
     * @return the coordinate of location
     */
    public Coordinate getLocation() {
        return location;
    }

    /**
     * setter for coordinate of node (in Coordinate form)
     * @param location the coordinate of location you want to set it to
     */
    public void setLocation(Coordinate location) {
        this.location = location;
    }

    /**
     * getter for type of node
     * @return the type of node
     */
    public String getType() {
        return type;
    }

    /**
     * setter for type of node
     * @param type the type that you want the node to be
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * getter for long name of node
     * @return the long name of the node
     */
    public String getLongName() {
        return longName;
    }

    /**
     * setter for long name of node
     * @param longName the long name you want to set the node to be
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * getter for shortname of node
     * @return the short name of the node
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * setter for shortname of node
     * @param shortName the short name you want the node to have
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * getter for floor
     * @return the floor of the node
     */
    public String getFloor() {
        return floor;
    }

    /**
     * setter for floor
     * @param floor the floor you want the node to be on
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * checks in generic object is equal
     * @param o a generic object
     * @return true if objects are equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeData nodeData = (NodeData) o;

        if (!id.equals(nodeData.id)) return false;
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
        int result = id.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + floor.hashCode();
        result = 31 * result + shortName.hashCode();
        return result;
    }
}

