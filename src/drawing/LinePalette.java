package drawing;

import drawing.lines.DashedLine;
import drawing.lines.LineBuilder;
import drawing.lines.SolidLine;
import exceptions.IllegalInstructionException;
import java.util.HashMap;
import java.util.Map;


/**
 * Keeps track of all the possible line styles that the turtle can draw.
 * 
 * @author Scott Valentine
 *
 */
public class LinePalette {

    
    /** This is the default style to build lines with */
    //public static final LineBuilder DEFAULT_LINE_STYLE = new DashedLine();
    
    /** This is a mapping of indecies to their line styles */
    private Map<Integer, LineBuilder> myLineStyles;
    

    /** New Lne palette that contains all possible line styles */
    public LinePalette() {
        initializeLineStyleMap();
    }

    /**
     * Returns the line style that corresponds to the given index.
     * 
     * @param index of the line style
     * @return the line Builder at index
     * @throws IllegalInstructionException if there is no LineBuilder defined for the given index.
     */
    public LineBuilder getLineStyle(int index) throws IllegalInstructionException {
        if (!myLineStyles.containsKey(index)) {
            throw new IllegalInstructionException(Palette.UNDEFINED_INDEX_MESSAGE + index);
        }
        return myLineStyles.get(index);
    }
    
    private void initializeLineStyleMap() {
        myLineStyles = new HashMap<Integer, LineBuilder>();
        
        myLineStyles.put(SolidLine.PALETTE_INDEX, new SolidLine());
        myLineStyles.put(DashedLine.PALETTE_INDEX, new DashedLine());
        
        // TODO: don't hardcode here (this is hard to find)
    }

}
