package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class IfElse extends BaseInstruction {
    private Instruction myValue;
    private Instruction myTrueInstruction;
    private Instruction myFalseInstruction;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myValue = nextInstruction(line, parser);
        
        
        myTrueInstruction = parser.parseList(line);
        myFalseInstruction = parser.parseList(line);
    }

    @Override
    public int  execute (Model model) {
        if (myValue.execute(model) != 0) {
            return myTrueInstruction.execute(model);
        }
        else {
            return myFalseInstruction.execute(model);
        }
    }

}
