package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import control.Controller;

/**
 * The View is comprised of everything visible to the user.
 * The View is the interactive space containing a file menu, buttons, and the Canvas.
 * @author srwareham
 *
 */
public class View implements SLogoViewable {
/**
 * Creates an instance of the View.
 */
    public View() {
    
    }
    
    //TODO: we may add addJComponent(JComponent j) to our controller so that it can recieve instances of swing objects so that it can use "j.addKeyListener( new .....)" would need to document as a change to our API  
    //the issue stems from the fact that the view holds the JComponent, while the Control hods the definiton for what the actionPerformed should be.  May also need seperate one for JButton to be able to addActionListener for some reason
    /*
     * Example: 
     *  protected JButton makeClear () {
        JButton result = new JButton(myResources.getString("ClearCommand"));
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                myTextArea.setText("");
            }
        });
        return result;
    }
    
    probably we want to send the button or any other componenet type (not sure if we want several specific methods or 1 more general one) and then add the listener and action performed to it 
    
    otherwise stuck wondering if there is a way to pass off a JButton with an ActionListener to the control class. Then have the control class override the actionPerformed so that it does what control knows it needs to
    suppose we could pass off the JComponents with as much info as possible from the view. Then let the controller read all of its Listener info, create a new instance with the proper actionPerformed, and then 
    place this newer ActionListener w/ complete actionPerformed into the JButton
    
     */
/**
 * This method will most likely send the controller all of the JComponents w/listeners that it needs to add actions to
 * ie send in a textbox and the controller adds a text focus listener for the
 * @param controller The master controller which will determine how each JComponent should be handled
 */
    @Override 
    public void setControllers(Controller controller) {


    }


    @Override
    public void displayText(String text) {


    }



}