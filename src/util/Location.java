package util;

import java.awt.Dimension;
import java.awt.geom.Point2D;


/**
 * This class represents a Location (in pixels) on the screen and
 * adds some utility functions to the Point2D class.
 * 
 * Note, this might be overkill, it was just annoying that Point2D
 * did not implement translate :(
 * 
 * @author Robert C. Duvall
 */
public class Location extends Point2D.Double {
    // default serialization ID
    private static final long serialVersionUID = 1L;

    /**
     * Create a location at the origin.
     */
    public Location () {
        super(0, 0);
    }

    /**
     * Create a location at given (x, y) coordinates.
     */
    public Location (double x, double y) {
        super(x, y);
    }

    /**
     * Create a location that is identical to the given other location.
     */
    public Location (Point2D source) {
        super(source.getX(), source.getY());
    }

    /**
     * Reset this location to origin.
     */
    public void reset () {
        setLocation(0, 0);
    }

    /**
     * Move this location by given vector.
     * 
     * @see java.awt.Point#translate(int, int)
     */
    public void translate (Vector amount) {
        setLocation(getX() + amount.getXChange(), getY() + amount.getYChange());
    }

    /**
     * Returns a vector that is the difference between this location and
     * the given other location.
     */
    public Vector difference (Point2D other) {
        return new Vector(this, other);
    }

    /**
     * Resets the provided location to lie within the provided bounds
     * 
     * @param location
     * @param bounds
     * @return true if location changed.
     */
    public boolean tryCorrectingBounds (Dimension bounds) {
        Location start = new Location(getX(), getY());
        double width = bounds.getWidth();
        double height = bounds.getHeight();
        double x = getX();
        double y = getY();
        while (x < 0) {
            x += width;
        }
        while (y < 0) {
            y += height;
        }
        x = x % width;
        y = y % height;
        this.setLocation(x, y);
        return (start.getX() != x || start.getY() != y);
    }

}
