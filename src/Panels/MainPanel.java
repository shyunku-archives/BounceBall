package Panels;

import Drawables.BounceBallImage;
import Drawables.SolidColorBackground;
import Drawables.Tile;
import Managers.ImageManager;
import Global.Constant;
import Global.Function;
import Managers.FontManager;
import ObjectUtil.*;

import java.awt.*;

public class MainPanel extends TrackablePanel {
    private Layer backgroundLayer = new Layer();
    private Layer bounceBallLayer = new Layer();
    private ButtonLayer buttonLayer = new ButtonLayer();
    private Layer groundTileLayer = new Layer();
    BounceBallImage bounceBallImage = new BounceBallImage();

    public MainPanel(){
        backgroundLayer.add(new SolidColorBackground(new Color(233, 233, 255)));
        groundTileLayer.add(new Tile(900, 0));
        groundTileLayer.add(new Tile(900, 150));
        groundTileLayer.add(new Tile(1050, 0));
        groundTileLayer.add(new Tile(1050, 150));
        groundTileLayer.add(new Tile(1050, 300));
        groundTileLayer.add(new Tile(1050, 450));
        groundTileLayer.add(new Tile(1200, 0));
        groundTileLayer.add(new Tile(1200, 150));
        groundTileLayer.add(new Tile(1200, 300));

        bounceBallImage.setCollisionLayer(groundTileLayer);
        bounceBallLayer.add(bounceBallImage);

        CButton playSingleButton = new CButton(0, 200, ImageManager.MAIN_MENU_BOX_IMAGE, "Single Play");
        CButton playMultiButton = new CButton(0, 320, ImageManager.MAIN_MENU_BOX_IMAGE, "MultiPlay");
        CButton makeCustomMapButton = new CButton(0, 440, ImageManager.MAIN_MENU_BOX_IMAGE, "Map Editor");
        makeCustomMapButton.setClickListener(() -> Function.movePanel(new MakeMapPanel()));

        buttonLayer.add(playSingleButton);
        buttonLayer.add(playMultiButton);
        addButton(makeCustomMapButton);
    }

    @Override
    protected void paintComponent(Graphics gd) {
        super.paintComponent(gd);
        Graphics2D g = (Graphics2D)gd;
        FontManager.smoothRendering(g);

        backgroundLayer.draw(g);
        bounceBallLayer.draw(g);
        groundTileLayer.draw(g);

        buttonLayer.draw(g);

        g.setColor(Color.BLACK);

        Function.setFontSize(g, 75f);
        g.drawString("Bounce Ball", 100, 100);
        Function.setFontSize(g, 25f);
        g.drawString(Constant.VERSION, 800, 720);
    }

    private void addButton(CButton btn){
        this.add(btn);
        buttonLayer.add(btn);
    }
}