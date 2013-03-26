package control.factories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Scanner;

import control.Controller;

/**
Abstract Class that acts as a factory for maps that map integer indices
* to objects. This class allows for object data to be read in from file.
* This class works for creating maps that are read from a text file.
* <br>
* An example line from file:<br>
* 0 = turtle.gif
* <br>
* In this case the data needed to create <code> V </code> object is the string:
* "turtle.gif"
* 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
*
* @param <V> is the object to which integer indices map.
*/
public abstract class MapFactory<V> {

	public static final String ERROR_MESSAGE = "Missing class names";
	public static final char COMMENT_CHARACTER = '#';


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
		} catch (FileNotFoundException e) {
			throw new MissingResourceException(ERROR_MESSAGE, "", "");
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
		return line.charAt(0) == COMMENT_CHARACTER || line.length() <= 0;
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