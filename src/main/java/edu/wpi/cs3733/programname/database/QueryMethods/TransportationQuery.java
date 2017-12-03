package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import edu.wpi.cs3733.programname.commondata.TransportationRequest;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransportationQuery {
    private DBConnection dbConnection;

    public TransportationQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

//    public ArrayList<TransportationRequest> queryAllTransportationRequests(){
//        TransportationRequest queryResult;
//        ArrayList<TransportationRequest> resultList = new ArrayList<TransportationRequest>();
//        try {
//            String sql = "SELECT * FROM TransportationRequest";
//            Statement stmt = dbConnection.getConnection().createStatement();
//            ResultSet result = stmt.executeQuery(sql);
//            int serviceID;
//            String transportType;
//            String destination;
//            String reservationTime;
//
//            while(result.next()) {
//                serviceID = result.getInt("serviceID");
//                transportType = result.getString("transportType");
//                destination = result.getString("destination");
//                reservationTime = result.getString("reservationTime");
//                queryResult = new TransportationRequest();
//                resultList.add(queryResult);
//            }
//        } catch (SQLException e) {
//            System.out.println("Query Transportation Request Failed!");
//            e.printStackTrace();
//        }
//        return resultList;
//    }
}
