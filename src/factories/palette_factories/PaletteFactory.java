package factories.palette_factories;

import java.awt.Color;
import java.util.Map;
import java.util.ResourceBundle;
import util.Pixmap;
import drawing.GraphicsMap;
import drawing.PriorityPixmap;
import drawing.lines.LineBuilder;
import factories.PrototypeMapFactory;


/**
 * Used to initialize GraphicsMaps for the Palette with all the default options.
 * 
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class PaletteFactory {
    /** Default index of the black color */
    public static final int DEFAULT_BLACK_INDEX = 0;
    /** Default index of the clear color */
    public static final int DEFAULT_CLEAR_INDEX = -1;
    /** Default Turtle image index */
    public static final int DEFAULT_IMAGE_INDEX = 0;

    // index files for creating various maps within palette
    private static final String SHAPE_INDEX =
            "/src/resources/resource_indices/TurtleShapes";

    private static final String LINE_STYLE_LOCATION = "drawing.lines.";
    private static final String LINE_STYLE_INDEX =
            "/src/resources/resource_indices/line_styles_index";
    private static final String BACKGROUND_IMAGE_INDEX =
            "/src/resources/resource_indices/BackgroundImages";
    private static final String COLOR_INDEX =
            "/src/resources/resource_indices/color_indices";

    private ResourceBundle myResources;

    /**
     * Creates a new Factory for palette items based on a passed resourceBundle
     * 
     * @param resources is the resource bundle containing any specific language strings.
     */
    public PaletteFactory (ResourceBundle resources) {
        myResources = resources;
    }

    /**
     * Initializes images from a default text file.
     * 
     * @return A GraphicsMap that maps indices to images (pixmaps)
     */
    public GraphicsMap<Pixmap> initializeImages () {
        ImageMapFactory imf = new ImageMapFactory(SHAPE_INDEX, myResources);
        Map<Integer, Pixmap> shapeMap = imf.buildIndexMap();
        return new GraphicsMap<>(shapeMap);
    }

    /**
     * Initializes default colors from a default text file.
     * 
     * @return A GraphicsMap of indices to colors.
     */
    public GraphicsMap<Color> initializeColors () {
        IndexMapFactory<Color> cmf = new ColorMapFactory(COLOR_INDEX, myResources);
        Map<Integer, Color> colors = cmf.buildIndexMap();

        return new GraphicsMap<Color>(colors);
    }

    /**
     * Initializes line styles from default location.
     * 
     * @return A GraphicsMap from indices to LineBuilders
     */
    public GraphicsMap<LineBuilder> initializeLineStyles () {
        PrototypeMapFactory<LineBuilder> pt = new
                PrototypeMapFactory<LineBuilder>(myResources, LINE_STYLE_INDEX,
                                                 LINE_STYLE_LOCATION);
        Map<Integer, LineBuilder> lineStyles = pt.buildIndexMap();
        return new GraphicsMap<LineBuilder>(lineStyles);
    }

    /**
     * Initializes background images from a default text file.
     * 
     * @return A GraphicsMap from indices to Background images (priority pixmaps).
     */
    public GraphicsMap<PriorityPixmap> initializeBackgroundColorImages () {
        BackgroundMapFactory bgf = new BackgroundMapFactory(BACKGROUND_IMAGE_INDEX,
                                                            myResources);
        Map<Integer, PriorityPixmap> shapeMap = bgf.buildIndexMap();
        return new GraphicsMap<>(shapeMap);
    }

}
