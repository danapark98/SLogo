package exceptions;

/**
 * The IncorrectFileFormatException will be thrown when the Environment fails
 * to load its state from the provided InputStream.  This could be because
 * the InputStream cannot be read from, because the SLogo program requires
 * loading only from sources that were saved previously by the same program.
 *
 */
public class IncorrectFileFormatException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString () {
        return "Error: " + "File format is not compatible";
    }
}
