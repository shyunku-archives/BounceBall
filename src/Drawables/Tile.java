package Drawables;

import Managers.ImageManager;
import ObjectUtil.CollideShape;
import ObjectUtil.Drawable;

public class Tile extends Drawable {
    public Tile() {
        super(false);
        setImage(ImageManager.TILE_0);
        activatePhysics(true);
        activateCollidable(CollideShape.RECTANGLE);
    }

    public Tile(int x, int y) {
        super(false);
        setImage(ImageManager.TILE_0);
        activatePhysics(true);
        activateCollidable(CollideShape.RECTANGLE);
        setPos(x, y);
    }
}
