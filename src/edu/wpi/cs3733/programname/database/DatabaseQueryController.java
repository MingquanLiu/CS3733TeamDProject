package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;
import java.util.List;


public class DatabaseQueryController {

    DBConnection dbConnection;


    public DatabaseQueryController(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    // Query By ID
    public NodeData queryNodeById(DBConnection dbConnection, String nID) {
        return NodesQuery.queryNodeByID(dbConnection, nID);
    }


    public EdgeData queryEdgeById(String eID) {
        return EdgesQuery.queryEdgeByID(dbConnection, eID);
    }




    // Get List of  all data
    public List<NodeData> getAllNodeData() {
        return NodesQuery.getAllNodeInfo(dbConnection);
    }


    public List<EdgeData> getAllEdgeData() {
        return EdgesQuery.getAllEdgeInfo(dbConnection);
    }



    // Query by type
    public List<NodeData> queryNodeByType(DBConnection dbConnection, String nType) {
        return NodesQuery.queryNodeByType(dbConnection, nType);
    }




}
