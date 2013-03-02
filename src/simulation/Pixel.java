package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;

public class Pixel extends Sprite {
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("line.png");
    private Color myColor;
    
    public Pixel(double x, double y, Color color) {
        super(DEFAULT_IMAGE, new Location(x,y), new Dimension(1,1));
        myColor = color;
    }
    
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(myColor);
        pen.drawRect((int) getX(), (int) getY(), 1, 1);
    }
}
