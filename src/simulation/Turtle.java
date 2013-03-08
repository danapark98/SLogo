package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.ValueText;
import util.Vector;
import view.View;


/**
 * Represents the turtle displayed on screen which paints lines.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Turtle extends Sprite {
    /** The picture of that represents the turtle on the screen. */
    public static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle_art.png");
    /** The picture that represents the invisible turtle. */
    public static final Pixmap NO_IMAGE = new Pixmap("blank.png");
    private static final Dimension DEFAULT_SIZE = new Dimension(70, 70);
    private static final String X_LABEL = "X:";
    private static final String Y_LABEL = "Y:";
    private static final String ANGLE_LABEL = "\u03f4" + ":";
    private static final double X_OFFSET = 25;
    private static final double Y_OFFSET = 15;
    private static final int REVERSE_ANGLE_VALUE = 180;
    private DisplayEditor myDisplayEditor;
    private List<ValueText> myStatus;
    private Color myPenColor;

    /**
     * Creates a new instance of turtle by passing it a DisplayEditor to draw lines with.
     * 
     * @param la is a DisplayEditor that draws lines.
     */
    public Turtle (DisplayEditor la) {
        super(DEFAULT_IMAGE, startingLocation(), DEFAULT_SIZE);
        myDisplayEditor = la;
        initStatus();
        myPenColor = Color.BLACK;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);
        updateStatus();
    }

    /**
     * Updates the Value text which displays information about the turtle's current position.
     */
    private void updateStatus () {
        Location current = getLocationOnCanvas();
        int x = (int) current.getX();
        int y = (int) current.getY();
        int angle = (int) getAngle();
        int[] currentStatus = { x, y, angle };

        for (int i = 0; i < myStatus.size(); i++) {
            ValueText vt = myStatus.get(i);
            vt.resetValue();
            vt.updateValue(currentStatus[i]);
        }
    }

    @Override
    public void paint (Graphics2D pen) {
        super.paint(pen);
        paintStatus(pen);
    }

    /**
     * Paints the value text which represents the turtle's position (x, y, heading).
     * 
     * @param pen is the graphic that the value text paints with.
     */
    private void paintStatus (Graphics2D pen) {
        Location textLoc = new Location(X_OFFSET, 0);
        for (ValueText vt : myStatus) {
            textLoc.setLocation(X_OFFSET, textLoc.getY() + Y_OFFSET);
            vt.paint(pen, textLoc, Color.BLACK);
        }
    }

    /**
     * Changes the color of the turtle's lines.
     * 
     * @param color is the new color of the turtle's lines.
     */
    public void changePen (Color color) {
        myPenColor = color;
    }

    /**
     * Gives the current color of the turtle's lines
     * 
     * @return the current color of the turtle's lines.
     */
    public Color getPenColor () {
        return myPenColor;
    }

    /**
     * Calculates the location of the center of the screen (the starting location) based on the size
     * of the canvas
     * 
     * @return The location at the center of the canvas.
     */
    public static Location startingLocation () {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        return new Location(bounds.getWidth() / 2, bounds.getHeight() / 2);
    }

    /**
     * Calculates the current position of the turtle as a location with (0,0) at the center of the
     * canvas.
     * 
     * @return The current location of the turtle in the coordinate system with the origin at the
     *         center of the canvas.
     */
    public Location getLocationOnCanvas () {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        double x = getX() - bounds.getWidth() / 2;
        double y = bounds.getHeight() / 2 - getY();
        return new Location(x, y);
    }

    /**
     * Moves the turtle by the provided magnitude, and also draws the lines for
     * the turtle's path.
     */
    @Override
    public void translate (Vector v) {
        drawLines(v.getMagnitude());
        super.translate(v);
        Location location = new Location(getX(), getY());
        location.tryCorrectingBounds(View.PREFERRED_CANVAS_SIZE);
        super.setCenter(location);
    }

    /**
     * Draws all necessary lines between the start and end positions.
     * 
     * @param distance is the distance between the last point and the next point.
     */
    private void drawLines (double distance) {
        Location start = new Location(getX(), getY());
        double angle = getAngle();
        if (distance < 0) {
            // We are ignoring checkstyle here since the following operation only 'flips' the
            // distance
            distance = -1 * distance;
            angle += REVERSE_ANGLE_VALUE;
        }
        recursiveLineCreation(distance, start, angle);
    }

    /**
     * Draws lines for the turtle one pixel at a time.
     * 
     * @param distanceRemaining
     * @param start is the current position
     * @param angle is the direction to draw the lines in
     */
    private void recursiveLineCreation (double distanceRemaining, Location start, double angle) {
        if (distanceRemaining < 0) return;
        Location end = new Location(start);
        end.translate(new Vector(angle, 1));
        if (!end.tryCorrectingBounds(View.PREFERRED_CANVAS_SIZE)) {
            myDisplayEditor.addLine(new Line(start, end, myPenColor));
        }
        recursiveLineCreation(distanceRemaining - 1, end, angle);
    }

    /**
     * Initiates the value texts that represent the x-coordinate, y-coordinate, and heading of the
     * turtle.
     */
    private void initStatus () {
        myStatus = new ArrayList<ValueText>();
        myStatus.add(new ValueText(X_LABEL, 0));
        myStatus.add(new ValueText(Y_LABEL, 0));
        myStatus.add(new ValueText(ANGLE_LABEL, 0));
    }

}
