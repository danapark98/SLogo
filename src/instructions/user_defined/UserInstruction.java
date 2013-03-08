package instructions.user_defined;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import simulation.Model;


/**
 * Represents a user defined Instruction. TODO: complete this java doc
 * 
 * @author Scott Valentine
 * 
 */
public class UserInstruction extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1776254460831303292L;
    private CompoundInstruction myVariables;
    private Instruction myInstruction;

    /**
     * Creates a new UserInstruction from the number of 
     * parameters it takes and how it acts on those parameters.
     * 
     * @param numberOfArgs is the number of arguments this instruction takes.
     * @param commands - the instruction that executes based on these arguments.
     */
    public UserInstruction(Instruction variables, Instruction commands) {
        myVariables = (CompoundInstruction) variables;
        myInstruction = commands;
        setNumberOfArguments(myVariables.getSize());
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        addVariablesToEnvironment(model);
        int result = myInstruction.execute(model);
        removeVariablesFromEnvironment(model);
        return result;        
    }

    private void addVariablesToEnvironment (Model model) throws IllegalInstructionException {
        for (int i = 0; i < getNumberOfArguments(); i++) {
            VariableInstruction currentVariable = 
                    (VariableInstruction) myVariables.getInstruction(i);
            String variableName = currentVariable.getName();
            int variableValue = nextOperand().execute(model);
            model.addInstruction(variableName, new ConstantInstruction(variableValue));
        }
    }

    private void removeVariablesFromEnvironment (Model model) {
        for (int i = 0; i < getNumberOfArguments(); i++) {
            VariableInstruction currentVariable = 
                    (VariableInstruction) myVariables.getInstruction(i);
            String variableName = currentVariable.getName();
            model.removeVariable(variableName);
        }
    }

    @Override
    public BaseInstruction newCopy() {
        return new UserInstruction(myVariables, myInstruction);
    }

}
