package view;

import java.awt.Dimension;

import javax.swing.JComponent;

import simulation.Model;

import control.Controller;

/**
 * The Abstract that all implementations of a view for this SLogo implementaiton will use.
 * @author srwareham
 *
 */
public abstract class SLogoView {
/**
 * Preferred Dimensions of the Canvas.
 */
    public static final Dimension PREFERRED_CANVAS_SIZE = new Dimension(400, 400);

    private Canvas myCanvas;
    private Controller myController;
    
    
    /**
     * Creates a SLogoView.
     */
    public SLogoView() {
        
    }
    
   
    /**
     * Method to display a text to the user in a display Box.
     * @param text Text to display
     */    
    public abstract void displayText(String text);
    
    /**
     * Sets the Controller for this View.
     * @param controller Controller that will watch over all View Actions
     *  / will create listeners
     */
    public void setController(Controller controller) {
        myController = controller;
    }
    /**
     * Sets the canvas for the view.  Will be called from the model or controller.
     * Called with command within controller such as myView.super.setCanvas(model)
     * @param model Model that should be displayed within the Canvas
     */
    public void setCanvas (Model model) {
        myCanvas = new Canvas(PREFERRED_CANVAS_SIZE, this, model);
    }
    
    private void sendComponent(JComponent component) {
        //TODO: need a public method within the controller to add listeners to swing components
        //from the view.  Needs to be done by the controller because it is the only place 
        //where definitions for the @override actionPerformed for the anonymous actionListener
        //to add to API
    	myController.sendString(s)
        myController.addListener(component);
    }
    
}
