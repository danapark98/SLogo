package control;

import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import instructions.user_defined.VariableInstruction;
import java.util.Scanner;
import exceptions.IllegalInstructionException;


/**
 * Converts a user inputed string into an Instruction that can execute on the model.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Parser {

    /** Environment contains the system functions and user defined variables/functions. */
    private Environment myEnvironment;

    /**
     * Creates new parser that uses an environment for to create new instructions from text.
     * 
     * @param environment contains information to convert text into relevant instructions.
     */
    public Parser (Environment environment) {
        myEnvironment = environment;
    }

    /**
     * Takes user input and converts it to either
     * <ol>
     * <li>CompoundInstruction to be executed on the model.</li>
     * <li>An exception to be displayed in the command history.</li>
     * </ol>
     * 
     * @param userInput - A string of data to be parsed into an instruction.
     * @return The instruction that represents the user input.
     * @throws IllegalInstructionException If the instruction is not mapped in the
     *         environment, throw this exception with argument of the incompatible string
     */
    public Instruction generateInstruction (String userInput) throws IllegalInstructionException {
        Preparser preparser = new Preparser(myEnvironment);
        String s = preparser.preParse(userInput);
        Scanner line = new Scanner(s);
        return generateInstruction(line);
    }

    /**
     * Takes user input and creates an executable instruction
     * <ol>
     * <li>CompoundInstruction to be executed on the model.</li>
     * <li>An exception to be displayed in the command history.</li>
     * </ol>
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

            Instruction result;
            if (commandName.equals("[")) {
                result = generateInstruction(new Scanner(parseList(line)));
            }
            else if (commandName.charAt(0) == ':') {
                result = new VariableInstruction(commandName);
            }
            else {
                try {
                    result = new ConstantInstruction(Integer.parseInt(commandName));
                }
                catch (NumberFormatException e) {
                    BaseInstruction base = myEnvironment.systemInstructionSkeleton(commandName);
                    base.load(line, this);
                    result = base;
                }
            }
            resultInstruct.add(result);
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
        return generateInstruction(new Scanner(next));
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
            if (!line.hasNext()) throw new IllegalInstructionException("");
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
