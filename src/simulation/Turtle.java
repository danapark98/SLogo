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

public class Turtle extends Sprite {
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle.png");
    private static final Dimension DEFAULT_SIZE = new Dimension(42, 42);
    private LineAdder myLineAdder;
    private List<ValueText> myTexts;
    private Color penColor;
    
    // ValueText, and Pen
    
    public Turtle (LineAdder la, Dimension bounds) {
        super(DEFAULT_IMAGE, getCenter(bounds),DEFAULT_SIZE);
        myLineAdder = la;
        initTexts();
    }
    
    private void initTexts () {
        myTexts = new ArrayList<ValueText>();
        //TODO: finish this.
        
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
        Line line = new Line();
        myLineAdder.addLine(line);
    }

    private static Location getCenter (Dimension bounds) {
        return new Location(bounds.getWidth()/2, bounds.getHeight()/2);
    }
    
    public void resetTurtle(Dimension bounds) {
    	setCenter(getCenter(bounds));
    }

    
}
