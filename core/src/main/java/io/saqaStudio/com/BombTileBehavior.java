package main.java.io.saqaStudio.com;

public class BombTileBehavior implements TileBehavior {
    @Override
    public void activate(Tile tile, Field field) {
        System.out.println("💣 Bomb activated!");

        int tileIndex = field.getActiveTiles().indexOf(tile, true);
        int x = tileIndex % field.getRank();
        int y = tileIndex / field.getRank();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (field.isValidPosition(nx, ny)) {
                    field.removeTile(nx, ny);
                }
            }
        }

        tile.type = -1;
        tile.addAction(com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence(
            com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut(0.25f),
            field.getAfterMatch()
        ));
    }
}
