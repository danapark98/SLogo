package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


/**
 * represents a user defined Instruction
 * 
 * @author Scott Valentine
 * 
 */
public class UserInstruction extends BaseInstruction {

    private Instruction myInstruction;
    private String myKeyword;

    public UserInstruction (String keyword) {
        myKeyword = keyword;
    }

    public String keyword () {
        return myKeyword;
    }

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myInstruction = parser.generateInstruction(line);
    }

    @Override
    public int execute (Model model) {
        return myInstruction.execute(model);
    }

    @Override
    public Instruction copy () {
        Instruction instruct = myInstruction.copy();
        UserInstruction copy = new UserInstruction(myKeyword);
        copy.setInstruction(instruct);
        return copy;
    }

    public void setInstruction (Instruction copy) {
        myInstruction = copy;
    }

}
