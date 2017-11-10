package edu.wpi.cs3733.programname.pathfind;

public class Edge {
    private String firstNodeId;
    private String secondNodeId;
    //private PathStrategy restriction;

    public String getFirstNodeId() {
        return firstNodeId;
    }

    public String getSecondNodeId() {
        return secondNodeId;
    }

    public Edge(String firstNode, String secondNode){ //add pathstrat, call "restricted", when implemented!
        this.firstNodeId = firstNode;
        this.secondNodeId = secondNode;
        //this.restriction = restricted;
    }
}
