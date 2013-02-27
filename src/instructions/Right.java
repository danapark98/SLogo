package instructions;

import simulation.Model;
import simulation.Turtle;


/**
 * Instruction for the turtle to rotate to the right
 * 
 * @author Scott Valentine
 * 
 */
public class Right extends Left {

    /** keyword that calls this instruction */
    public static final String KEYWORD = "right";

    @Override
    public void execute (Model model) {
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() - getRotations());
    }

}
