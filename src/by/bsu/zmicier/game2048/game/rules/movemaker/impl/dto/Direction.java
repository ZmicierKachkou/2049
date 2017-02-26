package by.bsu.zmicier.game2048.game.rules.movemaker.impl.dto;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;

/**
 * Created on 03.04.2016.
 *
 * @author Źmicier Dzikański
 */
public class Direction<T> {
    private FirstPlayerMove direction;
    private Position<T> position;

    public Direction(Position<T> position, FirstPlayerMove direction) {
        this.position = position;
        this.direction = direction;
    }

    private int getX(int x, int y) {
        if(direction == by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove.UP) return y;
        else if(direction == by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove.DOWN) return position.getSize()-y-1;
        else if(direction == by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove.LEFT) return x;
        else return position.getSize()-x-1;
    }

    private int getY(int x, int y) {
        if(direction == by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove.UP) return x;
        else if(direction == by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove.DOWN) return position.getSize()-x-1;
        else if(direction == by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove.LEFT) return y;
        else return position.getSize()-y-1;
    }

    public by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> getTile(int x, int y) {
        return position.getTile(getX(x, y), getY(x, y));
    }

    public void setTile(by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> t, int x, int y) {
        position.setTile(getX(x, y), getY(x, y), t);
    }

    public int getSize() {
        return position.getSize();
    }

    public by.bsu.zmicier.game2048.game.dto.position.Position<T> getPosition() {
        return position;
    }
}
