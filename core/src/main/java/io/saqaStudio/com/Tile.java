package main.java.io.saqaStudio.com;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends Image {

    public int type = -1;

    public void init(TextureRegion sprite, int index) {
        setBounds(getX(index), getY(index), getWidth(), getHeight());
        setDrawable(new TextureRegionDrawable(sprite));
        this.type = index;
    }
}
