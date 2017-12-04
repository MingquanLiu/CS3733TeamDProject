package edu.wpi.cs3733.programname.commondata;

import java.util.ArrayList;

public class Interpreter extends Employee{
    ArrayList<String> languages;

    public Interpreter(String username, String password, String firstName, String middleName, String lastName,
                       boolean sysAdmin, String serviceType, String email, ArrayList<String> languages) {
        super(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
        this.languages = languages;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
    public void addLanguage(String language){
        this.languages.add(language);
    }

    public Employee toEmployee(){
        String username = this.getUsername();
        String password = this.getPassword();
        String firstName = this.getFirstName();
        String middleName = this.getMiddleName();
        String lastName = this.getLastName();
        boolean sysAdmin = this.getSysAdmin();
        String serviceType = "interpreter";
        String email = this.getEmail();
        Employee employee = new Employee(username,password,firstName,middleName,lastName,sysAdmin,serviceType,email);
        return employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interpreter)) return false;
        if (!super.equals(o)) return false;

        Interpreter that = (Interpreter) o;

        return getLanguages() != null ? getLanguages().equals(that.getLanguages()) : that.getLanguages() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLanguages() != null ? getLanguages().hashCode() : 0);
        return result;
    }
}
