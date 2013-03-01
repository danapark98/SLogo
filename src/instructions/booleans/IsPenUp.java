package instructions.booleans;

import simulation.Model;

public class IsPenUp extends IsPenDown{

    @Override
    public int execute (Model model) {
        // if the pen is not clear
        if(model.getTurtle().getPenColor().getAlpha()!= 0){
            return 0;
        }
        return 1;
    }
}
