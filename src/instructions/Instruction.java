package instructions;

import exceptions.IllegalInstructionException;
import java.io.Serializable;
import simulation.Model;


/**
 * Represents every user or otherwise generated instruction that acts on the model.
 * <br><br>
 * for instance, if the user types in:
 * fd 50 right 90 fd 90
 * <br>
 * and a CompoundInstruction will be made consisting of the BaseInstructions 
 * (Forward, Rotate, Forward) that will act on the turtle.
 * <br><br>
 * Instruction extends <i>Serializable</i> so that all classes that implement this can
 * be saved and restored.
 * 
 * @author Scott Valentine
 * 
 */
public interface Instruction extends Serializable {

    /**
     * executes this Instruction on the model
     * 
     * i.e. the Instruction Forward would move the turtle forward by the magnitude unique to the
     * instruction
     * 
     * @param model is the model on which this Instruction will execute
     * @throws IllegalInstructionException if instruction cannot be performed
     * (i.e. dividing by zero)
     */
    public int execute (Model model) throws IllegalInstructionException;


}
