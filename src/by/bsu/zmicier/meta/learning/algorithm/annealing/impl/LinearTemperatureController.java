package by.bsu.zmicier.meta.learning.algorithm.annealing.impl;

import by.bsu.zmicier.meta.learning.algorithm.annealing.TemperatureController;

/**
 * Created on 28.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class LinearTemperatureController extends TemperatureController{
    private double speed;

    public LinearTemperatureController(double initialState, double terminateState, double speed) {
        super(initialState, terminateState);
        this.speed = speed;
    }

    @Override
    public double getNextTemperature() {
        return currentState - speed * (initialState - terminateState);
    }
}
