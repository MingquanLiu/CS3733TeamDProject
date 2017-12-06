package edu.wpi.cs3733.programname.boundary;

import java.util.ArrayList;

public class Building {
    private String name;
    private ArrayList<Floor> floors;

    public Building(String n){
        name = n;
        floors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor fl) {
        floors.add(fl);
    }

    public void addAllFloors(ArrayList<Floor> fls) {
        floors.addAll(fls);
    }

    public  String toString(){
        return name;
    }
}
