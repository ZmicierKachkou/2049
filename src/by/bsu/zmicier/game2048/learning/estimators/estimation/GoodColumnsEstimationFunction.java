package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class GoodColumnsEstimationFunction implements EstimationFunction<Position<Integer>> {

    @Override
    public float estimatePosition(Position<Integer> p) {
        return 0;
    }
}
