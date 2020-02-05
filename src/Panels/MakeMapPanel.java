package Panels;

import Managers.ImageManager;
import Global.Constant;
import Global.Function;
import Global.Variables;
import Managers.FontManager;
import ObjectUtil.TrackablePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MakeMapPanel extends TrackablePanel {
    private final int minXOffset = 50;
    private final int minYOffset = 50;

    private final int COORDINATE_ADJUST_OFFSET_X = 17;
    private final int COORDINATE_ADJUST_OFFSET_Y = 8;

    private final int GRID_SIZE = 40;

    private int cameraX = 0;
    private int cameraY = 0;

    private int mouseCoordX = 0;
    private int mouseCoordY = 0;


    @Override
    protected void paintComponent(Graphics gd) {
        super.paintComponent(gd);
        Graphics2D g = (Graphics2D) gd;
        FontManager.smoothRendering(g);

        g.setColor(new Color(202, 70, 0, 87));
        //y-axis
        int yAxisCoord = COORDINATE_ADJUST_OFFSET_X*GRID_SIZE + cameraX;
        g.fillRect(yAxisCoord, 0, GRID_SIZE, Constant.RESOLUTION.height);

        g.setColor(new Color(0, 115, 202, 87));
        //y-axis
        int xAxisCoord = COORDINATE_ADJUST_OFFSET_Y*GRID_SIZE + cameraY;
        g.fillRect(0, xAxisCoord, Constant.RESOLUTION.width, GRID_SIZE);

        g.setColor(Color.BLACK);
        //y-axis parallel grid line
        for(int i=-1;;i++){
            int x = GRID_SIZE *i + cameraX% GRID_SIZE;
            if(x > Constant.RESOLUTION.width) break;
            g.drawLine(x, 0, x, Constant.RESOLUTION.height);
        }

        //x-axis parallel grid line
        for(int i=-1;;i++){
            int y = GRID_SIZE *i + cameraY% GRID_SIZE;
            if(y > Constant.RESOLUTION.height) break;
            g.drawLine(0, y, Constant.RESOLUTION.width, y);
        }

        Function.drawImage(g, ImageManager.SELECT_ARRANGE_ITEM_MENU_TOP, 5, 40);
        for(int i=0;i<9;i++)
            Function.drawImage(g, ImageManager.SELECT_ARRANGE_ITEM_MENU_MID, 5, 100+60*i);
        Function.drawImage(g, ImageManager.SELECT_ARRANGE_ITEM_MENU_BOT, 5, 640);

        yAxisCoord = (mouseCoordX + COORDINATE_ADJUST_OFFSET_X)*GRID_SIZE + cameraX;
        xAxisCoord = -(mouseCoordY - COORDINATE_ADJUST_OFFSET_Y)*GRID_SIZE + cameraY;


        //mouse coordinate rect
        g.setColor(new Color(167, 167, 167, 132));
        g.fillRect(yAxisCoord+1, xAxisCoord+1, GRID_SIZE-1, GRID_SIZE-1);

        Function.setFontSize(g, 10f);
        g.drawString(String.format("%d,%d", mouseCoordX, mouseCoordY), yAxisCoord + GRID_SIZE + 5, xAxisCoord - 5);

        super.paintLogs(g);
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
                Point cur = Variables.mousePos;
                mouseCoordX = (int) Math.floor((cur.x - cameraX)/ (double)GRID_SIZE) - COORDINATE_ADJUST_OFFSET_X;
                mouseCoordY = -(int) Math.floor((cur.y - cameraY)/ (double)GRID_SIZE) + COORDINATE_ADJUST_OFFSET_Y;
            }
        });

        new Thread(() -> {
            while(true){
                applyLogs(new String[]{
                        "CameraPos "+ Function.getPairStr(cameraX, cameraY),
                        "MouseCoord "+ Function.getPairStr(mouseCoordX, mouseCoordY),
                });
                try {
                    Thread.sleep(1000/ Constant.FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}