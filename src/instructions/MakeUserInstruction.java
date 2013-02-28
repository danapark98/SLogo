package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class MakeUserInstruction extends BaseInstruction {
    private String myCommandName;

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        // get name
        // get list of variables
        // get list of instructions
        // make new UserInstruction
        // add UserInstruction to environment
    }

    @Override
    public int execute (Model model) {
        // default return value (this instruction only defines something)
        return 0;
    }

}
