package view;

import control.Controller;

/**
 * Interface that all Views for this SLogo implementation must implement.
 * Defines required methods as per the API.
 * A View must also have an appropriately formated Controller implementation.
 * @author srwareham
 *
 */
public interface SLogoViewable {

    /**
     * Sets the controller for the view and its constituent classes.
     * @param controller The Controller for this view
     */
    public void setControllers(Controller controller);
    /**
     * Displays textual information to the user
     * @param text The text to be displayed
     */
    public void displayText(String text);

}
