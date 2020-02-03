package Drawables;

import Global.Constant;
import ObjectUtil.Drawable;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.*;

public class SolidColorBackground extends Drawable {
    private Color backgroundColor;

    public SolidColorBackground(Color color){
        super(false);
        this.backgroundColor = color;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, Constant.RESOLUTION.width, Constant.RESOLUTION.height);
    }
}