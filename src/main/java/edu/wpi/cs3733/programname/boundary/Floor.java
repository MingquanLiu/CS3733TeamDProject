package edu.wpi.cs3733.programname.boundary;

public class Floor {
    private String floorName;
    private String floorNum;
    private String building;
    private String imgUrl;

    public Floor(String floor, String building, String num, String img) {
        floorName = floor;
        this.building = building;
        floorNum = num;
        imgUrl = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String toString() {
        return floorName;
    }
}
