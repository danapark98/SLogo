package instructions;

import java.util.Scanner;

import control.Parser;
import simulation.Model;
import exceptions.IllegalInstructionException;

/**
 * Instruction for the turtle to rotate to the left
 * 
 * @author Scott Valentine
 *
 */
public class Left extends BaseInstruction{

    /** keyword that calls this instruction*/
    public final static String KEYWORD = "left";
    
    private double myRotation;
    
    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myRotation = line.nextDouble();
    }

    @Override
    public void execute (Model model) {
       // model.getTurtle().rotate(myRotation);
    }

    /**
     * allows other directions to use this class and access myRotation
     * @return
     */
    protected double getRotations() {
        return myRotation;
    }
}
