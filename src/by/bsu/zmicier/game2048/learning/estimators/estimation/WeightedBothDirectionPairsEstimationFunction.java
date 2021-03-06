package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class WeightedBothDirectionPairsEstimationFunction extends AbstractAnalyticsEstimationFunction {
    public WeightedBothDirectionPairsEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        super(factory);
    }

    @Override
    public float estimatePosition(Position<Integer> p) {
        return factory.getWrapper(p).getWeightedHorisontalPairs() + factory.getWrapper(p).getWeightedVerticalPairs();
    }
}
