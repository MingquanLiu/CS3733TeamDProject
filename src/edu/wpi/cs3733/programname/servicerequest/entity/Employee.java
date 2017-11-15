package edu.wpi.cs3733.programname.servicerequest.entity;

public class Employee {
    private String name;
    private int id;
    private boolean sysAdmin;

    /**
     * constructor for class Employee
     * @param name the name of the employee
     * @param id the id of the employee
     * @param sysAdmin whether the employee is a system admin
     */
    public Employee(String name, int id, boolean sysAdmin){
        this.name = name;
        this.id = id;
        this.sysAdmin = sysAdmin;
    }

    /**
     * getter for sysAdmin
     * @return whether the employee is a system admin
     */
    public boolean getSysAdmin(){
        return this.sysAdmin;
    }

}
