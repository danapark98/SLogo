package instructions.user_defined;

import control.Parser;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import java.util.Scanner;
import simulation.Model;


/**
 * Represents the definition of a custom variable as an instruction. Takes
 * two
 * arguments: what to name the custom variable and the value the variable will
 * take.
 * This instruction will always return the value of the variable. <br>
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
    private static final int NUMBER_OF_ARGUMENTS = 2;
    private String myName;
    private Instruction myArgument;
    
    /**
     * Initializes the definition of a custom variable.
     */
    public MakeVariable () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    
    @Override
    public void load(Scanner line, Parser parser) throws IllegalInstructionException {
        myName = line.next();
        myArgument = parser.nextInstruction(line);
    }
    
    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int variableValue = myArgument.execute(model);
        BaseInstruction variable = new ConstantInstruction(variableValue);
        model.getEnvironment().addInstruction(myName, variable);
        return variableValue;
    }
}
