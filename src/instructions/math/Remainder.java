package instructions.math;

import exceptions.IllegalInstructionException;
import simulation.Model;

public class Remainder extends DoubleValueMathInstruction {

    /**
     * 
     */
    private static final long serialVersionUID = -4013823908746631449L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    @Override
    public int execute (Model model) {
        
        int val1 = nextOperand().execute(model);
        int val2 = nextOperand().execute(model);
        
        if(val2 == 0){
            try {
                throw new IllegalInstructionException(DIVIDE_BY_ZERO_MESSAGE);
            } catch (IllegalInstructionException e) {
                // TODO Auto-generated catch block
                return 0;
            }
        }
        return val1 % val2;  
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
