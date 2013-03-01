package instructions.math_booleans;

import instructions.BaseInstruction;
import instructions.Instruction;
import java.util.Scanner;
import control.Parser;
import exceptions.IllegalInstructionException;


/**
 * Represents the math/boolean instructions which take in two values.
 * 
 * EXAMPLES:
 * 
 * and 1 0 ---> 0
 * -----
 * quotient 10 0 ---> throws IllegalInstructionException
 * -----
 * remainder 10 2 ---> 0
 * 
 * @author Scott Valentine
 * 
 */
public abstract class DoubleValueMathInstruction extends BaseInstruction {

    /**
     * message for execute to pass to IllegalInstructionException when instruction tries to divide
     * by 0
     */
    protected static final String DIVIDE_BY_ZERO_MESSAGE = "dividing by 0";

    private Instruction myFirstVal;
    private Instruction mySecondVal;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myFirstVal = nextInstruction(line, parser);
        mySecondVal = nextInstruction(line, parser);
    }

    /**
     * gives the two Instructions that represent the two values of this instruction
     * 
     * @return - an array of length 2 that contains this Instructions 1st value and 2nd value
     *         Instructions
     */
    protected Instruction[] getTwoValues () {
        Instruction[] myInstructs = new Instruction[2];
        myInstructs[0] = myFirstVal;
        myInstructs[1] = mySecondVal;
        return myInstructs;
    }

}
