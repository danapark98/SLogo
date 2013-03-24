package control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Scanner;


public abstract class FileReaderMapFactory<V> {
    
    public static final String USER_DIR = "user.dir";
    private static final String ERROR_MESSAGE = "Missing class names";

    /**
     * Builds a map of default keys to V types.
     * 
     * @return A Map of keys to V types.
     */
    public abstract Map<?, V> buildMap ();

    protected Scanner getScanner(String indexFile) {
        FileReader fileToBeRead = null;
        String currentDirectory = System.getProperty(USER_DIR);
        try {
            fileToBeRead = new FileReader(currentDirectory + indexFile);
        }
        catch (FileNotFoundException e) {
            throw new MissingResourceException(ERROR_MESSAGE, "", "");
        }
        Scanner line = new Scanner(fileToBeRead);
        return line;
    }
    
}
