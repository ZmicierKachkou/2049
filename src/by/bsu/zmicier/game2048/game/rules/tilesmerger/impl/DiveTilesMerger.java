package by.bsu.zmicier.game2048.game.rules.tilesmerger.impl;

import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.Game2048AvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.Game2048TilesMerger;

/**
 * @author Dzmitry Kachkou [Dzmitry_Kachkou@epam.com]
 */
public class DiveTilesMerger extends AbstractIntegerTilesMerger {
    public DiveTilesMerger(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public boolean isMerged(Tile<Integer> t1, Tile<Integer> t2) {
        return t1.getValue() % t2.getValue() == 0 || t2.getValue() % t1.getValue() == 0;
    }
}
