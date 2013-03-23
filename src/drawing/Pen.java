package drawing;

import drawing.lines.LineBuilder;
import drawing.lines.SolidLine;
import exceptions.IllegalInstructionException;
import java.awt.Color;
import java.awt.Dimension;
import simulation.DisplayEditor;
import simulation.Turtle;
import util.Location;
import util.Pixmap;
import util.Vector;

/**
 * Paints lines.
 * 
 * @author Scott Valentine
 *
 */
public class Pen {
    
    private static final double REVERSE_ANGLE_VALUE = 180;


    private static final int OPAQUE_COLOR = 255;
    private static final double DEFAULT_PEN_THICKNESS = 4.0;

    private static final int DEFAULT_LINE_STYLE = 0;
    
    
    private DisplayEditor myDisplayEditor;
    private Turtle myTurtle;
    private Color myColor;
    private LineBuilder myLineBuilder;
    private double myThickness;
    private Pixmap myImage;
    
    /**
     * Creates a pen centered around a turtle and which can edit a displayEditor.
     * 
     * @param turtle moves the pen
     * @param displayEditor is what the pen draws on.
     */
    public Pen(Turtle turtle, DisplayEditor displayEditor) {
        myTurtle = turtle;
        myDisplayEditor = displayEditor;
        try {
            myColor = displayEditor.getPalette().getColor(ColorPalette.DEFAULT_BLACK_INDEX);
        }
        catch (IllegalInstructionException e) {
            myColor = Color.BLACK;
        }
        myThickness = DEFAULT_PEN_THICKNESS;
        
        try {
            myLineBuilder = displayEditor.getPalette().getLineStyle(DEFAULT_LINE_STYLE);
        }
        catch (IllegalInstructionException e) {
            myLineBuilder = new SolidLine();
        }
        try {
        	myImage = displayEditor.getPalette().getImage(ImagePalette.DEFAULT_IMAGE_INDEX);
        }
        catch (IllegalInstructionException e) {
        	myImage = ImagePalette.DEFAULT_IMAGE;
        }
    }
    
    /**
     * Draws all necessary lines between the start and end positions.
     * 
     * @param mag is the distance between the last point and the next point.
     * @param bounds is the dimension of the current workspace.
     */
    public void draw (double mag, Dimension bounds) {
        double distance = mag;
        Location start = new Location(myTurtle.getX(), myTurtle.getY());
        double angle = myTurtle.getAngle();
        if (distance < 0) {
            // We are ignoring checkstyle here since the following operation only 'flips' the
            // distance
            distance = -1 * distance;
            angle += REVERSE_ANGLE_VALUE;
        }
        recursiveLineCreation(distance, start, angle, bounds);
    }

    /**
     * Draws lines for the turtle one pixel at a time.
     * 
     * @param distanceRemaining
     * @param start is the current position
     * @param angle is the direction to draw the lines in
     * @param bounds is the current dimension of the workspace
     */
    private void recursiveLineCreation (double distanceRemaining, Location start, double angle, 
                                        Dimension bounds) {
        
        if (distanceRemaining < 0) {
            return;
        }
        Location end = new Location(start);
        end.translate(new Vector(angle, 1));
        if (!end.tryCorrectingBounds(bounds)) {
            myDisplayEditor.addLine(myLineBuilder.buildLine(start, end, myThickness, myColor));
        }
        recursiveLineCreation(distanceRemaining - 1, end, angle, bounds);
    }
    
    /**
     * Picks the pen up. Disables the drawing of lines.
     */
    public void penOff() {
        int r = myColor.getRed();
        int g = myColor.getGreen();
        int b = myColor.getBlue();
        myColor = new Color(r, g, b, 0);
    }
    
    /**
     * Puts the pen down: enables the drawing of lines.
     */
    public void penOn() {
        int r = myColor.getRed();
        int g = myColor.getGreen();
        int b = myColor.getBlue();
        myColor = new Color(r, g, b, OPAQUE_COLOR);
    }
    
    /**
     * The current color of this pen.
     * 
     * @return The current color of this pen.
     */
    public Color penColor () {
        return myColor;
    }
    
    /**
     * Changes the color of the pen.
     * @param c is the new color of the pen.
     */
    public void changeColor(Color c) {
        myColor = c;
    }
    
    /**
     * Changes the size of the pen.
     * 
     * @param size The diameter of points drawn by the pen.
     */
    public void changeSize(int size) {
        myThickness = size;
    }

    /** 
     * Changes the line style to the passed lineBuilder
     * 
     * @param lb is the LineBuilder to be used to draw lines.
     */
    public void changeLineStyle(LineBuilder lb) {
        myLineBuilder = lb;
    }
    
    public void changeImage(Pixmap picture) {
    	myImage = picture;
    }
    
    public Pixmap getImage() {
    	return myImage;
    }

}
