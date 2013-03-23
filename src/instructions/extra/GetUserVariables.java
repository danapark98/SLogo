package instructions.extra;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

public class GetUserVariables extends BaseInstruction {

    /**
     * Auto-Generated ID for I/O
     */
    private static final long serialVersionUID = -2910134302013903018L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    public GetUserVariables(){
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        String info = model.getEnvironment().customValuesToString(model);
        model.informView(info);
        return 1;
    }

}
