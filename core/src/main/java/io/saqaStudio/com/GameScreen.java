package main.java.io.saqaStudio.com;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

        label = new Label("TIME", game.getSkin());
        table.add(label);
        table.row().padBottom(170);

        timeBar = new ProgressBar(0, 60, 1, false, game.getSkin(), "startup");
        timeBar.setValue(60);
        timeBar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (timeBar.getValue() <= timeBar.getMinValue()) {
                    for (Actor tile : field.getChildren()) {
                        tile.setTouchable(Touchable.disabled);
                        float distance = (float) -Math.random() * 1000;
                        tile.addAction(Actions.moveBy(0, distance, 1));
                    }
                    field.addAction(Actions.sequence(Actions.fadeOut(1),
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                Table table = new Table();
                                table.setSize(field.getWidth(), field.getHeight());
                                table.setOrigin(field.getOriginX(), field.getOriginY());
                                table.setBounds(field.getX(), field.getY(), field.getWidth(), field.getHeight());
                                table.defaults().align(Align.center).space(10);

                                Label label = new Label("Game over, save your score!", game.getSkin());
                                table.add(label).align(Align.center);
                                table.row();

                                HorizontalGroup group = new HorizontalGroup();
                                group.setBounds(table.getX(), table.getY(), 200, 100);
                                group.setOrigin(Align.center);

                                TextField textField = new TextField("Enter your name", game.getSkin());
                                textField.setSize(180, 30);
                                group.addActor(textField);

                                TextButton saveBtn = new TextButton("OK", game.getSkin());
                                saveBtn.setSize(60, 30);
                                saveBtn.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        //TODO: GameServices.playClick();

                                        String name   = textField.getText().trim();
                                        //TODO: String oldRec = GameServices.loadRecords();
                                        //TODO: String entry  = field.getScore() + "\n" + name + (oldRec.isEmpty() ? "" : "\n" + oldRec);

                                        //TODO: GameServices.saveRecords(entry);

                                        window.removeActor(mainTable);
                                        game.setScreen(new MenuScreen(game));
                                    }
                                });
                                group.addActor(saveBtn);
                                group.space(10);

                                table.add(group).align(Align.center);

                                mainTable.removeActor(field);
                                mainTable.add(table).size(table.getWidth(), table.getHeight());
                                return true;
                            }
                        }));
                }
            }
        });
        table.add(timeBar);
        table.row();

        TextButton menuBtn = new TextButton("Menu", game.getSkin());
        menuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playClick();
                window.removeActor(mainTable);
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(menuBtn).size(menuBtn.getWidth() * 4).height(menuBtn.getHeight() * 2).align(Align.bottom);

        window.add(mainTable);
    }
}
