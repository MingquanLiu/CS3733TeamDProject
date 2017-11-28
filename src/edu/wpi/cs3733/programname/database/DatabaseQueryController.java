package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;

import java.util.List;


public class DatabaseQueryController {

    private DBConnection dbConnection;
    private NodesQuery nodesQuery;
    private EdgesQuery edgesQuery;
    public DatabaseQueryController(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        nodesQuery = new NodesQuery(dbConnection);
        edgesQuery = new EdgesQuery(dbConnection);
    }


    // Query By ID
    public NodeData queryNodeById(String nID) {
        return nodesQuery.queryNodeByID( nID);
    }


    public EdgeData queryEdgeById(String eID) {
        return edgesQuery.queryEdgeByID(eID);
    }


//    public List<ServiceRequestInfo> queryServiceRequesByID(DBConnection dbConnection, String srID) {
//        return ServiceRequestsQuery.queryByID(dbConnection, srID);
//    }


    // Get List of  all data
    public List<NodeData> getAllNodeData() {
        return nodesQuery.getAllNodeInfo();
    }


    public List<EdgeData> getAllEdgeData() {
        return edgesQuery.getAllEdgeInfo();
    }


//    public List<EmployeeInfo> getAllEmployeeInfo() {
//        return EmployeeQuery.getAllEmployeeInfo(dbConnection);
//    }

//
//    public List<ServiceRequestInfo> getAllServiceRequestInfo() {
//        return ServiceRequestQuery.getAllServiceRequest(dbConnection);
//    }




    // Query by type
    public List<NodeData> queryNodeByType(String nType) {
        return nodesQuery.queryNodeByType(nType);
    }

//    public List<ServiceRequestInfo> queryServiceRequesByType(DBConnection dbConnection, String type) {
//        return ServiceRequestQuery.queryByType(dbConnection, type);
//    }
//

//    // Query by status
//    public List<ServiceRequestInfo> queryServiceRequesByStatus(DBConnection dbConnection, String status) {
//        return ServiceRequestQuery.queryByStatus(dbConnection, status);
//    }

//
//    // Query by userName
//    public EmployeeInfo queryEmployeesByUserName(DBConnection dbConnection, String uName){
//        return EmployeeQuery.queryByUser(dbConnection, uName);
//    }

//
//    // Query by fullName
//    public EmployeeInfo queryEmployeesByFullName(DBConnection dbConnection, String fName, String mName, String lName){
//        return EmployeeQuery.queryByName(dbConnection, fName, mName, lName);
//    }




}
