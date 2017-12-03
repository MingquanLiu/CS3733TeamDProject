package edu.wpi.cs3733.programname.commondata;

public class TransportationRequest extends ServiceRequest{
    String transportType;
    String destination;
    String reservationTime;

    TransportationRequest(int serviceID, String sender, String serviceType, String location1, String location2,
                          String description, String reservationTime, String transportType, String destination, int severity){
        super(serviceID, sender, serviceType, location1, location2, description, severity);
        this.transportType = transportType;
        this.destination = destination;
        this.reservationTime = reservationTime;
    }

    TransportationRequest(int serviceID, String sender, String receiver, String serviceType, String location1, String location2,
                          String description, String requestTime, String handleTime, String completionTime, String status,
                          String reservationTime, String transportType, String destination, int severity){
        super(serviceID, sender, receiver, serviceType, location1, location2, description, requestTime, handleTime, completionTime,status,severity);
        this.transportType= transportType;
        this.destination = destination;
        this.reservationTime = reservationTime;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportationRequest)) return false;
        if (!super.equals(o)) return false;

        TransportationRequest that = (TransportationRequest) o;

        if (getTransportType() != null ? !getTransportType().equals(that.getTransportType()) : that.getTransportType() != null)
            return false;
        if (getDestination() != null ? !getDestination().equals(that.getDestination()) : that.getDestination() != null)
            return false;
        return getReservationTime() != null ? getReservationTime().equals(that.getReservationTime()) : that.getReservationTime() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTransportType() != null ? getTransportType().hashCode() : 0);
        result = 31 * result + (getDestination() != null ? getDestination().hashCode() : 0);
        result = 31 * result + (getReservationTime() != null ? getReservationTime().hashCode() : 0);
        return result;
    }
}
