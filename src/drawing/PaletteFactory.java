package drawing;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Scanner;

import control.Controller;
import control.factories.PrototypeMapFactory;
import simulation.Turtle;
import util.Pixmap;
import drawing.lines.DashedLine;
import drawing.lines.LineBuilder;
import drawing.lines.RainbowLine;
import drawing.lines.SolidLine;
import exceptions.CorruptedEnvironmentException;

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

    private static final String SHAPE_INDEX_FILE = 
            "/src/resources/TurtleShapes";

    /** Error Message to display when a class can not be instantiated from file*/
    private static final String ERROR_MESSAGE = "Missing instruction class names";

    /** Constants for shape indices. */
    public static final int DEFAULT_INVISIBLE_INDEX = -1;
    public static final int DEFAULT_IMAGE_INDEX = 0;
    public static final int DOG_IMAGE_INDEX = 1;
    public static final int SQUIRREL_IMAGE_INDEX = 2;
    public static final int UNICORN_IMAGE_INDEX = 3;


    /**
     * Unused right now.  Could be used to load in turtle pictures using reflection
     * @return
     * @throws CorruptedEnvironmentException
     */
    public static GraphicsMap<Pixmap> initializeImages() throws CorruptedEnvironmentException{
        FileReader fileToBeRead = null;
        String currentDirectory = System.getProperty(Controller.USER_DIR);

        try {
            fileToBeRead = new FileReader(currentDirectory + SHAPE_INDEX_FILE);
        }
        catch (FileNotFoundException e) {
            throw new MissingResourceException(ERROR_MESSAGE, "", "");
        }
        Scanner line = new Scanner(fileToBeRead);

        Map<Integer, Pixmap> shapeMap = new HashMap<Integer, Pixmap>();
        while(line.hasNext()) {
            String str = line.nextLine();
            String [] shapes = str.split("=");
            try{
                shapeMap.put(Integer.parseInt(shapes[0].trim()), new Pixmap(shapes[1].trim()));
            }
            catch(NumberFormatException e) {
                line.close();
                throw new CorruptedEnvironmentException();
            }
        }
        line.close();
        return new GraphicsMap<>(shapeMap);

    }



    public static GraphicsMap<Color> initializeColors() {
        Map<Integer, Color> colors = new HashMap<Integer, Color>();
        colors.put(DEFAULT_CLEAR_INDEX, CLEAR);
        colors.put(DEFAULT_BLACK_INDEX, Color.BLACK);
        return new GraphicsMap<Color>(colors);
    }

    public static GraphicsMap<LineBuilder> initializeLineStyles() {
        String pack = "drawing.lines.";
        String indexFile = "/src/resources/line_styles_index.txt";
        PrototypeMapFactory<LineBuilder> pt = new PrototypeMapFactory<LineBuilder>(indexFile, pack);
        
        Map<Integer, LineBuilder> lineStyles = pt.buildIndexMap();

        return new GraphicsMap<LineBuilder>(lineStyles);
    }

    //    public static GraphicsMap<Pixmap> initializeImages() {
    //        Map<Integer, Pixmap> images = new HashMap<Integer, Pixmap>();
    //        images.put(DEFAULT_INVISIBLE_INDEX, new Pixmap("blank.png"));
    //        images.put(DEFAULT_IMAGE_INDEX, Turtle.DEFAULT_IMAGE);
    //        images.put(DOG_IMAGE_INDEX, new Pixmap("dog.jpg"));
    //        images.put(SQUIRREL_IMAGE_INDEX, new Pixmap("squirrel.png"));
    //        images.put(UNICORN_IMAGE_INDEX, new Pixmap("Unicorn.jpg"));
    //        return new GraphicsMap<Pixmap>(images);
    //    }
}
