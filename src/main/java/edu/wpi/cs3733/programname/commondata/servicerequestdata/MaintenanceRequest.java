package edu.wpi.cs3733.programname.commondata.servicerequestdata;

public class MaintenanceRequest extends ServiceRequest {
    String maintenanceType;

    public MaintenanceRequest(int serviceID, String sender, String serviceType, String location1, String location2,
                       String description, int severity, String maintenanceType){
        super(serviceID, sender, serviceType, location1, location2, description, severity);
        this.maintenanceType = maintenanceType;
    }

    public MaintenanceRequest(int serviceID, String sender, String receiver, String serviceType, String location1, String location2,
                       String description, String requestTime, String handleTime, String completionTime, String status,
                        int severity, String maintenanceType){
        super(serviceID, sender, receiver, serviceType, location1, location2, description, requestTime, handleTime, completionTime, status, severity);
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceRequest)) return false;
        if (!super.equals(o)) return false;

        MaintenanceRequest that = (MaintenanceRequest) o;

        return getMaintenanceType() != null ? getMaintenanceType().equals(that.getMaintenanceType()) : that.getMaintenanceType() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMaintenanceType() != null ? getMaintenanceType().hashCode() : 0);
        return result;
    }
}
