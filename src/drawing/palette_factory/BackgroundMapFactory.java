package drawing.palette_factory;

import drawing.PriorityPixmap;

/**
 * Factory that creates a mapping of indices to PriorityPixmaps from info from a text file.
 * 
 * @author Scott Valentine
 *
 */
public class BackgroundMapFactory extends IndexMapFactory<PriorityPixmap> {

    protected BackgroundMapFactory(String indexFileLocation) {
        super(indexFileLocation);
    }

    @Override
    protected PriorityPixmap getObject(String objectData) {
        String[] parts = objectData.split(" ");
        
        String fileName = parts[0];
        int priority = Integer.parseInt(parts[1]);
                
        return new PriorityPixmap(fileName, priority);
    }

}
