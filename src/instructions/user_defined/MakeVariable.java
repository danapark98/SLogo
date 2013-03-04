package instructions.user_defined;

import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import java.util.Scanner;
import control.Parser;
import exceptions.IllegalInstructionException;
import simulation.Model;


public class MakeVariable extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1453503090469524379L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    private String myName;
    private Instruction myArgument;
    

    @Override
    public void load(Scanner line, Parser parser) throws IllegalInstructionException {
        myName = line.next();
        myArgument = parser.nextInstruction(line);
    }
    
    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int variableValue = myArgument.execute(model);
        BaseInstruction variable = new ConstantInstruction(variableValue);
        model.addInstruction(myName, variable);
        return variableValue;
    }
    
    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
