package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Location;


/**
 * Represents the displacement and rotation of the turtle to its default
 * position (home) as an instruction.
 * Takes zero arguments and returns the distance the turtle traveled.<br>
 * <br>
 * <u> Examples:</u> <br>
 * home ---> places the turtle at (0,0) and facing north (this is a default
 * position). <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Home extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7834890056016622585L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a return to default position instruction.
     */
    public Home () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) {
        Turtle turtle = model.getTurtle();
        Location startLocation = new Location(turtle.getX(), turtle.getY());
        turtle.setCenter(Turtle.startingLocation());
        turtle.setAngle(0);
        Location endLocation = new Location(turtle.getX(), turtle.getY());
        return (int) startLocation.difference(endLocation).getMagnitude();
    }
}
