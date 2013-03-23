package drawing;

import java.util.HashMap;
import java.util.Map;
import exceptions.IllegalInstructionException;
import util.Pixmap;

/**
 * This class creates a palette that is used to select different pictures for the turtle
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */

public class ImagePalette {

    /** The picture of that represents the turtle on the screen. */
    public static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle_art.png");
  
    /** Constants for shape indices. */
    public static final int DEFAULT_CLEAR_INDEX = -1;
    public static final int DEFAULT_IMAGE_INDEX = 0;
    public static final int DOG_IMAGE_INDEX = 1;
    public static final int SQUIRREL_IMAGE_INDEX = 2;
    public static final int UNICORN_IMAGE_INDEX = 3;

  

    private Map<Integer, Pixmap> myImages;

    public ImagePalette () {
        initializeImageMap();
    }

    private void initializeImageMap () {
    	myImages = new HashMap<Integer, Pixmap>();
        myImages.put(DEFAULT_IMAGE_INDEX, DEFAULT_IMAGE);
        myImages.put(DEFAULT_CLEAR_INDEX, new Pixmap("blank.png"));
        myImages.put(DOG_IMAGE_INDEX, new Pixmap("dog.jpg"));
        myImages.put(SQUIRREL_IMAGE_INDEX, new Pixmap("squirrel.png"));
    }

    public Pixmap getImage (int index) throws IllegalInstructionException {
        if (!myImages.containsKey(index)) {
        throw new IllegalInstructionException(Palette.UNDEFINED_INDEX_MESSAGE + index);
        }
        return myImages.get(index);
    }

}
