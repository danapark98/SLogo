package instructions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;

public class MakeUserInstruction extends BaseInstruction {
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

}
