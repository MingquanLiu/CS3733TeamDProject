package edu.wpi.cs3733.programname.commondata;

public class Coordinate {

    private int x;
    private int y;

    /**
     * constructor for Coordinate class
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * getter for y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * setter for x coordinate
     * @param x the coordinate to be set
     */
    public void setX(int x) { this.x = x; }

    /**
     * setter for y coordinate
     * @param y the coordinate to be set
     */
    public void setY(int y) { this.y = y; }

    /**
     * checks if generic object is equal
     * @param o any object
     * @return true if objects equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * converts item to hash code using the x and y coordinate
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
