package instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import control.Parser;
import exceptions.CorruptedEnvironmentException;
import exceptions.IllegalInstructionException;


/**
 * Represents an instruction definition in the predefined instruction set. These
 * include: <br>
 * <ul>
 * <li>Turtle actions (forward, left, etc.)
 * <li>Boolean operations (and, or, equal, etc.)
 * <li>Math operations (sum, difference, product, quotient, etc.)
 * <li>User-defined instruction (if, repeat, make instruction, make variable, etc.)
 * </ul>
 * Note that any instruction to be added should in someway extend this class.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public abstract class BaseInstruction implements Instruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2028940084662626878L;
    private ListIterator<Instruction> myOperands;
    private int myNumberOfArguments;

    /**
     * Loads this instruction from a scanner of user input.
     * 
     * 
     * @param line - scanner that contains necessary information for this
     *        instruction
     * @param parser parses passed instructions. This is used for nested instructions.
     * @throws IllegalInstructionException - when the user attempts to call an
     *         instruction that does not exist or has not been defined
     */

    public void load (Scanner line, Parser parser)
                                                  throws IllegalInstructionException {
        List<Instruction> operands = new ArrayList<Instruction>();
        for (int i = 0; i < getNumberOfArguments(); i++) {
            operands.add(parser.nextInstruction(line));
        }
        myOperands = operands.listIterator();
    }

    /**
     * Gives the next operand in the instruction.
     * 
     * @return The next instruction in this instructions operands.
     */
    public Instruction nextOperand () {
        if (!myOperands.hasNext()) {
            resetOperands();
        }
        return myOperands.next();
    }

    /**
     * used when the execute method is called more than once.
     */
    private void resetOperands () {
        while (myOperands.hasPrevious()) {
            myOperands.previous();
        }
    }

    /**
     * Gives a string representing this instruction.
     * 
     * @return The name of this Instruction Class.
     */
    @Override
    public String toString () {

        StringBuilder sb = new StringBuilder();

        // USES REFLECTION
        String fullName = this.getClass().getName();
        String[] nameParts = fullName.split("[.]");
        String className = nameParts[nameParts.length - 1];
        sb.append(className + " ");

        sb.append(operandsToString());

        return sb.toString();
    }

    private String operandsToString () {
        StringBuilder sb = new StringBuilder();
        resetOperands();
        while (myOperands.hasNext()) {
            sb.append(" [ " + nextOperand().toString() + " ] ");
        }

        return sb.toString();
    }

    /**
     * copies this instruction.
     * 
     * @return a copy of this instruction
     */

    public BaseInstruction newCopy () {
        BaseInstruction copy = null;
        try {
            copy = this.getClass().newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            throw new CorruptedEnvironmentException();
        }
        return copy;
    }

    /**
     * Gives the number of arguments a BaseInstruction takes.
     * 
     * @return The number of arguments needed for this instruction.
     */
    public int getNumberOfArguments () {
        return myNumberOfArguments;
    }

    protected void setNumberOfArguments (int numberOfArguments) {
        myNumberOfArguments = numberOfArguments;
    }

}
