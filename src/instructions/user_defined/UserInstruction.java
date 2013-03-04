package instructions.user_defined;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;


/**
 * represents a user defined Instruction
 * 
 * @author Scott Valentine
 * 
 */
public class UserInstruction extends BaseInstruction {
    private int myNumberOfArguments;
    private Instruction myInstruction;

    public UserInstruction (int numberOfArgs, Instruction instruction) {
        myNumberOfArguments = numberOfArgs;
        myInstruction = instruction;
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        //TODO: need a way of loading in arguments.
        return myInstruction.execute(model);
    }

    @Override
    public BaseInstruction newCopy () {
        return new UserInstruction(myNumberOfArguments, myInstruction.newCopy());
    }

    @Override
    public int getNumberOfArguments () {
        return myNumberOfArguments;
    }

}
