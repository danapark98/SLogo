package instructions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import control.Parser;
import exceptions.CorruptedEnvironmentException;
import exceptions.IllegalInstructionException;


/**
 * Represents an instruction definition in the predefined instruction set.
 * 
 * @author Scott Valentine
 * 
 */
public abstract class BaseInstruction implements Instruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2028940084662626878L;
    
    private Iterator<Instruction> myOperands;

    
    /**
     * loads this instruction from a scanner of user input
     * 
     * 
     * @param line - scanner that contains necessary information for this instruction
     * @throws IllegalInstructionException - when the user attempts to call an 
     * instruction that does not exist or has not been defined
     */
    
    public void load (Scanner line, Parser parser)  throws IllegalInstructionException {
        List<Instruction> operands = new ArrayList<Instruction>();
        for (int i = 0; i < getNumberOfArguments(); i++) {
            operands.add(parser.nextInstruction(line));
        }
        myOperands = operands.iterator();
    }
    
    
    public Instruction nextOperand() {
        return myOperands.next();
    }
    
    /**
     * copies this instruction. result is only identical in Class name
     * 
     * @return a copy of this instruction
     */
    
    public BaseInstruction copy () {
        BaseInstruction copy = null;
        try {
            copy = this.getClass().newInstance();
        }
        catch (InstantiationException|IllegalAccessException e) {
            throw new CorruptedEnvironmentException();
        }
        return copy;
    }

    public abstract int getNumberOfArguments();
    
    
    
//    // the number of catch blocks suggests this might be a bad solution.
//    public int getNumberOfArguments() {
//        try {
//            Field number = this.getClass().getDeclaredField("NUMBER_OF_ARGUMENTS");
//            number.setAccessible(true);
//            return number.getInt(this);
//        }
//        catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        catch (SecurityException e) {
//            e.printStackTrace();
//        }
//        catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//        catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }


}
