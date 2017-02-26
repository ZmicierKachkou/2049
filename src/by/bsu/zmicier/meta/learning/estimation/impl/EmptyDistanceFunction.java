package by.bsu.zmicier.meta.learning.estimation.impl;

import by.bsu.zmicier.meta.learning.estimation.DistanceFunction;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class EmptyDistanceFunction<S> implements DistanceFunction<S> {
    @Override
    public float getDistance(S from, S to) {
        return 0;
    }
}
