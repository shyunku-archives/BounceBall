package Core;

import Global.Constant;
import Global.Variables;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setTitle("BounceBall "+ Constant.VERSION);
        this.setSize(Constant.RESOLUTION);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_F3){
                    Variables.isLogPanelVisible = !Variables.isLogPanelVisible;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
