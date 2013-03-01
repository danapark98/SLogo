package instructions;

import simulation.Model;


public class IfElse extends BaseInstruction {
    public static final String KEYWORD = "IFELSE";
    private static final int NUMBER_OF_ARGUMENTS = 3;

    @Override
    public int  execute (Model model) {
        int condition = nextOperand().execute(model);
        Instruction trueInstruction = nextOperand();
        Instruction falseInstruction = nextOperand();
        if (condition != 0) {
            return trueInstruction.execute(model);
        }
        else {
            return falseInstruction.execute(model);
        }
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
