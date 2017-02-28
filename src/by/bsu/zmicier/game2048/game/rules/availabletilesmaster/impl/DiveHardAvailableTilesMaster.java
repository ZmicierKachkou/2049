package by.bsu.zmicier.game2048.game.rules.availabletilesmaster.impl;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.Game2048AvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;

import java.util.*;

/**
 * @author Dzmitry Kachkou [Dzmitry_Kachkou@epam.com]
 */
public class DiveHardAvailableTilesMaster extends Game2048AvailableTilesMaster<Integer> {
    public DiveHardAvailableTilesMaster(Game2048RulesMediator<Integer> server) {
        super(server);
    }

    @Override
    public List<Tile<Integer>> getAvailableTiles(Position<Integer> pos) {
        Set<Integer> divs = new HashSet<Integer>();

        for(int x = 0; x < pos.getSize(); x++) {
            for(int y = 0; y < pos.getSize(); y++) {
                if(pos.getTile(x, y) != null) {
                    int n = pos.getTile(x, y).getValue();
                    for (int i = 2; i <= n / i; i++) {
                        while (n % i == 0) {
                            divs.add(i);
                            n /= i;
                        }
                    }
                    if (n > 1) {
                        divs.add(n);
                    }
                }
            }
        }

        List<Tile<Integer>> list = new LinkedList<Tile<Integer>>();

        for(Integer factor: divs) {
            list.add(new Tile<Integer>(factor));
        }

        if(list.isEmpty()) {
            list.add(new Tile<Integer>(2));
        }

        return list;
    }
}
