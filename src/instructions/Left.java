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

    private Instruction myRotation;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myRotation = nextInstruction(line, parser);
    }

    @Override
    public int execute (Model model) {
        
        int rotation = getRotations().execute(model);
        
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() - rotation);
        
        return rotation;
    }

    /**
     * allows other directions to use this class and access myRotation
     * 
     * @return
     */
    protected Instruction getRotations () {
        return myRotation;
    }
}
