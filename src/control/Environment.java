package control;

import drawing.Palette;
import exceptions.FileSavingException;
import exceptions.IllegalInstructionException;
import exceptions.IncorrectFileFormatException;
import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import instructions.user_defined.VariableInstruction;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import simulation.Model;


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
    //private Map<String, BaseInstruction> myInstructionMap;
    
    private InstructionMap myInstructionMap;

    private Palette myPalette;

    /**
     * Creates a new Environment with the default instructions located
     * .\resources\instruction_index.txt
     * 
     * @param resource is the ResourceBundle where all of the instruction keywords are stored.
     */
    public Environment (ResourceBundle resource) {
        
        myInstructionMap = new InstructionMap(resource);

        myPalette = new Palette();
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
    public void addInstruction (String keyword,
                                           BaseInstruction userInstruction) {
        myInstructionMap.addInstruction(keyword, userInstruction);
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
    
    public void addLocalVar(VariableInstruction instruct, int value){
        String name = instruct.getName();
        myInstructionMap.remove(name);
        BaseInstruction constant = new ConstantInstruction(value);
        myInstructionMap.addInstruction(name, constant);
    }
    
    public void removeLocalVar(String key) {
        myInstructionMap.remove(key);
    }
    
    public String customValuesToString() {
        return myInstructionMap.userDefinedInstructionstoString() + myInstructionMap.variablesToString();
    }


    /**
     * Gives the Instruction associated with the passed keyword.
     * 
     * @param commandName - the keyword for the instruction
     * @return The Instruction associated with the keyword
     * @throws IllegalInstructionException This occurs when the keyword is not
     *         found in the environment.
     */
    public BaseInstruction getInstruction (String commandName) throws IllegalInstructionException {
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
            
            myInstructionMap = (InstructionMap) in.readObject();
            myPalette = (Palette) in.readObject();
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
            //TODO: make Palette and its variables serializable, and test saving/loading
            out.writeObject(myPalette);
        }
        catch (IOException e) {
            throw new FileSavingException();
        }
    }
}
