package exceptions;

public class IllegalInstructionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String myCommand;

    public IllegalInstructionException (String command) {
        super();
        myCommand = command;
    }

    @Override
    public String getMessage () {
        return "Error: " + myCommand + " is not a legal instruction";
    }

}
