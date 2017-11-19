package edu.wpi.cs3733.programname.servicerequest.entity;

public class Employee {
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean sysAdmin;
    private String serviceType;


    /**
     * constructor for class Employee
     * @param username the username of the employee
     * @param password the password of the employee
     * @param firstName the first name of the employee
     * @param middleName the middle name of the employee
     * @param lastName the last name of the employee
     * @param sysAdmin whether the employee is a system admin
     * @param serviceType the service type this employee can assigned to
     */
    public Employee(String username, String password, String firstName, String middleName, String lastName, boolean sysAdmin, String serviceType){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sysAdmin = sysAdmin;
        this.serviceType = serviceType;
    }

    /**
     * getter for sysAdmin
     * @return whether the employee is a system admin
     */
    public boolean getSysAdmin(){
        return this.sysAdmin;
    }

}
