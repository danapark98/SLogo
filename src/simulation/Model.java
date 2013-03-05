package simulation;

import instructions.BaseInstruction;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import control.Environment;
import exceptions.IllegalInstructionException;


public class Model implements DisplayEditor {
    private Turtle myTurtle;
    private Collection<Line> myLines;
    private Environment myEnvironment;

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
    
    public void setEnvironment (Environment environment) {
        myEnvironment = environment;
    }
    
    /**
     * adds a new user defined instruction to the environment
     * 
     * @param keyword associated with the instruction for future calls
     * @param unstruction - instruction to be added to the environment
     */
    public void addInstruction(String keyword, BaseInstruction instruction) {
        myEnvironment.addUserDefinedInstruction(keyword, instruction);
    }
    
    /**
     * Gives the Instruction associated with the passed keyword.
     * 
     * @param commandName - the keyword for the instruction
     * @return The Instruction associated with the keyword
     * @throws IllegalInstructionException This occurs when the keyword is not
     *         found in the environment.
     */
    public BaseInstruction getVariableInstruction(String name) throws IllegalInstructionException {
        return myEnvironment.systemInstructionSkeleton(name);
    }
}
