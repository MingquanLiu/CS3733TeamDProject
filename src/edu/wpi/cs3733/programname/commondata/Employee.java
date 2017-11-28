package edu.wpi.cs3733.programname.commondata;

public class Employee {
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean sysAdmin;
    private String serviceType;
    private String email;


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
    public Employee(String username, String password, String firstName, String middleName, String lastName, boolean sysAdmin, String serviceType, String email){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sysAdmin = sysAdmin;
        this.serviceType = serviceType;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(boolean sysAdmin) {
        this.sysAdmin = sysAdmin;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sysAdmin=" + sysAdmin +
                ", serviceType='" + serviceType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "username='" + username + '\'' + System.lineSeparator() +
//                ", firstName='" + firstName + '\'' +
//                " middleName='" + middleName + '\'' +
//                " lastName='" + lastName + '\'' + System.lineSeparator() +
//                ", sysAdmin=" + sysAdmin + System.lineSeparator() +
//                ", serviceType='" + serviceType + '\'' + System.lineSeparator() +
//                ", email='" + email + '\'' + System.lineSeparator();
//    }
}
