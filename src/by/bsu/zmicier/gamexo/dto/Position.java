package by.bsu.zmicier.gamexo.dto;

import by.bsu.zmicier.meta.game.dto.MetaPosition;

import java.util.Arrays;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class Position implements MetaPosition<Position> {
    private CellState[] table;
    public final static int ROW = 3;
    public final static int SIZE = ROW * ROW;

    public Position() {
        table = new CellState[SIZE];
        for(int i=0; i<SIZE; i++) {
            table[i] = CellState.EMPTY;
        }
    }

    public CellState getCell(int index) {
        if(index < 0 || index >= SIZE) {
            return null;
        }

        return table[index];
    }

    public CellState getCell(int x, int y) {
        if(x < 0 || y < 0 || x >= ROW || y >= ROW) {
            return null;
        }

        return table[x * ROW + y];
    }

    public void setCell(int index, CellState state) {
        if(index >= 0 && index < SIZE) {
            table[index] = state;
        }
    }

    public void setCell(int x, int y, CellState state) {
        if(x >= 0 && y >= 0 && x < ROW && y < SIZE) {
            table[x * ROW + y] = state;
        }
    }

    public Position copy() {
        Position newPosition = new Position();
        System.arraycopy(this.table, 0, newPosition.table, 0, SIZE);
        return newPosition;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int x = 0; x < ROW; x++) {
            for(int y = 0; y < ROW; y++) {
                builder.append(table[x * ROW + y]).append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
