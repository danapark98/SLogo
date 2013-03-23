package drawing;

import drawing.lines.LineBuilder;
import exceptions.IllegalInstructionException;
import java.awt.Color;

import util.Pixmap;


/**
 * Represents all of the available colors in the active environment.
 * 
 * @author Scott Valentine
 * 
 */
public class Palette {
    /** Error message for undefined indices in palettes */
    public static final String UNDEFINED_INDEX_MESSAGE = "Not defined at index: ";
    
    private static final String COLOR_VALUE_OUT_OF_RANGE = "Not valid color value";
    private static final int MAX_COLOR_VALUE = 255;

    private ColorPalette myColorPalette;
    private LinePalette myLinePalette;
    private ImagePalette myImagePalette;
    
    private int myCurrentImageIndex;
    private int myCurrentColorIndex;

    /**
     * Constructs a Palette with the two default colors: clear and black
     */
    public Palette() {
    	myImagePalette = new ImagePalette();
        myColorPalette = new ColorPalette();
        myLinePalette = new LinePalette();
    }

    /**
     * Returns the color at the given index.
     * 
     * @param index of the color.
     * @return The color at the index.
     * @throws IllegalInstructionException Occurs when the index has not yet been defined.
     */
    public Color getColor(int index) throws IllegalInstructionException {
        myCurrentColorIndex = index;
        return myColorPalette.getColor(index);
    }
    
    public Pixmap getImage(int index) throws IllegalInstructionException {
    	myCurrentImageIndex = index;
    	return myImagePalette.getImage(index);
    }

    /**
     * Adds a new color to the palette.
     * 
     * @param index is where the color will be added in the palette.
     * @param r is the red component of the color
     * @param g is the greeen component of the color
     * @param b is the blue component of the color
     * @throws IllegalInstructionException This occurs when the index is not valid (index <= 0.)
     */
    public void addColor(int index, int r, int g, int b) throws IllegalInstructionException {
        if (r < 0 || r > MAX_COLOR_VALUE || g < 0 || g > MAX_COLOR_VALUE || 
                b < 0 || b > MAX_COLOR_VALUE) {
            throw new IllegalInstructionException(COLOR_VALUE_OUT_OF_RANGE);
        }
        myColorPalette.setColorAt(index, r, g, b);
    }

    /**
     * Returns the line style at the given index.
     * 
     * @param index of the line style
     * @return a LineBuilder that builds lines of a certain style.
     * @throws IllegalInstructionException This occurs when the index has not yet been defined for
     *         the palette.
     */
    public LineBuilder getLineStyle(int index) throws IllegalInstructionException {
        return myLinePalette.getLineStyle(index);
    }

    /**
     * Gives the current index of the color being used.
     * 
     * @return The index of the current color.
     */
    public int currentColorIndex() {
        return myCurrentColorIndex;
    }
    /**
     * Gives the current index of the image being used.
     * 
     * @return The index of the current image.
     */   
    public int getCurrentImageIndex() {
    	return myCurrentImageIndex;
    }
}
