package exceptions;

/**
 * The FileSavingException is thrown when the Environment fails to save its
 * current state.  This might happen if the OutputStream provided cannot be
 * written to.  The end-user should then try to provide a different way of
 * saving the state depending on the channel he or she wants to save to.
 * (e.g. picking a different file)
 *
 */
@SuppressWarnings("serial")
public class FileSavingException extends Exception {

    /**
     * The message provided to the user when exception is thrown.
     */
    @Override
    public String toString () {
        return "Error: " + "Could not save to file.";
    }
}
