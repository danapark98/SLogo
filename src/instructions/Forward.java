package instructions;

import java.util.Scanner;
import simulation.Model;
import simulation.Turtle;
import util.Vector;
import control.Parser;


/**
 * represents the forward instruction
 * 
 * i.e.
 * fd 50 moves the turtle 50 pixels in its current direction
 * 
 * @author Scott Valentine
 * 
 */
public class Forward extends BaseInstruction {

    /** the keyword associated with this instruction for user generated code */
    public static final String KEYWORD = "fd";

    private Instruction myMagnitude;


    @Override
    public void load (Scanner line, Parser parser) {
        myMagnitude = nextInstruction(line, parser);
    }

    @Override
    public int execute (Model model) {
        int mag  = myMagnitude.execute(model);        
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), mag));
        return mag;
    }

}
