package edu.wpi.cs3733.programname.commondata;

public class Coordinate {

    private int xcoord;
    private int ycoord;


    /**
     * Coordinate constructor
     *
     * @param xcoord
     * @param ycoord
     */
    public Coordinate(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }


    /**
     * xcoord getter
     *
     * @return int xcoord
     */
    public int getXCoord() {
        return xcoord;
    }


    /**
     * xcoord setter
     *
     * @param xcoord
     */
    public void setXCoord(int xcoord) { this.xcoord = xcoord; }


    /**
     * ycoord getter
     *
     * @return int ycoord
     */
    public int getYCoord() {
        return ycoord;
    }

    /**
     * ycoord setter
     *
     * @param ycoord
     */
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
    public String toString() {
        return  xcoord + "," + ycoord;
    }

    /**
     * converts item to hash code using the x and y coordinate
     * @return the hashcode
     */

    @Override
    public int hashCode() {
        int result = xcoord;
        result = 31 * result + ycoord;
        return result;
    }
}
