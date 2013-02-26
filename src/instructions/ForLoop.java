package instructions;

import java.util.Scanner;

import control.Parser;
import simulation.Model;
import exceptions.IllegalInstructionException;

public class ForLoop extends BaseInstruction {
    public static String KEYWORD = "REPEAT";
    private int myRepeats;
    private Instruction myInstruction;
    
    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myRepeats = line.nextInt();
        myInstruction = parseList(line, parser);
    }

    @Override
    public void execute (Model model) {
        for(int i = 0; i < myRepeats; ++i){
            myInstruction.execute(model);
        }
    }

}
