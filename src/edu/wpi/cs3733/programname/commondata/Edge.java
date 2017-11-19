package edu.wpi.cs3733.programname.commondata;

import java.util.LinkedList;

public class Edge {


    public enum Restriction  {
        GENERAL, DEPTH, BREADTH, HANDICAPPED, ASTAR
    }

    private String firstNodeId;
    private String secondNodeId;
    private String edgeId;
    private LinkedList<Restriction> restrictions;

    public Edge(String first, String second, String edgeId, LinkedList<Restriction> restrictions) {
        this.firstNodeId = first;
        this.secondNodeId = second;
        this.edgeId = edgeId;
        this.restrictions = restrictions;
    }

    public LinkedList<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(LinkedList<Restriction> restrictions) {
        this.restrictions = restrictions;
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
