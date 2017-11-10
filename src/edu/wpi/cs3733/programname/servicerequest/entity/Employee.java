package edu.wpi.cs3733.programname.servicerequest.entity;

public class Employee {
    private String name;
    private int id;
    private boolean sysAdmin;

    // constructor of Employee
    public Employee(String name, int id, boolean sysAdmin){
        this.name = name;
        this.id = id;
        this.sysAdmin = sysAdmin;
    }

    // getter of sysAdmin
    public boolean getSysAdmin(){
        return this.sysAdmin;
    }

}
