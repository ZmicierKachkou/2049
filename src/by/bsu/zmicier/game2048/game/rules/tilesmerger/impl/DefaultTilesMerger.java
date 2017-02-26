package by.bsu.zmicier.game2048.game.rules.tilesmerger.impl;

import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;

/**
 * Created on 29.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultTilesMerger extends AbstractIntegerTilesMerger {

    public DefaultTilesMerger(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public boolean isMerged(Tile<Integer> t1, Tile<Integer> t2) {
        return t1.getValue().equals(t2.getValue());
    }


}
