package exceptions;

public class IllegalInstructionException extends Exception {

    private static final long serialVersionUID = 1L;
    private String myCommand;

    public IllegalInstructionException(String command) {
        super();
        myCommand = command;
    }
    
    @Override
    public String toString() {
        return "Error: " + myCommand + "is not a legal instruction";
    }
    
}
