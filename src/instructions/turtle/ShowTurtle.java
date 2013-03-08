package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;

/**
 * Represents making the turtle visible as an instruction. Takes zero
 * arguments and always returns 1.<br>
 * <br>
 * <u> Examples:</u> <br>
 * showturtle ---> shows turtle on screen if turtle is not already visible. <br>
 * st ---> shows turtle on screen if turtle is not already visible. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class ShowTurtle extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 6890687622386597570L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a display turtle picture instruction.
     */
    public ShowTurtle() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        turtle.setView(Turtle.DEFAULT_IMAGE);
        return 1;
    }
}
