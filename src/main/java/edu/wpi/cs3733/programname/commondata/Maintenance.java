package edu.wpi.cs3733.programname.commondata;

public class Maintenance extends Employee{
    String maintenanceType;

    public Maintenance(String username, String password, String firstName, String middleName, String lastName, boolean sysAdmin, String serviceType, String email, String maintenanceType) {
        super(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email);
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
        if (!(o instanceof Maintenance)) return false;
        if (!super.equals(o)) return false;

        Maintenance that = (Maintenance) o;

        return getMaintenanceType() != null ? getMaintenanceType().equals(that.getMaintenanceType()) : that.getMaintenanceType() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMaintenanceType() != null ? getMaintenanceType().hashCode() : 0);
        return result;
    }
}
