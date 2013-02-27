package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import view.SLogoView;


public class Model implements LineAdder {
    private SLogoView myView;
    private Turtle myTurtle;
    private Collection<Line> myLines;

    public Model (SLogoView view) {
        myView = view;
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


    public void displayMessage (String s) {
        myView.displayText(s);
    }
}
