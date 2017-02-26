package by.bsu.zmicier.meta.learning.estimation;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface DistanceFunction<S> {
    float getDistance(S from, S to);
}
