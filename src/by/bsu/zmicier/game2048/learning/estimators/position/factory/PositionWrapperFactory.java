package by.bsu.zmicier.game2048.learning.estimators.position.factory;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.learning.estimators.position.PositionWrapper;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface PositionWrapperFactory<T, S extends PositionWrapper<T>> {
     S getWrapper(Position<T> position);
}
