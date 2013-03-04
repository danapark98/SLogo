import view.SLogoView;
import view.View;


/**
 * Runs the SLogo simulation.
 * 
 * @author Yoshida, Sean, Ellango, Ryan, Scott
 */
public class Main {

    private static final String TITLE = "SLogo";
    private static final String LANGUAGE = "English";
    
    /**
     * Creates the SLogo simulation.
     */
    public static void main (String[] args) {
        @SuppressWarnings("unused")
        View view = new SLogoView(TITLE, LANGUAGE);
    }
}
