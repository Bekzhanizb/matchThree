
package main.java.io.saqaStudio.com;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends Image {

    public int type = -1;
    private TileBehavior behavior;

    private boolean armed = false;

    public void setBehavior(TileBehavior behavior) {
        this.behavior = behavior;
    }

    public void activate(Field field) {
        if (behavior != null) {
            behavior.activate(this, field);
        }
    }

    public void arm() {
        this.armed = true;
    }

    public void disarm() {
        this.armed = false;
    }

    public boolean isArmed() {
        return armed;
    }

    public boolean hasBehavior(Class<? extends TileBehavior> clazz) {
        return behavior != null && behavior.getClass() == clazz;
    }

    public void init(TextureRegion sprite, int index){
        setBounds(getX(), getY(), getWidth(), getHeight());
        setDrawable(new TextureRegionDrawable(sprite));
        this.type = index;
    }
}
