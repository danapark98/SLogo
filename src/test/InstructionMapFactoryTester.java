package test;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.Map;
import org.junit.Test;
import control.InstructionMapFactory;

public class InstructionMapFactoryTester {

    @Test
    public void testFactory() throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        InstructionMapFactory imf= new InstructionMapFactory("English");
        
        Map<String, instructions.Instruction> map = imf.buildInstructionMap("/src/resources/test_index.txt");
        
        assertEquals(4, map.keySet().size());       
    }
}
