package exceptions;

public class IncorrectFileFormatException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage () {
        return "Error: " + "File format is not compatible";
    }
}
