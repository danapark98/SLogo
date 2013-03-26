package instructions.extra;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


/**
 * Gives the current user defined variables and functions in the environment.
 * 
 * @author Scott Valentine
 *
 */
public class GetUserVariables extends BaseInstruction {

    /**
     * Auto-Generated ID for I/O
     */
    private static final long serialVersionUID = -2910134302013903018L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Default constructor, makes new instance to be loaded.
     */
    public GetUserVariables() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        String info = model.getEnvironment().customValuesToString();
        model.informView(info);
        return 1;
    }

}
