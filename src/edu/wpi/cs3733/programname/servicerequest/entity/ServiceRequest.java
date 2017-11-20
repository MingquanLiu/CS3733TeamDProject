package edu.wpi.cs3733.programname.servicerequest.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServiceRequest {
    private Employee sender;
    private LocalDateTime time;
    private String type;
    private ArrayList<Employee> recipients;
    private NodeData location;
    private String description;

    /**
     * constructor for class ServiceRequest
     * @param sender the employee who sent the service request
     * @param time the date AND time of when service request was sent
     * @param type the type of service request
     */
    public ServiceRequest(Employee sender, LocalDateTime time, String type, ArrayList<Employee> recipients, NodeData location, String description) {
        this.sender = sender;
        this.time = time;
        this.type = type;
        this.recipients = recipients;
        this.location = location;
        this.description = description;
    }

    public ArrayList<Employee> getRecipients() {
        return recipients;
    }

    public void printRequest(){
        System.out.println("Sender: "+sender);
        System.out.println("Time Created: "+time);
        System.out.println("Type: "+type);
        System.out.println("Recipients: "+recipients);
        System.out.println("Location: "+location);
        System.out.println("Description: "+description);
        System.out.println("");
    }

    // move this method to DatabaseQueryController class
    public ArrayList<Employee> getGroupEmployees(String type){
        Employee queryResult = null;
        ArrayList<Employee> group = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM Employees WHERE serviceType = " + type;
            //Statement stmt = dbConnection.getConnection().createStatement();
            Statement stmt = null;
            ResultSet result = stmt.executeQuery(sql);

            String username;
            String password;
            String firstName;
            String middleName;
            String lastName;
            boolean sysAdmin;
            int sysAdminInt;
            String serviceType;

            while(result.next()) {
                username = result.getString("username");
                password = result.getString("password");
                firstName = result.getString("firstName");
                middleName = result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdminInt = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");
                if(sysAdminInt==1){
                    sysAdmin = true;
                }
                else{
                    sysAdmin = false;
                }
                queryResult = new Employee(username, password, firstName, middleName, lastName, sysAdmin, serviceType);
                group.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Employee Failed!");
            e.printStackTrace();
        }
        return group;
    }



//    public ArrayList<Employee> assignTo(boolean group){
//
//        if(group){
//            return addGroupRecipients();
//        }
//        else {
//            return addRecipients();
//        }
//    }

}
