package drawing;

import java.awt.Dimension;
import java.awt.Graphics2D;
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
    
    /**
     * When painted, original parameters of location, size, and velocity are reset
     * so that these fields are, in effect, immutable (at least graphically)
     */
    @Override
    public void paint (Graphics2D pen) {
        reset();
        super.paint(pen);
    }

}
