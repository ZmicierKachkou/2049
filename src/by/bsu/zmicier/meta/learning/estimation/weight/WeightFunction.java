package by.bsu.zmicier.meta.learning.estimation.weight;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface WeightFunction {
    /**
     * method calculates weighted heuristic value
     * @param param heuristic value
     * @return weighted value
     */
    float getValue(float param);
}
