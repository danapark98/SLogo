package instructions.turtle;

import drawing.lines.DashedLine;
import drawing.lines.LineBuilder;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;

/**
 * 
 * Instruction that switches the pen on current turtles to drawing a dashed line.
 * 
 * @author Scott Valentine
 *
 */
public class DrawDashed extends BaseInstruction {

    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = -2419193839050275170L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a default instruction to draw dashed lines.
     */
    public DrawDashed () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        LineBuilder lb = model.getPalette().getLineStyle(DashedLine.PALETTE_INDEX);
        model.getTurtle().getPen().changeLineStyle(lb);
        return DashedLine.PALETTE_INDEX;
    }

}
