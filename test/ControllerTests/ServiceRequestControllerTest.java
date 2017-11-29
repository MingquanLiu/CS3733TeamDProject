//
//import edu.wpi.cs3733.programname.commondata.Coordinate;
//import edu.wpi.cs3733.programname.commondata.NodeData;
//import edu.wpi.cs3733.programname.database.DBConnection;
//import edu.wpi.cs3733.programname.servicerequest.EmployeesQuery;
//import edu.wpi.cs3733.programname.servicerequest.ServiceRequestController;
//import edu.wpi.cs3733.programname.servicerequest.ServiceRequestsQuery;
//import edu.wpi.cs3733.programname.commondata.Employee;
//import edu.wpi.cs3733.programname.commondata.ServiceRequest;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
////import static org.junit.Assert.assertEquals;
//
//public class ServiceRequestControllerTest {
//
//    //CHANGE TARGET EMAIL IF YOU RUN THIS TEST PLEASE
////    @Test
////    public void testSendEmail() {
////        Employee emp = new Employee("admin", "admin", "admin", "a",
////                "admin", true, Constants.INTERPRETER_REQUEST);
////        ArrayList<Employee> recipients = new ArrayList<Employee>();
////        NodeData location = new NodeData("request_loc", new Coordinate(4000,500), "2F", Constants.INTERPRETER_REQUEST,
////                "request", "request");
////        recipients.add(emp);
////        ServiceRequest request = new ServiceRequest(emp, LocalDateTime.now(), Constants.INTERPRETER_REQUEST,
////                recipients, location, null, "Interpreter Request needed for this test case!!!!");
////        ServiceRequestMessage message = new ServiceRequestMessage("cs3733teamD@gmail.com", "dbswenarton@wpi.edu",
////                request);
////        message.sendMessage();
////        assertTrue(true);
////        // note: check target email to confirm test pass. change
////    }
//
//
//    // test email
//    @Test
//    public void testEmail(){
//        DBConnection dbConnection = new DBConnection();
//        EmployeesQuery queryEmployee = new EmployeesQuery(dbConnection);
//        ServiceRequestsQuery queryServiceRequest = new ServiceRequestsQuery(dbConnection);
//        ServiceRequestController srController = new ServiceRequestController(dbConnection, queryEmployee, queryServiceRequest);
//        Employee wong = new Employee("user", "pass", "Wilson", "", "Wong", true, "interpreter");
//        Coordinate aBathroomCoord = new Coordinate(4125, 625);
//        NodeData bBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","REST","Restroom B elevator Floor 2", "Restroom B");
//        NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","REST","Restroom B elevator Floor 2", "Restroom B");
//        ServiceRequest serviceRequest = new ServiceRequest(1, wong,"interpreter",aBathroom,bBathroom,"Need someone speaks Spanish");
//        srController.sendEmail(serviceRequest);
//    }
//
//
//
//    // This test is for checking the sql query String
//    // This is not working
//    @Test
//    public void testQuery(){
//        DBConnection dbConnection = new DBConnection();
//        EmployeesQuery queryEmployee = new EmployeesQuery(dbConnection);
//        ServiceRequestsQuery queryServiceRequest = new ServiceRequestsQuery(dbConnection);
//        ServiceRequestController srController = new ServiceRequestController(dbConnection, queryEmployee, queryServiceRequest);
//        Employee john = new Employee("userjohn", "passjohn", "John", "J", "John", true, "transportation");
//        Coordinate aBathroomCoord = new Coordinate(4125, 625);
//        NodeData bBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","REST","Restroom B elevator Floor 2", "Restroom B");
//        NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","REST","Restroom B elevator Floor 2", "Restroom B");
//        // test insert service request
//        srController.createServiceRequest(john, "transportation", aBathroom, bBathroom, "need a wheelchair");
//        assertEquals(0,0);
//    }
//
//
//
//}
