package simulation;

import control.Environment;
import drawing.Palette;
import drawing.StampSprite;
import drawing.lines.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Represents the simulation of the drawings on the screen. The model holds the drawer (the turtle)
 * and all of the lines that have been drawn.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Model implements DisplayEditor {

    private Collection<Turtle> myTurtles;
    private Collection<Turtle> myActiveTurtles;
    private Turtle myTurtle;
    private Collection<Point> myLines;
    private Collection<StampSprite> myStamps;
    private Environment myEnvironment;

    /**
     * Instantiates a model with a turtle and a collection of lines.
     */
    public Model () {

        myTurtles = new ArrayList<Turtle>();

        myEnvironment = new Environment();
        myTurtle = new Turtle(this);

        myTurtles.add(myTurtle);

        myLines = new ArrayList<Point>();
        myStamps = new ArrayList<StampSprite>();
    }

    /**
     * Updates all of the elements of model.
     * 
     * @param elapsedTime is the time since the last update.
     * @param bounds is the current bounds of the canvas in the view. (This is the area where the
     *        lines and turtle are displayed).
     */
    public void update (double elapsedTime, Dimension bounds) {

        for (Turtle turt : myTurtles) {
            turt.update(elapsedTime, bounds);
        }
    }

    /**
     * Paints all current elements (turtles and lines) of the model.
     * 
     * @param pen is the graphic that is used to paint lins and turtles.
     */
    public void paint (Graphics2D pen) {
        // myTurtle.paint(pen);

        for (Turtle t : myTurtles) {
            t.paint(pen);
        }

        for (Point line : myLines) {
            line.paint(pen);
        }
        for (StampSprite st : myStamps) {
            st.paint(pen);
        }
    }

    /**
     * Clears stamps and lines from model.
     */
    public void clear () {
        clearLines();
        clearStamps();
    }

    @Override
    public void addLine (Point line) {
        myLines.add(line);
    }

    /**
     * Adds a stamp of the turtle.
     * 
     * @param st Stamp to be added.
     */
    public void addStamp (StampSprite st) {
        myStamps.add(st);
    }

    /**
     * Clears all stamps in the current workspace.
     */
    public void clearStamps () {
        myStamps.clear();
    }

    /**
     * Clears all lines from the model.
     */
    public void clearLines () {
        myLines.clear();
    }

    /**
     * Gives the current active turtle in the model.
     * 
     * @return The active turtle in the model.
     */
    public Turtle getTurtle () {
        // TODO: update so that this returns active turtles or first turtle or something
        return myTurtle;
    }

    /**
     * Sets the environment that contains necessary instruction information.
     * 
     * @param environment is environment that contains all relevant instruction information.
     */
    public void setEnvironment (Environment environment) {
        myEnvironment = environment;
    }

    /**
     * Returns the environment containing instructions, variables, Palette.
     * 
     * @return environment
     */
    public Environment getEnvironment () {
        return myEnvironment;
    }

    /**
     * Gives the current Palette is use in the current environment
     * 
     * @return
     */
    public Palette getPalette () {
        return myEnvironment.getPalette();
    }

}
