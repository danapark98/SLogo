package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;
import simulation.Turtle;
import util.Location;
import instructions.BaseInstruction;

public class SetTowards extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1335005859209640256L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    /**
     * Addition required so that angle is relative to vertical axis and 
     * measured clockwise.
     */
    private static final double ANGLE_OFFSET = 270;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        Turtle turtle = model.getTurtle();
        Location currentLocation = turtle.getLocationOnCanvas();
        Location givenLocation = 
                new Location(nextOperand().execute(model), nextOperand().execute(model));
        double startAngle = turtle.getAngle();
        double endAngle = ANGLE_OFFSET - givenLocation.difference(currentLocation).getDirection();
        turtle.setAngle(endAngle);
        return (int) Math.abs(endAngle - startAngle);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
