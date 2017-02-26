package by.bsu.zmicier.meta.learning.algorithm.knn;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface DataConverter<S, P> {
    S convertData(P data);
}
