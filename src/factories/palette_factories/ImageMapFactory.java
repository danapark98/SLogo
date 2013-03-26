package factories.palette_factories;

import java.util.ResourceBundle;
import util.Pixmap;


/**
 * Factory that reads in picture data from text file to create map of indices to images.
 * 
 * @author Scott Valentine
 * 
 */
public class ImageMapFactory extends IndexMapFactory<Pixmap> {

    protected ImageMapFactory (String indexFileLocation, ResourceBundle resources) {
        super(indexFileLocation, resources);
    }

    @Override
    protected Pixmap getObject (String objectData) {
        return new Pixmap(objectData.trim());
    }

}
