package io.saqaStudio.com.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import main.java.io.saqaStudio.com.WindowController;

// lwjgl3:
public class Lwjgl3WindowController implements WindowController {
    @Override
    public void moveWindow(int deltaX, int deltaY) {
        Lwjgl3Graphics graphics = (Lwjgl3Graphics) Gdx.graphics;
        Lwjgl3Window window = graphics.getWindow();
        window.setPosition(window.getPositionX() + deltaX, window.getPositionY() + deltaY);
    }
}
