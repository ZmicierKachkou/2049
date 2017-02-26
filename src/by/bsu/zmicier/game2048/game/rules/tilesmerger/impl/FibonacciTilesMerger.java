package by.bsu.zmicier.game2048.game.rules.tilesmerger.impl;

import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class FibonacciTilesMerger extends AbstractIntegerTilesMerger {

    public FibonacciTilesMerger(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public boolean isMerged(Tile<Integer> t1, Tile<Integer> t2) {

        int tmp;
        int a = 1;
        int b = 1;

        int max = Math.max(t1.getValue(), t2.getValue());
        int min = Math.min(t1.getValue(), t2.getValue());

        while (b < max) {
            tmp = b;
            b = a + b;
            a = tmp;
        }

        return  (b == max && a == min);
    }
}
