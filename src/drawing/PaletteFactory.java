package drawing;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import simulation.Turtle;
import util.Pixmap;
import drawing.lines.DashedLine;
import drawing.lines.LineBuilder;
import drawing.lines.RainbowLine;
import drawing.lines.SolidLine;

/**
 * Used to initialize GraphicsMaps for the Palette with all the default options
 * 
 * TODO: try to do this without hardcoding, similar to InstructionMapFactory
 *
 */
public class PaletteFactory {
    /** Default index of the black color */
    public static final int DEFAULT_BLACK_INDEX = 0;
    
    /** Default index of the clear color */
    public static final int DEFAULT_CLEAR_INDEX = -1;
    
    /** Default Clear color value */
    private static final Color CLEAR = new Color(0, 0, 0, 0);
    
    /** Constants for shape indices. */
    public static final int DEFAULT_INVISIBLE_INDEX = -1;
    public static final int DEFAULT_IMAGE_INDEX = 0;
    public static final int DOG_IMAGE_INDEX = 1;
    public static final int SQUIRREL_IMAGE_INDEX = 2;
    public static final int UNICORN_IMAGE_INDEX = 3;

    
    public static GraphicsMap<Color> initializeColors() {
        Map<Integer, Color> colors = new HashMap<Integer, Color>();
        colors.put(DEFAULT_CLEAR_INDEX, CLEAR);
        colors.put(DEFAULT_BLACK_INDEX, Color.BLACK);
        return new GraphicsMap<Color>(colors);
    }
    
    public static GraphicsMap<LineBuilder> initializeLineStyles() {
        Map<Integer, LineBuilder> lineStyles = new HashMap<Integer, LineBuilder>();
        lineStyles.put(SolidLine.PALETTE_INDEX, new SolidLine());
        lineStyles.put(DashedLine.PALETTE_INDEX, new DashedLine());
        lineStyles.put(RainbowLine.RAINBOW_INDEX, new RainbowLine());
        return new GraphicsMap<LineBuilder>(lineStyles);
    }
    
    public static GraphicsMap<Pixmap> initializeImages() {
        Map<Integer, Pixmap> images = new HashMap<Integer, Pixmap>();
        images.put(DEFAULT_INVISIBLE_INDEX, new Pixmap("blank.png"));
        images.put(DEFAULT_IMAGE_INDEX, Turtle.DEFAULT_IMAGE);
        images.put(DOG_IMAGE_INDEX, new Pixmap("dog.jpg"));
        images.put(SQUIRREL_IMAGE_INDEX, new Pixmap("squirrel.png"));
        images.put(UNICORN_IMAGE_INDEX, new Pixmap("Unicorn.jpg"));
        return new GraphicsMap<Pixmap>(images);
    }
}
