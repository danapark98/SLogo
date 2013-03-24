package control;

import drawing.Palette;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class Environment implements Serializable {

    private static final long serialVersionUID = 4319876122154622698L;

    private static final int GLOBAL_SCOPE = 0;

    private static final String UNDEFINED_INSTRUCTION = " is undefined and";

    private static final String SCOPE_LEVEL_HEADER = "AT SCOPE LEVEL ";
    
    /** Mapping of Instruction keywords to Instruction */

    //private Map<String, BaseInstruction> myInstructionMap;
    
    //private InstructionMap myInstructionMap;
    
    private int myScope;
    private List<InstructionMap> myInstructions;
    private Palette myPalette;

    /**
     * Creates a new Environment with the default instructions located
     * .\resources\instruction_index.txt
     * 
     * @param resource is the ResourceBundle where all of the instruction keywords are stored.
     */
    public Environment (ResourceBundle resource) {
        
        myInstructions = new ArrayList<InstructionMap>();
        
        myScope = GLOBAL_SCOPE;
        
        InstructionMap iMap = new InstructionMap(resource);

        myInstructions.add(iMap);

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
    public void defineFunction(String keyword, Instruction instruction) {
        myInstructions.get(myScope).addUserDefFunct(keyword, instruction);
    }
    
    /**
     * Defines a new user variable.
     * 
     * @param keyword is the name of the variable.
     * @param value is the value of the variable.
     */
    public void defineVariable(String keyword, Instruction value) {
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
    
//    /**
//     * Adds a local variable to the environment.
//     * 
//     * @param instruct is the variable to be added.
//     * @param value is the value of the variable to be added.
//     */
//    public void addLocalVar(VariableInstruction instruct, int value) {
//        String name = instruct.toString();
//        myInstructionMap.remove(name);
//        BaseInstruction constant = new ConstantInstruction(value);
//        myInstructionMap.addInstruction(name, constant);
//    }
//    
//    /**
//     * Removes a local variable from the environment.
//     * 
//     * @param key is the key for the local variable to be removed.
//     */
//    public void removeLocalVar(String key) {
//        myInstructionMap.remove(key);
//    }
    
    /**
     * Gives all user defined functions and variables as a string.
     * 
     * @return A String that contains all information on user defined functions.
     */
    public String customValuesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= myScope; ++i) {
            sb.append(SCOPE_LEVEL_HEADER + i + "\n");
            sb.append(myInstructions.get(i).userDefinedInstructionstoString());
            sb.append(myInstructions.get(GLOBAL_SCOPE).variablesToString());
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
        //BaseInstruction res = null;
        for (int i = 0; i <= myScope; ++i) {
            if (myInstructions.get(i).containsKey(commandName)) {
                return myInstructions.get(i).get(commandName);
            }
        }
//        // TODO: get rid of if statments
//        if (res == null) {
        throw new IllegalInstructionException(commandName + UNDEFINED_INSTRUCTION);
//        }
//        else {
//            return res;
//        }
    }

    /**
     * Increase the current scope of variables.
     */
    public void inScope() {
        myScope += 1;
        
        myInstructions.add(new InstructionMap());
    }
    
    /**
     * Decrease the scope of the current variables.
     */
    public void outScope() {
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
    public void save(ObjectOutput out) throws IOException {
        out.writeObject(myInstructions);
        myPalette.save(out);
    }
    
    /**
     * Called by the controller to load in the state of the environment
     * 
     * Objects must be loaded in the same order they were saved.
     * 
     * @param in to read objects in 
     * @throws ClassNotFoundException if file not saved properly or objects read
     * in wrong order
     * @throws IOException if objects can't be read
     */
    @SuppressWarnings("unchecked")
    public void load(ObjectInput in) throws ClassNotFoundException, IOException {
        myInstructions = (List<InstructionMap>) in.readObject();
        myPalette.load(in);
    }
}
