package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Location;


/**
 * Represents translation of the turtle to a specific position as an
 * instruction. Takes
 * two
 * arguments which are the x-coordinate and y-coordinate of the desired position
 * of the turtle. This instruction returns this distance (in pixels) the turtle
 * moves to complete this translation.<br>
 * <br>
 * <u> Examples:</u> <br>
 * setxy 0 0 ---> positions the turtle at the origin (0,0) <br>
 * setxy -10 200 ---> positions the turtle at the coordinates (-10, 200)
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class SetPosition extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -7480055529053641527L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    /**
     * Initializes a movement to given position translation instruction.
     */
    public SetPosition() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        Turtle turtle = model.getTurtle();
        Location start = new Location(turtle.getX(), turtle.getY());
        Location centerOfScreen = Turtle.startingLocation();
        Location end =
                new Location(centerOfScreen.getX() +
                             nextOperand().execute(model),
                             centerOfScreen.getY() -
                                     nextOperand().execute(model));
        turtle.setCenter(end);
        return (int) start.difference(end).getMagnitude();
    }
}
