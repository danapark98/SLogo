package mathBoolean;

import instructions.BaseInstruction;
import java.util.Scanner;
import simulation.Model;
import control.Parser;
import exceptions.IllegalInstructionException;

public class IsPenDown extends BaseInstruction {

    @Override
    public void load (Scanner line, Parser parser) throws IllegalInstructionException {
        // exists by itself, do not do anything
    }

    @Override
    public int execute (Model model) {
        // if the pen is clear
        if(model.getTurtle().getPenColor().getAlpha()== 0){
            return 0;
        }
        return 1;
    }

}
