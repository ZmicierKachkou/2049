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
public class LargeTilesEstimationFunction extends AbstractAnalyticsEstimationFunction {
    public LargeTilesEstimationFunction(PositionAnalyticsWrapperFactory factory) {
        super(factory);
    }

    private boolean isNear(Coords t1, Coords t2) {
        if(t1.getX() == t2.getX() && Math.abs(t1.getY()-t2.getY()) == 1) {
            return true;
        }
        if(t1.getY() == t2.getY() && Math.abs(t1.getX()-t2.getX()) == 1) {
            return true;
        }
        return false;
    }

    private boolean hasWay(Position<Integer> p, Coords t1, Coords t2) {
        if(t1.getY() == t2.getY()) {
            for(int i=1; i<Math.abs(t1.getX()-t2.getX()); i++)
                if(p.getTile(Math.min(t1.getX(), t2.getX())+i, t1.getY())!=null) {
                    return false;
                }
            return true;
        }
        if(t1.getX() == t2.getX()) {
            for(int i=1; i<Math.abs(t1.getY()-t2.getY()); i++)
                if(p.getTile(t1.getX(), Math.min(t1.getY(), t2.getY())+i)!=null) {
                    return false;
                }
            return true;
        }
        return false;
    }

    @Override
    public float estimatePosition(Position<Integer> p) {

        PositionAnalyticsWrapper wrapper = factory.getWrapper(p);
        TreeMap<Tile<Integer>, List<Coords>> map =  wrapper.getMap();

        Tile<Integer> t = map.lastKey();
        while(t!=null) {
            if(map.get(t).size() == 1) t = map.lowerKey(t);
            else break;
        }
        if(t==null) {
            return 1;
        }
        else {
            int result = 0;
            for(int i=0; i<map.get(t).size(); i++) {
                for(int j=i+1; j<map.get(t).size(); j++) {
                    if(isNear(map.get(t).get(i), map.get(t).get(j))) {
                        result++;
                    }
                    if(hasWay(p, map.get(t).get(i), map.get(t).get(j))) {
                        result++;
                    }
                }
            }
            return result;
        }
    }
}
