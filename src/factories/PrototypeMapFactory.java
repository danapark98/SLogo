package factories;

import exceptions.CorruptedEnvironmentException;
import java.util.HashMap;
import java.util.Map;
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
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class PrototypeMapFactory<V> extends MapFactory<String, V> {

    private static final String PROPERTIES_SEPERATOR = "[,]";

    /**
     * Character that indicates a comment when places at beginning of line of
     * Instruction index file.
     */

    private String myIndexFile;
    private String myPackageLocation;

    /**
     * Constructor that creates new factory based on the resource bundle of
     * instruction keywords.
     * 
     * @param resources is a resource bundle that contains keywords for
     *        instructions
     * 
     * @param indexFile is the location of the text file that contains prototyping class info.
     * 
     * @param packageLoc is the location of the package where all the classes to be 
     *        prototyped are located
     */
    public PrototypeMapFactory(ResourceBundle resources, String indexFile, String packageLoc) {
        super(resources); 
        myPackageLocation = packageLoc;
        myIndexFile = indexFile;
    }

    /**
     * Builds an instruction map that maps keyword strings to their
     * instructions.
     * 
     * @return Map of keywords to instructions.
     */
    public Map<String, V> buildStringMap() {
        Scanner line = getScanner();
        Map<String, V> protoMap =
                new HashMap<String, V>();

        while (line.hasNextLine()) {
            String nextLine = line.nextLine();
            if (!commentedLine(nextLine)) {
                V generic = getValue(nextLine);

                String[] keyWords = getKeys(nextLine);

                for (String keyword : keyWords) {
                    if (keyword.length() > 0) {
                        protoMap.put(keyword, generic);
                    }
                }           
            }
        }
        line.close();
        return protoMap;
    }


    private String[] getKeys(String line) {
        String className = getClassName(line);
        String entry = getResources().getString(className);
        return entry.split(PROPERTIES_SEPERATOR);
    }

    @Override
    protected V getMapValue(String[] restOfLine) {
        return getValue(restOfLine[1].trim());
    }

    @SuppressWarnings("unchecked")
    private V getValue(String className) {
        String classPath = myPackageLocation + className;
        try {
            Class<?> genericClass = Class.forName(classPath);
            return (V) genericClass.newInstance();
        }
        catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            throw new CorruptedEnvironmentException();
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

    @Override
    protected String getIndexFile() {
        return myIndexFile;
    }

}
