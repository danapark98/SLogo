package instructions.math;

import instructions.BaseInstruction;
import exceptions.IllegalInstructionException;
import simulation.Model;

public class Quotient extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6567230868551176878L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    
    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int arg0 = nextOperand().execute(model);
        int arg1 = nextOperand().execute(model);
        
        if(arg1 == 0){
            throw new IllegalInstructionException(IllegalInstructionException.DIVIDE_BY_ZERO);
        }
        return arg0 / arg1;        
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
