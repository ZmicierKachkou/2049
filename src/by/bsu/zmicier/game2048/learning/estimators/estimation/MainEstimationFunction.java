package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.meta.learning.estimation.weight.WeightFunction;
import by.bsu.zmicier.meta.learning.estimation.weight.impl.LinearWeightFunction;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class MainEstimationFunction<P> implements EstimationFunction<P> {
    private List<FunctionalPair<P>> functions = new LinkedList<FunctionalPair<P>>();

    public void addFunction(EstimationFunction<P> es, WeightFunction weight) {
        functions.add(new FunctionalPair<P>(es, weight));
    }

    public void addFunction(EstimationFunction<P> es, float coeff) {
        functions.add(new FunctionalPair<P>(es, new LinearWeightFunction(coeff)));
    }

    public int getSize() {
        return functions.size();
    }

    @Override
    public float estimatePosition(P p) {
        float estimation = 0;
        for(FunctionalPair<P> pair: functions) {
            estimation += pair.getValue(p);
        }
        return estimation;
    }

    private static class FunctionalPair<P> {
        private EstimationFunction<P> estimation;
        private WeightFunction weight;

        private FunctionalPair(EstimationFunction<P> estimation, WeightFunction weight) {
            this.estimation = estimation;
            this.weight = weight;
        }

        public float getValue(P position) {
            return weight.getValue(estimation.estimatePosition(position));
        }
    }


}
