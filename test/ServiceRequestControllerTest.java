import edu.wpi.cs3733.programname.commondata.Constants;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.servicerequest.entity.Employee;
import edu.wpi.cs3733.programname.servicerequest.entity.ServiceRequest;
import edu.wpi.cs3733.programname.servicerequest.entity.ServiceRequestMessage;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceRequestControllerTest {

    //CHANGE TARGET EMAIL IF YOU RUN THIS TEST PLEASE
    @Test
    public void testSendEmail() {
        Employee emp = new Employee("admin", "admin", "admin", "a",
                "admin", true, Constants.INTERPRETER_REQUEST);
        ArrayList<Employee> recipients = new ArrayList<Employee>();
        NodeData location = new NodeData("request_loc", new Coordinate(4000,500), "2F", Constants.INTERPRETER_REQUEST,
                "request", "request");
        recipients.add(emp);
        ServiceRequest request = new ServiceRequest(emp, LocalDateTime.now(), Constants.INTERPRETER_REQUEST,
                recipients, location, null, "Interpreter Request needed for this test case!!!!");
        ServiceRequestMessage message = new ServiceRequestMessage("cs3733teamD@gmail.com", "dbswenarton@wpi.edu",
                request);
        message.sendMessage();
        assertTrue(true);
        // note: check target email to confirm test pass. change
    }



}
