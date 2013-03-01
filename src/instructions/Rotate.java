package instructions;

import simulation.Model;
import simulation.Turtle;

public abstract class Rotate extends BaseInstruction {
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) {
        int rotation = getRotation(model);        
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() + rotation);
        return Math.abs(rotation);
    }
    
    public abstract int getRotation(Model model);
    
    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
