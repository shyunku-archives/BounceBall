package Panels;

import Global.Constant;
import Global.Function;
import Global.Variables;
import Managers.FontManager;
import ObjectUtil.TrackablePanel;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {
    private String logs[];

    public LogPanel(){
        this.setVisible(true);
        this.setSize(Constant.RESOLUTION);
        this.setBackground(new Color(0,0,0,0));
        this.setFont(FontManager.getFont(15f));

        logs = new String[]{};

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    logs = new String[]{
                            "FrameCount "+ Variables.FrameCount,
                            "MousePos "+Function.getMousePosAsString(),
                            "MouseCoordPos "+Function.getMouseCoordPosAsString(),
                            "FPS "+Constant.FPS,
                            "ElapsedTime "+Function.getElapsedTimeAsString()
                    };
                    try {
                        Thread.sleep(1000/ Constant.FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics gd) {
        super.paintComponent(gd);

        if(!Variables.isLogPanelVisible)return;
        Graphics2D g = (Graphics2D)gd;
        FontManager.smoothRendering(g);

        g.setColor(new Color(20, 70, 20));
        for(int i=0;i<logs.length;i++){
            g.drawString(logs[i], 10, 15+20*i);
        }
    }
}
