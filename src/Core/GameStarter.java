package Core;

import Engine.ImageEngine;
import Managers.FontManager;
import Managers.FrameManager;
import Managers.SoundManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameStarter {
    public static ImageEngine imageEngine = new ImageEngine();
    public static FrameManager frameManager;
    public static SoundManager soundManager;

    public static void main(String[] args){
        try {
            FontManager.DefaultFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\font\\Reko.ttf"));
        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            soundManager = new SoundManager();
            frameManager = new FrameManager();
        }
    }
}
