package drawing;

import drawing.lines.LineBuilder;
import exceptions.IllegalInstructionException;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import util.Pixmap;


/**
 * Represents all of the available colors, lines, and shapes in the active environment.
 * 
 * @author Scott Valentine, Ellango
 * 
 */
public class Palette implements Serializable {

    private static final long serialVersionUID = 8628824944325378976L;
    private static final String COLOR_VALUE_OUT_OF_RANGE = "Not valid color value";
    private static final int MAX_COLOR_VALUE = 255;
    
    private GraphicsMap<Color> myColors;
    private GraphicsMap<LineBuilder> myLineStyles;
    private GraphicsMap<Pixmap> myImages;


    /**
     * Constructs Palette with default colors, linestyles, and shapes
     */
    public Palette() {
        myColors = PaletteFactory.initializeColors();
        myLineStyles = PaletteFactory.initializeLineStyles();
        myImages = PaletteFactory.initializeImages();
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
     * Returns the color at the given index.
     * 
     * @param index of the color.
     * @return The color at the index.
     * @throws IllegalInstructionException occurs when the index has not yet been defined.
     */
    public Color getColor(int index) throws IllegalInstructionException {
        return myColors.get(index);
    }
    
    /**
     * Returns the image of the Turtle to paint at the given index 
     * 
     * @param index of the turtle image
     * @return Pixmap that represents Turtle's view
     * @throws IllegalInstructionException 
     */
    public Pixmap getImage(int index) throws IllegalInstructionException {
        return myImages.get(index);

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

    /**
     * Save the mutable state of the palette to be loaded in later
     * 
     * @param out to write objects needed later
     * @throws IOException if objects can't be written
     */
    public void save (ObjectOutput out) throws IOException {
        out.writeObject(myColors);
    }

    /**
     * Load in the mutable state of the palette
     * 
     * Objects must be loaded in the same order they were saved.
     * 
     * @param in to read objects in 
     * @throws ClassNotFoundException if file not saved properly or objects read
     * in wrong order
     * @throws IOException if objects can't be read
     */
    @SuppressWarnings("unchecked")
    public void load (ObjectInput in) throws ClassNotFoundException, IOException {
        myColors = (GraphicsMap<Color>) in.readObject();
    }
}
