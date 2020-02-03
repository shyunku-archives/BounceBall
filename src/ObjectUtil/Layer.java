package ObjectUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Layer extends ArrayList<Drawable> {
    public void draw(Graphics2D g){
        Iterator<Drawable> iter = iterator();
        while(iter.hasNext()){
            Drawable d = iter.next();
            try{
                d.draw(g);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        removeIf(Drawable::getKilled);
    }
}