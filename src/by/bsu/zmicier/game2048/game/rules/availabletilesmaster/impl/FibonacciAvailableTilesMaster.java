package by.bsu.zmicier.game2048.game.rules.availabletilesmaster.impl;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.Game2048AvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Kachkou [Dzmitry_Kachkou@epam.com]
 */
public class FibonacciAvailableTilesMaster extends Game2048AvailableTilesMaster<Integer> {
    public FibonacciAvailableTilesMaster(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public List<Tile<Integer>> getAvailableTiles(Position<Integer> pos) {
        List<Tile<Integer>> list = new ArrayList<Tile<Integer>>(2);

        list.add(new Tile<Integer>(1));
        list.add(new Tile<Integer>(2));

        return list;
    }
}
