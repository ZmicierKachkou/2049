package by.bsu.zmicier.meta.learning.algorithm.knn;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class EmptyDataConverter<P> implements DataConverter<P, P> {
    @Override
    public P convertData(P data) {
        return data;
    }
}
