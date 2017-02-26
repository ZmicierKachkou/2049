package by.bsu.zmicier.meta.game.player.impl.estimators;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;
import by.bsu.zmicier.meta.learning.estimation.impl.EmptyEstimationFunction;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractEstimatorPlayer<M, P extends MetaPosition<P>> extends AbstractPlayer<M, P> {

    protected EstimationFunction<P> estimationFunction;

    protected AbstractEstimatorPlayer() {
        estimationFunction = new EmptyEstimationFunction<P>();
    }

    public AbstractEstimatorPlayer(EstimationFunction<P> estimationFunction) {
        this.estimationFunction = estimationFunction;
    }

    public EstimationFunction<P> getEstimationFunction() {
        return estimationFunction;
    }

    public void setEstimationFunction(EstimationFunction<P> estimationFunction) {
        this.estimationFunction = estimationFunction;
    }

}
