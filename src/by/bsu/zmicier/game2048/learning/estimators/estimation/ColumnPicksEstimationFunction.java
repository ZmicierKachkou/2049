package by.bsu.zmicier.game2048.learning.estimators.estimation;


import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class ColumnPicksEstimationFunction implements EstimationFunction<Position<Integer>> {
    @Override
    public float estimatePosition(Position<Integer> p) {
        float counter = 0;
        for(int y = 0; y < p.getSize(); y++) {
            List<Tile<Integer>> list = new ArrayList<Tile<Integer>>();
            for(int x = 0; x<p.getSize(); x++)
                if(p.getTile(x, y) != null &&
                        (list.size() == 0 || !p.getTile(x,y).equals(list.get(list.size()-1))))
                    list.add(p.getTile(x,y));
            for(int i=1; i<list.size()-1; i++) {
                if((list.get(i).getValue() - list.get(i-1).getValue()) * (list.get(i+1).getValue() - list.get(i).getValue()) < 0) counter++;
            }
        }
        return -counter;
    }
}
