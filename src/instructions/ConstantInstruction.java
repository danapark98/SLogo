package instructions;

import simulation.Model;

/**
 * Represents an a constant integer as an instruction.
 * 
 * @author Scott Valentine
 *
 */
public class ConstantInstruction extends BaseInstruction {
   
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2244620866117586663L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    private int myValue;
    
    /**
     * Simply creates an instruction from the constant integer value it represents. 
     * 
     * @param value is the integer this instruction will represent
     */
    public ConstantInstruction(int value) {
        myValue = value;
    }
    
    @Override
    public int execute (Model model) {
        return myValue;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
    
    @Override
    public BaseInstruction newCopy() {
        return new ConstantInstruction(myValue);
    }

}
