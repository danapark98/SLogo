package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;

public class SetHeading extends BaseInstruction { 
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1L;
    private static final int NUMBER_OF_ARGUMENTS = 1;


    @Override
    public int execute (Model model) throws IllegalInstructionException { 
        Turtle turtle = model.getTurtle();
        int originalAngle = (int) turtle.getAngle();
        int absoluteHeading = nextOperand().execute(model);
        turtle.setAngle(absoluteHeading);
        return Math.abs(absoluteHeading - originalAngle);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
