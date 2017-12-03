package edu.wpi.cs3733.programname.boundary;

public class Floor {
    private String floorName;
    private String building;

    public Floor(String floor, String building){
        floorName = floor;
        this.building = building;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
