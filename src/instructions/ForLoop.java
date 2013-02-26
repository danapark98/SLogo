package instructions;

import java.util.Scanner;
import simulation.Model;
import exceptions.IllegalInstructionException;

public class ForLoop extends BaseInstruction {
    private int myRepeats;
    private Instruction myInstruction;
    
    @Override
    public void load (Scanner line) throws IllegalInstructionException {
        // TODO Auto-generated method stub

    }

    @Override
    public void execute (Model model) {
        for(int i = 0; i < myRepeats; ++i){
            myInstruction.execute(model);
        }

    }

}
