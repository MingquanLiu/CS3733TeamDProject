package edu.wpi.cs3733.programname.commondata;

public class StaffTitleData {
    String accountName;
    String acronym;

    public StaffTitleData(String accountName, String acronym) {
        this.accountName = accountName;
        this.acronym = acronym;
    }

    public String getAccountName() {return this.accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public String getAcronym() {return this.acronym;}

    public void setAcronym(String acronym) {this.acronym = acronym;}

}
