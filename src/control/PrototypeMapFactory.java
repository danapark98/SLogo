package control;

import exceptions.CorruptedEnvironmentException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * Creates a map for all of the keywords to a prototype of the corresponding class <br>
 * <br>
 * Creating the map requires an index file. This is set in the constructor.
 * 
 * @param <V> is the Class of the prototypes that the properties file maps to.
 * 
 * @author Scott Valentine
 * 
 */
public class PrototypeMapFactory<V> extends FileReaderMapFactory<V> {

    private static final String PROPERTIES_SEPERATOR = "[,]";
    private static final char COMMENT_CHARACTER = '#';


    /** Default Language */
    private static final String ENGLISH_LANGUAGE = "English";
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";

    /**
     * Character that indicates a comment when places at beginning of line of
     * Instruction index file.
     */

    private String myIndexFile;

    /** Resources for SLogo */
    private ResourceBundle myResources;

    /**
     * Instantiates the factory based on the language to be used for the
     * commands.
     * 
     * @param language of the commands (must be file in resource
     *        folder)
     * 
     * @param indexFile is the location of the text file that contains prototyping class info.
     */
    public PrototypeMapFactory(String language, String indexFile) {
        try {
            myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
                                                   + language);
        }
        catch (MissingResourceException e) {
            myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
                                                   + ENGLISH_LANGUAGE);
        }
        myIndexFile = indexFile;
    }

    /**
     * Constructor that creates new factory based on the resource bundle of
     * instruction keywords.
     * 
     * @param resources is a resource bundle that contains keywords for
     *        instructions
     * 
     * @param indexFile is the location of the text file that contains prototyping class info.
     */
    public PrototypeMapFactory(ResourceBundle resources, String indexFile) {
        myIndexFile = indexFile;
        myResources = resources;
    }

    /**
     * Builds an instruction map that maps keyword strings to their
     * instructions.
     * 
     * @return Map of keywords to instructions.
     */
    public Map<String, V> buildMap() {
        Scanner line = getScanner(myIndexFile);
        Map<String, V> protoMap =
                new HashMap<String, V>();

        while (line.hasNextLine()) {
            String nextLine = line.nextLine();
            parseLine(protoMap, nextLine);
        }
        line.close();
        return protoMap;
    }

    /**
     * Parses line of instruction and keyword and adds it to the instruction
     * map.
     * 
     * @param protoMap is a map of keywords to instructions.
     * @param line is the current line being read.
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private void parseLine(Map<String, V> protoMap, String line) {
        if (line.charAt(0) != COMMENT_CHARACTER && line.length() > 0) {

            V generic;
            try {
                Class<?> genericClass = Class.forName(line);
                generic = (V) genericClass.newInstance();
            }
            catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
                throw new CorruptedEnvironmentException();
            }

            // gets parameters from line
            String className = getClassName(line);

            String entry = myResources.getString(className);

            String[] keywords = entry.split(PROPERTIES_SEPERATOR);

            for (String keyword : keywords) {
                if (keyword.length() > 0) {
                    protoMap.put(keyword, generic);
                }
            }
        }
    }

    /**
     * Determines the name of the class from the class path.
     * 
     * @param classPath is the classPath to determine the class name.
     * @return The name of the class at the given classPath.
     */
    private String getClassName(String classPath) {
        String[] path = classPath.split("[.]");
        String str = path[path.length - 1];
        return str;
    }
}
