package by.bsu.zmicier.meta.learning.algorithm;

import by.bsu.zmicier.game2048.learning.estimators.estimation.MainEstimationFunction;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.Player;
import by.bsu.zmicier.meta.game.player.impl.estimators.AbstractEstimatorPlayer;
import by.bsu.zmicier.meta.learning.algorithm.annealing.TemperatureController;
import by.bsu.zmicier.meta.learning.arena.events.impl.EndGameArenaEvent;
import by.bsu.zmicier.meta.learning.arena.events.impl.LearningStateArenaEvent;
import by.bsu.zmicier.meta.learning.arena.events.impl.NewGameArenaEvent;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;
import by.bsu.zmicier.meta.learning.sensei.AbstractSensei;

import java.util.Iterator;
import java.util.List;

/**
 * Created on 28.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class AnnealingSensei<M, P extends MetaPosition<P>> extends AbstractSensei<M, P> {
    private List<EstimationFunction<P>> functions;
    private TemperatureController temperatureController;
    private double estimation;
    private double temperature;
    private float[] bestCoeffs;
    private float[] currentStateCoeffs;
    private float[] currentCoeffs;

    private AbstractEstimatorPlayer<M, P> student;
    private AbstractEstimatorPlayer<M, P> master;

    public AnnealingSensei(List<EstimationFunction<P>> functions) {
        this.functions = functions;
    }

    public AnnealingSensei(List<EstimationFunction<P>> functions, TemperatureController temperatureController) {
        this.functions = functions;
        this.temperatureController = temperatureController;
    }

    public AnnealingSensei(List<EstimationFunction<P>> functions, TemperatureController temperatureController,
                           AbstractEstimatorPlayer<M, P> student, AbstractEstimatorPlayer<M, P> master) {
        this.functions = functions;
        this.temperatureController = temperatureController;
        this.student = student;
        this.master = master;
    }

    public TemperatureController getTemperatureController() {
        return temperatureController;
    }

    public void setTemperatureController(TemperatureController temperatureController) {
        this.temperatureController = temperatureController;
    }

    @Override
    public Player<M, P> getStudent() {
        return getPlayer(currentCoeffs, false);
    }

    @Override
    public Player<M, P> getMaster() {
        return getPlayer(bestCoeffs, true);
    }

    @Override
    public void processStartLearningEvent(LearningStateArenaEvent event) {
        currentCoeffs = generateInitialState();
        bestCoeffs = currentCoeffs;

        estimation = Double.MIN_VALUE;
    }

    @Override
    public void processNewGameEvent(NewGameArenaEvent event) {
        temperature = temperatureController.getNextTemperature();
    }

    @Override
    public void processEndGameEvent(EndGameArenaEvent<P> event) {
        float points = 0;
        if(getId().equals(event.getPlayerId())) {
            points = event.getResult().getFirstPoints();
        } else if(getId().equals(event.getOpponentId())) {
            points = event.getResult().getSecondPoints();
        }

        if(points > estimation || Math.random() < Math.exp(-(estimation - points) / temperature)) {
            estimation = points;
            bestCoeffs = currentCoeffs;
            System.out.println("new points: " + estimation);
        }

        currentCoeffs = generateNextState(currentCoeffs);

    }

    private float[] generateInitialState() {
        float[] coeffs = new float[functions.size()];
        float average = 0;

        for(int j=0; j < coeffs.length; j++) {
            coeffs[j] = (float)Math.random();
            average += coeffs[j] * coeffs[j];
        }

        if(average != 0) {
            average = (float) Math.sqrt(average);
            for(int j=0; j < coeffs.length; j++) {
                coeffs[j] /= average;
            }
        }

        return coeffs;
    }

    private float[] generateNextState(float[] oldcoeffs) {
        float[] coeffs = new float[oldcoeffs.length];
        float average = 0;

        for(int j=0; j < coeffs.length; j++) {
            if(Math.random() < 0.3) {
                coeffs[j] = (float) Math.random();
            }
            average += coeffs[j] * coeffs[j];
        }

        if(average != 0) {
            average = (float) Math.sqrt(average);
            for(int j=0; j < coeffs.length; j++) {
                coeffs[j] /= average;
            }
        }

        return coeffs;
    }

    private AbstractEstimatorPlayer<M, P> getPlayer(float[] coeffs, boolean isMaster) {
        MainEstimationFunction<P> function = new MainEstimationFunction<P>();
        Iterator<EstimationFunction<P>> iter = functions.iterator();

        for(float coeff: coeffs) {
            EstimationFunction<P> esFunction = iter.next(); //sizes of coeff[] and functions are equal
            function.addFunction(esFunction, coeff);
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
