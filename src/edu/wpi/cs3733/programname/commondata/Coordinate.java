package edu.wpi.cs3733.programname.commondata;

public class Coordinate {

    private int xcoord;
    private int ycoord;


    public Coordinate(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }


    public int getXCoord() {
        return xcoord;
    }


    public int getYCoord() {
        return ycoord;
    }


    public void setXCoord(int xcoord) { this.xcoord = xcoord; }


    public void setYCoord(int ycoord) { this.ycoord = ycoord; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (xcoord != that.xcoord) return false;
        return ycoord == that.ycoord;
    }


    @Override
    public int hashCode() {
        int result = xcoord;
        result = 31 * result + ycoord;
        return result;
    }
}
