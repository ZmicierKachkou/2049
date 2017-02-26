package by.bsu.zmicier.meta.learning.estimation.impl;

import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class EmptyEstimationFunction<P> implements EstimationFunction<P>{
    @Override
    public float estimatePosition(P position) {
        return 0;
    }
}
