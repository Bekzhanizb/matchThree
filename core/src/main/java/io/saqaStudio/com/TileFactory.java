
package main.java.io.saqaStudio.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class TileFactory {

    public static Tile createRandomTile(Array<TextureAtlas.AtlasRegion> entities,
                                        ClickListener clickListener) {
        int index = MathUtils.random(0, entities.size - 1);
        Tile tile = new Tile();

        if (MathUtils.randomBoolean(0.03f)) {
            tile.setBehavior(new BombTileBehavior());

            Texture bombTexture = new Texture(Gdx.files.internal("assets/texture/bomb.png"));
            TextureRegion bombRegion = new TextureRegion(bombTexture);
            tile.init(bombRegion, index);
        } else {
            tile.setBehavior(new NormalTileBehavior());
            tile.init(entities.get(index), index);
        }

        tile.addListener(clickListener);
        return tile;
    }
}
