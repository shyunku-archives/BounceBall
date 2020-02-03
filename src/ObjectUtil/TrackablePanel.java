package ObjectUtil;

import Global.Constant;
import Global.Function;
import Global.Variables;
import Managers.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.security.Key;

public class TrackablePanel extends JPanel {
    private String logs[] = new String[0];

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

    public void applyLogs(String[] strs){
        logs = strs;
    }

    public void paintLogs(Graphics2D g){
        Function.setFontSize(g, 15f);
        g.setColor(Constant.LOG_TEXT_COLOR);
        for(int i=0;i<logs.length;i++){
            g.drawString(logs[i], 10, 15+20*(i+Function.getGeneralLogSize()+1));
        }
    }
}
