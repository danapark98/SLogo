package instructions.user_defined;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;


/**
 * Represents a user defined Instruction. TODO: complete this java doc
 * 
 * @author Scott Valentine
 * 
 */
public class UserInstruction extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1776254460831303292L;
    private int myNumberOfArguments;
    private Instruction myInstruction;

    /**
     * Creates a new UserInstruction from the number of 
     * parameters it takes and how it acts on those parameters.
     * 
     * @param numberOfArgs is the number of arguments this instruction takes.
     * @param instruction - the instruction that executes based on these arguments.
     */
    public UserInstruction(int numberOfArgs, Instruction instruction) {
        myNumberOfArguments = numberOfArgs;
        myInstruction = instruction;
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        // TODO: need a way of loading in arguments.
        return myInstruction.execute(model);
    }

    @Override
    public BaseInstruction newCopy() {
        return new UserInstruction(myNumberOfArguments, myInstruction.newCopy());
    }

    @Override
    public int getNumberOfArguments() {
        return myNumberOfArguments;
    }

}
