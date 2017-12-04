package edu.wpi.cs3733.programname.commondata.ServiceRequest;

public class InterpreterRequest extends ServiceRequest {
    String language;

    InterpreterRequest(int serviceID, String sender, String serviceType, String location1, String location2,
                           String description, String reservationTime, int severity, String language){
        super(serviceID, sender, serviceType, location1, location2, description, reservationTime, severity);
        this.language = language;
    }

    InterpreterRequest(int serviceID, String sender, String receiver, String serviceType, String location1, String location2,
                           String description, String requestTime, String handleTime, String completionTime, String status,
                           String reservationTime, int severity, String language){
        super(serviceID, sender, receiver, serviceType, location1, location2, description, requestTime, handleTime, completionTime,status,reservationTime, severity);
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
        if (!(o instanceof InterpreterRequest)) return false;
        if (!super.equals(o)) return false;

        InterpreterRequest that = (InterpreterRequest) o;

        return getLanguage() != null ? getLanguage().equals(that.getLanguage()) : that.getLanguage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        return result;
    }

}
