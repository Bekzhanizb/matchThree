package main.java.io.saqaStudio.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class GameServices {
    private static final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sound/touch_glass.ogg"));
    private static final Sound swapSuccessSound = Gdx.audio.newSound(Gdx.files.internal("sound/swap_success.ogg"));

    private static final FileHandle recordsFile = Gdx.files.local("Records");

    private GameServices() {}

    public static void playClick() {
        clickSound.play(0.3f);
    }

    public static void playSwapSuccess() {
        swapSuccessSound.play(0.2f);
    }

    public static void saveRecords(String content) {
        recordsFile.writeString(content, false);
    }

    public static String loadRecords() {
        return recordsFile.exists() ? recordsFile.readString() : "";
    }

    public static boolean recordsExist() {
        return recordsFile.exists();
    }

    public static void dispose() {
        clickSound.dispose();
        swapSuccessSound.dispose();
    }
}
