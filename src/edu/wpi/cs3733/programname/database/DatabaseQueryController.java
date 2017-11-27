package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery;

import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;
import edu.wpi.cs3733.programname.servicerequest.EmployeesQuery;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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


//    public List<ServiceRequest> queryServiceRequesByID(DBConnection dbConnection, String srID) {
//        return ServiceRequestQuery.queryByID(dbConnection, srID);
//    }


    // Get List of  all data
    public List<NodeData> getAllNodeData() {
        return NodesQuery.getAllNodeInfo(dbConnection);
    }


    public List<EdgeData> getAllEdgeData() {
        return EdgesQuery.getAllEdgeInfo(dbConnection);
    }


//    public List<Employee> getAllEmployeeInfo() {
//        return EmployeesQuery.getAllEmployee(dbConnection);
//    }
//
//
//    public List<ServiceRequest> getAllServiceRequestInfo() {
//        return ServiceRequestQuery.getAllServiceRequest(dbConnection);
//    }




    // Query by type
    public List<NodeData> queryNodeByType(DBConnection dbConnection, String nType) {
        return NodesQuery.queryNodeByType(dbConnection, nType);
    }

//    public List<ServiceRequest> queryServiceRequesByType(DBConnection dbConnection, String type) {
//        return ServiceRequestQuery.queryByType(dbConnection, type);
//    }
//
//
//    // Query by status
//    public List<ServiceRequest> queryServiceRequesByStatus(DBConnection dbConnection, String status) {
//        return ServiceRequestQuery.queryByStatus(dbConnection, status);
//    }
//
//
//    // Query by userName
//    public Employee queryEmployeesByUserName(DBConnection dbConnection, String uName){
//        return EmployeesQuery.queryByUsername(dbConnection, uName);
//    }
//
//
//    // Query by fullName
//    public Employee queryEmployeesByFullName(DBConnection dbConnection, String fName, String mName, String lName){
//        return EmployeesQuery.queryByName(dbConnection, fName, mName, lName);
//    }




}
