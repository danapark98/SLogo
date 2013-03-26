package control;

import instructions.BaseInstruction;
import instructions.Instruction;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import drawing.Palette;
import exceptions.IllegalInstructionException;


/**
 * Represents the current available commands and variables in the workspace. Any
 * user defined variables or instructions are also added to the environment.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Environment implements Serializable {

    private static final long serialVersionUID = 4319876122154622698L;

    private static final int GLOBAL_SCOPE = 0;

    private static final String UNDEFINED_INSTRUCTION = "undefinedInstruction";

    private static final String SCOPE_LEVEL_HEADER = "scopeHeader";

    private static final String USER_FUNCTIONS_HEADER = "functionsHeader";

    private static final String USER_VARIABLE_HEADER = "variableHeader";

    private int myScope;
    private List<InstructionMap> myInstructions;

    private Palette myPalette;

    private ResourceBundle myResources;

    /**
     * Creates a new Environment with the default instructions located
     * .\resources\instruction_indices\instruction_index.txt
     * 
     * @param resource is the ResourceBundle where all of the instruction keywords are stored.
     */
    public Environment (ResourceBundle resource) {

        myResources = resource;

        myInstructions = new ArrayList<InstructionMap>();

        myScope = GLOBAL_SCOPE;

        InstructionMap iMap = new InstructionMap(resource);

        myInstructions.add(iMap);

        myPalette = new Palette(resource);
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
                                Instruction userInstruction) {
        InstructionMap currentScope = myInstructions.get(myScope);
        currentScope.addInstruction(keyword, userInstruction);
    }

    /**
     * Defines a new user function.
     * 
     * @param keyword is the name of the new user function.
     * @param instruction is the instruction for the new function.
     */
    public void defineFunction (String keyword, Instruction instruction) {
        myInstructions.get(myScope).addUserDefFunct(keyword, instruction);
    }

    /**
     * Defines a new user variable.
     * 
     * @param keyword is the name of the variable.
     * @param value is the value of the variable.
     */
    public void defineVariable (String keyword, Instruction value) {
        myInstructions.get(myScope).addUserDefVar(keyword, value);
    }

    /**
     * Deletes an instruction from the environment.
     * Variable scope is implemented by removing the variableInstruction when
     * it is no longer visible.
     * 
     * @param instructionName of the instruction to be deleted.
     */
    public void removeInstruction (String instructionName) {
        InstructionMap currentScope = myInstructions.get(myScope);
        currentScope.remove(instructionName);
    }

    /**
     * Gives all user defined functions and variables as a string.
     * 
     * @return A String that contains all information on user defined functions.
     */
    public String customValuesToString () {
        StringBuilder sb = new StringBuilder();
        for (int i = GLOBAL_SCOPE; i <= myScope; ++i) {
            sb.append(myResources.getString(SCOPE_LEVEL_HEADER) + i + "\n");
            sb.append(myInstructions.get(i).userDefinedInstructionstoString(myResources.getString(
                    USER_FUNCTIONS_HEADER)));
            sb.append(myInstructions.get(GLOBAL_SCOPE).variablesToString(myResources.getString(
                    USER_VARIABLE_HEADER)));
        }

        return sb.toString();
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
        for (int i = GLOBAL_SCOPE; i <= myScope; ++i) {
            if (myInstructions.get(i).containsKey(commandName)) return myInstructions.get(i)
                    .get(commandName);
        }
        throw new IllegalInstructionException(commandName +
                                              myResources.getString(UNDEFINED_INSTRUCTION));
    }

    /**
     * Increase the current scope of variables.
     */
    public void inScope () {
        myScope += 1;

        myInstructions.add(new InstructionMap());
    }

    /**
     * Decrease the scope of the current variables.
     */
    public void outScope () {
        myInstructions.remove(myInstructions.size() - 1);
        myScope -= 1;
    }

    /**
     * Called by the controller to save the state of the environment to be
     * loaded in later
     * 
     * @param out to write objects needed later
     * @throws IOException if objects can't be written
     */
    public void save (ObjectOutput out) throws IOException {
        for (InstructionMap im : myInstructions) {
            im.save(out);
        }
        myPalette.save(out);
    }

    /**
     * Called by the controller to load in the state of the environment
     * 
     * Objects must be loaded in the same order they were saved.
     * 
     * @param in to read objects in
     * @throws ClassNotFoundException if file not saved properly or objects read
     *         in wrong order
     * @throws IOException if objects can't be read
     */
    public void load (ObjectInput in) throws ClassNotFoundException, IOException {
        for (InstructionMap im : myInstructions) {
            im.load(in);
        }
        myPalette.load(in);
    }

    /**
     * Gives the resources used by this environment.
     * 
     * @return Resource bundle used by this environment
     */
    public ResourceBundle getResources () {
        return myResources;
    }
}
