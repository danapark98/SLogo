package simulation;

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
}
