package control.factories;

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
 * 
 */
public class PrototypeMapFactory<V> extends TextFileReader<V> {
    
    private static final String PROPERTIES_SEPERATOR = "[,]";

    /**
     * Character that indicates a comment when places at beginning of line of
     * Instruction index file.
     */

    private String myIndexFile;
    private String myPackageLocation;

    private ResourceBundle myResources;

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
        this(indexFile, packageLoc);
        myResources = resources;    
    }
    
    /**
     * Constructs a new factory that can build Index Maps.
     * 
     * @param indexFile is the location of the text file that contains prototyping class info.
     * @param packageLoc is the location of the package where all the classes to be 
     *        prototyped are located
     */
    public PrototypeMapFactory(String indexFile, String packageLoc) {
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
        Scanner line = getScanner(myIndexFile);
        Map<String, V> protoMap =
                new HashMap<String, V>();

        while (line.hasNextLine()) {
            String nextLine = line.nextLine();
            if (!commentedLine(nextLine)) {
                String classPath = myPackageLocation + nextLine;
                V generic = getValue(classPath);

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
    
    /**
     * Builds a Map of prototypes based on index in text file.
     * 
     * @return
     */
    public Map<Integer, V> buildIndexMap() {
        Scanner line = getScanner(myIndexFile);
        Map<Integer, V> protoMap =
                new HashMap<Integer, V>();
        
        
        while (line.hasNextLine()) {           
            String currentLine = line.nextLine();            
            if (!commentedLine(currentLine)) {
                String[] data = currentLine.split("=");
                int key = Integer.parseInt(data[0].trim());
                
                String className = data[1].trim();
                
                String classPath = myPackageLocation + className;       
                V val = getValue(classPath);
                protoMap.put(key, val);
            }        
        }
        line.close();
        return protoMap;
    }
    
    private String[] getKeys(String line) {
        String className = getClassName(line);
        String entry = myResources.getString(className);
        return entry.split(PROPERTIES_SEPERATOR);
    }

    @SuppressWarnings("unchecked")
    private V getValue(String classPath) {
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

}
