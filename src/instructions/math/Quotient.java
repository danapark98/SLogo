package instructions.math;

import instructions.BaseInstruction;
import exceptions.IllegalInstructionException;
import simulation.Model;

public class Quotient extends BaseInstruction {

    /**
     * 
     */
    private static final long serialVersionUID = -6567230868551176878L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    private static String DIVIDE_BY_ZERO_MESSAGE = "Dividing by zero";
    
    
    @Override
    public int execute (Model model) {
        int val1 = nextOperand().execute(model);
        int val2 = nextOperand().execute(model);
        
        if(val2 == 0){
            try {
                throw new IllegalInstructionException(DIVIDE_BY_ZERO_MESSAGE);
            } catch (IllegalInstructionException e) {
                return 0;
            }
        }
        return val1 / val2;        
    }


    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
