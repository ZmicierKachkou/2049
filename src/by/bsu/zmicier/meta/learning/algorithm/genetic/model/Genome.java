package by.bsu.zmicier.meta.learning.algorithm.genetic.model;

import java.util.Arrays;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public class Genome implements Comparable<Genome> {
    private float[] coeff;
    private float fitness = -1f;

    public Genome(int size) {
        coeff = new float[size];
    }

    public Genome(float[] coeff) {
        this.coeff = coeff;
    }

    public float[] getCoeff() {
        return coeff;
    }

    public void setCoeff(float[] coeff) {
        this.coeff = coeff;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Genome o) {
        return (int)(o.fitness - fitness);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genome)) return false;

        Genome genome = (Genome) o;

        if (!Arrays.equals(coeff, genome.coeff)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return coeff != null ? Arrays.hashCode(coeff) : 0;
    }
}
