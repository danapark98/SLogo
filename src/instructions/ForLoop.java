package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class ForLoop extends BaseInstruction {
    public static String KEYWORD = "REPEAT";
    private int myRepeats;
    private Instruction myInstruction;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myRepeats = line.nextInt();
        myInstruction = parser.parseList(line);
    }

    @Override
    public void execute (Model model) {
        for (int i = 0; i < myRepeats; ++i) {
            myInstruction.execute(model);
        }
    }

}
