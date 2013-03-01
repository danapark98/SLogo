package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;


public class Model implements DisplayEditor {
    private Turtle myTurtle;
    private Collection<Line> myLines;

    public Model () {
        myTurtle = new Turtle(this);
        myLines = new ArrayList<Line>();
    }

    public void update (double elapsedTime, Dimension bounds) {
        myTurtle.update(elapsedTime, bounds);
    }

    public void paint (Graphics2D pen) {
        myTurtle.paint(pen);
        for (Line line : myLines) {
            line.paint(pen);
        }
    }

    @Override
    public void addLine (Line line) {
        myLines.add(line);
    }
    
    public void clearLines() {
        myLines.clear();
    }
    
    public Turtle getTurtle () {
        return myTurtle;
    }
}
