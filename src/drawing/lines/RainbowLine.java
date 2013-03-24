package drawing.lines;

import java.awt.Color;
import util.Location;

/**
 * Makes a rainbow colored line.
 * 
 * @author Scott Valentine
 *
 */
public class RainbowLine implements LineBuilder {

    /** Index of the rainbow line builder in the LinePalette*/
    public static final int RAINBOW_INDEX = 2;
    
    private static final double FREQUENCY = .1;

    private static final double R_OFFSET = 0;

    private static final double G_OFFSET = 2.0;

    private static final double B_OFFSET = 4.0;

    private static final double COLOR_CENTER = 127.5;

    
    private static final Color CLEAR = new Color(0, 0, 0, 0);


    private int myR;
    private int myG;
    private int myB;
    private Location myLast;

    private int myCount;
    

    /**
     * New RainbowLine builder
     */
    public RainbowLine() {
        myR = 0;
        myG = 0;
        myB = 0;
        myLast = new Location();

    }

    @Override
    public Point buildLine(Location start, Location end, double thickness, Color color) {
        if (myLast.equals(start)) { 
            return new Point(start, thickness, CLEAR); 
        }
        else {
            Color c = new Color(myR, myG, myB);
            myCount += 1;
            updateColors();
            myLast = start;
            return new Point(start, thickness, c);
        }
        
    }

    private void updateColors() {
        myR = (int) (Math.sin(FREQUENCY * myCount + R_OFFSET) * COLOR_CENTER + COLOR_CENTER);
        myG = (int) (Math.sin(FREQUENCY * myCount + G_OFFSET) * COLOR_CENTER + COLOR_CENTER);
        myB = (int) (Math.sin(FREQUENCY * myCount + B_OFFSET) * COLOR_CENTER + COLOR_CENTER);

    }

}
