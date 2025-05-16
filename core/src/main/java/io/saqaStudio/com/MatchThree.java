package main.java.io.saqaStudio.com;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MatchThree extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}
