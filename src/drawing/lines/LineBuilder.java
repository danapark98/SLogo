package drawing.lines;

import java.awt.Color;
import util.Location;

/**
 * Builds a line in a certain style.
 * 
 * @author Scott Valentine
 *
 */
public interface LineBuilder {

    
    
    /**
     * Creates the next point in the line.
     * 
     * @param start is the start of the line.
     * @param end is the end of the line.
     * @param thickness is the thickness of the line.
     * @param color is the color of the line.
     * @return A point that that is part of the line being created. 
     */
    public abstract Point buildLine(Location start, Location end, double thickness, Color color);

    
    
}
