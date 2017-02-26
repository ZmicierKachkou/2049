package by.bsu.zmicier.game2048.game.dto.coords;

/**
 * Created on 01.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class Coords {
    private int x = 0;
    private int y = 0;

    public Coords() {
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coords)) return false;

        Coords coords = (Coords) o;

        if (x != coords.x) return false;
        if (y != coords.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
