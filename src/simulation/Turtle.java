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


public class Turtle extends Sprite {
    public static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle_art.png");
    public static final Pixmap NO_IMAGE = new Pixmap("blank.png");
    private static final Dimension DEFAULT_SIZE = new Dimension(70, 70);
    private static final String X_LABEL = "X-coordinate:";
    private static final String Y_LABEL = "Y-coordinate:";
    private static final String ANGLE_LABEL = "Angle:";
    private static final double X_OFFSET = 65;
    private static final double Y_OFFSET = 15;
    private DisplayEditor myDisplayEditor;
    private List<ValueText> myStatus;
    private Color myPenColor;

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

    private void updateStatus () {
        Location current = getLocationOnCanvas();
        int x = (int) current.getX();
        int y = (int) current.getY(); 
        int angle = (int) getAngle();
        int[] currentStatus = {x, y, angle};
        
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

    private void paintStatus (Graphics2D pen) {
        Location textLoc = new Location(X_OFFSET, 0);
        for (ValueText vt:myStatus) {
            textLoc.setLocation(X_OFFSET, textLoc.getY() + Y_OFFSET);
            vt.paint(pen, textLoc, Color.BLACK);
        }
    }
    
    public void changePen (Color color) {
        myPenColor = color;
    }

    public Color getPenColor () {
        return myPenColor;
    }

    public static Location startingLocation () {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        return new Location(bounds.getWidth() / 2, bounds.getHeight() / 2);
    }
   
    public Location getLocationOnCanvas() {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        double x = getX() - bounds.getWidth()/2;
        double y = bounds.getHeight()/2 - getY();
        return new Location(x,y);
    }

    @Override
    public void translate (Vector v) {
        drawLines(v.getMagnitude());
        super.translate(v);
        Location location = new Location(getX(), getY());
        location.tryCorrectingBounds(View.PREFERRED_CANVAS_SIZE);
        super.setCenter(location);
    }
    
    private void drawLines(double distance) {
        Location start = new Location(getX(), getY());
        double angle = getAngle();
        if (distance < 0) {
            distance = -1*distance;
            angle += 180;
        }
        recursiveLineCreation(distance, start, angle);
    }

    private void recursiveLineCreation (double distanceRemaining, Location start, double angle) {
        if (distanceRemaining < 0) {
            return;
        }
        Location end = new Location(start);
        end.translate(new Vector(angle, 1));
        if (!end.tryCorrectingBounds(View.PREFERRED_CANVAS_SIZE)){
            myDisplayEditor.addLine(new Line(start, end, myPenColor));
        }
        recursiveLineCreation(distanceRemaining - 1, end, angle);
    }

    private void initStatus () {
        myStatus = new ArrayList<ValueText>();
        myStatus.add(new ValueText(X_LABEL, 0));
        myStatus.add(new ValueText(Y_LABEL, 0));
        myStatus.add(new ValueText(ANGLE_LABEL, 0));
    }

}