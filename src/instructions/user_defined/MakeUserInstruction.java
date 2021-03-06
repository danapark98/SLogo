package instructions.user_defined;

import instructions.BaseInstruction;
import instructions.Instruction;
import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


/**
 * Represents the definition of a custom instruction as an instruction. Takes
 * three
 * arguments: what to name the custom instruction, the variables, if any, that
 * are parameters of the custom instruction,
 * and the instruction it will
 * represent. Note that the second and
 * third arguments (the variables the instruction) must be contained
 * in
 * brackets.<br>
 * <br>
 * <u> Examples:</u> <br>
 * <i>to sample [ x y ] [ fd x rt y ]</i> ---> The instruction <i> sample </i>
 * will now take two arguments and will move forward by the first arguments and
 * turn right by the second argument. <br>
 * <i>to sample2 [ ] [ fd 50 ]</i> ---> The instruction <i> sample2 </i> will
 * now move the turtle forward by 50 pixels every time it is called. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class MakeUserInstruction extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 4760595117209610147L;
    private static final int NUMBER_OF_ARGUMENTS = 3;
    private String myCommandName;
    private Instruction myVariables;
    private Instruction myCommands;

    /**
     * Initializes user the definition of a custom instruction.
     */
    public MakeUserInstruction () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        myCommandName = line.next();

        if (myCommandName.equals("[")) {
            myCommandName = parser.unpackList(line);
        }

        myVariables = parser.nextInstruction(line);
        myCommands = parser.nextInstruction(line);
    }

    @Override
    public int execute (Model model) {
        BaseInstruction instruction = new UserInstruction(myVariables, myCommands);
        model.getEnvironment().defineFunction(myCommandName, instruction);
        return 1;
    }
}
