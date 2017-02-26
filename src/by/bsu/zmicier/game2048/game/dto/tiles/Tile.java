package by.bsu.zmicier.game2048.game.dto.tiles;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class Tile<T> {

    private T value;

    public Tile() {
    }

    public Tile(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile that = (Tile) o;

        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "(" + value + ")";
    }
}
