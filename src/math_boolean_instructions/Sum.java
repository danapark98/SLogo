package math_boolean_instructions;

import instructions.Instruction;
import simulation.Model;


public class Sum extends DoubleValueMathInstruction {
    public static final String KEYWORD = "SUM";
    public static final String KEYWORD1 = "+";

    @Override
    public int execute (Model model) {
        Instruction[] myInstructions = getValues();
        return myInstructions[0].execute(model) + myInstructions[0].execute(model);
    }
    

}
