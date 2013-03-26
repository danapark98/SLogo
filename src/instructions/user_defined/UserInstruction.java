package instructions.user_defined;

import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import simulation.Model;
import control.Environment;
import exceptions.IllegalInstructionException;


/**
 * Represents a user defined Instruction.
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
     * @param variables is an instruction representing all of the variables in this
     *        custom instruction.
     * @param commands - the instruction that executes based on these arguments.
     */
    public UserInstruction (Instruction variables, Instruction commands) {
        myVariables = (CompoundInstruction) variables;
        myInstruction = commands;
        setNumberOfArguments(myVariables.getSize());
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        Environment environment = model.getEnvironment();
        int[] localVarValues = variableValuesToArray(model);

        environment.inScope();

        for (int i = 0; i < myVariables.getSize(); ++i) {
            VariableInstruction instruct = (VariableInstruction) myVariables.getInstruction(i);
            Instruction value = new ConstantInstruction(localVarValues[i]);
            environment.addInstruction(instruct.toString(), value);

        }
        int result = myInstruction.execute(model);
        //
        // for (int i = 0; i < myVariables.getSize(); ++i) {
        // VariableInstruction vi = (VariableInstruction)myVariables.getInstruction(i);
        // String name = vi.toString();
        // environment.removeLocalVar(name);
        // }

        environment.outScope();

        return result;
    }

    private int[] variableValuesToArray (Model model) throws IllegalInstructionException {
        int[] values = new int[getNumberOfArguments()];
        for (int i = 0; i < getNumberOfArguments(); i++) {
            values[i] = nextOperand().execute(model);
        }
        return values;
    }

    @Override
    public String toString () {
        return myVariables.toString() + "\t" + myInstruction.toString();
    }

    @Override
    public BaseInstruction newCopy () {
        return new UserInstruction(myVariables, myInstruction);
    }

}
