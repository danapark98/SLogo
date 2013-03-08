package instructions;

import exceptions.IllegalInstructionException;
import java.util.ArrayList;
import java.util.List;
import simulation.Model;


/**
 * Represents a list of instructions as one whole instruction.<br>
 * <br>
 * For example, when a user inputs <i> fd 50 rt 90 fd 50 rt 90 </i>, a compound
 * instruction is created consisting of
 * the instructions for <i>forward 50, right 90, forward 50,</i> and <i>right
 * 90.</i>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class CompoundInstruction implements Instruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 8327060009397874139L;
    /** list of base instructions this is made of */
    private List<Instruction> myInstructions;

    /**
     * Creates a new empty CompoundInstruction.
     */
    public CompoundInstruction() {
        myInstructions = new ArrayList<Instruction>();
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int lastReturn = 0;
        for (Instruction i : myInstructions) {
            lastReturn = i.execute(model);
        }
        return lastReturn;
    }

    /**
     * Adds a new instruction to the list of instructions.
     * 
     * @param instruction to be added to this compound instruction.
     */
    public void add(Instruction instruction) {
        myInstructions.add(instruction);
    }
    
    public int getSize() {
        return myInstructions.size();
    }
    
    public Instruction getInstruction (int index) {
        return myInstructions.get(index);
    }
}
