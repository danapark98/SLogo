package control;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import instructions.user_defined.UserInstruction;
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

    private static final String LINE_BREAK = "*************************\n";
    
    private static final String VARIABLE_HEADER =
            LINE_BREAK +
            "Current User Variables\n" +
            LINE_BREAK +
            "NAME \tVALUE \n";
    private static final String FUNCTIONS_HEADER =
            LINE_BREAK +
            "Current User Functions\n" +
            LINE_BREAK +
            "NAME \tVARIABLES \tINSTRUCTION \n";
    /**
     * Auto-generated ID for I/O
     */
    private static final long serialVersionUID = -5723192296795370586L;

    private Collection<Map<String, BaseInstruction>> myInstructionMaps;

    private Map<String, BaseInstruction> myInstructions;
    
    private Map<String, BaseInstruction> myUserInstructions;
    
    private Map<String, BaseInstruction> myGlobalVariables;

    private Map<String, BaseInstruction> myLocalVariables;
    
    /**
     * Creates an Instruction Map based on the bass resource bundle.
     * 
     * @param resource is the ResourceBundle that contains all of the instruction keywords.
     */
    public InstructionMap(ResourceBundle resource) {


        myInstructionMaps = new ArrayList<Map<String, BaseInstruction>>();

        initiateInstructionMap(resource);
        myInstructionMaps.add(myInstructions);
        myLocalVariables = new HashMap<String, BaseInstruction>();
        myInstructionMaps.add(myLocalVariables);
        
        myUserInstructions = new HashMap<String, BaseInstruction>();
        
        myInstructionMaps.add(myUserInstructions);
        
        myGlobalVariables = new HashMap<String, BaseInstruction>();
        myInstructionMaps.add(myGlobalVariables);
    }

    /**
     * Populates myInstructionMap with relevant instructions
     * from the instruction_index.txt file and their keywords from a .properties
     * file
     */
    private void initiateInstructionMap(ResourceBundle resource) {
        InstructionMapFactory imf =
                new InstructionMapFactory(resource);
        myInstructions = imf.buildInstructionMap();
    }

    /**
     * Adds a new user defined instruction to the environment.
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction is the instruction to be added to the environment.
     */
    public void addInstruction(String keyword, BaseInstruction userInstruction) {

        if (userInstruction instanceof ConstantInstruction) {
            myGlobalVariables.put(keyword, userInstruction);
        }
        else {     
            myUserInstructions.put(keyword, userInstruction);
        }
    }

    /**
     * Adds a local variable to the InstructionMap.
     * 
     * @param key is the keyword for the local instruction
     * @param value is the value of the local instruction
     */
    public void addLocal(String key, int value) {
        BaseInstruction bi = new ConstantInstruction(value);
        myLocalVariables.put(key, bi);
    }

    /**
     * Removes a local variable from this.
     * 
     * @param name is the keyword of the instruction (the local variable) to be removed.
     */
    public void removeLocal(String name) {
        myLocalVariables.remove(name);
    }

    /**
     * Makes a string containing info for all user defined variables.
     * 
     * @return String containing info on user defined values.
     */
    public String variablesToString() {
        StringBuilder sb = new StringBuilder();

        sb.append(VARIABLE_HEADER);

        for (String key : myGlobalVariables.keySet()) {
            BaseInstruction bi = myGlobalVariables.get(key);
            sb.append(key + "\t" + bi.toString());
            
        }
        return sb.toString();
    }

    /**
     * Makes a string containing information of user-defined functions.
     * 
     * @return A String with user-defined function info. 
     */
    public String userDefinedInstructionstoString() {
        StringBuilder sb = new StringBuilder();

        sb.append(FUNCTIONS_HEADER);

        for (String key : myUserInstructions.keySet()) {
            UserInstruction instruct = (UserInstruction) myUserInstructions.get(key);

           
            
            String i = instruct.toString();

            sb.append(key + "\t");
            
            sb.append(i  + "\n");
        }
        return sb.toString();
    }

    /**
     * Whether the key is corresponds to an instruction in this.
     * 
     * @param key to check in the InstructionMap
     * @return Whether the key is used in this InstructionMap.
     */
    public boolean containsKey(String key) {      
        for (Map<String, BaseInstruction> map: myInstructionMaps) {
            if (map.containsKey(key)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * Returns the instruction corresponding to the passed keyword.
     * 
     * @param key is the keyword to lookup.
     * @return The instruction with the key.
     * @throws IllegalInstructionException Occurs when the key is not in this.
     */
    public BaseInstruction get(String key) throws IllegalInstructionException {
        for (Map<String, BaseInstruction> map : myInstructionMaps) {
            if (map.containsKey(key)) { return map.get(key); }
        }
        throw new IllegalInstructionException(key);
    }

    /**
     * Deletes an instruction from the environment.
     * Variable scope is implemented by removing the variableInstruction when
     * it is no longer visible.
     * 
     * @param key of the instruction to be deleted.
     */
    public void remove(String key) {
        for (Map<String, BaseInstruction> map : myInstructionMaps) {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
    }
}
