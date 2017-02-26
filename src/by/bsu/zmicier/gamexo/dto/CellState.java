package by.bsu.zmicier.gamexo.dto;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public enum CellState {
    X, O, EMPTY;

    @Override
    public String toString() {
        if(this == EMPTY) {
            return "-";
        } else {
            return super.toString();
        }
    }
}
