package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import util.Vector;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

public abstract class Translate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -8721896768117828518L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int magnitude = getMagnitude(model);
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), magnitude));
        return nextOperand().execute(model);
    }

    protected abstract int getMagnitude (Model model) throws IllegalInstructionException;

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
