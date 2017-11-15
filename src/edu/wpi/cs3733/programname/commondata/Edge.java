package edu.wpi.cs3733.programname.commondata;

public class Edge {

    private String firstNodeId;
    private String secondNodeId;
    private String edgeId;

    public Edge(String first, String second, String edgeId) {
        this.firstNodeId = first;
        this.secondNodeId = second;
        this.edgeId = edgeId;
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

    public String getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(String edgeId) {
        this.edgeId = edgeId;
    }
}