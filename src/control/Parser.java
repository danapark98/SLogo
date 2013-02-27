package control;

import instructions.CompoundInstruction;
import instructions.Instruction;
import java.util.Scanner;
import exceptions.IllegalInstructionException;


/**
 * Converts a user inputed string into an SystemInstruction that can do work on the model
 * 
 * @author Scott Valentine
 * 
 */
public class Parser {

    /** environment contains the system functions and user defined variables/functions */
    private Environment myEnvironment;

    /**
     * default constructor, sets the environment for the parser to use as reference
     * 
     * @param environment - environment that the parser uses for reference
     */
    public Parser (Environment environment) {
        myEnvironment = environment;

    }

    /**
     * Takes user input and converts it to either
     * 1. CompoundInstruction to be executed on the model
     * 2. An exception to be displayed in the command history
     * 
     * @param line - scanner of data to be parsed into an instruction
     * @return - the instruction that represents the user input
     * @throws IllegalInstructionException
     */
    public Instruction generateInstruction (Scanner line) throws IllegalInstructionException {
        CompoundInstruction resultInstruct = new CompoundInstruction();

        while (line.hasNext()) {
            String commandName = line.next();

            // right now the environment will handle the exceptions
            Instruction base = myEnvironment.systemInstructionSkeleton(commandName);

            base.load(line, this);

            resultInstruct.add(base);

        }
        return resultInstruct;
    }

    /**
     * secondary method signature: can call by using a string instead of a scanner
     * 
     * @param userInput
     * @return
     * @throws IllegalInstructionException
     */
    public Instruction generateInstruction (String userInput) throws IllegalInstructionException {
        Scanner line = new Scanner(userInput);
        return generateInstruction(line);
    }

    public Instruction parseList (Scanner line) {
        StringBuilder sb = new StringBuilder();
        String str = line.next();
        int counterBracket = 1;
        while (counterBracket != 0) {
            str = line.next();
            if (str.equals("[")) {
                counterBracket++;
            }
            if (str.equals("]"))
            {
                counterBracket--;
                if (counterBracket == 0)
                {
                    break;
                }
            }
            sb.append(str);
            sb.append(" ");
        }
        return generateInstruction(sb.toString());
    }
}
