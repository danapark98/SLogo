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

    private final String myMessage;

    /**
     * Constructor for Redo Exception with message.
     * 
     * @param message is the message for the excpetion to display
     */
    public RedoException (String message) {
        myMessage = message;
    }

    /**
     * The message provided to the user when exception is thrown.
     */
    @Override
    public String toString () {
        return myMessage;
    }
}
