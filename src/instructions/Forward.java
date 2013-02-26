package instructions;

import java.util.Scanner;

import control.Parser;
import simulation.Model;
import simulation.Turtle;

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
    
    /** the keyword associated with this instruction for user generated code*/
    public static String KEYWORD = "fd";

    private int myMagnitude;

    public Forward () {
        myMagnitude = 0;
    }

    @Override
    public void load (Scanner line, Parser parser) {
        myMagnitude = (int) line.nextDouble();
    }

    @Override
    public void execute (Model model) {
        Turtle turtle = model.getTurtle();
        //turtle.moveForward(myMagnitude);
    }


}
