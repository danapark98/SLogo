package test;

import static org.junit.Assert.assertEquals;
import instructions.Instruction;
import org.junit.Test;
import simulation.Model;
import control.Environment;
import control.Parser;
import exceptions.IllegalInstructionException;

public class InstructionTests {

    @Test
    public void testSum() throws IllegalInstructionException{
        
        Environment e = new Environment();
        Parser p = new Parser(e);
        
        String command = " SUM [ SUM 10 10 ] 10";
      
        Instruction i = p.generateInstruction(command);
        
        Model model = new Model();
        
        assertEquals(5, i.execute(model));
    }
}
