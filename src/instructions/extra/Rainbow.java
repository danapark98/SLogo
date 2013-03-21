package instructions.extra;

import exceptions.IllegalInstructionException;
import drawing.lines.LineBuilder;
import drawing.lines.RainbowLine;
import instructions.BaseInstruction;
import simulation.Model;

public class Rainbow extends BaseInstruction{

    
    
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a '<' instruction.
     */
    public Rainbow() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        LineBuilder lb = model.getPalette().getLineStyle(RainbowLine.RAINBOW_INDEX);
        
        model.getTurtle().turtlePen().changeLineStyle(lb);
        return RainbowLine.RAINBOW_INDEX;
    }

}
