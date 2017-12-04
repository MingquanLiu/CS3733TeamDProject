package edu.wpi.cs3733.programname.commondata;

public class InterpreterRequest extends ServiceRequest{
    String language;
    String reservationTime;

    InterpreterRequest(int serviceID, String sender, String serviceType, String location1, String location2,
                           String description, String reservationTime, int severity, String language){
        super(serviceID, sender, serviceType, location1, location2, description, severity);
        this.language = language;
        this.reservationTime = reservationTime;
    }

    InterpreterRequest(int serviceID, String sender, String receiver, String serviceType, String location1, String location2,
                           String description, String requestTime, String handleTime, String completionTime, String status,
                           String reservationTime, int severity, String language){
        super(serviceID, sender, receiver, serviceType, location1, location2, description, requestTime, handleTime, completionTime,status, severity);
        this.language = language;
        this.reservationTime = reservationTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public ServiceRequest toServiceRequest(){
        int serviceID = this.getServiceID();
        String senderUsername = this.getSender();
        String receiverUsername = this.getReceiver();
        String serviceType = this.getServiceType();
        String node1ID = this.getLocation1();
        String node2ID = this.getLocation2();
        String description = this.getDescription();
        String requestTime = this.getRequestTime();
        String handleTime = this.getHandleTime();
        String completionTime = this.getCompletionTime();
        String status = this.getStatus();
        int severity = this.getSeverity();
        ServiceRequest serviceRequest = new ServiceRequest(serviceID,senderUsername,receiverUsername,serviceType,node1ID,
                node2ID,description,requestTime,handleTime,completionTime,status,severity);
        return serviceRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterpreterRequest)) return false;
        if (!super.equals(o)) return false;

        InterpreterRequest that = (InterpreterRequest) o;

        if (getLanguage() != null ? !getLanguage().equals(that.getLanguage()) : that.getLanguage() != null)
            return false;
        return getReservationTime() != null ? getReservationTime().equals(that.getReservationTime()) : that.getReservationTime() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        result = 31 * result + (getReservationTime() != null ? getReservationTime().hashCode() : 0);
        return result;
    }
}
