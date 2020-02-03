package Engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageEngine {
    public static final int BOUNCE_BALL_IMAGE = 1000;
    public static final int MAIN_MENU_BOX_IMAGE = 1001;

    public static final int TILE_0 = 10000;
    private static HashMap<Integer, BufferedImage> imageBundle = new HashMap<>();

    public ImageEngine(){
        addImage(BOUNCE_BALL_IMAGE, "bounce_ball.png", 70);
        addImage(MAIN_MENU_BOX_IMAGE, "main_menu_bar.png", 400);

        addImage(TILE_0, "b0.png", 150);
    }

    private void addImage(int key, String name){
        String filePath = "resources\\img\\"+name;
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            imageBundle.put(key, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addImage(int key, String name, int w){
        String filePath = "resources\\img\\"+name;
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            int h = w*image.getHeight()/image.getWidth();
            BufferedImage converted = resizeImage(image, w, h);
            imageBundle.put(key, converted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(int key){
        return imageBundle.get(key);
    }

    public static BufferedImage getResizedImage(int key, int w) {
        BufferedImage bi = imageBundle.get(key);
        int h = w*bi.getHeight()/bi.getWidth();
        return resizeImage(bi, w, h);
    }

    public static BufferedImage getResizedImage(int key, int w, int h) {
        BufferedImage bi = imageBundle.get(key);
        return resizeImage(bi, w, h);
    }

    private static BufferedImage resizeImage(BufferedImage bi, int x, int y) {
        BufferedImage resized = null;
        int ox = bi.getWidth();
        int oy = bi.getHeight();
        if(x==0) {
            if(y==0) return bi;
            int rx = (int)((double)(ox*y)/(double)oy);
            resized = new BufferedImage(rx,y,BufferedImage.TYPE_INT_ARGB);
            resized.getGraphics().drawImage(bi.getScaledInstance(rx, y, Image.SCALE_AREA_AVERAGING),0,0,rx,y,null);
        }else if(y==0) {
            int ry = (int)((double)(oy*x)/(double)ox);
            resized = new BufferedImage(x,ry,BufferedImage.TYPE_INT_ARGB);
            resized.getGraphics().drawImage(bi.getScaledInstance(x, ry, Image.SCALE_AREA_AVERAGING),0,0,x,ry,null);
        }else {
            resized = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);
            resized.getGraphics().drawImage(bi.getScaledInstance(x, y, Image.SCALE_AREA_AVERAGING),0,0,x,y,null);
        }
        return resized;
    }
}
