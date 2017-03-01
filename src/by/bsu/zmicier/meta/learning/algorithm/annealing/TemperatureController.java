package by.bsu.zmicier.meta.learning.algorithm.annealing;

/**
 * Created on 28.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class TemperatureController {
    protected double initialState;
    protected double terminateState;
    protected double currentState;

    protected TemperatureController(double initialState, double terminateState) {
        this.initialState = initialState;
        this.terminateState = terminateState;
    }

    public abstract double getNextTemperature();

    public boolean isTerminal() {
        return currentState <= terminateState;
    }

}
