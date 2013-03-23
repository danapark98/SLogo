package instructions.turtle;

import drawing.ImagePalette;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Pixmap;


/**
 * Represents making the turtle invisible as an instruction. Takes zero
 * arguments and always returns 0.<br>
 * <br>
 * <u> Examples:</u> <br>
 * hideturtle ---> does not show turtle on screen if turtle is not already invisible. <br>
 * ht ---> does not shows turtle on screen if turtle is not already invisible. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class HideTurtle extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7797738746693384437L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Initializes a hide turtle instruction.
     */
    public HideTurtle() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        Pixmap invisible = model.getPalette().getImage(ImagePalette.DEFAULT_CLEAR_INDEX);
        model.getTurtle().getPen().changeImage(invisible);
        return 0;
    }
}
