package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

public abstract class Rotate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -8001794506937082081L;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int rotation = getRotation(model);        
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() + rotation);
        return nextOperand().execute(model);
    }

    protected abstract int getRotation (Model model) throws IllegalInstructionException;
}
