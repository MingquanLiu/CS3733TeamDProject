package edu.wpi.cs3733.programname.commondata;

public class Interpreter extends Employee{
    String language;

    public Interpreter(String username, String password, String firstName, String middleName, String lastName, boolean sysAdmin, String serviceType, String email, String language) {
        super(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interpreter)) return false;
        if (!super.equals(o)) return false;

        Interpreter that = (Interpreter) o;

        return getLanguage() != null ? getLanguage().equals(that.getLanguage()) : that.getLanguage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        return result;
    }
}
