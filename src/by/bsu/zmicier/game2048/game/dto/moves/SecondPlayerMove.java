package by.bsu.zmicier.game2048.game.dto.moves;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class SecondPlayerMove<T> extends Coords {
    private Tile<T> tile;

    public SecondPlayerMove() {
    }

    public SecondPlayerMove(Integer x, Integer y, by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> tile) {
        super(x, y);
        this.tile = tile;
    }

    public SecondPlayerMove(by.bsu.zmicier.game2048.game.dto.coords.Coords coords, by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> tile) {
        super(coords.getX(), coords.getY());
        this.tile = tile;
    }

    public by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> getTile() {
        return tile;
    }

    public void setTile(by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> tile) {
        this.tile = tile;
    }

    @Override
    public String toString() {
        return tile.getValue() + ", (" + this.getX() + ", " + this.getY() + ")";
    }
}
