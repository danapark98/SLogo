package instructions.booleans;

import instructions.BaseInstruction;
import simulation.Model;

public class Equal extends BaseInstruction {
    
    /**
     * 
     */
    private static final long serialVersionUID = -9085755361792996511L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    @Override
    public int execute (Model model) {
        if(nextOperand().execute(model) == nextOperand().execute(model)){
            return 1;
        }
        return 0;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
