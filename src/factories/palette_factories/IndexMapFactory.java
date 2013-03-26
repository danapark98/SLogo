package factories.palette_factories;

import java.util.ResourceBundle;
import factories.MapFactory;


/**
 * Abstract Class that acts as a factory for maps that map integer indices
 * to objects. This class allows for object data to be read in from file. <br>
 * An example line from file:<br>
 * 0 = turtle.gif <br>
 * In this case the data needed to create <code> V </code> object is the string:
 * "turtle.gif"
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 * @param <V> is the object to which integer indices map.
 */
public abstract class IndexMapFactory<V> extends MapFactory<Integer, V> {

    private String myIndexFile;

    protected IndexMapFactory (String indexFileLocation, ResourceBundle resources) {
        super(resources);
        myIndexFile = indexFileLocation;
    }

    @Override
    protected V getMapValue (String[] restOfLine) {
        String objectData = "";
        for (int i = 1; i < restOfLine.length; ++i) {
            objectData += " " + restOfLine[i].trim();
        }
        return getObject(objectData.trim());
    }

    protected abstract V getObject (String objectData);

    @Override
    protected String getIndexFile () {
        return myIndexFile;
    }
}
