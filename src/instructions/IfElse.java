package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class IfElse extends BaseInstruction {
    private int myValue;
    private Instruction myTrueInstruction;
    private Instruction myFalseInstruction;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myValue = line.nextInt();
        myTrueInstruction = parser.parseList(line);
        myFalseInstruction = parser.parseList(line);
    }

    @Override
    public void execute (Model model) {
        if (myValue != 0) {
            myTrueInstruction.execute(model);
        }
        else {
            myFalseInstruction.execute(model);
        }
    }

}
