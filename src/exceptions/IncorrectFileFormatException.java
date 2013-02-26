package exceptions;

public class IncorrectFileFormatException extends Exception{

	public IncorrectFileFormatException() {
		super();
	}

	@Override
	public String toString() {
		return "Error: " + "File format is not recognized";
	}
}
