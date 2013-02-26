package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;

public class Turtle extends Sprite {
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle.png");
    private static final Dimension DEFAULT_SIZE = new Dimension(42, 42);
    // ValueText, and Pen
    
    public Turtle (Dimension bounds) {
        super(DEFAULT_IMAGE, getCenter(bounds),DEFAULT_SIZE);
    }
    
    @Override
    public void update(double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);
    }
    
    @Override
    public void paint(Graphics2D pen) {
       super.paint(pen);
    }
    
    @Override
    public void translate(Vector v) {
        Location originalLocation = new Location(this.getX(), this.getY());
        super.translate(v);
        Location newLocation = new Location(this.getX(), this.getY());
        // create new Line
    }

    private static Location getCenter (Dimension bounds) {
        return new Location(bounds.getWidth()/2, bounds.getHeight()/2);
    }
    
    public void resetTurtle(Dimension bounds) {
    	setCenter(getCenter(bounds));
    }

    
}
