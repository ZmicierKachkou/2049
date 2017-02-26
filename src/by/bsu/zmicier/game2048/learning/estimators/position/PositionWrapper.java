package by.bsu.zmicier.game2048.learning.estimators.position;

import by.bsu.zmicier.game2048.game.dto.position.Position;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class PositionWrapper<T> {
    protected Position<T> position;

    public PositionWrapper(Position<T> position) {
        this.position = position;
    }
}
