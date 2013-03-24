package control;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import util.Pixmap;
import exceptions.CorruptedEnvironmentException;

public class ImageMapFactory extends FileReaderMapFactory<Pixmap> {

    
    private String myIndexFile;
    
    public ImageMapFactory(String indexFile){
        myIndexFile = indexFile;
    }

    @Override
    public Map<Integer, Pixmap> buildMap() {
        Scanner line = getScanner(myIndexFile);       
        Map<Integer, Pixmap> shapeMap = new HashMap<Integer, Pixmap>();
        while (line.hasNext()) {
            String str = line.nextLine();
            String[] shapes = str.split("=");
            Pixmap image = new Pixmap(shapes[1].trim());           
            try {
                shapeMap.put(Integer.parseInt(shapes[0].trim()), image);
            }
            catch (NumberFormatException e) {
                throw new CorruptedEnvironmentException();
            }
        }
        line.close();
        return shapeMap;

    }

}
