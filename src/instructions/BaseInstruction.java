package instructions;

import java.util.Scanner;
import control.Parser;
import exceptions.IllegalInstructionException;


/**
 * represents an instruction definition in the predefined instruction set
 * 
 * @author Scott Valentine
 * 
 */
public abstract class BaseInstruction extends Instruction {

    /**
     * loads the base instruction from a scanner of user input
     * 
     * @param line - scanner from which information is read
     * @throws IllegalInstructionException
     */
    @Override
    public abstract void load (Scanner line, Parser parser) throws IllegalInstructionException;

    @Override
    public Instruction copy () {
        Instruction copy = null;
        try {
            copy = this.getClass().newInstance();
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }

}
