package instructions;

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
    public int execute (Model model) {
        //TODO: need a way of loading in arguments.
        return myInstruction.execute(model);
    }

    @Override
    public Instruction copy () {
        return new UserInstruction(myNumberOfArguments, myInstruction.copy());
    }

    @Override
    public int getNumberOfArguments () {
        return myNumberOfArguments;
    }

}
