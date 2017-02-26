package by.bsu.zmicier.meta.learning.estimation;


/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface EstimationFunction<P> {
    /**
     * method contains logic of position estimation
     * @param position is a current position
     * @return a score of the position
     */
    public float estimatePosition(P position);
}
