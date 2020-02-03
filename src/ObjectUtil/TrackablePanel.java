package ObjectUtil;

import Global.Constant;
import Global.Variables;
import Managers.FontManager;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.security.Key;

public class TrackablePanel extends JPanel {
    public TrackablePanel(){
        this.setVisible(true);
        this.setSize(Constant.RESOLUTION);
        this.setFont(FontManager.DefaultFont);

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Variables.mousePos = e.getPoint();
            }
        });
    }
}
