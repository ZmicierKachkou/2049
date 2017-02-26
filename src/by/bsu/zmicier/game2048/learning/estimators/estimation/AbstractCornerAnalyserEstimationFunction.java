package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractCornerAnalyserEstimationFunction extends AbstractAnalyticsEstimationFunction {

    public AbstractCornerAnalyserEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        super(factory);
    }

    protected boolean isCorner(Position<Integer> p, Coords coords) {
        return (coords.getX() == 0 || coords.getX() == p.getSize()-1) && (coords.getY() == 0 || coords.getY() == p.getSize()-1);
    }
}
