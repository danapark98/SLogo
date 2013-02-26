package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;

public class Sum extends BaseInstruction {
    public static final String KEYWORD = "SUM";
    public static final String KEYWORD1 = "+";
    
    private int myFirstVal;
    private int mySecondVal;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myFirstVal = line.nextInt();
        mySecondVal = line.nextInt();
    }

    @Override
    public void execute (Model model) {
        Integer result = myFirstVal + mySecondVal;
        model.displayMessage(result.toString());
    }

}
