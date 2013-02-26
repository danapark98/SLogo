package instructions;

import java.util.Scanner;

import control.Parser;
import exceptions.IllegalInstructionException;
import simulation.Model;


/**
 * represents an instruction definition in the predefined instruction set
 * 
 * @author Scott Valentine
 * 
 */
public abstract class BaseInstruction extends Instruction {

    
    /**
     * loads the base instruction from a scanner of user input
     * 
     * @param line - scanner from which information is read
     * @throws IllegalInstructionException 
     */
    public abstract void load(Scanner line, Parser parser) throws IllegalInstructionException;
    
    protected Instruction parseList(Scanner line, Parser parser) {
        StringBuilder sb = new StringBuilder();
        String str = line.next();
        int counterBracket = 1;
        while (counterBracket != 0) {
            str = line.next();
            if (str.equals("[")) {
                counterBracket ++;
            }
            if (str.equals("]"))
            {
                counterBracket --;
                if (counterBracket == 0) 
                {
                     break;
                }
            }
            sb.append(str);
            sb.append(" ");           
        }
        return parser.generateInstruction(sb.toString());
    }
   
    

    @Override
    public Instruction copy () {
        Instruction copy = null;
        try {
            copy =  this.getClass().newInstance();
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }

}
