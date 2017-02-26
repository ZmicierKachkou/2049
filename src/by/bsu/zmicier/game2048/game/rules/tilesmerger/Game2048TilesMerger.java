package by.bsu.zmicier.game2048.game.rules.tilesmerger;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;

import java.util.List;

/**
 * Created on 29.10.2015.
 *
 * @author Źmicier Dzikański
 * this manager checks if tiles should be merged
 */
public abstract class Game2048TilesMerger<T> extends AbstractRule<FirstPlayerMove, SecondPlayerMove<T>, Position<T>, Game2048RulesMediator<T>> {

    public Game2048TilesMerger(Game2048RulesMediator<T> server) {
        super(server);
    }

    /**
     * checks if tiles should be merged
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return true if they should be merger and false otherwise
     */
    public abstract boolean isMerged(Tile<T> t1, Tile<T> t2);

    /**
     * for special versions of the game. Checks if several tiles should be merged in one
     * @param list is a list of tiles
     * @return true if all tiles should be merged and false otherwise
     */
    public abstract boolean isMerged(List<by.bsu.zmicier.game2048.game.dto.tiles.Tile<T>> list);

    /**
     * merges two tiles
     * @param t1 is a first tile
     * @param t2 is a second tile
     * @return new tile
     */
    public abstract Tile<T> merge(Tile<T> t1, Tile<T> t2);

    /**
     * for special versions of the game. Merges several tiles in one
     * @param list is a list of tiles
     * @return new tile
     */
    public abstract Tile<T> merge(List<Tile<T>> list);
}
