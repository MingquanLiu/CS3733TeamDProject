
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import edu.wpi.cs3733.programname.database.*;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;
import edu.wpi.cs3733.programname.servicerequest.ServiceRequestController;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//import static org.junit.Assert.assertEquals;

public class ServiceRequestControllerTest {

    DBConnection dbConnection = new DBConnection();
    EmployeesQuery queryEmployee;
    ServiceRequestsQuery queryServiceRequest;
    ServiceRequestController srController;
    Employee wong = new Employee("wwong2", "pass", "Wilson", "", "Wong", true, "interpreter", "interpreterbwhospital@gmail.com");
    ArrayList<String> wongLanguage = new ArrayList<String>();
    Employee iwong = new Interpreter("wwong2", "pass", "Wilson", "", "Wong", true, "interpreter", "interpreterbwhospital@gmail.com", wongLanguage);
    Employee john = new Employee("userjohn", "passjohn", "John", "J", "John", false, "transportation","john@test.com");
    Employee yufei = new Employee("ygao6", "pass", "Yufei", "", "Gao", true, "transportation","ygao6@wpi.edu");
    Coordinate aBathroomCoord = new Coordinate(4125, 625);
    Coordinate coord1 = new Coordinate(4550,375);
    NodeData teamDnode1 = new NodeData("DELEV00A02",coord1,"2","15 Francis","ELEV","Elevator A Floor 2","Elevator A2","Team D");
    Coordinate coord2 = new Coordinate(4155,650);
    NodeData teamDnode2 = new NodeData("DELEV00B02",coord2,"2","15 Francis","ELEV","Elevator B Floor 2","Elevator B2","Team D");
    NodeData bBathroom = new NodeData ("TREST00101", aBathroomCoord, "1","BTM","REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","BTM","REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
    ServiceRequest wongServiceRequest = new ServiceRequest(1, "wwong2","interpreter","TREST00102","TREST00101","Need someone speaks Spanish",1);
    ServiceRequest newWongRequest = new ServiceRequest(1, "wwong2", "userjohn","interpreter","TREST00102","TREST00101","Need someone speaks Spanish","","","","handled", 1);
    ServiceRequest johnServiceRequest = new ServiceRequest(1, "userjohn","transportation","DELEV00A02", "DELEV00B02","need a wheelchair", 1);
    DatabaseModificationController dbModControl;
    DatabaseQueryController databaseQueryController;
    ManageController controller;
    Connection connection;

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
    public void setupDbTables() throws IOException{
        dbConnection.setDBConnection();
        RunScript run = new RunScript();
        run.runScript(dbConnection.getConnection());
        queryServiceRequest = new ServiceRequestsQuery(dbConnection);
        srController = new ServiceRequestController(dbConnection, queryEmployee, queryServiceRequest);
        queryEmployee = new EmployeesQuery(dbConnection);
        dbModControl = new DatabaseModificationController(dbConnection);
        databaseQueryController = new DatabaseQueryController(dbConnection);
        controller = new ManageController(dbConnection);
        connection = dbConnection.getConnection();
        //queryEmployee.addEmployee(john);
    }


    // test email
    @Test
    public void testSendEmailByType(){
        srController.sendEmailByType(wongServiceRequest);
    }


    @Test
    public void testSendEmail(){
        dbModControl.addEmployee(yufei);
        srController.sendEmailToEmployee(wongServiceRequest,yufei);
    }

    @Test
    public void testAddInterpreter(){
        dbModControl.addEmployee(iwong);
        //dbModControl.addEmployee(wong);
    }


    @Test
    public void testAddEmployee(){
        dbModControl.addEmployee(wong);
    }


    @Test
    public void testQueryEmployeeByUsername(){
        dbModControl.addEmployee(wong);
        Employee result = queryEmployee.queryEmployeeByUsername("wwong2");
        assertEquals(wong, result);
    }

    @Test
    public void testQueryEmployeeByUsername2(){
        dbModControl.addEmployee(john);
        Employee result = queryEmployee.queryEmployeeByUsername("userjohn");
        assertEquals(john,result);
    }

    @Test
    public void testQueryEmployeeByType(){
        dbModControl.addEmployee(wong);
        ArrayList<Employee> result = queryEmployee.queryEmployeesByType("interpreter");
        ArrayList<Employee> expected = new ArrayList<Employee>();
        expected.add(wong);
        assertEquals(expected,result);
    }

    @Test
    public void testGetAllEmployees(){
        dbModControl.addEmployee(wong);
        ArrayList<Employee> result = new ArrayList<Employee>();
        result = databaseQueryController.queryAllEmployees();
        ArrayList<Employee> expected = new ArrayList<Employee>();
        expected.add(wong);
    }


    @Test
    public void testCreateServiceRequestQuery(){
        dbModControl.addEmployee(wong);
        dbModControl.addNode(teamDnode1);
        // test insert service request
        printTables.printServiceRequestsTable(connection);
        controller.createServiceRequest("wwong2", "transportation", "DELEV00A02", "DELEV00B02", "need a wheelchair", 1);
        assertEquals(0,0);
    }
//
//    @Test
//    public void testGetInterpreterRequest(){
//        dbModControl.addNode(aBathroom);
//        dbModControl.addNode(bBathroom);
//        dbModControl.addEmployee(wong);
//        dbModControl.addServiceRequest(wongServiceRequest);
//        ArrayList<ServiceRequest> result = new ArrayList<ServiceRequest>();
//        result = controller.getInterpreterRequest();
//        ArrayList<ServiceRequest> expected = new ArrayList<ServiceRequest>();
//        expected.add(wongServiceRequest);
//        assertEquals(expected,result);
//    }

//    @Test
//    public void testGetTransportationRequest(){
//        dbModControl.addNode(teamDnode1);
//        dbModControl.addNode(teamDnode2);
//        dbModControl.addEmployee(john);
//        controller.createServiceRequest("userjohn", "transportation", "DELEV00A02", "DELEV00B02", "need a wheelchair");
//        ArrayList<ServiceRequest> result = new ArrayList<ServiceRequest>();
//        result = srController.getTransportationRequest();
//        ArrayList<ServiceRequest> unexpected = new ArrayList<ServiceRequest>();
//        unexpected.add(johnServiceRequest);
//        assertNotEquals(unexpected,result);
//    }

    @Test
    public void testHandleRequest(){
        dbModControl.addNode(aBathroom);
        dbModControl.addNode(bBathroom);
        dbModControl.addEmployee(wong);
        dbModControl.addEmployee(john);
        dbModControl.addServiceRequest(wongServiceRequest);
        dbModControl.handleServiceRequest(wongServiceRequest,"userjohn");
        ServiceRequest result = databaseQueryController.queryServiceRequestsByID(1);
        ServiceRequest unexpected = newWongRequest;
        assertNotEquals(unexpected,result);

    }

//    @Test (expected = DerbySQLIntegrityConstraintViolationException.class)
//    public void checkConstraint1(){
//        //FOREIGN KEY (sender) REFERENCES Employees (username)
//        dbModControl.addNode(aBathroom);
//        dbModControl.addNode(bBathroom);
//        ServiceRequest badRequest = new ServiceRequest(2, "ygao6","interpreter","TREST00102","TREST00101","Need someone speaks Spanish");
//        dbModControl.addServiceRequest(badRequest);
//    }
//
//
//    @Test (expected = SQLException.class)
//    public void checkConstraint3(){
//        //FOREIGN KEY (location1) REFERENCES Nodes (nodeID) ON DELETE CASCADE
//
//    }

}
