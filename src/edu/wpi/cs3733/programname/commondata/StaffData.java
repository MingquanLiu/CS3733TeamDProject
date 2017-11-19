package edu.wpi.cs3733.programname.commondata;

public class StaffData {
    String accountName;
    String accountPassword;
    String firstName;
    String middleInitial;
    String lastName;

    public StaffData (String accountName, String accountPassword, String firstName, String middleInitial, String lastName) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
    }


    public String getAccountName() {return this.accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public String getAccountPassword() {return this.accountPassword;}

    public void setAccountPassword(String accountPassword) {this.accountPassword = accountPassword;}

    public String getFirstName() {return this.firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getMiddleInitial() {return this.middleInitial;}

    public void setMiddleInitial(String middleInitial) {this.middleInitial = middleInitial;}

    public String getLastName() {return this.lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}
}
