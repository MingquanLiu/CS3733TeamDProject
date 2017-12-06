package edu.wpi.cs3733.programname.commondata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean sysAdmin;
    private String serviceType;
    private String email;

    private final StringProperty firstNameProperty;
    private final StringProperty lastNameProperty;
    private final StringProperty usernameProperty;
    private final StringProperty passwordProperty;
    private final StringProperty serviceTypeProperty;
    private final StringProperty emailProperty;
    private final StringProperty sysAdminProperty;


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

        this.firstNameProperty = new SimpleStringProperty(firstName);
        this.lastNameProperty = new SimpleStringProperty(lastName);
        this.usernameProperty = new SimpleStringProperty(username);
        this.passwordProperty = new SimpleStringProperty(password);
        this.serviceTypeProperty = new SimpleStringProperty(serviceType);
        this.emailProperty = new SimpleStringProperty(email);
        if(sysAdmin) this.sysAdminProperty = new SimpleStringProperty("Yes");
        else this.sysAdminProperty = new SimpleStringProperty("No");
    }

    public String getFirstNameProperty() {
        return firstNameProperty.get();
    }

    public StringProperty firstNameProperty() {
        return firstNameProperty;
    }

    public void setFirstNameProperty(String firstNameProperty) {
        this.firstNameProperty.set(firstNameProperty);
    }

    public String getLastNameProperty() {
        return lastNameProperty.get();
    }

    public StringProperty lastNameProperty() {
        return lastNameProperty;
    }

    public void setLastNameProperty(String lastNameProperty) {
        this.lastNameProperty.set(lastNameProperty);
    }

    public String getUsernameProperty() {
        return usernameProperty.get();
    }

    public StringProperty usernameProperty() {
        return usernameProperty;
    }

    public void setUsernameProperty(String usernameProperty) {
        this.usernameProperty.set(usernameProperty);
    }

    public String getPasswordProperty() {
        return passwordProperty.get();
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    public void setPasswordProperty(String passwordProperty) {
        this.passwordProperty.set(passwordProperty);
    }

    public String getServiceTypeProperty() {
        return serviceTypeProperty.get();
    }

    public StringProperty serviceTypeProperty() {
        return serviceTypeProperty;
    }

    public void setServiceTypeProperty(String serviceTypeProperty) {
        this.serviceTypeProperty.set(serviceTypeProperty);
    }

    public String getEmailProperty() {
        return emailProperty.get();
    }

    public StringProperty emailProperty() {
        return emailProperty;
    }

    public void setEmailProperty(String emailProperty) {
        this.emailProperty.set(emailProperty);
    }

    public String getSysAdminProperty() {
        return sysAdminProperty.get();
    }

    public StringProperty sysAdminProperty() {
        return sysAdminProperty;
    }

    public void setSysAdminProperty(String sysAdminProperty) {
        this.sysAdminProperty.set(sysAdminProperty);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getSysAdmin() != employee.getSysAdmin()) return false;
        if (getUsername() != null ? !getUsername().equals(employee.getUsername()) : employee.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(employee.getPassword()) : employee.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(employee.getFirstName()) : employee.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(employee.getMiddleName()) : employee.getMiddleName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(employee.getLastName()) : employee.getLastName() != null)
            return false;
        if (getServiceType() != null ? !getServiceType().equals(employee.getServiceType()) : employee.getServiceType() != null)
            return false;
        return getEmail() != null ? getEmail().equals(employee.getEmail()) : employee.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getSysAdmin() ? 1 : 0);
        result = 31 * result + (getServiceType() != null ? getServiceType().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
