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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (firstNodeId != null ? !firstNodeId.equals(edge.firstNodeId) : edge.firstNodeId != null) return false;
        if (secondNodeId != null ? !secondNodeId.equals(edge.secondNodeId) : edge.secondNodeId != null) return false;
        return edgeId != null ? edgeId.equals(edge.edgeId) : edge.edgeId == null;
    }

    @Override
    public int hashCode() {
        int result = firstNodeId != null ? firstNodeId.hashCode() : 0;
        result = 31 * result + (secondNodeId != null ? secondNodeId.hashCode() : 0);
        result = 31 * result + (edgeId != null ? edgeId.hashCode() : 0);
        return result;
    }
}
