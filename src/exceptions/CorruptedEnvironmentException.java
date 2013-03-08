package exceptions;

/**
 * A runtime exception that is thrown when the Environment tries to create
 * a return a new instruction but fails. This typically means a programming bug
 * such as not loading the environment correctly, or not providing an
 * appropriate instantiation method for a instruction in the library.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
@SuppressWarnings("serial")
public class CorruptedEnvironmentException extends RuntimeException {
}
