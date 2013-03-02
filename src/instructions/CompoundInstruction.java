package instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import simulation.Model;
import control.Parser;


/**
 * represents a list of predefined instructions as one whole instruction
 * 
 * @author Scott Valentine
 * 
 */
public class CompoundInstruction extends Instruction {

    /**
     * 
     */
    private static final long serialVersionUID = 8327060009397874139L;
    /** list of base instructions this is made of */
    private List<Instruction> myInstructions;

    /**
     * default constructor initializes the list
     */
    public CompoundInstruction () {
        myInstructions = new ArrayList<Instruction>();
    }

    @Override
    public int execute (Model model) {
        int lastReturn = 0;
        for (Instruction i : myInstructions) {
            lastReturn = i.execute(model);
        }
        return lastReturn;
    }

    /**
     * adds a new instruction to the list of instructions
     * 
     * @param instruction - instruction to be added to this
     */
    public void add (Instruction instruction) {
        myInstructions.add(instruction);
    }

    @Override
    @Deprecated
    public void load (Scanner line, Parser parser) {
        // do nothing, can't load a compound instruction directly
        return;
    }

    @Override
    public Instruction copy () {
        CompoundInstruction copy = new CompoundInstruction();
        for (Instruction i : myInstructions) {
            copy.add(i);
        }
        return copy;
    }

}
