package instructions.turtle;

import java.awt.Color;
import simulation.Model;
import simulation.Turtle;
import instructions.BaseInstruction;

public class PenUp extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -45623041636717507L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        // set 0 for alpha value so that it is invisible.
        turtle.changePen(new Color(0, 0, 0, 0));
        return 0;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
