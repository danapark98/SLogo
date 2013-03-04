package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import util.Pixmap;
import instructions.booleans.BooleanInstruction;

public class IsShowing extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -3384186474769687332L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public boolean executeBoolean (Model model) {
        Turtle turtle = model.getTurtle();
        Pixmap visible = Turtle.DEFAULT_IMAGE;
        Pixmap current = turtle.getView();
        return visible.toString().equals(current.toString());
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
