package math_boolean_instructions;

import instructions.BaseInstruction;
import instructions.Instruction;
import java.util.Scanner;
import control.Parser;
import exceptions.IllegalInstructionException;

public abstract class DoubleValueMathInstruction extends BaseInstruction {

    protected static final String DIVIDE_BY_ZERO_MESSAGE = "dividing by 0";

    
    private Instruction myFirstVal;
    private Instruction mySecondVal;
    
    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myFirstVal = nextInstruction(line, parser);
        mySecondVal = nextInstruction(line, parser);
    }
    
    protected Instruction[] getValues() {
        Instruction[] myInstructs = new Instruction[2];
        myInstructs[0] = myFirstVal;
        myInstructs[1] = mySecondVal;
        return myInstructs;
    }

}
