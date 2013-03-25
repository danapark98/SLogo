package drawing.palette_factory;

import util.Pixmap;

/**
 * Factory that reads in picture data from text file to create map of indices to images.
 * 
 * @author Scott Valentine
 *
 */
public class ImageMapFactory extends IndexMapFactory<Pixmap> {

    protected ImageMapFactory(String indexFileLocation) {
        super(indexFileLocation);
    }

    @Override
    protected Pixmap getObject(String objectData) {
        return new Pixmap(objectData.trim());
    }

}
