package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;
import by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper;

import java.util.*;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class MaxFragmentsEstimationFunction extends AbstractCornerAnalyserEstimationFunction {
    public MaxFragmentsEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        super(factory);
    }

    @Override
    public float estimatePosition(Position<Integer> p) {
        PositionAnalyticsWrapper wrapper = factory.getWrapper(p);

        TreeMap<Tile<Integer>, List<Coords>> map =  wrapper.getMap();
        int[][] chains = wrapper.getChains();

        int counter = 0;
        List<Coords> allMax = new ArrayList<Coords>();
        List<Coords> cornerMax = new LinkedList<Coords>();
        for (Coords coords : map.get(map.lastKey())) {
            allMax.add(coords);
            if (isCorner(p, coords)) cornerMax.add(coords);
        }
        while (!cornerMax.isEmpty()) {
            counter++;
            HashSet<Coords> fragmentMax = new HashSet<Coords>();
            fragmentMax.add(cornerMax.remove(0));
            while (!fragmentMax.isEmpty()) {
                Coords coords = fragmentMax.iterator().next();
                fragmentMax.remove(coords);
                allMax.remove(coords);
                cornerMax.remove(coords);
                Coords leftCoords = new Coords(coords.getX() - 1, coords.getY());
                if (allMax.contains(leftCoords)) {
                    fragmentMax.add(leftCoords);
                    allMax.remove(leftCoords);
                    cornerMax.remove(leftCoords);
                }
                Coords rightCoords = new Coords(coords.getX() + 1, coords.getY());
                if (allMax.contains(rightCoords)) {
                    fragmentMax.add(rightCoords);
                    allMax.remove(rightCoords);
                    cornerMax.remove(rightCoords);
                }
                Coords upCoords = new Coords(coords.getX(), coords.getY() + 1);
                if (allMax.contains(upCoords)) {
                    fragmentMax.add(upCoords);
                    allMax.remove(upCoords);
                    cornerMax.remove(upCoords);
                }
                Coords downCoords = new Coords(coords.getX(), coords.getY() - 1);
                if (allMax.contains(downCoords)) {
                    fragmentMax.add(downCoords);
                    allMax.remove(downCoords);
                    cornerMax.remove(downCoords);
                }
            }
        }
        return 1 - counter - allMax.size();
    }
}
