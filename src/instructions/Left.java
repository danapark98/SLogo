package instructions;

import java.util.Scanner;
import simulation.Model;
import simulation.Turtle;
import control.Parser;
import exceptions.IllegalInstructionException;


/**
 * Instruction for the turtle to rotate to the left
 * 
 * @author Scott Valentine
 * 
 */
public class Left extends BaseInstruction {

    /** keyword that calls this instruction */
    public static final String KEYWORD = "left";

    private double myRotation;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myRotation = line.nextDouble();
    }

    @Override
    public void execute (Model model) {
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() - getRotations());
        model.displayMessage(getRotations() + "");
    }

    /**
     * allows other directions to use this class and access myRotation
     * 
     * @return
     */
    protected double getRotations () {
        return myRotation;
    }
}
