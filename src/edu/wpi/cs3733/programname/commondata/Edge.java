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

    /**
     * constructor for Coordinate-Represents the connection between two Nodes
     * @param first - the starting point of a given Edge
     * @param second - the ending point of a given Edge
     * @param edgeId - the unique identifier for a given Edge
     */
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

    /**
     * getter for nodeID of first node of an edge
     * @return the nodeID of the first ID
     */
    public String getFirstNodeId() {
        return firstNodeId;
    }

    /**
     * setter for nodeID of first node of an edge
     * @param firstNodeId the nodeID of the first ID
     */
    public void setFirstNodeId(String firstNodeId) {
        this.firstNodeId = firstNodeId;
    }

    /**
     * getter for nodeID of second node of an edge
     * @return the nodeID of the second node
     */
    public String getSecondNodeId() {
        return secondNodeId;
    }

    /**
     * setter for nodeID of a second node of an edge
     * @param secondNodeId the nodeID of the second node
     */
    public void setSecondNodeId(String secondNodeId) {
        this.secondNodeId = secondNodeId;
    }

    /**
     * getter for ID of an edge
     * @return the ID of the edge
     */
    public String getEdgeId() {
        return edgeId;
    }

    /**
     * setter for ID of an edge
     * @param edgeId the ID of the edge
     */
    public void setEdgeId(String edgeId) {
        this.edgeId = edgeId;
    }

    /**
     * checks if generic object is equal
     * @param o any generic object
     * @return true if objects equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (firstNodeId != null ? !firstNodeId.equals(edge.firstNodeId) : edge.firstNodeId != null) return false;
        if (secondNodeId != null ? !secondNodeId.equals(edge.secondNodeId) : edge.secondNodeId != null) return false;
        return edgeId != null ? edgeId.equals(edge.edgeId) : edge.edgeId == null;
    }

    /**
     * sets the hascode using nodeIDs
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = firstNodeId != null ? firstNodeId.hashCode() : 0;
        result = 31 * result + (secondNodeId != null ? secondNodeId.hashCode() : 0);
        result = 31 * result + (edgeId != null ? edgeId.hashCode() : 0);
        return result;
    }
}
