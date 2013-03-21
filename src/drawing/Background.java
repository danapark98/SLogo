package drawing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import exceptions.IllegalInstructionException;
import util.Pixmap;


public class Background  {
    private static final String OTHER = "other";

    private static final String GRID = "grid";

    private static final String BACKGROUND = "background";

    private static final Pixmap BACKGROUND_PIXMAP = new Pixmap("underwater.jpg");

    private static final Pixmap GRID_PIXMAP = new Pixmap("grid.png");
    

    private PriorityQueue<Pixmap> myActivePixmaps;

    private Map<String, List<Pixmap>> myUtilities;
    // private Collection<Pixmap> myActiveBackgrounds;
    private Map<Pixmap, Integer> myAffinities;

    public Background () {
        initializeImages();

    }

    public void setBackground (int index) throws IllegalInstructionException {
        index -= 1;
        if ((index >= myUtilities.get(GRID).size()) || (index < 0)) { 
            throw new IllegalInstructionException(
            "Index Out of Bounds: " + index);
        }
        myActivePixmaps.add(myUtilities.get(BACKGROUND).get(index));
    }
    
    private void addPixmap(Pixmap input, String type, int affinity) {
        List<Pixmap> current = myUtilities.get(type);
        current.add(input);
        myUtilities.put(type, current);
//        myUtilities.put(type, myUtilities.get(type).add(input));
        myAffinities.put(input, affinity);
    }
    
    private void addPixmaps() {
        addPixmap(BACKGROUND_PIXMAP, BACKGROUND, 0);
        addPixmap(GRID_PIXMAP,GRID,1);
        
    }
    
    private void initializeImages () {
        myUtilities = new HashMap<String, List<Pixmap>>();         
        myAffinities = new HashMap<Pixmap, Integer>();
        
        addPixmaps();
        myActivePixmaps = new PriorityQueue<Pixmap>(3, new Comparator<Pixmap>() {
            
            @Override
            public int compare (Pixmap o1, Pixmap o2) {
                return myAffinities.get(o1).compareTo(myAffinities.get(o2));
               
            }
        });

    }

    private void paint() {
        //TODO: not sure how you guys want to handle this.. possibly the contents of this class will be subsumed somwhere else where all onscreen canvas paining is handled
        //queues are nice!
    }


}
