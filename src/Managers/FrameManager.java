package Managers;

import Core.MainFrame;
import Global.Constant;
import Global.Variables;
import Panels.LogPanel;
import Panels.MainPanel;

import javax.swing.*;

public class FrameManager {
    public MainFrame frame = new MainFrame();

    public LogPanel logPanel = new LogPanel();

    public FrameManager(){
        startDrawing();

        MainPanel panel = new MainPanel();
        movePanel(panel);
    }

    public void movePanel(JPanel newPanel){
        frame.getContentPane().removeAll();
        frame.add(logPanel);
        frame.add(newPanel);
    }

    private void startDrawing(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000/ Constant.FPS);
                        Variables.FrameCount++;
                        frame.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}