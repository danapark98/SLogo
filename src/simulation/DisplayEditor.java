package simulation;

import simulation.LineDrawing.Line;
import control.Palette;

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
    public void addLine (Line line);
    
    /**
     * Gives a palette of colors used for drawing.
     * 
     * @return The Palette of colors that can be used in editing.
     */
    public Palette getPalette();
        
    
}
