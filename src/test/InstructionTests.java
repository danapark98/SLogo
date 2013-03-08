package test;

import static org.junit.Assert.assertEquals;
import java.util.ResourceBundle;
import instructions.Instruction;
import org.junit.Test;
import simulation.Model;
import control.Environment;
import control.Parser;
import control.Preparser;
import exceptions.IllegalInstructionException;

public class InstructionTests {

    @Test
    public void testSum() throws IllegalInstructionException{
        
        Environment e = new Environment(ResourceBundle.getBundle("resources.English"));
        Parser p = new Parser(e);
        
        //String command = " SUM [ SUM 10 10 ] 10";
        String command = "SUM [ SUM [ SUM [ SUM [ SUM 1 1 ] 1 ] 1 ] 1 ] 1"; 
        
        Instruction i = p.generateInstruction(command);
        
        Model model = new Model();
        
        assertEquals(6, i.execute(model));
    }
    
    @Test
    public void testPreParse() throws IllegalInstructionException {
        String userInput = "repeat 4 [ fd 100 rt 90 ]";
        Preparser preparser = new Preparser(new Environment(ResourceBundle.getBundle("resources.English")));
        String s = preparser.preParse(userInput);
        assertEquals("[ repeat 4 [ [ fd 100 ] [ rt 90 ] ] ] ", s);
    }
    
    
}
