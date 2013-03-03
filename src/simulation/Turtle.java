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
    private static final double X_OFFSET = 60;
    private static final double Y_OFFSET = 15;
    private DisplayEditor myLineAdder;
    private List<ValueText> myStatus;
    private Color myPenColor;

    public Turtle (DisplayEditor la) {
        super(DEFAULT_IMAGE, startingLocation(), DEFAULT_SIZE);
        myLineAdder = la;
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
    
    public Location getLocationOnCanvas() {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        double x = getX() - bounds.getWidth()/2;
        double y = bounds.getHeight()/2 - getY();
        return new Location(x,y);
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

    public static Location startingLocation () {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        return new Location(bounds.getWidth() / 2, bounds.getHeight() / 2);
    }
    
    @Override
    public void translate (Vector v) {
        Location startLocation = new Location(getX(), getY());
        super.translate(v);
        Location endLocation = new Location(getX(), getY());
        Line line = new Line(startLocation, endLocation, myPenColor);
        myLineAdder.addLine(line);
        fixCenter();
    }

//    @Override
//    public void translate (Vector v) {
//        createLines(v.getMagnitude(), new Location(getX(), getY()));
//        super.translate(v);
//        fixCenter();
//    }
//
//    private void createLines (double distanceRemaining, Location start) {
//        if (distanceRemaining < 0) {
//            return;
//        }
//        Location end = new Location(start);
//        end.translate(new Vector(getAngle(), 1));
//        myLineAdder.addLine(new Line(start, end, myPenColor));
//        createLines(distanceRemaining - 1, end);
//    }
    
    private void fixCenter () {
        Dimension bounds = View.PREFERRED_CANVAS_SIZE;
        double width = bounds.getWidth();
        double height = bounds.getHeight();
        double x = getX();
        double y = getY();
        while (x < 0) {
            x += width;
        }
        while (y < 0) {
            y += height;
        }
        x = x%width;
        y = y%height;
        super.setCenter(x, y);
    }
    
    public void changePen (Color color) {
        myPenColor = color;
    }

    public Color getPenColor () {
        return myPenColor;
    }

    private void initStatus () {
        myStatus = new ArrayList<ValueText>();
        myStatus.add(new ValueText(X_LABEL, 0));
        myStatus.add(new ValueText(Y_LABEL, 0));
        myStatus.add(new ValueText(ANGLE_LABEL, 0));
    }

}
