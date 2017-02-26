package by.bsu.zmicier.game2048.game.rules.tilesmerger.impl;

import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.Game2048TilesMerger;

import java.util.List;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractIntegerTilesMerger extends Game2048TilesMerger<Integer> {
    public AbstractIntegerTilesMerger(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public boolean isMerged(List<Tile<Integer>> list) {
        return list.size() == 2 && isMerged(list.get(0), list.get(1));
    }

    @Override
    public Tile<Integer> merge(Tile<Integer> t1, Tile<Integer> t2) {
        return new Tile<Integer>(t1.getValue() + t2.getValue());
    }

    @Override
    public Tile<Integer> merge(List<Tile<Integer>> list) {
        return merge(list.get(0), list.get(1));
    }
}
