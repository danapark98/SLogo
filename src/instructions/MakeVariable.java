package instructions;

import java.util.Scanner;
import control.Parser;
import simulation.Model;


public class MakeVariable extends BaseInstruction {
    public static String KEYWORD = "MAKE";
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public void load(Scanner line, Parser parser) {
        //TODO: will probably have to do something special here.
    }
    
    @Override
    public int execute (Model model) {
        nextOperand();
        return nextOperand().execute(model);
    }
    
    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
