package by.bsu.zmicier.game2048.game.rules.positiongenerator.impl;


import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.positiongenerator.Game2048PositionGenerator;

import java.util.List;

/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultPositionGenerator<T> extends Game2048PositionGenerator<T> {

    public DefaultPositionGenerator(Game2048RulesMediator<T> server) {
        super(server);
    }

    @Override
    public Position<T> generatePosition(int size) {
        Position<T> pos = new by.bsu.zmicier.game2048.game.dto.position.Position<T>(size);

        Tile<T> t1 =  getRandom(getRulesMediator().getMoveMaker().getAvailableTiles(pos));
        Tile<T> t2 =  getRandom(getRulesMediator().getMoveMaker().getAvailableTiles(pos));

        List<Coords> coords = getRulesMediator().getMoveMaker().getEmptyCells(pos);
        Coords c1 = coords.remove((int) Math.floor(Math.random() * coords.size()));
        Coords c2 = getRandom(coords);

        pos.setTile(c1.getX(), c1.getY(), t1);
        pos.setTile(c2.getX(), c2.getY(), t2);
        return pos;
    }

    @Override
    public Position<T> generatePosition() {
        return generatePosition(Position.DEFAULT_SIZE);
    }

    private <S>S getRandom(List<S> list) {
        if(list == null || list.isEmpty()) {
            return null;
        }

        return list.get((int)Math.floor(Math.random() * list.size()));
    }
}
