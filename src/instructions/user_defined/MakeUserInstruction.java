package instructions.user_defined;

import java.util.Scanner;
import control.Parser;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


public class MakeUserInstruction extends BaseInstruction {

   
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 4760595117209610147L;
    private String myCommandName;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public void load(Scanner line, Parser parser) throws IllegalInstructionException {
        myCommandName = line.next();
        
        super.load(line, parser);
    }

    @Override
    public int execute (Model model) {
        
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }


}
