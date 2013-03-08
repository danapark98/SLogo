package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Location;


/**
 * Represents rotation of the turtle to face a specific position as an
 * instruction. Takes two arguments which are the x-coordinate and y-coordinate
 * of the desired position
 * for the turtle should face. This instruction returns the angle (in degrees)
 * the turtle
 * rotates to face the indicated position.<br>
 * <br>
 * <u> Examples:</u> <br>
 * towards 0 0 ---> positions the turtle facing the origin (0,0) <br>
 * towards -10 200 ---> positions the turtle facing the coordinates (-10, 200)
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
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
    
    /**
     * Initializes a rotation to face a given position rotation-instruction.
     */
    public SetTowards () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        Turtle turtle = model.getTurtle();
        Location currentLocation = turtle.getLocationOnCanvas();
        Location givenLocation =
                new Location(nextOperand().execute(model), 
                             nextOperand().execute(model));
        double startAngle = turtle.getAngle();
        double endAngle =
                ANGLE_OFFSET -
                    givenLocation.difference(currentLocation).getDirection();
        turtle.setAngle(endAngle);
        return (int) Math.abs(endAngle - startAngle);
    }

}
