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
    public int execute (Model model) {
        int rotation = getRotations().execute(model);
        
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() + rotation);
        return rotation;
    }

}
