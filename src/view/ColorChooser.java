package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;


/**
 * Creates a color chooser for SLOGO
 * 
 * @author Yoshida
 * 
 */
public class ColorChooser extends JFrame
        implements ChangeListener {
    
    private JColorChooser myTcc;
    private View myView;
    private Color myColor;
    private static final Dimension CHOOSER_BOUNDS = new Dimension(800, 600);
    private static final String SET_PALLETE = "SetPalette";
    private static final String SET_PEN_COLOR = "SetPenColor";
    private static final int MAX_RGB = 255;
    
    public ColorChooser (View view) {
        myView = view;
        setTitle("SLogo Color Chooser");
        // Set up color chooser for setting text color
        myTcc = new JColorChooser();
        myTcc.getSelectionModel().addChangeListener(this);
        myTcc.setBorder(BorderFactory.createTitledBorder("Choose Pen Color"));
        myTcc.setPreferredSize(CHOOSER_BOUNDS);
        add(myTcc, BorderLayout.CENTER);
        pack();
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                float[] compArray = null;
                compArray = myColor.getRGBColorComponents(compArray);
                int red = (int) (compArray[0] * MAX_RGB);
                int green = (int) (compArray[1] * MAX_RGB);
                int blue = (int) (compArray[2] * MAX_RGB);
                myView.getController().createRunInstruction(SET_PALLETE + " " + 1 + " " + red + " " +
                                                            green + " " + blue);
                myView.getController().createRunInstruction(SET_PEN_COLOR + " 1");
            }
        });
    }
    
    public void stateChanged (ChangeEvent e) {
        myColor = myTcc.getColor();
    }
    
    private String getResourceLocation (String input) {
        return myView.getResources().getString(input);
    }
}
