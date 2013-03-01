package instructions;

import simulation.Model;
import simulation.Turtle;
import util.Vector;


public abstract class Translate extends BaseInstruction {

    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) {
        int magnitude = getMagnitude(model);
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), magnitude));
        return Math.abs(magnitude);
    }

    public abstract int getMagnitude (Model model);
    
    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
