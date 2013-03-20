package drawing.lines;

import java.awt.Color;
import util.Location;

/**
 * Builds Solid lines.
 * 
 * @author Scott Valentine
 *
 */
public class SolidLine extends LineBuilder {
    
    /** Index of this line builder in the line palette */
    public static final int PALETTE_INDEX = 0;
    
    @Override
    public Point buildLine(Location start, Location end, double thickness, Color color) {
        return new Point(start, thickness, color);
    }

}
