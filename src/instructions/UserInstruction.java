package instructions;

import java.util.Scanner;
import control.Parser;
import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * represents a user defined Instruction
 * 
 * @author Scott Valentine
 *
 */
public class UserInstruction extends BaseInstruction {
   
    private Instruction myInstruction;
    private String myKeyword;
    
    public UserInstruction(String keyword) {
        myKeyword = keyword;
    }

    public String keyword() {
        return myKeyword;
    }
    
    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException{
        myInstruction = parser.generateInstruction(line);
    }

    @Override
    public void execute (Model model) {
        myInstruction.execute(model);
    }

    @Override
    public Instruction copy () {
        Instruction instruct = myInstruction.copy();
        UserInstruction copy = new UserInstruction(myKeyword);
        copy.setInstruction(instruct);
        return copy;
    }

    private void setInstruction (Instruction copy) {
        this.myInstruction = copy;
    }

}
