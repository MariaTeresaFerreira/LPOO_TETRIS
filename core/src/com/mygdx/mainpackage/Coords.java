package com.mygdx.mainpackage;

/**
 * Class for coordinates.
 */
public class Coords {

    private float x;
    private float y;

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

    public int compareTo(Coords c){
        float dx = Math.abs(this.X() - c.X());
        float dy = Math.abs(this.Y() - c.Y());
        if (dx < dy){
            if (this.Y() < c.Y()) return -1;
            return 1;
        }
        else{
            if (this.X() < c.X()) return -1;
            return 1;
        }
    }
}