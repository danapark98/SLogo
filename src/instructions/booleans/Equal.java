package instructions.booleans;

import instructions.Instruction;
import instructions.math.DoubleValueMathInstruction;
import simulation.Model;

public class Equal extends DoubleValueMathInstruction {
    
    public final static String KEYWORD1 = "EQUAL?";
    public final static String KEYWORD2 = "EQUALP";
    
    @Override
    public int execute (Model model) {
        Instruction[] myInstructions = getTwoValues();
        if(myInstructions[0].execute(model) == myInstructions[1].execute(model)){
            return 1;
        }
        return 0;
    }

}
