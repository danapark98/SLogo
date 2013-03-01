package mathBoolean;

import instructions.Instruction;
import simulation.Model;

public class NotEqual extends DoubleValueMathInstruction {

    public final static String KEYWORD1 = "NOTEQUAL?";
    public final static String KEYWORD2 = "NOTEQUALP";
    
    @Override
    public int execute (Model model) {
        Instruction[] myInstructions = getTwoValues();
        if(myInstructions[0].execute(model) != myInstructions[1].execute(model)){
            return 1;
        }
        return 0;
    }

}
