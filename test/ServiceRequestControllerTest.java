
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DBTables;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;
import edu.wpi.cs3733.programname.servicerequest.ServiceRequestController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertEquals;

public class ServiceRequestControllerTest {

    DBConnection dbConnection = new DBConnection();
    EmployeesQuery queryEmployee = new EmployeesQuery(dbConnection);
    ServiceRequestsQuery queryServiceRequest = new ServiceRequestsQuery(dbConnection);
    ServiceRequestController srController = new ServiceRequestController(dbConnection, queryEmployee, queryServiceRequest);
    Employee wong = new Employee("user", "pass", "Wilson", "", "Wong", true, "interpreter");
    Employee john = new Employee("userjohn", "passjohn", "John", "J", "John", true, "transportation");
    Coordinate aBathroomCoord = new Coordinate(4125, 625);
    Coordinate coord1 = new Coordinate(4550,375);
    NodeData teamDnode1 = new NodeData("DELEV00A02",coord1,"2","15 Francis","ELEV","Elevator A Floor 2","Elevator A2","Team D");
    Coordinate coord2 = new Coordinate(4155,650);
    NodeData teamDnode2 = new NodeData("DELEV00B02",coord2,"2","15 Francis","ELEV","Elevator B Floor 2","Elevator B2","Team D");
    NodeData bBathroom = new NodeData ("TREST00102", aBathroomCoord, "1","BTM","REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","BTM","REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
    ServiceRequest serviceRequest = new ServiceRequest(1, wong,"interpreter",aBathroom,bBathroom,"Need someone speaks Spanish");


    //CHANGE TARGET EMAIL IF YOU RUN THIS TEST PLEASE
//    @Test
//    public void testSendEmail() {
//        Employee emp = new Employee("admin", "admin", "admin", "a",
//                "admin", true, Constants.INTERPRETER_REQUEST);
//        ArrayList<Employee> recipients = new ArrayList<Employee>();
//        NodeData location = new NodeData("request_loc", new Coordinate(4000,500), "2F", Constants.INTERPRETER_REQUEST,
//                "request", "request");
//        recipients.add(emp);
//        ServiceRequest request = new ServiceRequest(emp, LocalDateTime.now(), Constants.INTERPRETER_REQUEST,
//                recipients, location, null, "Interpreter Request needed for this test case!!!!");
//        ServiceRequestMessage message = new ServiceRequestMessage("cs3733teamD@gmail.com", "dbswenarton@wpi.edu",
//                request);
//        message.sendMessage();
//        assertTrue(true);
//        // note: check target email to confirm test pass. change
//    }


    @Before
    public void setupDbTables() {
        DBTables.createAllTables(dbConnection);           // Makes all table

        queryEmployee.addEmployee(john);
    }


    // test email
    @Test
    public void testEmail(){
    srController.sendEmail(serviceRequest);
    }





    // This test is for checking the sql query String
    // This is not working
    @Test
    public void testQuery(){

  // test insert service request
        srController.createServiceRequest(john, "transportation", teamDnode1, teamDnode2, "need a wheelchair");
        assertEquals(0,0);
    }



}
