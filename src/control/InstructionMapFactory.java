package control;

import instructions.BaseInstruction;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * Creates a map for all of the instructions in the user defined text file of
 * each instruction's keyword to an instance of the instruction. <br>
 * <br>
 * Creating the map requires an instruction index file. <br>
 * <br>
 * To change the instruction index file, change the value of the static field
 * INSTRUCTION_INDEX_FILE
 * 
 * @author Scott Valentine
 * 
 */
public class InstructionMapFactory {

    /** Location of all instruction classpath data. */
    private static final String INSTRUCTION_INDEX_FILE =
            "/src/resources/instruction_index.txt";
    private static final String PROPERTIES_SEPERATOR = "[,]";

    /**
     * Character that indicates a comment when places at beginning of line of
     * Instruction index file.
     */
    private static final char COMMENT_CHARACTER = '#';

    private ResourceBundle myResources;


    /**
     * Constructor that creates new factory based on the resource bundle of
     * instruction keywords.
     * 
     * @param resources is a resource bundle that contains keywords for
     *        instructions
     */
    public InstructionMapFactory (ResourceBundle resources) {
        myResources = resources;
    }

    /**
     * Builds an instruction map that maps keyword strings to their
     * instructions.
     * 
     * @return Map of keywords to instructions.
     */
    public Map<String, BaseInstruction> buildInstructionMap () {

        String currentDirectory = System.getProperty("user.dir");

        FileReader fileToBeRead = null;
        try {
            fileToBeRead = new FileReader(currentDirectory + INSTRUCTION_INDEX_FILE);
        }
        catch (FileNotFoundException e) {
            throw new MissingResourceException("Missing instruction class names", "", "");
        }
        Scanner line = new Scanner(fileToBeRead);

        Map<String, BaseInstruction> instructionMap =
                new HashMap<String, BaseInstruction>();

        while (line.hasNextLine()) {
            String nextLine = line.nextLine();
            parseLine(instructionMap, nextLine);
        }
        line.close();
        return instructionMap;
    }

    /**
     * Parses line of instruction and keyword and adds it to the instruction
     * map.
     * 
     * @param instructionMap is a map of keywords to instructions.
     * @param line is the current line being read.
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    private void parseLine (Map<String, BaseInstruction> instructionMap, String line) {
        if (line.charAt(0) != COMMENT_CHARACTER && line.length() > 0) {

            BaseInstruction instruct;
            try {
                Class<?> instructionClass = Class.forName(line);
                instruct = (BaseInstruction) instructionClass.newInstance();
            }
            catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
                // if not possible, skip
                return;
            }
            // gets parameters from line
            String className = getClassName(line);

            String entry = myResources.getString(className);

            String[] keywords = entry.split(PROPERTIES_SEPERATOR);

            for (String keyword : keywords) {
                instructionMap.put(keyword, instruct);
            }
        }
    }

    /**
     * Determines the name of the class from the class path.
     * 
     * @param classPath is the classPath to determine the class name.
     * @return The name of the class at the given classPath.
     */
    private String getClassName (String classPath) {
        String[] path = classPath.split("[.]");
        String str = path[path.length - 1];
        return str;
    }
}
