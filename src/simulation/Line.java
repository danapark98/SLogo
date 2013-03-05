package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;

/**
 * Represents a 2d line as a sprite.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Line extends Sprite {
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("line.png");
    private Location myStart;
    private Location myEnd;
    private Color myColor;

    /**
     * Creates a new line based on two points and a color.
     * 
     * @param start is one end of the line.
     * @param end is the other end of the line.
     * @param color is the color of the line.
     */
    public Line (Location start, Location end, Color color) {
        super(DEFAULT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myColor = color;
    }

    /**
     * Calculates the length of the line.
     * 
     * @param start is one end of the line.
     * @param end is the other end of the line.
     * @return The length of this line.
     */
    private static Dimension getSize (Location start, Location end) {
        int width = (int) Math.abs(start.getX() - end.getX());
        int height = (int) Math.abs(start.getY() - end.getY());
        return new Dimension(width, height);
    }

    /**
     * Calculates the midpoint of this line.
     * 
     * @param start is one end of the line.
     * @param end is the other end of the line.
     * @return the location of the midpoint of the line.
     */
    private static Location getCenter (Location start, Location end) {
        return new Location((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(myColor);
        pen.drawLine((int) myStart.getX(), (int) myStart.getY(),
                     (int) myEnd.getX(), (int) myEnd.getY());
    }
}
