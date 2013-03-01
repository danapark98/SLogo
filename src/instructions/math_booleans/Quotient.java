package instructions.math_booleans;

import instructions.Instruction;
import exceptions.IllegalInstructionException;
import simulation.Model;

public class Quotient extends DoubleValueMathInstruction {

    public final static String KEYWORD = "QUOTIENT";
    
    
    @Override
    public int execute (Model model) {
        Instruction[] myInstructions = getTwoValues();
        int val1 = myInstructions[0].execute(model);
        int val2 = myInstructions[1].execute(model);
        
        if(val2 == 0){
            throw new IllegalInstructionException(DIVIDE_BY_ZERO_MESSAGE);
        }
        return val1 / val2;        
    }

}
