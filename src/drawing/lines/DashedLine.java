package drawing.lines;

import java.awt.Color;
import util.Location;


/**
 * Builds dashed lines.
 * 
 * @author Scott Valentine
 * 
 */
public class DashedLine extends LineBuilder {


    /** Index of this line builder in the line palette */
    public static final Integer PALETTE_INDEX = 1;
    
    private static final int DEFAULT_SPACE = 15;

    private static final Color CLEAR = new Color(0, 0, 0, 0);


    private int myCount;
    private Location myLast;

    /**
     * Default constructor. creation of dashed lines.
     */
    public DashedLine() {
        myCount = 0;
        myLast = new Location();
    }

    @Override
    public Point buildLine(Location start, Location end, double thickness, Color color) {
        if (myLast.equals(start)) { 
            return new Point(start, thickness, CLEAR); 
        }
        myLast = start;
        if (myCount % (DEFAULT_SPACE * 2) < DEFAULT_SPACE) {
            myCount += 1;
            return new Point(start, thickness, color);
        }
        else {
            myCount += 1;
            return new Point(start, thickness, CLEAR);
        }

    }

}
