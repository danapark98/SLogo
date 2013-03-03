package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;
import simulation.Turtle;
import util.Location;
import instructions.BaseInstruction;

public class SetPosition extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -7480055529053641527L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        Turtle turtle = model.getTurtle();
        Location start = new Location(turtle.getX(), turtle.getY());
        Location centerOfScreen = Turtle.startingLocation();
        Location end = new Location(centerOfScreen.getX() + nextOperand().execute(model),
                centerOfScreen.getY() - nextOperand().execute(model)); 
        turtle.setCenter(end);
        return (int) start.difference(end).getMagnitude();
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
