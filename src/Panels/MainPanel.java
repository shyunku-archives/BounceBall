package Panels;

import Drawables.BounceBallImage;
import Drawables.SolidColorBackground;
import Drawables.Tile;
import Engine.ImageEngine;
import Global.Constant;
import Global.Function;
import Global.Variables;
import Managers.FontManager;
import ObjectUtil.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends TrackablePanel {
    private Layer backgroundLayer = new Layer();
    private Layer bounceBallLayer = new Layer();
    private ButtonLayer buttonLayer = new ButtonLayer();
    private Layer groundTileLayer = new Layer();
    BounceBallImage bounceBallImage = new BounceBallImage();

    public MainPanel(){


        backgroundLayer.add(new SolidColorBackground(new Color(233, 233, 255)));
        groundTileLayer.add(new Tile(900, 150));
        groundTileLayer.add(new Tile(1050, 150));
        groundTileLayer.add(new Tile(1050, 300));

        bounceBallImage.setCollisionLayer(groundTileLayer);
        bounceBallLayer.add(bounceBallImage);

        CButton playSingleButton = new CButton(0, 200, ImageEngine.MAIN_MENU_BOX_IMAGE, "Single Play");
        CButton playMultiButton = new CButton(0, 320, ImageEngine.MAIN_MENU_BOX_IMAGE, "MultiPlay");
        CButton makeCustomMapButton = new CButton(0, 440, ImageEngine.MAIN_MENU_BOX_IMAGE, "Map Editor");
        makeCustomMapButton.setClickListener(() -> System.out.println("asdf"));

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
    }

    private void addButton(CButton btn){
        this.add(btn);
        buttonLayer.add(btn);
    }
}