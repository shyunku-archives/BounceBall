package ObjectUtil;

import Global.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class CButton extends JButton {
    private BufferedImage image;
    private String tooltip = "";

    public CButton(int x, int y, int key, String str){
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.image = Function.getImage(key);
        this.setBounds(x, y, image.getWidth(), image.getHeight());
        tooltip = str;
    }

    public void draw(Graphics2D g) {
        if(image != null){
            g.setColor(Color.BLACK);

            Rectangle bounds = this.getBounds();
            g.drawImage(image, null, bounds.x, bounds.y);
            int cx = bounds.x + bounds.width/2;
            int cy = bounds.y + bounds.height/2;
            Function.drawCenteredString(g, tooltip, cx, cy+10, 40f);
        }
    }

    public void setClickListener(ClickListener listener){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.onClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
