package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import java.awt.Color;
import simulation.Model;
import simulation.Turtle;

/**
 * Changes the pen color that the turtle draws with. These colors are predefined values
 * that the user can select from.
 * 
 * @author Scott Valentine
 *
 */
public class SetPenColor extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 5820283386042014110L;

    // TODO: add number of agruments
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        Turtle turtle = model.getTurtle();
        // TODO: get pen color choices (from environment?)
        turtle.changePen(new Color(0, 0, 0, 0));
        return 0;
    }

}
