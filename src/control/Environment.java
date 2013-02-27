package control;

import instructions.Forward;
import instructions.Instruction;
import instructions.Left;
import instructions.Right;
import java.util.HashMap;
import java.util.Map;
import exceptions.IllegalInstructionException;


/**
 * Represents the current available commands and variables in the workspace
 * 
 * @author Scott Valentine
 * 
 */
public class Environment {

    /** mapping of Instruction keywords to Instruction */
    private Map<String, Instruction> myInstructionMap;
    private Map<String, Integer> myVariables;

    /**
     * default constructor initiates the instructionMap
     */
    public Environment () {
        myInstructionMap = new HashMap<String, Instruction>();
        initiateInstructionMap();
        myVariables = new HashMap<String, Integer>();
    }

    /**
     * populates myInstructionMap with relevant instructions
     */
    private void initiateInstructionMap () {
        myInstructionMap.put(Forward.KEYWORD, new Forward());
        myInstructionMap.put(Right.KEYWORD, new Right());
        myInstructionMap.put(Left.KEYWORD, new Left());
    }

    /**
     * adds a new user defined instruction to the environment
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction - instruction to be added to the environment
     */
    public void addUserDefinedFunction (String keyword, Instruction userInstruction) {
        myInstructionMap.put(keyword, userInstruction);
    }

    public void addUserDefinedVariable (String variableName, int value) {
        myVariables.put(variableName, value);
    }

    /**
     * gives the Instruction associated with the passed keyword
     * 
     * if the keyword is not associated with an Instruction, this
     * will throw an IllegalInstructionExcpection
     * 
     * @param commandName - the keyword for the instruction
     * @return - the Instruction associated with the keyword
     * @throws IllegalInstructionException
     */
    public Instruction systemInstructionSkeleton (String commandName)
                                                                     throws IllegalInstructionException {

        if (!myInstructionMap.containsKey(commandName))
            throw new IllegalInstructionException(commandName);

        return myInstructionMap.get(commandName).copy();

    }

}
