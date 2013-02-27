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
import view.SLogoView;


public class Turtle extends Sprite {
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle_art.png");
    private static final Dimension DEFAULT_SIZE = new Dimension(42, 42);
    private static final double X_OFFSET = 10;
    private static final double Y_OFFSET = 10;
    private LineAdder myLineAdder;
    private List<ValueText> myStatus;
    private Color myPenColor;

    public Turtle (LineAdder la) {
        super(DEFAULT_IMAGE, getCenter(), DEFAULT_SIZE);
        myLineAdder = la;
        initTexts();
        myPenColor = Color.BLACK;
    }

    private void initTexts () {
        myStatus = new ArrayList<ValueText>();
        // TODO: finish this.

    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);
        updateStatus();
    }

    private void updateStatus () {
        // TODO Auto-generated method stub
    }

    @Override
    public void paint (Graphics2D pen) {
        super.paint(pen);
        paintStatus(pen);
    }

    private void paintStatus (Graphics2D pen) {
        Location textLoc = new Location(X_OFFSET, Y_OFFSET);
        for (ValueText vt:myStatus) {
            textLoc.setLocation(X_OFFSET, textLoc.getY() + Y_OFFSET);
            vt.paint(pen, textLoc, Color.WHITE);
        }
    }

    @Override
    public void translate (Vector v) {
        Location originalLocation = new Location(getX(), getY());
        super.translate(v);
        Location newLocation = new Location(getX(), getY());
        Line line = new Line(originalLocation, newLocation, myPenColor);
        myLineAdder.addLine(line);
    }

    private static Location getCenter () {
        Dimension bounds = SLogoView.PREFERRED_CANVAS_SIZE;
        return new Location(bounds.getWidth() / 2, bounds.getHeight() / 2);
    }

   
    public void resetTurtle () {
        setCenter(getCenter());
        setAngle(0);
    }

    public void changePen (Color color) {
        myPenColor = color;
    }

    public Color getPenColor () {
        return myPenColor;
    }

}
