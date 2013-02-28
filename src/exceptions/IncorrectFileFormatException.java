package exceptions;

public class IncorrectFileFormatException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IncorrectFileFormatException () {
        super();
    }

    @Override
    public String toString () {
        return "Error: " + "File format is not recognized";
    }
}
