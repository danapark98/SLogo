package drawing;

import drawing.lines.LineBuilder;
import exceptions.IllegalInstructionException;
import java.awt.Color;

import util.Pixmap;


/**
 * Represents all of the available colors, lines, and shapes in the active environment.
 * 
 * @author Scott Valentine, Ellango
 * 
 */
public class Palette {
    private static final String COLOR_VALUE_OUT_OF_RANGE = "Not valid color value";
    private static final int MAX_COLOR_VALUE = 255;
    
    private GraphicsMap<Color> myColors;
    private GraphicsMap<LineBuilder> myLineStyles;
    private GraphicsMap<Pixmap> myImages;
    
    private int myCurrentColorIndex;
    private int myCurrentImageIndex;


    /**
     * Constructs Palette with default colors, linestyles, and shapes
     */
    public Palette() {
        myColors = PaletteFactory.initializeColors();
        myLineStyles = PaletteFactory.initializeLineStyles();
        myImages = PaletteFactory.initializeImages();
    }

    /**
     * Returns the color at the given index.
     * 
     * @param index of the color.
     * @return The color at the index.
     * @throws IllegalInstructionException occurs when the index has not yet been defined.
     */
    public Color getColor(int index) throws IllegalInstructionException {
        Color color = myColors.get(index);
        myCurrentColorIndex = index;
        return color;
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
        myColors.put(index, new Color(r, g, b));
    }
    
    /**
     * Gives the current index of the color being used.
     * 
     * @return The index of the current color.
     */
    public int getColorIndex() {
        return myCurrentColorIndex;
    }
    
    /**
     * Returns the image of the Turtle to paint at the given index 
     * 
     * @param index of the turtle image
     * @return Pixmap that represents Turtle's view
     * @throws IllegalInstructionException 
     */
    public Pixmap getImage(int index) throws IllegalInstructionException {
        Pixmap image = myImages.get(myCurrentImageIndex);
        myCurrentImageIndex = index;
        return image;
    }
    
    /**
     * Gives the current index of the image being used.
     * 
     * @return The index of the current image.
     */   
    public int getImageIndex() {
        return myCurrentImageIndex;
    }
    
    /**
     * Returns the line style at the given index.
     * 
     * @param index of the line style
     * @return a LineBuilder that builds lines of a certain style.
     * @throws IllegalInstructionException if a line style not defined for that index
     */
    public LineBuilder getLineStyle(int index) throws IllegalInstructionException {
        return myLineStyles.get(index);
    }
    
}
