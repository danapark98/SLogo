package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;

public class ConstantInstruction extends Instruction {
    
    private int myValue;
    
    public ConstantInstruction(int value) {
        myValue = value;
    }
    
    @Override
    public int execute (Model model) {
        return myValue;
    }

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        // should never happen (see parser)
    }

    @Override
    public Instruction copy () {
        // should never have to copy either
        return new ConstantInstruction(myValue);
    }

}
