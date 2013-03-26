package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Location;
import drawing.StampSprite;
import exceptions.IllegalInstructionException;


/**
 * Represents an instruction which makes a stamp of the current shape on the workspace<br>
 * <br>
 * <u> Examples:</u> <br>
 * stamp ---> makes a triangle shape when the turtle is a triangle shape <br>
 * right now this default returns 0
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Stamp extends BaseInstruction {

    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = -1752745552642834353L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Default constructor for baseInstruction type
     */
    public Stamp () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        // model.addStamp() --> this does it through the model
        Turtle t = model.getTurtle();

        StampSprite st = new StampSprite(t.getView(), new Location(t.getX(),
                                                                   t.getY()), t.getSize());
        st.setAngle(t.getAngle());
        model.addStamp(st);

        // default return values
        return 1;
    }

}
