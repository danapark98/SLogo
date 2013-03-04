package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import util.Location;
import instructions.BaseInstruction;

public class Home extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7834890056016622585L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        Location startLocation = new Location(turtle.getX(), turtle.getY());
        turtle.setCenter(Turtle.startingLocation());
        turtle.setAngle(0);
        Location endLocation = new Location(turtle.getX(), turtle.getY());
        return (int) startLocation.difference(endLocation).getMagnitude();
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
