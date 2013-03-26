package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents an instruction that changes the diameter of the pen used by a turtle to make lines.<br>
 * <br>
 * <u> Examples:</u> <br>
 * setpensize 10 ---> sets pen of active turtles to diameter of 10 pixels <br>
 * setps -4 ---> throws and illegal instruction exception (can't do any number less than 1) <br>
 * setps 500---> sets pen of active turtles to diameter of 500 pixels
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class SetPenSize extends BaseInstruction {

    private static final String ERROR_MESSAGE = "Size out of bounds";
    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = 7082991607433095482L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    /**
     * Initializes a put pen down instruction.
     */
    public SetPenSize () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {

        int size = nextOperand().execute(model);

        if (size <= 0) throw new IllegalInstructionException(ERROR_MESSAGE);

        model.getTurtle().getPen().changeSize(size);
        return size;
    }

}
