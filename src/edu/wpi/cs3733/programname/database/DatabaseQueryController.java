package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery.queryEdgeByID;
import static edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery.queryNodeByID;

public class DatabaseQueryController {

    DBConnection dbConnection;

    public DatabaseQueryController(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public NodeData queryNodeById(DBConnection dbConnection, String nID) { return queryNodeByID(dbConnection, nID); }

    public EdgeData queryEdgeById(String eID) { return queryEdgeByID(dbConnection, eID); }


    public List<NodeData> getAllNodeData() { return NodesQuery.getAllNodeInfo(dbConnection); }

    public List<EdgeData> getAllEdgeData() { return EdgesQuery.getAllEdgeInfo(dbConnection); }


    public List<NodeData> queryNodeByType(DBConnection dbConnection, String nType) { return NodesQuery.queryNodeByType(dbConnection, nType);}




}
