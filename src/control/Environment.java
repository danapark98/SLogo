package control;

import drawing.Palette;
import exceptions.FileSavingException;
import exceptions.IllegalInstructionException;
import exceptions.IncorrectFileFormatException;
import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.user_defined.VariableInstruction;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Represents the current available commands and variables in the workspace. Any
 * user defined variables or instructions are also added to the environment.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Environment {

    /** Mapping of Instruction keywords to Instruction */
    private Map<String, BaseInstruction> myInstructionMap;
    private Palette myPalette;

    /**
     * Creates a new Environment with the default instructions located
     * .\resources\instruction_index.txt
     * 
     * @param resource is the ResourceBundle where all of the instruction keywords are stored.
     */
    public Environment (ResourceBundle resource) {
        initiateInstructionMap(resource);
        myPalette = new Palette(); 
    }


	/** Creates an useless environment without instructions */
    public Environment () {
        myPalette = new Palette();
    }

    /**
     * Populates myInstructionMap with relevant instructions
     * from the instruction_index.txt file and their keywords from a .properties
     * file
     */
    private void initiateInstructionMap (ResourceBundle resource) {

        InstructionMapFactory imf =
                new InstructionMapFactory(resource);
        myInstructionMap = imf.buildInstructionMap();

    }

    /**
     * Gives the palette currently in use by this environment.
     * 
     * @return The current palette in use.
     */
    public Palette getPalette () {
        return myPalette;
    }


    /**
     * Adds a new user defined instruction to the environment.
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction is the instruction to be added to the environment.
     */
    public void addUserDefinedInstruction (String keyword,
                                           BaseInstruction userInstruction) {
        myInstructionMap.put(keyword, userInstruction);
    }

    /**
     * Deletes an instruction from the environment.
     * Variable scope is implemented by removing the variableInstruction when
     * it is no longer visible.
     * 
     * @param instructionName of the instruction to be deleted.
     */
    public void removeInstruction (String instructionName) {
        myInstructionMap.remove(instructionName);
    }

    /**
     * TODO: comment
     * @param variables
     */
    public void addVariables (CompoundInstruction variables, int[] variableValues) {
        for (int i = 0; i < variables.getSize(); i++) {
            VariableInstruction currentVariable = 
                    (VariableInstruction) variables.getInstruction(i);
            addVariable(currentVariable, variableValues[i]);
        }
    }
    
    /**
     * TODO: comment
     * @param variable
     * @param value
     */
    public void addVariable(VariableInstruction variable, int value) {
        String variableName = variable.getName();
        BaseInstruction variableValue = new ConstantInstruction(value);
        addUserDefinedInstruction(variableName, variableValue);
    }

    /**
     * TODO: Comment
     */
    public void removeVariables (CompoundInstruction variables) {
        for (int i = 0; i < variables.getSize(); i++) {
            VariableInstruction currentVariable = 
                    (VariableInstruction) variables.getInstruction(i);
            String variableName = currentVariable.getName();
            removeInstruction(variableName);
        }
    }

    /**
     * Gives the Instruction associated with the passed keyword.
     * 
     * @param commandName - the keyword for the instruction
     * @return The Instruction associated with the keyword
     * @throws IllegalInstructionException This occurs when the keyword is not
     *         found in the environment.
     */
    public BaseInstruction systemInstructionSkeleton (String commandName)
                                                                         throws IllegalInstructionException {

        if (!myInstructionMap.containsKey(commandName)) { throw new IllegalInstructionException(
                                                                                                commandName); }
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
    public void load (InputStream is) throws IncorrectFileFormatException {
        ObjectInput in;
        try {
            in = new ObjectInputStream(is);
            myInstructionMap = (Map<String, BaseInstruction>) in.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
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
    public void save (OutputStream os) throws FileSavingException {
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(os);
            out.writeObject(myInstructionMap);
            out.writeObject(myPalette);
        }
        catch (IOException e) {
            throw new FileSavingException();
        }

    }

}
