package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseModificationController {
    DBConnection conn;

    /**
     *
     * @param conn the connection to the database
     */
    public DatabaseModificationController(DBConnection conn){
        this.conn = conn;
    }

    /**
     * updates database with the node that is passed in added
     * @param data the information/field values of the node you want to add
     */
    public void addNode(NodeData data) {
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        String str;
        try {
            // expected "insert into Nodes values (id, x, y, type, 2, 15 Francis, longName, shortName, Team D)"
            str = "insert into Nodes values('" + id + "', " + x + ", " + y + ", '2', '15 Francis', '" + type + "', '" + longName + "', '" + shortName + "', 'Team D')";
            System.out.println(str);
            conn.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
    }

    /**
     * looks up the node with the id of the given values in 'data' and updates all of the values to those of 'data'
     * @param data the information/field values of the node you want to edit
     */
    public void editNode(NodeData data) {
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        String str;
        try {
            // expected "update Nodes set xcoord = x, ycoord = y, floor = '2', building = 15 Francis, nodeType = type, longName = longName, shortName = shortName, teamAssigned = Team D where nodeID = id
            str = "update Nodes set xcoord = " + x + ", ycoord = " + y +
                    ", floor = '2', building = '15 Francis', nodeType = '" + type + "', longName = '" + longName + "', shortName = '"
                    + shortName + "', teamAssigned = 'Team D' where nodeID = '" + id + "'";
            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Edit Node Failed!");
            e.printStackTrace();
        }
    }

    /**
     * the given node is deleted from the database
     * @param data the information/field values of the node you want to remove
     */
    public void deleteNode(NodeData data){
        String id = data.getId();
        String str;
        // just for testing
        //str ="delete from Nodes where nodeID = " + id;
        try {
            str ="delete from Nodes where nodeID = '" + id + "'";
            System.out.println(str);
            conn.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Delete Node Failed!");
            e.printStackTrace();
        }
    }

    /**
     * adds a new edge to the database using 2 already existing nodes (search for the nodes in database using given IDs)
     * @param node1Id the id of the first node to be part of the edge
     * @param node2Id the id of the second node to be part of the edge
     */
    public void addEdge(String node1Id, String node2Id){
        String id = node1Id + "_" + node2Id;
        String str;
        try {
            str = "insert into Edges values('" + id + "', '" + node1Id + "', '" + node2Id +"')";
            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Add Edge Failed!");
            e.printStackTrace();
        }
    }

    /**
     * looks up the edge with the id of the given values in 'edge' parameter and updates all of the values of mathcing edge to those of 'edge'
     * @param edge the information/field values of the edge you want to edit
     */
    public void editEdge(Edge edge){
        String id = edge.getEdgeId();
        String start = edge.getFirstNodeId();
        String end = edge.getSecondNodeId();
        String str;
        try {
            str = "update Edges set startNode = '" + start + "', endNode = '" + end + "' where edgeId = '" + id + "'";
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
    public void deleteEdge(Edge data) {
        String id = data.getEdgeId();
        String str;
        try {
            str = "delete from Edges where edgeID = '" + id +"'";
            System.out.println(str);
            conn.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Delete Edge Failed!");
            e.printStackTrace();
        }
    }
}
