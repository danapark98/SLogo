package control;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import simulation.Model;


public class InstructionMap implements Serializable {

    private static final String VARIABLE_HEADER =
            "Current User Variables\n*************************\n";
    private static final String FUNCTIONS_HEADER =
            "Current User Functions\n**************************\n";
    private static final String UNABLE_TO_COMPUTE = "Not currently computable";

    /**
     * Auto-generated ID for I/O
     */
    private static final long serialVersionUID = -5723192296795370586L;

    private Collection<Map<String, BaseInstruction>> myInstructionMaps;

    private Map<String, BaseInstruction> myInstructions;

    private Map<String, BaseInstruction> myLocalVariables;

    private Collection<String> myUserDefinedInstructionKeys;

    public InstructionMap(ResourceBundle resource) {

        myUserDefinedInstructionKeys = new ArrayList<String>();

        myInstructionMaps = new ArrayList<Map<String, BaseInstruction>>();

        initiateInstructionMap(resource);
        myInstructionMaps.add(myInstructions);
        myLocalVariables = new HashMap<String, BaseInstruction>();
        myInstructionMaps.add(myLocalVariables);
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

        // TODO determine if global variable, user def function, or default

        // We could always just keep a list of names? -- not the best
        myUserDefinedInstructionKeys.add(keyword);
        myInstructions.put(keyword, userInstruction);
    }

    /**
     * TODO: comment
     * 
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
    public void removeLocal(String name) {
        myLocalVariables.remove(name);
    }

    public String variablesToString() {
        StringBuilder sb = new StringBuilder();

        sb.append(VARIABLE_HEADER + "\n");

        for (String key : myInstructions.keySet()) {
            if (key.startsWith(":")) {
                sb.append(key + "\n");
            }
        }
        return sb.toString();
    }

    public String userDefinedInstructionstoString(Model model) {
        StringBuilder sb = new StringBuilder();

        sb.append(FUNCTIONS_HEADER + "\n");

        for (String key : myUserDefinedInstructionKeys) {
            Instruction instruct = myInstructions.get(key);

            // TODO: I hate this
            if (instruct instanceof ConstantInstruction) {
                try {
                    sb.append(key + "\t" + instruct.execute(model) + "\n");
                }
                catch (IllegalInstructionException e) {
                    sb.append(UNABLE_TO_COMPUTE + "\n");
                }
            }
            else {
                sb.append(key + "\t" + UNABLE_TO_COMPUTE + "\n");
            }

        }
        return sb.toString();
    }

    public boolean containsKey(String key) {
        return myInstructions.containsKey(key) || myLocalVariables.containsKey(key);
    }

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
