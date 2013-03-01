package instructions;

import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


/**
 * represents every user or otherwise generated instruction that acts on the model
 * 
 * for instance, if the user types in:
 * fd 50 right 90 fd 90
 * 
 * a CompoundInstruction will be made consisting of the BaseInstructions (Forward, Rotate, Forward)
 * that will act on the turtle
 * 
 * @author Scott Valentine
 * 
 */
public abstract class Instruction{

    /**
     * executes this Instruction on the model
     * 
     * i.e. the Instruction Forward would move the turtle forward by the magnitude unique to the
     * instruction
     * 
     * @param model is the model on which this Instruction will execute
     */
    
    public abstract int execute (Model model);

    /**
     * loads this instruction from a scanner of user input
     * 
     * 
     * @param line - scanner that contains necessary information for this instruction
     * @throws IllegalInstructionException - when the user attemps to call an instruction that does
     *         not exist or has not been defined
     */
    public abstract void load (Scanner line, Parser parser) throws IllegalInstructionException;

    /**
     * copies this instruction. Level of similarity depends on the subclass.
     * CompoundInstructions and UserInstructions are completely identical
     * BaseInstructions are only identical in Class name
     * 
     * @return a copy of this instruction
     */
    public abstract Instruction copy ();

}
