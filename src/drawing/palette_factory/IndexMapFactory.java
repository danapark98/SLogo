package drawing.palette_factory;
import control.factories.TextFileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Abstract Class that acts as a factory for maps that map integer indices
 * to objects. This class allows for object data to be read in from file.
 * <br>
 * An example line from file:<br>
 * 0 = turtle.gif
 * <br>
 * In this case the data needed to create <code> V </code> object is the string:
 * "turtle.gif"
 * 
 * @author Scott Valentine
 *
 * @param <V> is the object to which integer indices map.
 */
public abstract class IndexMapFactory<V> extends TextFileReader<V> {

    private String myIndexFile;

    protected IndexMapFactory(String indexFileLocation) {
        myIndexFile = indexFileLocation;
    }

    /**
     * Builds an mapping of indices to objects
     * 
     * @return
     */
    public Map<Integer, V> buildMap() {
        Map<Integer, V> result = new HashMap<Integer, V>();

        Scanner fileScanner = getScanner(myIndexFile);

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            if (!commentedLine(currentLine)) {
                String[] data = currentLine.split("[=]");

                int key = Integer.parseInt(data[0].trim());

                String objectData = "";
                for (int i = 1; i < data.length; ++i) {
                    objectData += " " + data[i].trim();
                }

                V value = getObject(objectData.trim());

                result.put(key, value);
            }
        }
        fileScanner.close();
        return result;

    }

    protected abstract V getObject(String objectData);
}
