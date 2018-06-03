package com.mygdx.mainpackage;

/**
 * Class for coordinates.
 */
public class Coords implements Comparable<Coords>{

    private float x;
    private float y;

    public Coords(){
        this.x = 0;
        this.y = 0;
    }

    /*
     * Constructs the object
     *
     * @param      x     X coordinate
     * @param      y     Y coordinate
     */
    public Coords(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Coords(Coords c) {
    	this.x = c.X();
    	this.y = c.Y();
    }

    /**
     * Equals
     *
     * @param      t     Object to be compared to the calling coordinate
     *
     * @return     true if Object t is a Coordinate and has the same values as the calling obj
     */
    public boolean equals(Object t) {
        return t != null && t instanceof Coords && ((Coords) t).X() == this.x &&
                ((Coords) t).Y() == this.y;
    }

    /**
     * Get X
     *
     * @return     x
     */
    public float X() {
        return this.x;
    }

    /**
     * Get Y
     *
     * @return     y
     */
    public float Y() {
        return this.y;
    }

    /**
     * Sets the coordinates.
     *
     * @param      x     new x
     * @param      y     new y
     */
    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Copy Constructor
     *
     * @param      nc    Coords to be copied
     */
    public void setCoords(Coords nc) {
        this.x = nc.X();
        this.y = nc.Y();
    }

    @Override
    public int compareTo(Coords c){

        if (this.X() < c.X()) {
            return -1;
        } else if (this.X() > c.X()) {
            return 1;
        } else {
            if (this.Y() > c.Y()) return -1;
            else if (this.Y() < c.Y()) return 1;
            else return 0;
        }
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}