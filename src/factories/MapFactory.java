package factories;

import control.Controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * Abstract Class that acts as a factory for maps that map keys
 * to objects. This class allows for object data to be read in from file.
 * This class works for creating maps that are read from a text file.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 * @param <V> is the object to which keys map.
 * @param <K> is the key that maps to the object.
 */
public abstract class MapFactory<K, V> {

    private static final String ERROR_MESSAGE_KEY = "map_initiation_error";
    private static final String COMMENT_CHARACTER_KEY = "comment";

    private ResourceBundle myResources;

    protected MapFactory(ResourceBundle resources) {
        myResources = resources;

        myResources.getString(COMMENT_CHARACTER_KEY);

    }

    protected ResourceBundle getResources() {
        return myResources;
    }

    /**
     * Creates a scanner from the file to read in
     * 
     * 
     * @return Scanner
     */
    protected Scanner getScanner() {
        FileReader fileToBeRead = null;
        String currentDirectory = System.getProperty(Controller.USER_DIR);
        try {
            fileToBeRead = new FileReader(currentDirectory + getIndexFile());
        }
        catch (FileNotFoundException e) {
            throw new MissingResourceException(myResources.getString(ERROR_MESSAGE_KEY), "", "");
        }
        Scanner line = new Scanner(fileToBeRead);
        return line;
    }

    /**
     * Returns if line is commented so that it will not be read
     * 
     * @param line to read
     * @return true if commented line
     */
    protected boolean commentedLine(String line) {
        return line.charAt(0) == myResources.getString(COMMENT_CHARACTER_KEY).charAt(0) ||
               line.length() <= 0;
    }

    /**
     * Builds a Map of prototypes based on index in text file.
     * 
     * @return
     */
    public Map<Integer, V> buildIndexMap() {
        Scanner line = getScanner();
        Map<Integer, V> protoMap =
                new HashMap<Integer, V>();

        while (line.hasNextLine()) {
            String currentLine = line.nextLine();
            if (!commentedLine(currentLine)) {
                String[] data = currentLine.split("=");
                int key = Integer.parseInt(data[0].trim());
                protoMap.put(key, getMapValue(data));
            }
        }
        line.close();
        return protoMap;
    }

    protected abstract V getMapValue(String[] restOfLine);

    /**
     * 
     * @return the string of the file name
     */
    protected abstract String getIndexFile();
}
