package Panels;

import Global.Constant;
import Global.Variables;
import Managers.FontManager;
import ObjectUtil.TrackablePanel;
import com.sun.scenario.effect.Offset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MakeMapPanel extends TrackablePanel {
    private final int minXOffset = 50;
    private final int minYOffset = 50;

    private final int gridSize = 40;

    private int cameraX = 0;
    private int cameraY = 0;

    @Override
    protected void paintComponent(Graphics gd) {
        super.paintComponent(gd);
        Graphics2D g = (Graphics2D) gd;
        FontManager.smoothRendering(g);

        g.setColor(Color.BLACK);
        //y-axis parallel grid line
        for(int i=-1;;i++){
            int x = gridSize*i + cameraX%gridSize;
            if(x > Constant.RESOLUTION.width) break;
            g.drawLine(x, 0, x, Constant.RESOLUTION.height);
        }

        //x-axis parallel grid line
        for(int i=-1;;i++){
            int y = gridSize*i + cameraY%gridSize;
            if(y > Constant.RESOLUTION.height) break;
            g.drawLine(0, y, Constant.RESOLUTION.width, y);
        }

        g.setColor(Color.RED);
    }

    public MakeMapPanel(){
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - Variables.mousePos.x;
                int dy = e.getY() - Variables.mousePos.y;
                cameraX += dx;
                cameraY += dy;

                Variables.mousePos = e.getPoint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }
}