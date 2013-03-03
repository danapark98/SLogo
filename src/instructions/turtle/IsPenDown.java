package instructions.turtle;

import instructions.booleans.BooleanInstruction;
import simulation.Model;

public class IsPenDown extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 5215411259109944598L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    

    @Override
    public boolean executeBoolean (Model model) {
        return model.getTurtle().getPenColor().getAlpha() != 0;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
