package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.EmployeeInfo;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.ServiceRequestInfo;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeeQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestQuery;


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


    public List<ServiceRequestInfo> queryServiceRequesByID(DBConnection dbConnection, String srID) {
        return ServiceRequestQuery.queryByID(dbConnection, srID);
    }


    // Get List of  all data
    public List<NodeData> getAllNodeData() {
        return NodesQuery.getAllNodeInfo(dbConnection);
    }


    public List<EdgeData> getAllEdgeData() {
        return EdgesQuery.getAllEdgeInfo(dbConnection);
    }


    public List<EmployeeInfo> getAllEmployeeInfo() {
        return EmployeeQuery.getAllEmployeeInfo(dbConnection);
    }


    public List<ServiceRequestInfo> getAllServiceRequestInfo() {
        return ServiceRequestQuery.getAllServiceRequest(dbConnection);
    }




    // Query by type
    public List<NodeData> queryNodeByType(DBConnection dbConnection, String nType) {
        return NodesQuery.queryNodeByType(dbConnection, nType);
    }

    public List<ServiceRequestInfo> queryServiceRequesByType(DBConnection dbConnection, String type) {
        return ServiceRequestQuery.queryByType(dbConnection, type);
    }


    // Query by status
    public List<ServiceRequestInfo> queryServiceRequesByStatus(DBConnection dbConnection, String status) {
        return ServiceRequestQuery.queryByStatus(dbConnection, status);
    }


    // Query by userName
    public EmployeeInfo queryEmployeesByUserName(DBConnection dbConnection, String uName){
        return EmployeeQuery.queryByUser(dbConnection, uName);
    }


    // Query by fullName
    public EmployeeInfo queryEmployeesByFullName(DBConnection dbConnection, String fName, String mName, String lName){
        return EmployeeQuery.queryByName(dbConnection, fName, mName, lName);
    }



}
