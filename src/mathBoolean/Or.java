package mathBoolean;

import instructions.Instruction;
import simulation.Model;

public class Or extends DoubleValueMathInstruction {

    public final static String KEYWORD = "OR";
    
    @Override
    public int execute (Model model) {
        Instruction[] myInstructions = getTwoValues();
        if(myInstructions[0].execute(model) == 1 || myInstructions[1].execute(model) == 1){
            return 1;
        }
        return 0;
    }

}
