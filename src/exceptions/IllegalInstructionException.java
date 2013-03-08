package exceptions;

/**
 * Exception for instructions and variables that are not defined in the current
 * environment.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
@SuppressWarnings("serial")
public class IllegalInstructionException extends Exception {

    /** String that resulted in the exception. */
    private final String myCommand;

    /**
     * Creates new instance from the string that resulted in this exception.
     * 
     * @param command is the string that resulted in the exception.
     */
    public IllegalInstructionException (String command) {
        super();
        myCommand = command;
    }

    @Override
    public String toString () {
        return "Error: " + myCommand + " is not a legal instruction";
    }

}
