package drawing;

import java.awt.Dimension;
import util.Location;
import util.Pixmap;
import util.Sprite;

/**
 * Represents a stamp of the turtle in the workspace.
 * 
 * @author Scott Valentine
 *
 */
public class StampSprite extends Sprite {

    /**
     * Creates a new stamp in the workspace.
     * 
     * @param image of the stamp.
     * @param center of the stamp.
     * @param size of the stamp.
     */
    public StampSprite(Pixmap image, Location center, Dimension size) {
        super(image, center, size);
       
    }

}
