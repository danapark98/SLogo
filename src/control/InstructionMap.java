package control;

import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
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

    
    private static final String VARIABLE_HEADER = "variableHeader";
    private static final String FUNCTIONS_HEADER = "functionsHeader";
    /**
     * Auto-generated ID for I/O
     */
    private static final long serialVersionUID = -5723192296795370586L;

    private Collection<Map<String, Instruction>> myInstructionMaps;

    private Map<String, Instruction> myInstructions;
    
    private Map<String, Instruction> myUserInstructions;
    
    private Map<String, Instruction> myGlobalVariables;

    private Map<String, Instruction> myLocalVariables;
    
    private ResourceBundle myResources; 
    
    /**
     * Creates an Instruction Map based on the bass resource bundle.
     * 
     * @param resource is the ResourceBundle that contains all of the instruction keywords.
     */
    public InstructionMap(ResourceBundle resource) {
        
        this();        

        myResources = resource;
        
        initiateInstructionMap();
        myInstructionMaps.add(myInstructions);

    }

    /**
     * Creates a new instruction map with local, global variable maps for this scope.
     */
    public InstructionMap() {
        myInstructionMaps = new ArrayList<Map<String, Instruction>>();
        myLocalVariables = new HashMap<String, Instruction>();
        myInstructionMaps.add(myLocalVariables);
        
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
    private void initiateInstructionMap() {
        InstructionMapFactory imf =
                new InstructionMapFactory(myResources);
        myInstructions = imf.buildInstructionMap();
    }

    /**
     * Adds a new user defined instruction to the environment.
     * 
     * @param keyword associated with the instruction for future calls
     * @param userInstruction is the instruction to be added to the environment.
     */
    public void addInstruction(String keyword, Instruction userInstruction) {

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
    public void addUserDefVar(String keyword, Instruction value) {
        myGlobalVariables.put(keyword, value);
    }
    
    /**
     * Adds a new user defined function.
     * 
     * @param keyword is the name of the function.
     * @param instruction is the function.
     */
    public void addUserDefFunct(String keyword, Instruction instruction) {
        myUserInstructions.put(keyword, instruction);
    }
    

    /**
     * Adds a local variable to the InstructionMap.
     * 
     * @param key is the keyword for the local instruction
     * @param value is the value of the local instruction
     */
    public void addLocal(String key, int value) {
        Instruction bi = new ConstantInstruction(value);
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

        sb.append(myResources.getString(VARIABLE_HEADER));

        for (String key : myGlobalVariables.keySet()) {
            Instruction bi = myGlobalVariables.get(key);
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

        sb.append(myResources.getString(FUNCTIONS_HEADER));

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
    public boolean containsKey(String key) {      
        for (Map<String, Instruction> map: myInstructionMaps) {
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
     */
    public BaseInstruction get(String key) {
        for (Map<String, Instruction> map : myInstructionMaps) {
            if (map.containsKey(key)) { 
                
                BaseInstruction original = (BaseInstruction)map.get(key);
                
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
    public void remove(String key) {
        for (Map<String, Instruction> map : myInstructionMaps) {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
    }
}
