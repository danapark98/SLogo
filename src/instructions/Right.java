package instructions;

import java.util.Scanner;
import simulation.Model;
import exceptions.IllegalInstructionException;

/**
 * Instruction for the turtle to rotate to the right
 * 
 * @author Scott Valentine
 *
 */
public class Right extends Left {

    /** keyword that calls this instruction*/
    public final static String KEYWORD = "right";

    
    @Override
    public void execute (Model model) {
        model.getTurtle.rotate(-this.getRotations());

    }

}
