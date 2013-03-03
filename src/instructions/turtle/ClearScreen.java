package instructions.turtle;

import simulation.Model;
import instructions.BaseInstruction;

public class ClearScreen extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7588143312248358069L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        model.clearLines();
        return new Home().execute(model);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
