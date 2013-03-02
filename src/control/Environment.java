package control;

import exceptions.IllegalInstructionException;
import instructions.Instruction;
import java.io.FileNotFoundException;
import java.util.Map;


/**
 * Represents the current available commands and variables in the workspace
 * 
 * @author Scott Valentine
 * 
 */
public class Environment {

    /** mapping of Instruction keywords to Instruction */
    private Map<String, Instruction> myInstructionMap;

    /**
     * default constructor initiates the instructionMap
     */
    public Environment() {
        initiateInstructionMap();
    }

    /**
     * populates myInstructionMap with relevant instructions
     */
    private void initiateInstructionMap() {

        // TODO: would much rather have the constructor take a ResourceBundle instead of a 
        // string that indicates where to find the ResourceBundle
        InstructionMapFactory imf = new InstructionMapFactory(InstructionMapFactory.ENGLISH);

        try {
            myInstructionMap = imf.buildInstructionMap();
        } 
        catch (FileNotFoundException e) {
            // TODO: do something if nothing is found 
            // (map will be empty and all user commands will fail)
            return;
        }
    }

    /**
     * adds a new user defined instruction to the environment
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction - instruction to be added to the environment
     */
    public void addUserDefinedFunction(String keyword,
                                       Instruction userInstruction) {
        myInstructionMap.put(keyword, userInstruction);
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
    public Instruction systemInstructionSkeleton(String commandName)
        throws IllegalInstructionException {

        if (!myInstructionMap.containsKey(commandName))
            throw new IllegalInstructionException(commandName);

        return myInstructionMap.get(commandName).copy();

    }
    
    

}
