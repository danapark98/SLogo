import view.SLogoView;
import view.View;


/**
 * Runs the SLogo simulation.
 * 
 * @author Yoshida, Sean, Ellango, Ryan, Scott
 */
public class Main {

    private static final String TITLE = "SLogo";
    public static final String LANGUAGE = "English";

    /**
     * @param Creates the SLogo simulation.
     */
    public static void main (String[] args) {
        @SuppressWarnings("unused")
        SLogoView view = new View(TITLE, LANGUAGE);
    }
}
