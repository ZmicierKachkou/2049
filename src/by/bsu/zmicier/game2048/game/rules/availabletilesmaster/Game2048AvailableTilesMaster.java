package by.bsu.zmicier.game2048.game.rules.availabletilesmaster;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;

import java.util.List;

/**
 * @author Dzmitry Kachkou [Dzmitry_Kachkou@epam.com]
 */
public abstract class Game2048AvailableTilesMaster<T> extends AbstractRule<FirstPlayerMove, SecondPlayerMove<T>, Position<T>, Game2048RulesMediator<T>> {
    public Game2048AvailableTilesMaster(Game2048RulesMediator<T> server) {
        super(server);
    }

    public abstract List<Tile<T>> getAvailableTiles(Position<T> pos);
}
