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

public class ServiceRequestControllerTest {

    //THIS CLASS IS USELESS BECAUSE WE THOUGHT CREATESERVICEREQUEST RETURNED BOOLEAN SO WE NEEDED TO TEST IT BUT JK TURNS OUT WE DONT HAVE TO IT RETURNS VOID
    /*
    @Test
    public void createServiceRequestTest1(){
        assertTrue(1,1);
    }
    */

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
        ServiceRequestMessage message = new ServiceRequestMessage("test", "dbswenarton@wpi.edu",
                request);
        message.sendMessage();
    }

}
