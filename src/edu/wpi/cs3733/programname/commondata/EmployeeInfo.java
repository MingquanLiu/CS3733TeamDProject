package edu.wpi.cs3733.programname.commondata;

public class EmployeeInfo {
    String userName;
    String passWord;
    String firstName;
    String middleName;
    String lastName;
    int sysAdmin;
    String serviceType;

    public EmployeeInfo(String userName, String passWord, String firstName, String middleName,
                        String lastName, int sysAdmin, String serviceType) {

        this.userName = userName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sysAdmin = sysAdmin;
        this.serviceType = serviceType;
    }


}
