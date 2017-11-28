package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.ModificationMethods.EdgesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.NodesMethod;



public class DatabaseModificationController {
    private DBConnection conn;

    /**
     *
     * @param conn the connection to the database
     */
    public DatabaseModificationController(DBConnection conn){
        this.conn = conn;
    }


    public void addNode(NodeData data) {
        NodesMethod.insertNode(conn, data);
    }


    public void editNode(NodeData data) {
        NodesMethod.modifyNode(conn, data);
    }


    public void deleteNode(NodeData data){
        NodesMethod.removeNode(conn, data);
    }



    public void addEdge(String node1ID, String node2ID){
        EdgesMethod.insertEdge(conn, node1ID, node2ID);
    }


    public void editEdge(EdgeData data){
        EdgesMethod.modifyEdge(conn, data);
    }

    /**
     * the given edge is deleted from the database (the nodes that make up the edge still exist)
     * @param data the edge that we want to delete
     */
    public void deleteEdge(EdgeData data) {
        EdgesMethod.removeEdge(conn, data);
    }
}
