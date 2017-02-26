package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractAnalyticsEstimationFunction implements EstimationFunction<Position<Integer>> {

    protected PositionAnalyticsWrapperFactory factory;

    public AbstractAnalyticsEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        this.factory = factory;
    }

}
