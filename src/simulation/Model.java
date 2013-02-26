package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import view.View;

public class Model {
    private View myView;
    private Turtle myTurtle;
    private Collection<Line> myLines;
    
    public Model(View view) {
        myView = view;
        myTurtle = new Turtle(myView.PREFERRED_CANVAS_SIZE);
        myLines = new ArrayList<Line>();
    }
    
    public void update (double elapsedTime, Dimension bounds) {
        myTurtle.update(elapsedTime, bounds);
    }
    
    public void paint(Graphics2D pen) {
        myTurtle.paint(pen);
        for (Line line : myLines) {
            line.paint(pen);
        }
    }
    
    public void addLine(Line line) {
        myLines.add(line);
    }
    
    public Turtle getTurtle() {
        return myTurtle;
    }
    
    public void reset() {
    	myTurtle.resetTurtle(myView.PREFERRED_CANVAS_SIZE);
    	myLines.clear();
    }
}
