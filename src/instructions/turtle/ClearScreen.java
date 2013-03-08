package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;


/**
 * Represents clearing the current screen of all lines as an instruction. This
 * does not take any agruments<br>
 * <br>
 * <u> Examples:</u> <br>
 * clearscreen ---> clears screen of all lines <br>
 * cs ---> clears screen of all lines<br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class ClearScreen extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7588143312248358069L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a return to default status instruction.
     */
    public ClearScreen() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) {
        model.clearLines();
        return new Home().execute(model);
    }

}
