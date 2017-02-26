package by.bsu.zmicier.game2048.learning.estimators.distance;

import by.bsu.zmicier.meta.learning.algorithm.knn.Move;
import by.bsu.zmicier.meta.learning.estimation.DistanceFunction;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class BasicDistanceFunction implements DistanceFunction<int[]> {
    @Override
    public float getDistance(int[] from, int[] to) {
        float estimate = 0;
        int size = from.length;
        for(int i = 0; i < size; i++) {
            estimate += compareField1(from, to, i);
        }
        for(int i = 0; i < size; i++) {
            for(int j = i+1; j<size; j++) {
                estimate += compareField2(from, to, i, j);
            }
        }

        return estimate;
    }

    private boolean bothZeroOrNot(int i, int j) {
        return !(i == 0 ^ j == 0);
    }

    private boolean bothTwoOrNot(int i, int j) {
        return !(i == 2 ^ j == 2);
    }

    private float compareField1(int[] from, int[] to, int i) {
        if(!bothZeroOrNot(from[i], to[i])) {
            return 0;
        } else if(!bothTwoOrNot(from[i], to[i])) {
            return 0.5f;
        } else {
            return 1;
        }
    }

    private float calculateDiff(int x, int y) {
        if(x == y) return 1f;
        if(x == 2 * y) return 1.5f;
        if(x > y) return 2f;
        if(y == 2 * x) return 0.5f;
        return 0f;
    }

    private float compareField2(int[] from, int[] to, int i, int j) {
        int diff1 = i/4 - j/4;
        int diff2 = i%4 - j%4;

        return 4 - Math.abs(calculateDiff(from[i], to[j]) - calculateDiff(from[i], to[j]))
                / (float)(diff1+diff2);
    }
}
