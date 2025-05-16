package main.java.io.saqaStudio.com;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Scaling;

public class GameWindow extends Window {

    private final WindowController controller;

    public GameWindow(String title, Skin skin, WindowController controller) {
        super(title, skin);
        this.controller = controller;

        getTitleTable().clearChildren();
        getTitleTable().defaults().space(5.0f);

        Button button = new Button(skin, "close");

        getTitleTable().add(button);

        Image image = new Image(skin, "title-bar-bg");
        image.setScaling(Scaling.stretchX);
        getTitleTable().add(image).growX();

        Label label = new Label("Match Three Game", skin);
        label.setFontScale(1.15f);
        getTitleTable().add(label).padLeft(20.0f).padRight(20.0f);

        image = new Image(skin, "title-bar-bg");
        image.setScaling(Scaling.stretchX);
        getTitleTable().add(image).growX();

        button = new Button(skin, "restore");
        getTitleTable().add(button);

        button = new Button(skin, "minimize");
        getTitleTable().add(button);
    }
    public void setMovable(Stage stage) {
        Vector2 position = new Vector2();
        getTitleTable().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                position.x = stage.stageToScreenCoordinates(new Vector2(x, y)).x;
                position.y = stage.stageToScreenCoordinates(new Vector2(x, y)).y;
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        getTitleTable().addListener(new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                Vector2 current = stage.stageToScreenCoordinates(new Vector2(x, y));
                int deltaX = (int)(current.x - position.x);
                int deltaY = (int)(current.y - position.y);

                controller.moveWindow(deltaX, deltaY);

                position.set(current);
            }
        });
    }
}
