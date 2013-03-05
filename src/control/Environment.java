package control;

import exceptions.FileSavingException;
import exceptions.IllegalInstructionException;
import exceptions.IncorrectFileFormatException;
import instructions.BaseInstruction;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;



/**
 * Represents the current available commands and variables in the workspace. Any
 * user defined variables or instructions are also added to the environment.
 * 
 * @author Scott Valentine
 * 
 */
public class Environment {

    /** Mapping of Instruction keywords to Instruction */
    private Map<String, BaseInstruction> myInstructionMap;

    /**
     * Creates a new Environment with the default instructions located
     * .\resources\instruction_index.txt
     */
    public Environment() {
        initiateInstructionMap();
    }

    /**
     * Populates myInstructionMap with relevant instructions
     * from the instruction_index.txt file and their keywords from a .properties
     * file
     */
    private void initiateInstructionMap() {

        // TODO: would much rather have the constructor take a ResourceBundle
        // instead of a
        // string that indicates where to find the ResourceBundle
        InstructionMapFactory imf =
                new InstructionMapFactory(
                                          InstructionMapFactory.ENGLISH_LANGUAGE);
        myInstructionMap = imf.buildInstructionMap();

    }

    /**
     * Adds a new user defined instruction to the environment.
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction is the instruction to be added to the environment.
     */
    public void addUserDefinedInstruction(String keyword,
                                          BaseInstruction userInstruction) {
        myInstructionMap.put(keyword, userInstruction);
    }

    public void remove (String variableName) {
        myInstructionMap.remove(variableName);        
    }

    /**
     * Gives the Instruction associated with the passed keyword.
     * 
     * @param commandName - the keyword for the instruction
     * @return The Instruction associated with the keyword
     * @throws IllegalInstructionException This occurs when the keyword is not
     *         found in the environment.
     */
    public BaseInstruction systemInstructionSkeleton(String commandName)
        throws IllegalInstructionException {

        if (!myInstructionMap.containsKey(commandName)) { 
            throw new IllegalInstructionException( commandName); 
        }
        return myInstructionMap.get(commandName).newCopy();
    }

    /**
     * Loads in instructions and variables for the Environment from an
     * InputStream. The source must be something saved by the save() method.
     * 
     * @param is the source to read in from
     * @throws IncorrectFileFormatException if not readable.
     * 
     */
    @SuppressWarnings("unchecked")
    // will only load from files saved by save()
    public void load(InputStream is) throws IncorrectFileFormatException {
        ObjectInput in;
        try {
            in = new ObjectInputStream(is);
            myInstructionMap = (Map<String, BaseInstruction>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new IncorrectFileFormatException();
        }

    }

    /**
     * Saves instructions and variables to an OutputStream. Used only for
     * reading in at a later point by the load() method.
     * 
     * @param os to write to
     * @throws FileSavingException is an exception thrown if the OutputStream
     *         provided cannot be written to successfully.
     */
    public void save(OutputStream os) throws FileSavingException {
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(os);
            out.writeObject(myInstructionMap);
        } catch (IOException e) {
            throw new FileSavingException();
        }

    }

}
