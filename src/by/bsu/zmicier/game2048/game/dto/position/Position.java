package by.bsu.zmicier.game2048.game.dto.position;

import by.bsu.zmicier.meta.game.dto.MetaPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class Position<T> implements MetaPosition<Position<T>> {
    public final static int DEFAULT_SIZE = 4;

    private final int size;
    private List<by.bsu.zmicier.game2048.game.dto.tiles.Tile<T>> table;


    public Position() {
        this(DEFAULT_SIZE);
    }

    public Position(int size) {
        this.size = size;
        table = new ArrayList<by.bsu.zmicier.game2048.game.dto.tiles.Tile<T>>(size * size);
        for(int i = 0; i < size * size; i++) {
            table.add(null);
        }
    }

    public int getSize() {
        return size;
    }

    public void setTile(int x, int y, by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> tile) {
        if(x >= size || x < 0) {
            throw new by.bsu.zmicier.game2048.game.exceptions.OutOfTableException(x);
        } else if( y >= size || y < 0) {
            throw new by.bsu.zmicier.game2048.game.exceptions.OutOfTableException(y);
        } else {
            table.set(y * size + x, tile);
        }
    }

    public by.bsu.zmicier.game2048.game.dto.tiles.Tile<T> getTile(int x, int y) {
        if(x >= size || x < 0) {
            throw new by.bsu.zmicier.game2048.game.exceptions.OutOfTableException(x);
        } else if( y >= size || y < 0) {
            throw new by.bsu.zmicier.game2048.game.exceptions.OutOfTableException(y);
        } else {
            return table.get(y * size + x);
        }
    }

    public Position<T> copy() {
        Position<T> newPosition = new Position<T>(size);
        for(int x=0; x<size; x++) {
            Collections.copy(newPosition.table, this.table);
        }
        return newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;

        if (size != position.size) {
            return false;
        }

        if (table != null ? !table.equals(position.table) : position.table != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                if(getTile(x, y) == null) {
                    builder.append(" - \t");
                } else {
                    builder.append(" ").append(getTile(x, y).getValue()).append(" \t");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
