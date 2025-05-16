package main.java.io.saqaStudio.com;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class AnimatedImage extends Image {
    private static AnimatedImage instance;

    protected Animation<TextureRegion> animation;
    private float stateTime = 0;

    public AnimatedImage(Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
    }

    public static AnimatedImage getInstance(float frameDuration, Array<? extends TextureRegion> frames) {
        if (instance == null) {
            Animation<TextureRegion> animation = new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
            instance = new AnimatedImage(animation);
        }
        return instance;
    }



    @Override
    public void act(float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
        super.act(delta);
    }
}
