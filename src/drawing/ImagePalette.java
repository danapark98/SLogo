package drawing;

import java.util.HashMap;
import java.util.Map;
import exceptions.IllegalInstructionException;
import util.Pixmap;


public class ImagePalette {

    /** The picture of that represents the turtle on the screen. */
    public static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle_art.png");

    /** The picture that represents the invisible turtle. */
    public static final Pixmap NO_IMAGE = new Pixmap("blank.png");

    /** Default index of the black color */
    public static final int DEFAULT_IMAGE_INDEX = 0;

    /** Default index of the clear color */
    public static final int DEFAULT_CLEAR_INDEX = -1;

    private Map<Integer, Pixmap> myImages;

    public ImagePalette () {
        myImages = new HashMap<Integer, Pixmap>();
        initializeImageMap();
    }

    private void initializeImageMap () {
        myImages.put(DEFAULT_IMAGE_INDEX, DEFAULT_IMAGE);
        myImages.put(DEFAULT_CLEAR_INDEX, NO_IMAGE);
    }

    public Pixmap getImage (int index) throws IllegalInstructionException {
        if (!myImages.containsKey(index)) {
        throw new IllegalInstructionException(Palette.UNDEFINED_INDEX_MESSAGE + index);
        }
        return myImages.get(index);
    }

}
