package by.bsu.zmicier.game2048.learning.estimators.estimation;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;
import by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper;

import java.util.List;
import java.util.TreeMap;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class NotCornerMaxTileEstimationFunction extends AbstractCornerAnalyserEstimationFunction {
    public NotCornerMaxTileEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        super(factory);
    }

    @Override
    public float estimatePosition(Position<Integer> p) {
        PositionAnalyticsWrapper wrapper = factory.getWrapper(p);

        TreeMap<Tile<Integer>, List<Coords>> map =  wrapper.getMap();

        for(Coords coords: map.get(map.lastKey())) {
            if(!isCorner(p, coords))
                return -1;
        }
        return 0;
    }
}
