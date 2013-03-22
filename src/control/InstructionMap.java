package control;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import instructions.user_defined.VariableInstruction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InstructionMap implements Serializable{

    /**
     * Auto-generated ID for I/O
     */
    private static final long serialVersionUID = -5723192296795370586L;
    
    private Collection<Map<String, BaseInstruction>> myInstructionMaps;
    
    private Map<String, BaseInstruction> myInstructions;
    //private Map<String, BaseInstruction> myUserDefinedVariables;
    //private Map<String, BaseInstruction> myUserDefinedFunctions;
    
    private Map<String, BaseInstruction> myLocalVariables;
    
    public InstructionMap(ResourceBundle resource) {
        
        myInstructionMaps = new ArrayList<Map<String, BaseInstruction>>();
        
        
        myInstructions = new HashMap<String, BaseInstruction>();
        myInstructionMaps.add(myInstructions);
        //myUserDefinedVariables = new HashMap<String, BaseInstruction>();
        //myInstructionMaps.add(myUserDefinedVariables);
        //myUserDefinedFunctions = new HashMap<String, BaseInstruction>();
        //myInstructionMaps.add(myUserDefinedFunctions);
        myLocalVariables = new HashMap<String, BaseInstruction>();
        myInstructionMaps.add(myLocalVariables);
         
        initiateInstructionMap(resource);
    }

    /**
     * Populates myInstructionMap with relevant instructions
     * from the instruction_index.txt file and their keywords from a .properties
     * file
     */
    private void initiateInstructionMap (ResourceBundle resource) {

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
    public void addInstruction (String keyword, BaseInstruction userInstruction) {
        
        // TODO determine if global variable, user def function, or default
        
        // We could always just keep a list of names? -- not the best
        
        myInstructions.put(keyword, userInstruction);
    }
    
    /**
     * TODO: comment
     * @param key
     * @param value
     */
    public void addLocal(String key, int value) {
        BaseInstruction bi = new ConstantInstruction(value);
        myLocalVariables.put(key, bi);
    }

    /**
     * TODO: Comment
     */
    public void removeLocal (String name) {
        myLocalVariables.remove(name);
    }
    
    public String variablesToString(){
        return null;
    }
    
    public String userDefinedInstructionstoString() {
        return null;
    }
    
    public boolean containsKey(String key) {
        return myInstructions.containsKey(key) || myLocalVariables.containsKey(key);
    }
    
    public BaseInstruction get(String key) throws IllegalInstructionException {
        for(Map<String, BaseInstruction> map : myInstructionMaps){
            if(map.containsKey(key)){
                return map.get(key);
            }
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
        for (Map<String, BaseInstruction> map : myInstructionMaps){
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
    }
}
