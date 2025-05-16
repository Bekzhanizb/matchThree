package main.java.io.saqaStudio.com;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;

public class GameWindow extends Window {

    public GameWindow(String title, Skin skin) {
        super(title, skin);
    }

    public GameWindow(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
    }

    public GameWindow(String title, WindowStyle style) {
        super(title, style);
    }
}
