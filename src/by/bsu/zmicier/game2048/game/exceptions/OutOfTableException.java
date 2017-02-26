package by.bsu.zmicier.game2048.game.exceptions;

/**
 * Created on 06.01.2017.
 *
 * @author Źmicier Dzikański
 */
public class OutOfTableException extends ArrayIndexOutOfBoundsException {
    public OutOfTableException() {
    }

    public OutOfTableException(int index) {
        super(index);
    }

    public OutOfTableException(String s) {
        super(s);
    }
}
