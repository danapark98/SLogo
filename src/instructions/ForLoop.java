package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class ForLoop extends BaseInstruction {
    public static String KEYWORD = "REPEAT";
    private Instruction myRepeats;
    private Instruction myInstruction;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myRepeats = nextInstruction(line, parser);
        myInstruction = parser.parseList(line);
    }

    @Override
    public int execute (Model model) {     
        int last = 0;
        for (int i = 0; i < myRepeats.execute(model); ++i) {
            last = myInstruction.execute(model);
        }
        return last;
    }

}
