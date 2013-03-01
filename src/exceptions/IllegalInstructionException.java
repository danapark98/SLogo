package exceptions;

/**
 * Exception for instructions that are not defined in the current environment.
 * used by the Parser.
 * 
 * @author Scott Valentine
 * 
 */
public class IllegalInstructionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    /** String that resulted in the exception.*/
    private String myCommand;

    /**
     * Creates new instance from the string that resulted in this exception.
     * @param command is the string that resulted in the exception.
     */
    public IllegalInstructionException(String command) {
        super();
        myCommand = command;
    }

    @Override
    public String toString() {
        return "Error: " + myCommand + " is not a legal instruction";
    }

}
