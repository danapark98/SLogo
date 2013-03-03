package instructions;

import simulation.Model;

public class ConstantInstruction extends BaseInstruction {
   
    /**
     * Eclipse auto-generated ID.
     */
    private static final long serialVersionUID = -2244620866117586663L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    private int myValue;
    
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

}
