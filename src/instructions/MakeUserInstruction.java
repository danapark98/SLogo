package instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;


public class MakeUserInstruction extends BaseInstruction {
<<<<<<< HEAD
    private String myCommandName;
    private static final int NUMBER_OF_ARGUMENTS = 3;
    private List<Instruction> myOperands = new ArrayList<>();

    @Override
    public void load (Scanner line, Parser parser)
                                                  throws IllegalInstructionException {
        // get name
        myCommandName = line.next();
        // get list of variables
        Instruction variables = nextInstruction(line, parser);
        // get list of instructions
        Instruction instruction = nextInstruction(line, parser);
        // make new UserInstruction
        UserInstruction userInstruction = new UserInstruction(myCommandName);
        userInstruction.setInstruction(instruction);
        // add UserInstruction to environment
        parser.getEnvironment().addUserDefinedFunction(myCommandName, userInstruction);
    }

    @Override
    public int execute (Model model) {
        // default return value (this instruction only defines something)
        return 0;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public void addOperand (Instruction instruction) {
        myOperands.add(instruction);
    }
=======
	private String myCommandName;

	@Override
	public void load(Scanner line, Parser parser)
			throws IllegalInstructionException {
		// get name
		myCommandName = line.next();
		// get list of variables
		Instruction variables = nextInstruction(line, parser);
		// get list of instructions
		Instruction instruction = nextInstruction(line, parser);
		// make new UserInstruction
		UserInstruction userInstruction= new UserInstruction(myCommandName);
		userInstruction.setInstruction(instruction);
		// add UserInstruction to environment
		parser.getEnvironment().addUserDefinedFunction(myCommandName, userInstruction);
	}


	@Override
	public int execute(Model model) {
		// default return value (this instruction only defines something)
		return 0;
	}
>>>>>>> 0e991c8a98149247ec80f4ed6b80f737298ce9ec

}
