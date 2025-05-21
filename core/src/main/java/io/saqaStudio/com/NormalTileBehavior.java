package main.java.io.saqaStudio.com;

public class NormalTileBehavior implements TileBehavior {
    @Override
    public void activate(Tile tile, Field field) {
        System.out.println("Normal tile activated");
        field.removeTile((int) tile.getX(), (int) tile.getY());
    }
}
