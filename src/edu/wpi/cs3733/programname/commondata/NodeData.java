package edu.wpi.cs3733.programname.commondata;

public class NodeData {

    private String id;
    private Coordinate location;
    private String type;
    private String longName;
    private String shortName;

    public NodeData(String id, Coordinate location,
                    String type, String longName, String shortName) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.longName = longName;
        this.shortName = shortName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public int getX() {
        return this.location.getX();
    }

    public int getY() {
        return this.location.getY();
    }

}
