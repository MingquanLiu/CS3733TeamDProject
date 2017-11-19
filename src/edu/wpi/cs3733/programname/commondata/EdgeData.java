package edu.wpi.cs3733.programname.commondata;

public class EdgeData {

    private String edgeID;
    private String startNode;
    private String endNode;

    public EdgeData(String edgeID, String startNode, String endNode) {
        this.edgeID = edgeID;
        this.startNode = startNode;
        this.endNode = endNode;
    }


    public String getEdgeID() {
        return edgeID;
    }


    public void setEdgeID(String edgeID) {
        this.edgeID = edgeID;
    }


    public String getStartNode() {
        return startNode;
    }


    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }


    public String getEndNode() {
        return endNode;
    }


    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdgeData edgeData = (EdgeData) o;

        if (startNode != null ? !startNode.equals(edgeData.startNode) : edgeData.startNode != null) return false;
        if (endNode != null ? !endNode.equals(edgeData.endNode) : edgeData.endNode != null) return false;
        return edgeID != null ? edgeID.equals(edgeData.edgeID) : edgeData.edgeID == null;
    }

    /**
     * sets the hascode using nodeIDs
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = edgeID != null ? edgeID.hashCode() : 0;
        result = 31 * result + (startNode != null ? startNode.hashCode() : 0);
        result = 31 * result + (endNode != null ? endNode.hashCode() : 0);
        return result;
    }
}
