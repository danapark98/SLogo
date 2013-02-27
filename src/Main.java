import simulation.Model;
import view.SLogoView;
import view.View;
import control.Controller;


/**
 * Runs the SLogo simulation.
 * 
 * @author Yoshida, Sean, Ellango
 */
public class Main {

    private static final String TITLE = "SLogo";
    private static final String LANGUAGE = "English";

    /**
     * @param Creates the SLogo simulation.
     */
    public static void main (String[] args) {
        @SuppressWarnings("unused")
        SLogoView view = new View(TITLE, LANGUAGE);
    }
}
