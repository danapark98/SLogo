package instructions.turtle;

import drawing.lines.LineBuilder;
import drawing.lines.SolidLine;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;

/**
 * Represents an instruction which tells the turtle to draw a solid line in the workspace.<br>
 * <br>
 * <u> Examples:</u> <br>
 * drawsolid ---> lines drawn will now be solid lines. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class DrawSolidLine extends BaseInstruction {

    
    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = 2824267394061758609L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a default instruction to draw solid lines.
     */
    public DrawSolidLine () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        LineBuilder lb = model.getPalette().getLineStyle(SolidLine.PALETTE_INDEX);
        
        model.getTurtle().turtlePen().changeLineStyle(lb);
        return SolidLine.PALETTE_INDEX;
    }

}
