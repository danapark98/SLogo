package instructions;

import java.util.Scanner;

import control.Parser;
import simulation.Model;
import exceptions.IllegalInstructionException;

public class IfElse extends BaseInstruction {
    private int myValue;
    private Instruction myTrueInstruction;
    private Instruction myFalseInstruction;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myValue = line.nextInt();
        myTrueInstruction = parseList(line, parser);
        myFalseInstruction = parseList(line, parser);

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
