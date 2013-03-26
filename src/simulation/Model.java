package simulation;

import control.Controller;
import control.Environment;
import drawing.Background;
import drawing.Palette;
import drawing.StampSprite;
import drawing.lines.Point;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
// import java.util.LinkedList; unimplemented
import view.View;


/**
 * Represents the simulation of the drawings on the screen. The model holds the drawer (the turtle)
 * and all of the lines that have been drawn.
 * 
 * For the model to be ready for use, both its constructor and initialize()
 * need to be called.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Model implements DisplayEditor {

    private static final int DEFAULT_TURTLE_ID = 1;

    // private static final int MAXIMUM_STATES_REMEMBERED = 10; unimplemented


    private State myState;
    // private LinkedList<State> myPreviousStates; unimplemented
    // private LinkedList<State> myUndoneStates; unimplemented

    private View myView;
    private ResourceBundle myResources;

    /**
     * Instantiates a model with a turtle and a collection of lines.
     */
    public Model() {
        myState = new State();
        // myPreviousStates = new LinkedList<State>(); unimplemented
        // myUndoneStates = new LinkedList<State>(); unimplemented

        myState.turtles = new HashMap<Integer, Turtle>();
        myState.lines = new ArrayList<Point>();
        myState.stamps = new ArrayList<StampSprite>();
        
        
    }

    /**
     * Initializes the model with an environment and at least one active turtle.
     * 
     * @return The environment that is initialized in the model.
     */
    public Environment initialize() {
        myState.environment = new Environment(myView.getResources());

        myState.activeTurtle = new Turtle(this);
        myState.turtleID = DEFAULT_TURTLE_ID;
        myState.turtles.put(DEFAULT_TURTLE_ID, myState.activeTurtle);

        myState.background = new Background(getPalette());

        myResources = myView.getResources();
        
        
        return myState.environment;
    }

    /**
     * Sets the view used by the model for painting.
     * 
     * @param view on which the model paints.
     */
    public void setView(View view) {
        myView = view;
    }

    /**
     * Updates all of the elements of model.
     * 
     * @param elapsedTime is the time since the last update.
     * @param bounds is the current bounds of the canvas in the view. (This is the area where the
     *        lines and turtle are displayed).
     */
    public void update(double elapsedTime, Dimension bounds) {
        for (Turtle turt : myState.turtles.values()) {
            turt.update(elapsedTime, bounds);
        }
    }

    /**
     * Paints all current elements (turtles and lines) of the model,
     * and the background layer.
     * 
     * @param pen is the graphic that is used to paint lins and turtles.
     */
    public void paint(Graphics2D pen) {
        myState.background.paint(pen);
        for (StampSprite st : myState.stamps) {
            st.paint(pen);
        }
        for (Turtle t : myState.turtles.values()) {
            t.paint(pen);
        }
        for (Point line : myState.lines) {
            line.paint(pen);
        }
        myState.activeTurtle.paintStatus(pen);
    }

    /**
     * Gives the current active turtle in the model.
     * 
     * @return The active turtle in the model.
     */
    public Turtle getTurtle() {
        return myState.activeTurtle;
    }

    /**
     * Returns ID of current active turtle
     */
    public int getTurtleID() {
        return myState.turtleID;
    }

    /**
     * Switches the active turtle to turtle with the provided ID.
     * @param index is the index to switch the active turtle to.
     */
    public void switchTurtle(int index) {
        if (!myState.turtles.containsKey(index)) {
            myState.turtles.put(index, new Turtle(this));
        }
        myState.activeTurtle = myState.turtles.get(index);
    }

    /**
     * Returns the environment containing instructions, variables, Palette.
     * 
     * @return environment
     */
    public Environment getEnvironment() {
        return myState.environment;
    }

    @Override
    public Palette getPalette() {
        return myState.environment.getPalette();
    }

    @Override
    public void addLine(Point line) {
        myState.lines.add(line);
    }

    @Override
    public void addStamp(StampSprite st) {
        myState.stamps.add(st);
    }

    /**
     * Clears stamps and lines from model.
     */
    public void clear() {
        clearLines();
        clearStamps();
    }

    /**
     * Clears all lines from the model.
     */
    public void clearLines() {
        myState.lines.clear();
    }

    /**
     * Clears all stamps in the current workspace.
     */
    public void clearStamps() {
        myState.stamps.clear();
    }

    /**
     * Returns the background layer so that it can be edited.
     * 
     * @return Background
     */
    public Background getBackground() {
        return myState.background;
    }

    /**
     * Calls the view method to display the result of the command, or an error
     * message back to the user. Appends an indicator string to the beginning
     * to differentiate the result from commands issued by the user.
     * 
     * @param s return message
     */
    public void informView(String s) {
        myView.displayText(myResources.getString(Controller.PRINT_INDICATOR) + s);
    }

    // /**
    // * After execution of an instruction, the current state is copied and stored
    // * onto a stack of previous states, so that the state can be restored at a
    // * later point with undo.
    // *
    // * It is not implemented right now because every object that the
    // * state contains needs to be copied, and we did not have time to write
    // * .copy() methods for each object
    // */
    // public void newState () {
    // State old = myState.copy();
    // if (myPreviousStates.size() > MAXIMUM_STATES_REMEMBERED) {
    // myPreviousStates.removeLast();
    // }
    // myPreviousStates.push(old);
    // }
    //
    // /**
    // * Restores the state to the previous most state on the stack
    // */
    // public void undo () {
    // try {
    // myUndoneStates.push(myState);
    // if (myPreviousStates.isEmpty()) { throw new UndoException(); }
    // myState = myPreviousStates.pop();
    // }
    // catch (UndoException e) {
    // informView(e.toString());
    // }
    //
    // }
    //
    // /**
    // * Restores the state to the last state undone
    // */
    // public void redo () {
    // try {
    // myPreviousStates.push(myState);
    // if (myUndoneStates.isEmpty()) { throw new RedoException(); }
    // myState = myUndoneStates.pop();
    // }
    // catch (RedoException e) {
    // informView(e.toString());
    // }
    // }

    /**
     * Represents all of the state of the simulation. It contains all of the
     * turtles, the active turtle, the lines and stamps on the screen, and the
     * environment for variables and functions.
     * 
     * It is stored as one class so that it can be reverted with undo/redo operations
     * 
     * Class is private so that only Model has access.
     */
    private class State {
        Map<Integer, Turtle> turtles;
        Turtle activeTurtle;
        int turtleID;
        Collection<Point> lines;
        Collection<StampSprite> stamps;
        Environment environment;
        Background background;
    }
    
}
