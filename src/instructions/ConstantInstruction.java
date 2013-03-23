package instructions;

import simulation.Model;


/**
 * Represents an a constant integer as an instruction.<br>
 * <br>
 * For example, the number 5 would be an instruction that would do nothing but
 * return 5 when executed.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
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
     * Creates an instruction from the constant integer value it
     * represents.
     * 
     * @param value is the integer this instruction will represent.
     */
    public ConstantInstruction (int value) {
        myValue = value;
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) {
        return myValue;
    }

    @Override
    public BaseInstruction newCopy () {
        return new ConstantInstruction(myValue);
    }
    
    @Override
    public String toString() {
        return "" + myValue;
    }

}
