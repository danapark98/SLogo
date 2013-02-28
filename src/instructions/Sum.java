package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class Sum extends BaseInstruction {
    public static final String KEYWORD = "SUM";
    public static final String KEYWORD1 = "+";

    private Instruction myFirstVal;
    private Instruction mySecondVal;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myFirstVal = nextInstruction(line, parser);
        mySecondVal = nextInstruction(line, parser);
    }

    @Override
    public int execute (Model model) {
        return myFirstVal.execute(model) + mySecondVal.execute(model);
    }
    

}
