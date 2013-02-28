package instructions;

import simulation.Model;


public interface IExecutable {

    /**
     * executes the process prescribed by the method on the passed model.
     * 
     * For example, an Instruction implements IExecutable and the instruction fd 50
     * would move the turtle in the model forward 50 pixels
     * 
     * @param model - model on which this instruction will act
     */
    public abstract int execute (Model model);
}
