//package DatabaseTests;
//
//import edu.wpi.cs3733.programname.commondata.*;
//import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
//import edu.wpi.cs3733.programname.database.DBConnection;
//import edu.wpi.cs3733.programname.database.DatabaseModificationController;
//import edu.wpi.cs3733.programname.database.DatabaseQueryController;
//import edu.wpi.cs3733.programname.database.RunScript;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
//public class NewServiceRequestTest {
//
//    DBConnection dbConnection = new DBConnection();
//    DatabaseModificationController dbModControl;
//    DatabaseQueryController databaseQueryController;
//    ArrayList<String> wongLanguage = new ArrayList<String>();
//    Interpreter wong;
//    InterpreterRequest interpreterRequest = new InterpreterRequest("1", "wwong", "","interpreter","TREST00102","TREST00101","Need someone speaks Spanish","","","","", 1, "Spanish", "");
//    Coordinate aBathroomCoord = new Coordinate(4125, 625);
//    NodeData bBathroom = new NodeData ("TREST00101", aBathroomCoord, "1","BTM","REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
//    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","BTM","REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
//    //ServiceRequest newWongRequest = new ServiceRequest(1, "wwong", "wwong","interpreter","TREST00102","TREST00101","Need someone speaks Spanish","","","","unhandle", 1);
//
//
//    public NewServiceRequestTest(){}
//
//    @Before
//    public void setupDbTables() throws IOException {
//        dbModControl = new DatabaseModificationController(dbConnection);
//        databaseQueryController = new DatabaseQueryController(dbConnection);
//        RunScript run = new RunScript();
//        run.runScript(dbConnection.getConnection());
//        wongLanguage.add("Spanish");
//        wongLanguage.add("other");
//        wong = new Interpreter("wwong", "pass", "Wilson", "", "Wong", true, "interpreter", "interpreterbwhospital@gmail.com", wongLanguage);
//
//    }
//
//    @Test
//    public void checkAddAndQueryInterpreter(){
//        dbModControl.addInterpreter(wong);
//        Interpreter compare = new Interpreter("wwong", "pass", "Wilson", "", "Wong", true, "interpreter", "interpreterbwhospital@gmail.com", wongLanguage);
//        Interpreter actual = (Interpreter) databaseQueryController.queryEmployeeByUsername("wwong");
//        //Interpreter compare = (Interpreter) wong;
//        assertEquals(compare,actual);
//    }
//
//    @Test
//    public void checkAddAndQueryInterpreterRequest(){
//        dbModControl.addNode(aBathroom);
//        dbModControl.addInterpreter(wong);
//        dbModControl.addInterpreterRequest(interpreterRequest);
//        InterpreterRequest actual = (InterpreterRequest)databaseQueryController.queryAllServiceRequests().get(0);
//    }
//
//    @Test
//    public void checkAddLanguage(){
//        dbModControl.addInterpreter(wong);
//        dbModControl.addLanguageToInterpreter("wwong","Mandarin");
//        ArrayList<String> updatedWongL = wongLanguage;
//        wongLanguage.add("Mandarin");
//        Interpreter updatedWong = new Interpreter("wwong", "pass", "Wilson", "", "Wong", true, "interpreter", "interpreterbwhospital@gmail.com", updatedWongL);
//        assertEquals(updatedWong,wong);
//    }
//
//}
