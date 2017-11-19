package edu.wpi.cs3733.programname.commondata;

public class PhoneExtensionData {
    String accountName;
    int phoneExt;

    public PhoneExtensionData(String accountName, int phoneExt) {
        this.accountName = accountName;
        this.phoneExt = phoneExt;
    }

    public String getAccountName() {return this.accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public int getPhoneExt() {return this.phoneExt;}

    public void setPhoneExt(int phoneExt) {this.phoneExt = phoneExt;}
}
