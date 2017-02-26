package by.bsu.zmicier.game2048.game.rules.movemaker.impl;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class FibonacciMoveMaker extends AbstractUsualMoveMaker<Integer> {
    public FibonacciMoveMaker(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public List<Tile<Integer>> getAvailableTiles(Position<Integer> pos) {
        List<Tile<Integer>> list = new ArrayList<by.bsu.zmicier.game2048.game.dto.tiles.Tile<Integer>>();

        list.add(new Tile<Integer>(1));
        list.add(new Tile<Integer>(2));

        return list;
    }
}

