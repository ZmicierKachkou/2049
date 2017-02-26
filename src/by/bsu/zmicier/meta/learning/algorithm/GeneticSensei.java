package by.bsu.zmicier.meta.learning.algorithm;

import by.bsu.zmicier.game2048.learning.estimators.estimation.MainEstimationFunction;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.impl.estimators.AbstractEstimatorPlayer;
import by.bsu.zmicier.meta.learning.algorithm.genetic.model.Genome;
import by.bsu.zmicier.meta.learning.arena.events.impl.EndGameArenaEvent;
import by.bsu.zmicier.meta.learning.arena.events.impl.NewGameArenaEvent;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;
import by.bsu.zmicier.meta.learning.sensei.AbstractSensei;

import java.util.*;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class GeneticSensei<M, P extends MetaPosition<P>> extends AbstractSensei<M, P> {
    public static final int POPULATION_SIZE = 80;
    public static final int GAMES_FOR_FITNESS_COUNTING = 1;

    public static final double ELITE_RATE = 0.05;
    public static final double SURVIVE_RATE = 0.1;
    public static final double MUTATION_RATE = 0.05;

    private Map<Genome, MainEstimationFunction<P>> cachedFunctions = new WeakHashMap<Genome, MainEstimationFunction<P>>();

    private float[] fitness = new float[POPULATION_SIZE];
    private List<Genome> currentGeneration;
    private int gameNumber = -1;
    private int step = 0;
    private List<EstimationFunction<P>> functions;

    private AbstractEstimatorPlayer<M, P> student;
    private AbstractEstimatorPlayer<M, P> master;

    private int size;

    public GeneticSensei(AbstractEstimatorPlayer<M, P> student, AbstractEstimatorPlayer<M,P> master, List<EstimationFunction<P>> functions) {
        this.student = student;
        this.master = master;
        this.functions = functions;
        this.size = functions.size();
        for(int i=0; i < fitness.length; i++) {
            fitness[i] = 0;
        }

        currentGeneration = initializePopulation();
    }

    @Override
    public AbstractEstimatorPlayer<M, P> getStudent() {
        return getPlayer(currentGeneration.get((gameNumber+1) / GAMES_FOR_FITNESS_COUNTING), false);
    }

    @Override
    public AbstractEstimatorPlayer<M, P> getMaster() {
        List<Genome> parents = analyzeGeneration(currentGeneration);
        return getPlayer(parents.get(0), true);
    }

    public void setStudent(AbstractEstimatorPlayer<M, P> student) {
        this.student = student;
    }

    public void setMaster(AbstractEstimatorPlayer<M, P> master) {
        this.master = master;
    }

    @Override
    public void processNewGameEvent(NewGameArenaEvent event) {
        gameNumber++;
    }

    @Override
    public void processEndGameEvent(EndGameArenaEvent<P> event) {
        float points = 0;
        if(getId().equals(event.getPlayerId())) {
            points = event.getResult().getFirstPoints();
        } else if(getId().equals(event.getOpponentId())) {
            points = event.getResult().getSecondPoints();
        }
        fitness[gameNumber / GAMES_FOR_FITNESS_COUNTING] += points / (float) GAMES_FOR_FITNESS_COUNTING;

        if(gameNumber == POPULATION_SIZE * GAMES_FOR_FITNESS_COUNTING - 1) {
            gameNumber = -1;
            step++;
            List<Genome> parents = analyzeGeneration(currentGeneration);

            System.out.print(step + " step: ");
            for(float f: parents.get(0).getCoeff()) {
                System.out.print(f + "f, ");
            }
            System.out.println("result: " + parents.get(0).getFitness());

            currentGeneration = getNewGeneration(parents);
            for(int i=0; i < fitness.length; i++) {
                fitness[i] = 0;
            }
        }
    }

    public List<Genome> initializePopulation() {
        List<Genome> list = new ArrayList<Genome>(POPULATION_SIZE);
        for(int i=0; i < POPULATION_SIZE; i++) {
            Genome genome = new Genome(size);
            float average = 0;
            for(int j=0; j < size; j++) {
                genome.getCoeff()[j] = generateRandomParam();
                average += genome.getCoeff()[j] * genome.getCoeff()[j];
            }
            if(average != 0) {
                average = (float) Math.sqrt(average);
                for(int j=0; j < size; j++) {
                    genome.getCoeff()[j] /= average;
                }
            }
            list.add(genome);
        }
        return list;
    }

    private float generateRandomParam() {
        return (float)(Math.random());
    }

    private void mutate(Genome g, int i) {
        g.getCoeff()[i] = (float)(Math.random());
    }

    private List<Genome> getNewGeneration(List<Genome> l) {
        int esize = (int) (POPULATION_SIZE * ELITE_RATE);
        List<Genome> newGeneration = new ArrayList<Genome>(POPULATION_SIZE);
        for(int i = 0; i < esize; i++) {
            newGeneration.add(l.get(i));
        }
        for(int i = esize; i < POPULATION_SIZE; i++) {
            Genome newCitizen = new Genome(size);
            int i1 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int i2 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            float average = 0;
            for(int j = 0; j < size; j++) {
                double rand = Math.random();
                newCitizen.getCoeff()[j] = (float) (l.get(i1).getCoeff()[j] * rand + l.get(i2).getCoeff()[j] * rand * (1 - rand));
                if(Math.random() < MUTATION_RATE) mutate(newCitizen, j);
                average += newCitizen.getCoeff()[j] * newCitizen.getCoeff()[j];
            }
            if(average != 0) {
                average = (float) Math.sqrt(average);
                for(int j=0; j < size; j++) {
                    newCitizen.getCoeff()[j] /= average;
                }
            }
            newGeneration.add(newCitizen);
        }
        return newGeneration;
    }

    private List<Genome> analyzeGeneration(List<Genome> parents) {
        for(int i = 0; i < parents.size(); i++) {
            parents.get(i).setFitness(fitness[i]);
        }
        Collections.sort(parents);
        return parents;
    }

    private AbstractEstimatorPlayer<M, P> getPlayer(Genome g, boolean isMaster) {
        MainEstimationFunction<P> function = cachedFunctions.get(g);
        if(function == null) {
            function = new MainEstimationFunction<P>();
            Iterator<EstimationFunction<P>> iter = functions.iterator();

            for(float coeff: g.getCoeff()) {
                EstimationFunction<P> esFunction = iter.next(); //sizes of coeff[] and functions are equal
                function.addFunction(esFunction, coeff);
            }

            cachedFunctions.put(g, function);
        }
        if(isMaster) {
            master.setEstimationFunction(function);
            return master;
        } else {
            student.setEstimationFunction(function);
            return student;
        }

    }
}
