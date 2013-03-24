package drawing;

import java.io.Serializable;
import java.util.Map;
import exceptions.IllegalInstructionException;



/**
 * Keeps track of all of the graphical options available (e.g. colors, linestyles)
 * 
 * A GraphicsMap is essentially a specialized map that is constrained to SLogo 
 * specifications and supports SLogo exception handling.
 * 
 * @author Ellango
 *
 * @param <E> is graphical element this GraphicsMap is storing
 */
public class GraphicsMap<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    /** Error message for undefined indices in palettes */
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Not defined at index: ";
    private static final String ILLEGAL_INDEX_MESSAGE = "Index of ";
    
    
    private Map<Integer, E> myMap;
    
    /**
     * Creates a GraphicsMap from a pre-constructed map that has been initialized
     * with default values by the programmer
     * 
     * @param map filled in with default values
     */
    public GraphicsMap (Map<Integer, E> map) {
        myMap = map;
    }
      
    /**
     * Returns the element that corresponds to the given index.
     * 
     * @param index is the location of the element
     * @return E The element at the index
     * @throws IllegalInstructionException if no element corresponds to the index
     */
    public E get (int index) throws IllegalInstructionException {
        if (!myMap.containsKey(index)) {
            throw new IllegalInstructionException(INDEX_OUT_OF_BOUNDS_MESSAGE + index);
        }
        return myMap.get(index);
    }
    
    /**
     * Sets the element at a specified index to the user defined value.
     * 
     * @param index of the element to be edited (or added if index is not yet in use)
     * @param E is the new element added
     * @throws IllegalInstructionException if index not positive
     */
    public void put(int index, E element) throws IllegalInstructionException {
        if (index <= 0) { 
            throw new IllegalInstructionException(ILLEGAL_INDEX_MESSAGE + index); 
        }
        myMap.put(index, element);
    }
    
    
}
