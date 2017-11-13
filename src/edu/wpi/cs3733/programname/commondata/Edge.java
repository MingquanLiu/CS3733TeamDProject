package edu.wpi.cs3733.programname.commondata;

public class Edge {

    private String firstNodeId;
    private String secondNodeId;

    public Edge(String first, String second) {
        this.firstNodeId = first;
        this.secondNodeId = second;
    }

    public String getFirstNodeId() {
        return firstNodeId;
    }

    public void setFirstNodeId(String firstNodeId) {
        this.firstNodeId = firstNodeId;
    }

    public String getSecondNodeId() {
        return secondNodeId;
    }

    public void setSecondNodeId(String secondNodeId) {
        this.secondNodeId = secondNodeId;
    }
}
