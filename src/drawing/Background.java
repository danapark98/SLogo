package drawing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import exceptions.CorruptedEnvironmentException;
import exceptions.IllegalInstructionException;
import simulation.Turtle;
import util.Pixmap;
import view.View;

/**
 * Represents all possible background layers for the Canvas.  The Background is 
 * paintable and paints all images in a pre-defined order.
 * 
 * 
 * Currently supports two layers: one grid, and one color image, but more can be
 * added by adding additional Maps for each type of background, and additional
 * indices
 * 
 * @author Ellango, Ryan
 *
 */
public class Background {
    private static final int GRID_PRIORITY = 0;
    private static final int WHITE_BACKGROUND_INDEX = 0;
    private static final PriorityPixmap GRID = 
            new PriorityPixmap("grid.png", GRID_PRIORITY);
    
    private Palette myPalette;
    private List<PriorityPixmap> myActiveImages;
    private int myCurrentColorImageIndex;
    
    /**
     * Constructs a BackgroundLayer with a white background image
     * 
     * @param grid
     * @param colorImages
     */
    public Background(Palette palette) {
        myPalette = palette;
        myActiveImages = new ArrayList<PriorityPixmap>();
        try {
            myActiveImages.add(myPalette.getBackgroundColorImage(WHITE_BACKGROUND_INDEX));
        }
        catch (IllegalInstructionException e) {
            throw new CorruptedEnvironmentException();
        }
        myCurrentColorImageIndex = WHITE_BACKGROUND_INDEX;
    } 
    
    /**
     * Paints the canvas with all the background layers in order
     * 
     * @param pen
     */
    public void paint (Graphics2D pen) {
        Collections.sort(myActiveImages);
        for (Pixmap image : myActiveImages) {
            image.paint(pen, Turtle.startingLocation(), View.PREFERRED_CANVAS_SIZE);
        }
    }
    
    /**
     * Turns the grid on
     */
    public void gridOn() {
        // don't add it again
        if (!myActiveImages.contains(GRID))
            myActiveImages.add(GRID);
    }
    
    /**
     * Turns the grid off
     */
    public void gridOff() {
        myActiveImages.remove(GRID);
    }
    
    /**
     * Switches the background color image with the one specified in the Palette
     * at the provided index
     * 
     * Adds the new image, and then removes the old.
     * 
     * @param index corresponding to the new image
     * @throws IllegalInstructionException if no image corresponds to the provided
     * index
     */
    public void switchColorImage(int index) throws IllegalInstructionException {
        // don't add it again
        if (myCurrentColorImageIndex != index) {
            myActiveImages.add(myPalette.getBackgroundColorImage(index));
            myActiveImages.remove(myPalette.getBackgroundColorImage(myCurrentColorImageIndex));
            myCurrentColorImageIndex = index;
        }
    }

}
