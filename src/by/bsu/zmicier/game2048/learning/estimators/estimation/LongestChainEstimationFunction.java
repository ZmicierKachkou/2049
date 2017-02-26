package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;
import by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class LongestChainEstimationFunction extends AbstractAnalyticsEstimationFunction {
    public LongestChainEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        super(factory);
    }

    @Override
    public float estimatePosition(Position<Integer> p) {
        PositionAnalyticsWrapper wrapper = factory.getWrapper(p);

        int maxChain = 0;
        for(int x = 0; x < p.getSize(); x++) {
            for(int y = 0; y < p.getSize(); y++) {
                if(wrapper.getChains()[x][y] > maxChain) {
                    maxChain = wrapper.getChains()[x][y];
                }
            }
        }
        return maxChain;
    }
}
