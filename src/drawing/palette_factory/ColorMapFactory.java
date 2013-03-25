package drawing.palette_factory;

import java.awt.Color;

/**
 * Factory that builds map of indices to colors read in from text file.
 * 
 * @author Scott Valentine
 *
 */
public class ColorMapFactory extends IndexMapFactory<Color> {

    // info for reading in from textfile
    private static final int R_INDEX = 0;
    private static final int G_INDEX = 1;
    private static final int B_INDEX = 2;
    private static final int ALPHA_INDEX = 3;

    
    /**
     * Default Constructor. Sets the file to read Color data from
     * 
     * @param indexFileLocation is the location of the text file containing color data.
     */
    public ColorMapFactory(String indexFileLocation) {
        super(indexFileLocation);
    }

    @Override
    protected Color getObject(String objectData) {
        String[] rgbAlpha = objectData.split(" ");

        int r = Integer.parseInt(rgbAlpha[R_INDEX]);
        int g = Integer.parseInt(rgbAlpha[G_INDEX]);
        int b = Integer.parseInt(rgbAlpha[B_INDEX]);
        int alpha = Integer.parseInt(rgbAlpha[ALPHA_INDEX]);

        return new Color(r, g, b, alpha);
    }

}
