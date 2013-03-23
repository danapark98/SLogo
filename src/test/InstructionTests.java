package test;

import control.Environment;
import control.Parser;
import control.Preparser;
import exceptions.IllegalInstructionException;
import instructions.Instruction;
import java.util.ResourceBundle;
import org.junit.Test;
import simulation.Model;
import static org.junit.Assert.assertEquals;



/**
 * A few small tests for instructions.
 * 
 * @author Scott Valentine
 *
 */
public class InstructionTests {

    private static final String DEFAULT_LANG = "resources.English";
    private static final int TEST_SUM_ANSWER = 6;
    
    /**
     * Tests a nested sum.
     * 
     * @throws IllegalInstructionException is someting is not formatted correctly.
     */
    @Test
    public void testSum () throws IllegalInstructionException {
        
        Environment e = new Environment(ResourceBundle.getBundle(DEFAULT_LANG));
        Parser p = new Parser(e);

        // String command = " SUM [ SUM 10 10 ] 10";
        String command = "SUM [ SUM [ SUM [ SUM [ SUM 1 1 ] 1 ] 1 ] 1 ] 1";
        
        Instruction i = p.generateInstruction(command);
        // NOT SURE 
        Model model = new Model();

        assertEquals(TEST_SUM_ANSWER, i.execute(model));
    }

    /**
     * Tests the preparser class
     * 
     * @throws IllegalInstructionException if something is not formatted correctly
     */
    @Test
    public void testPreParse () throws IllegalInstructionException {
        String userInput = "repeat 4 [ fd 100 rt 90 ]";
        Preparser preparser =
                new Preparser(new Environment(ResourceBundle.getBundle(DEFAULT_LANG)));
        String s = preparser.preParse(userInput);
        assertEquals("[ repeat 4 [ [ fd 100 ] [ rt 90 ] ] ] ", s);
    }

}
