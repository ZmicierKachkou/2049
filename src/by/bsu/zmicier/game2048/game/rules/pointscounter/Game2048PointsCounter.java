package by.bsu.zmicier.game2048.game.rules.pointscounter;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;

import java.math.BigInteger;
import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public abstract class Game2048PointsCounter<T> extends AbstractRule<FirstPlayerMove, SecondPlayerMove<T>, Position<T>, Game2048RulesMediator<T>> {
    public Game2048PointsCounter(Game2048RulesMediator<T> server) {
        super(server);
    }

    /**
     * calculate points for merge
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return points
     */
    public abstract long pointsForMerge(Tile<T> t1, Tile<T> t2);

    /**
     * for special versions of the game. Calculate points for merge
     * @param list is a list of tiles
     * @return points
     */
    public abstract long pointsForMerge(List<Tile<T>> list);
}
