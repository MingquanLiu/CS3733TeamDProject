package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import java.sql.SQLException;



public class DatabaseModificationController {
    DBConnection conn;

    /**
     *
     * @param conn the connection to the database
     */
    public DatabaseModificationController(DBConnection conn){
        this.conn = conn;
    }


    public void addNode(NodeData data) {
        String nodeID = data.getNodeID();
        int xcoord = data.getXCoord();
        int ycoord = data.getYCoord();
        String floor = data.getFloor();
        String building = data.getBuilding();
        String nodeType = data.getNodeType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        String teamAssigned = data.getTeamAssigned();
        String str;

        try {

            str = "INSERT INTO Nodes VALUES('" + nodeID + "', " + xcoord + ", " + ycoord + ", '" + floor + "', '" +
                    building + "', '" + nodeType + "', '" + longName + "', '" + shortName + "', '" + teamAssigned + "')";

            System.out.println(str);
            conn.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
    }


    public void editNode(NodeData data) {
        String nodeID = data.getNodeID();
        int xcoord = data.getXCoord();
        int ycoord = data.getYCoord();
        String floor = data.getFloor();
        String building = data.getBuilding();
        String nodeType = data.getNodeType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        String teamAssigned = data.getTeamAssigned();
        String str;
        try {

            str = "UPDATE Nodes SET xcoord = " + xcoord + ", ycoord = " + ycoord + ", floor = '" + floor +
                    "', building = '" + building + ", nodeType = '" + nodeType + "', longName = '" + longName + "', shortName = '"
                    + shortName + "', teamAssigned = '" + teamAssigned + "' WHERE nodeID = '" + nodeID + "'";

            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Edit Node Failed!");
            e.printStackTrace();
        }
    }


    public void deleteNode(NodeData data){
        String nodeID = data.getNodeID();
        String str;

        try {
            str ="DELETE FROM Nodes WHERE nodeID = '" + nodeID + "'";

            System.out.println(str);
            conn.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Delete Node Failed!");
            e.printStackTrace();
        }
    }


    public void addEdge(String node1ID, String node2ID){
        String nodeID = node1ID + "_" + node2ID;
        String str;

        try {
            str = "INSERT INTO Edges VALUES('" + nodeID + "', '" + node1ID + "', '" + node2ID +"')";
            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Add Edge Failed!");
            e.printStackTrace();
        }
    }


    public void editEdge(EdgeData data){
        String edgeID = data.getEdgeID();
        String startNode = data.getStartNode();
        String endNode = data.getEndNode();
        String str;

        try {
            str = "UPDATE Edges SET startNode = '" + startNode + "', endNode = '" + endNode + "' where edgeId = '" + edgeID + "'";
            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Add Edge Failed!");
            e.printStackTrace();
        }
    }

    /**
     * the given edge is deleted from the database (the nodes that make up the edge still exist)
     * @param data the edge that we want to delete
     */
    public void deleteEdge(EdgeData data) {
        String edgeID = data.getEdgeID();
        String str;

        try {
            str = "DELETE FROM Edges WHERE edgeID = '" + edgeID +"'";
            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Delete Edge Failed!");
            e.printStackTrace();
        }
    }
}
