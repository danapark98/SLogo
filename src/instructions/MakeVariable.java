package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class MakeVariable extends BaseInstruction {

    
    
    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        // load name
        // load value (which is an instruction)
        // put new const  --> Environment

    }

    @Override
    public int execute (Model model) {
        // is definition, so returns default value;
        return 0;
    }

}
