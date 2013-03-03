package control;

import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import java.util.Scanner;
import exceptions.IllegalInstructionException;


/**
 * Converts a user inputed string into an Instruction that can execute on the model.
 * 
 * @author Scott Valentine
 * 
 */
public class Parser {

    /** environment contains the system functions and user defined variables/functions */
    private Environment myEnvironment;

    /**
     * Default constructor: sets the environment for the parser to use as reference.
     * 
     * @param environment that the parser uses for reference to build instructions
     */
    public Parser (Environment environment) {
        myEnvironment = environment;
    }

    /**
     * Takes user input and converts it to either
     * 1. CompoundInstruction to be executed on the model
     * 2. An exception to be displayed in the command history
     * 
     * @param userInput - A string of data to be parsed into an instruction.
     * @return The instruction that represents the user input.
     * @throws IllegalInstructionException If the instruction is not mapped in the
     *         environment, throw this exception with argument of the incompatible string
     */
    public Instruction generateInstruction (String userInput) throws IllegalInstructionException {
        Scanner line = new Scanner(userInput);
        return generateInstruction(line);
    }

    /**
     * Takes user input and converts it to either
     * 1. CompoundInstruction to be executed on the model
     * 2. An exception to be displayed in the command history
     * 
     * @param line - A scanner of data to be parsed into an instruction.
     * @return The instruction that represents the user input.
     * @throws IllegalInstructionException If the instruction is not mapped in the
     *         environment, throw this exception with argument of the incompatible string
     */
    private Instruction generateInstruction (Scanner line) throws IllegalInstructionException {
        CompoundInstruction resultInstruct = new CompoundInstruction();
        while (line.hasNext()) {
            String commandName = line.next();
            commandName = commandName.toLowerCase();
            // ignore copied and pasted commands
            if (commandName.startsWith(">>")) {
                commandName = line.nextLine();
            }
            try {
                Instruction result = new ConstantInstruction(Integer.parseInt(commandName));
                resultInstruct.add(result);
            }
            catch (NumberFormatException e) {
                BaseInstruction base = myEnvironment.systemInstructionSkeleton(commandName);
                base.load(line, this);
                resultInstruct.add(base);
            }
        }
        return resultInstruct;
    }

    /**
     * Parses through the next complete instruction. <br>
     * <br>
     * <u>Example:</u> <br>
     * If the input comes from the Scanner that represents the
     * string "fd 50 backward 50"
     * then
     * nextInstruction will return the instruction fd 50.
     * 
     * @param line is the data to be parsed into an instruction.
     * @return The next instruction from the scanner, line.
     * @throws IllegalInstructionException if instruction not recognized
     */
    public Instruction nextInstruction (Scanner line) throws IllegalInstructionException {
        if (!line.hasNext())
            throw new IllegalInstructionException("");
        String next = line.next();
        if (next.equals("[")) {
            next = parseList(line);
        }
        return generateInstruction(next);
    }

    /**
     * Parses a list into an instruction that represents the contents of the list.
     * 
     * @param line is a scanner that iterates through the list.
     * @return an instruction that is made from the list.
     * @throws IllegalInstructionException if list not constructed properly
     */
    private String parseList (Scanner line) throws IllegalInstructionException {
        StringBuilder sb = new StringBuilder();
        String str = "";
        int counterBracket = 1;
        while (counterBracket != 0) {
            if (!line.hasNext()) {
                throw new IllegalInstructionException("");
            }
            str = line.next();
            // TODO: use resources to define the brackets
            if (str.equals("[")) {
                counterBracket++;
            }
            if (str.equals("]")) {
                counterBracket--;
                if (counterBracket == 0) {
                    break;
                }
            }
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }
}
