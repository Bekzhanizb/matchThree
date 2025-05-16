package main.java.io.saqaStudio.com;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class TileFactory {
    public static Tile creteRandomTile(Array<TextureAtlas.AtlasRegion> entities,
                                       ClickListener clickListener) {
        int index = MathUtils.random(0, entities.size - 1);
        Tile tile = new Tile();
        tile.addListener(clickListener);
        tile.init(entities.get(index), index);
        return tile;
    }
}
