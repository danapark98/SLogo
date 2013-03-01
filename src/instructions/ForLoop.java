package instructions;

import simulation.Model;


public class ForLoop extends BaseInstruction {
    
    public static String KEYWORD = "REPEAT";
    private static final int NUMBER_OF_ARGUMENTS = 2;


    @Override
    public int execute (Model model) { 
        int numberOfIterations = nextOperand().execute(model);
        Instruction commandsToLoop = nextOperand();
        int last = 0;
        for (int i = 0; i < numberOfIterations; ++i) {
            last = commandsToLoop.execute(model);
        }
        return last;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
