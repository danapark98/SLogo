package instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import control.Parser;
import simulation.Model;


/**
 * represents a list of predefined instructions as one whole instruction
 * 
 * @author Scott Valentine
 * 
 */
public class CompoundInstruction extends Instruction {

    /** list of base instructions this is made of */
    private List<Instruction> myInstructions;

    /**
     * default constructor initializes the list
     */
    public CompoundInstruction () {
        myInstructions = new ArrayList<Instruction>();
    }

    @Override
    public void execute (Model model) {
        for (Instruction i : myInstructions) {
            i.execute(model);
        }
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
    public void load (Scanner line, Parser parser) {
        // do nothing, can't load a compound instruction directly
        // TODO: find good way to get rid of this method
    }

    @Override
    public Instruction copy () {
        CompoundInstruction copy = new CompoundInstruction();
        for(Instruction i: myInstructions) {
            copy.add(i);
        }
        return copy;
    }

}
