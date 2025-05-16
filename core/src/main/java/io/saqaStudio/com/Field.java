package main.java.io.saqaStudio.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

public class Field extends Table implements Disposable{

    private int score = 0;
    private static final int RANK = 8;
    private final Array<TextureAtlas.AtlasRegion> entities;
    private final Array<Tile> activeTiles = new Array<>(64);
    private final Sound click = Gdx.audio.newSound(Gdx.files.internal("sound/touch_glass.ogg"));
    private final Sound wrongSwap = Gdx.audio.newSound(Gdx.files.internal("sound/swap_wrong.ogg"));
    private final Sound successSwap = Gdx.audio.newSound(Gdx.files.internal("sound/swap_success.ogg"));

    public Field(TextureRegion background, Array<TextureAtlas.AtlasRegion> sprites) {
        defaults().width(80).height(80);
        setBounds(0, 0, 640, 640);
        setBackground(new TextureRegionDrawable(background));
        entities = sprites;
        for(int i =0; i < RANK *RANK; i++){
            Tile tile = TileFactory.creteRandomTile(entities, clickListener);
            activeTiles.add(tile);

            if(i % RANK == 0) row();
            add(tile);

        }

        //TODO: Add findMatches method
    }

    private final ClickListener clickListener = new ClickListener() {
        Tile firstClick;
        int count = 0;

        final Action afterSwap = new Action() {
            @Override
            public boolean act(float delta) {
                // TODO: Add findMatches method
                return true;
            }
        };

        final Action afterClick = new Action() {
            @Override
            public boolean act(float delta) {
                for (Tile tile : activeTiles) {
                    tile.setTouchable(Touchable.enabled);
                }
                return true;
            }
        };

        @Override
        public void clicked(InputEvent event, float x, float y) {
            // TODO: Add GameServices
            Tile target = (Tile) event.getTarget();

            if (firstClick != null) {
                target.clearActions();
                firstClick.clearActions();

                int tileIndex1 = activeTiles.indexOf(firstClick, true);
                int tileIndex2 = activeTiles.indexOf(target, true);

                activeTiles.swap(tileIndex1, tileIndex2);
                if ((tileIndex1 == tileIndex2 - 1 || tileIndex1 == tileIndex2 + 1 ||
                    // TODO: Add hasMatches method -------------------------------------&& hasMathces()
                    tileIndex1 == tileIndex2 + RANK || tileIndex1 == tileIndex2 - RANK)) {
                    swapActor(tileIndex1, tileIndex2);
                    target.addAction(moveTo(firstClick.getX(), firstClick.getY(), 0.2f));
                    firstClick.addAction(sequence(moveTo(target.getX(), target.getY(), 0.2f), afterSwap));
                } else {
                    wrongSwap.play(0.2f, 1f, 0);
                    activeTiles.swap(tileIndex1, tileIndex2);
                    target.addAction(sequence(moveTo(firstClick.getX(), firstClick.getY(), 0.1f),
                        moveTo(target.getX(), target.getY(), 0.1f)));
                    firstClick.addAction(sequence(moveTo(target.getX(), target.getY(), 0.1f),
                        moveTo(firstClick.getX(), firstClick.getY(), 0.1f), afterSwap));
                }
            }

            count++;
            firstClick = target;

            if (count == 2) {
                firstClick = null;
                count = 0;
            } else {
                for (Tile tile : activeTiles) {
                    tile.setTouchable(Touchable.disabled);
                }
                target.setOrigin(Align.center);
                target.addAction(sequence(parallel(moveTo(target.getX(), target.getY() + 10, 0.125f, Interpolation.swingOut),
                        Actions.scaleBy(0.2f, 0.2f, 0.125f)),
                    parallel(Actions.scaleBy(-0.2f, -0.2f, 0.125f),
                        moveTo(target.getX(), target.getY(), 0.125f, Interpolation.swingIn)), afterClick));
            }
        }
    };

    @Override
    public void dispose() {

    }
}
