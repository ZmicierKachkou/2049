package by.bsu.zmicier.meta.learning.estimation.weight.impl;

import by.bsu.zmicier.meta.learning.estimation.weight.WeightFunction;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class LinearWeightFunction implements WeightFunction {
    private float coeff;

    public LinearWeightFunction(float coeff) {
        this.coeff = coeff;
    }

    public float getCoeff() {
        return coeff;
    }

    public void setCoeff(float coeff) {
        this.coeff = coeff;
    }

    @Override
    public float getValue(float param) {
        return coeff * param;
    }
}
