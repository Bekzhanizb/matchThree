package main.java.io.saqaStudio.com;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class GameScreen extends ScreenAdapter {


    private final MatchThree game;
    private Field field;
    private Label scoreCounter;
    private ProgressBar timeBar;

    public GameScreen(MatchThree game) {
        this.game = game;
    }

    @Override
    public void show() {
        GameWindow window = game.getWindow();

        Table mainTable = new Table();
        mainTable.addAction(Actions.sequence(Actions.fadeOut(0.01f), Actions.fadeIn(1)));

        Table table = new Table();
        table.padLeft(50);
        mainTable.setBackground(new TextureRegionDrawable(game.getTexture().findRegion("table")));
        table.defaults().align(Align.center).space(5);

        mainTable.add(table);
        field = new Field(game.getTexture().findRegion("background"), game.getTexture().findRegions("entity"));
        mainTable.add(field).padLeft(40);

        Label label = new Label("SCORE", game.getSkin());
        table.add(label);
        table.row().padBottom(160);

        scoreCounter = new Label("0", game.getSkin());
        scoreCounter.setFontScale(1.5f);
        table.add(scoreCounter);
        table.row();

        field.setMatchListener(new MatchListener() {
            @Override
            public void onMatch(int matches) {
                System.out.println("Matched " + matches + " tiles!");
            }
        });
    }
}
