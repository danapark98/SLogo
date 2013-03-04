package instructions.user_defined;

import control.Parser;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import java.util.Scanner;
import simulation.Model;


/**
 * Represents the definition of a custom variable as an instruction. Takes
 * two
 * arguments: what to name the custom variable and the value the variable will
 * take.
 * This instruction will always return TODO. <br>
 * <br>
 * <u> Examples:</u> <br>
 * <i>make var1 10 </i> ---> The variable <i> var1 </i> now contains the value
 * 10. <br>
 * <i>make var2 [ sum var1 10 ] </i> ---> The variable <i> var2 </i> now
 * contains the value of <i> var1 </i> + 10 = 20. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class MakeVariable extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1453503090469524379L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    private String myName;

    @Override
    public void load(Scanner line, Parser parser)
        throws IllegalInstructionException {
        myName = line.next();
        super.load(line, parser);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int variableValue = nextOperand().execute(model);
        BaseInstruction variable = new ConstantInstruction(variableValue);
        model.addInstruction(myName, variable);
        return variableValue;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
