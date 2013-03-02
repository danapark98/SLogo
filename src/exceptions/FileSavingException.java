package exceptions;

public class FileSavingException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public FileSavingException () {
        super();
    }

    @Override
    public String getMessage () {
        return "Error: " + "Could not save to file.";
    }
}
