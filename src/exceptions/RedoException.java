package exceptions;

/**
 * 
 * Exception when there are no more states available to restore.
 * 
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 *
 */

@SuppressWarnings("serial")
public class RedoException extends Exception {
    
    /**
     * The message provided to the user when exception is thrown.
     */
    @Override
    public String toString() {
        return "Cannot redo anymore";
    }
}
