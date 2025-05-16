package main.java.io.saqaStudio.com;

import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;

public class GameWindow extends Window {

    public GameWindow(String title, Skin skin) {
        super(title, skin);

        getTitleTable().clearChildren();
        getTitleTable().defaults().space(5.0f);

        Button button = new Button(skin, "Close");

        getTitleTable().add(button);

        Image image = new Image(skin, "title-bar-bg");
        image.setScale(0.5f);
        getTitleTable().add(image).growX();

        Label label = new Label("Math Three Game", skin);
        label.setFontScale(1.15f);
        getTitleTable().add(label).growX();
    }

    public GameWindow(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
    }

    public GameWindow(String title, WindowStyle style) {
        super(title, style);
    }
}
