package instructions.math;

import instructions.BaseInstruction;
import simulation.Model;


public class Sum extends BaseInstruction {
    /**
     * 
     */
    private static final long serialVersionUID = -191594058546524267L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute (Model model) {
        int arg0 = nextOperand().execute(model);
        int arg1 = nextOperand().execute(model);
        return arg0 + arg1;
        
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
    

}
