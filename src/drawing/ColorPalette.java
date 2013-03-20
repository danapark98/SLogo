package drawing;

import exceptions.IllegalInstructionException;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track and manages all colors that are in user in the workspace.
 * 
 * @author Scott Valentine
 *
 */
public class ColorPalette {

    /** Default index of the black color */
    public static final int DEFAULT_BLACK_INDEX = 0;
    
    /** Default index of the clear color */
    public static final int DEFAULT_CLEAR_INDEX = -1;

    private static final String INDEX_OUT_OF_BOUNDS_ERROR = "Index of ";
    
    /** Default Clear color value */
    private static final Color CLEAR = new Color(0, 0, 0, 0);

    private Map<Integer, Color> myColors;

    /**
     * Constructs a Palette with the two default colors: clear and black
     */
    public ColorPalette() {
        // reserve negative numbers for clear color

        myColors = new HashMap<Integer, Color>();

        myColors.put(DEFAULT_CLEAR_INDEX, CLEAR);
        myColors.put(DEFAULT_BLACK_INDEX, Color.BLACK);
    }

    /**
     * Sets the color at a specified index to the user defined value.
     * 
     * @param index of the color to be edited (or added if index is not yet in use)
     * @param r is the res component of the color
     * @param g is the green component of the color
     * @param b is the blue component of the color
     * @throws IllegalInstructionException This occurs when a zero or negative index is called.
     */
    public void setColorAt(int index, int r, int g, int b) throws IllegalInstructionException {
        if (index <= 0) { throw new IllegalInstructionException(INDEX_OUT_OF_BOUNDS_ERROR + index); }
        Color userDef = new Color(r, g, b);
        myColors.put(index, userDef);

    }

    /**
     * Returns the color that corresponds to the given index.
     * 
     * @param index is the location of the color in the ColorPalette.
     * @return The color at the index.
     * @throws IllegalInstructionException This occurs when the index is currently 
     * undefined in the palette.
     */
    public Color getColor(int index) throws IllegalInstructionException {
        if (!myColors.containsKey(index)) {
            throw new IllegalInstructionException(Palette.UNDEFINED_INDEX_MESSAGE + index);
        }
        return myColors.get(index);
    }

}
