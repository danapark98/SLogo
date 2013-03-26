package control;

import factories.PrototypeMapFactory;
import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import instructions.user_defined.UserInstruction;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Represents all instructions (which includes all variables, local and global).
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class InstructionMap implements Serializable {

    private static final String INSTRUCTION_INDEX_FILE =
            "/src/resources/resource_indices/instruction_index";

    // Please note the . at the end of instructions
    private static final String INSTRUCTION_PACKAGE_LOCATION = "instructions.";

    /**
     * Auto-generated ID for I/O
     */
    private static final long serialVersionUID = -5723192296795370586L;

    private Collection<Map<String, Instruction>> myInstructionMaps;

    private Map<String, Instruction> myInstructions;

    private Map<String, Instruction> myUserInstructions;

    private Map<String, Instruction> myGlobalVariables;

    private ResourceBundle myResources;

    /**
     * Creates an Instruction Map based on the bass resource bundle.
     * 
     * @param resource is the ResourceBundle that contains all of the instruction keywords.
     */
    public InstructionMap (ResourceBundle resource) {

        this();

        myResources = resource;

        initiateInstructionMap();
        myInstructionMaps.add(myInstructions);

    }

    /**
     * Creates a new instruction map with local, global variable maps for this scope.
     */
    public InstructionMap () {
        myInstructionMaps = new ArrayList<Map<String, Instruction>>();

        myUserInstructions = new HashMap<String, Instruction>();

        myInstructionMaps.add(myUserInstructions);

        myGlobalVariables = new HashMap<String, Instruction>();
        myInstructionMaps.add(myGlobalVariables);
    }

    /**
     * Populates myInstructionMap with relevant instructions
     * from the instruction_index.txt file and their keywords from a .properties
     * file
     */
    private void initiateInstructionMap () {
        PrototypeMapFactory<Instruction> imf =
                new PrototypeMapFactory<Instruction>(myResources,
                                                     INSTRUCTION_INDEX_FILE,
                                                     INSTRUCTION_PACKAGE_LOCATION);
        myInstructions = imf.buildStringMap();
    }

    /**
     * Adds a new user defined instruction to the environment.
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction is the instruction to be added to the environment.
     */
    public void addInstruction (String keyword, Instruction userInstruction) {

        if (userInstruction instanceof ConstantInstruction) {
            myGlobalVariables.put(keyword, userInstruction);
        }
        else {
            myUserInstructions.put(keyword, userInstruction);
        }
    }

    /**
     * Adds a new user defined variable.
     * 
     * @param keyword is the name of the variable.
     * @param value is the value of the variable.
     */
    public void addUserDefVar (String keyword, Instruction value) {
        myGlobalVariables.put(keyword, value);
    }

    /**
     * Adds a new user defined function.
     * 
     * @param keyword is the name of the function.
     * @param instruction is the function.
     */
    public void addUserDefFunct (String keyword, Instruction instruction) {
        myUserInstructions.put(keyword, instruction);
    }

    /**
     * Makes a string containing info for all user defined variables.
     * 
     * @param header is the header to display for the text.
     * 
     * @return String containing info on user defined values.
     */
    public String variablesToString (String header) {
        StringBuilder sb = new StringBuilder();

        sb.append(header);

        for (String key : myGlobalVariables.keySet()) {
            Instruction bi = myGlobalVariables.get(key);
            sb.append(key + "\t" + bi.toString());

        }
        return sb.toString();
    }

    /**
     * Makes a string containing information of user-defined functions.
     * 
     * @param header is the header to display for the text.
     * @return A String with user-defined function info.
     */
    public String userDefinedInstructionstoString (String header) {
        StringBuilder sb = new StringBuilder();

        sb.append(header);

        for (String key : myUserInstructions.keySet()) {
            UserInstruction instruct = (UserInstruction) myUserInstructions.get(key);

            String i = instruct.toString();

            sb.append(key + "\t");

            sb.append(i + "\n");
        }
        return sb.toString();
    }

    /**
     * Whether the key is corresponds to an instruction in this.
     * 
     * @param key to check in the InstructionMap
     * @return Whether the key is used in this InstructionMap.
     */
    public boolean containsKey (String key) {
        for (Map<String, Instruction> map : myInstructionMaps) {
            if (map.containsKey(key)) return true;
        }
        return false;
    }

    /**
     * 
     * Returns the instruction corresponding to the passed keyword.
     * 
     * @param key is the keyword to lookup.
     * @return The instruction with the key.
     */
    public BaseInstruction get (String key) {
        for (Map<String, Instruction> map : myInstructionMaps) {
            if (map.containsKey(key)) {

                BaseInstruction original = (BaseInstruction) map.get(key);

                return original.newCopy();
            }
        }
        return null;
    }

    /**
     * Deletes an instruction from the environment.
     * Variable scope is implemented by removing the variableInstruction when
     * it is no longer visible.
     * 
     * @param key of the instruction to be deleted.
     */
    public void remove (String key) {
        for (Map<String, Instruction> map : myInstructionMaps) {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
    }

    /**
     * Save the mutable state of the InstructionMap to be loaded in later
     * 
     * @param out to write objects needed later
     * @throws IOException if objects can't be written
     */
    public void save (ObjectOutput out) throws IOException {
        out.writeObject(myGlobalVariables);
        out.writeObject(myInstructionMaps);
        out.writeObject(myInstructions);
        out.writeObject(myUserInstructions);
    }

    /**
     * Load in the mutable state of the InstructionMap
     * 
     * Objects must be loaded in the same order they were saved.
     * 
     * @param in to read objects in
     * @throws ClassNotFoundException if file not saved properly or objects read
     *         in wrong order
     * @throws IOException if objects can't be read
     */
    @SuppressWarnings("unchecked")
    public void load (ObjectInput in) throws ClassNotFoundException, IOException {
        myGlobalVariables = (Map<String, Instruction>) in.readObject();
        myInstructionMaps = (Collection<Map<String, Instruction>>) in.readObject();
        myInstructions = (Map<String, Instruction>) in.readObject();
        myUserInstructions = (Map<String, Instruction>) in.readObject();
    }
}
