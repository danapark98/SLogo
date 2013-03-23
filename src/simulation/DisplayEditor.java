package simulation;

import drawing.Palette;
import drawing.StampSprite;
import drawing.lines.Point;

/**
 * Interface for objects that can have lines added.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public interface DisplayEditor {

    /**
     * Adds a line to this DisplayEditor
     * 
     * @param line is the line to be added.
     */
    public void addLine (Point line);
    
    /**
     * Adds a stamp of the turtle.
     * 
     * @param st Stamp to be added.
     */
    public void addStamp (StampSprite st);
    
    /**
     * Gives a palette of colors used for drawing.
     * 
     * @return The Palette of colors that can be used in editing.
     */
    public Palette getPalette();
        
    
}
