package ObjectUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ButtonLayer extends ArrayList<CButton> {
    public void draw(Graphics2D g){
        Iterator<CButton> iter = iterator();
        while(iter.hasNext()){
            CButton d = iter.next();
            try{
                d.draw(g);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
