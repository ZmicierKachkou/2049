package by.bsu.zmicier.game2048.game.rules.pointscounter.impl;

import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.pointscounter.Game2048PointsCounter;

import java.math.BigInteger;
import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public class DefaultPointsCounter extends Game2048PointsCounter<Integer> {
    public DefaultPointsCounter(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public long pointsForMerge(Tile<Integer> t1, Tile<Integer> t2) {
        return t1.getValue() + t2.getValue();
    }

    @Override
    public long pointsForMerge(List<by.bsu.zmicier.game2048.game.dto.tiles.Tile<Integer>> list) {
        return pointsForMerge(list.get(0), list.get(1));
    }
}
