package by.bsu.zmicier.game2048.game.rules.movemaker;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;
import by.bsu.zmicier.meta.game.rules.movemaker.MoveMaker;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager is responsible for checking moves. It uses TileMerger for correct tile merge
 */
public abstract class Game2048MoveMaker<T> extends AbstractRule<FirstPlayerMove, SecondPlayerMove<T>, Position<T>, Game2048RulesMediator<T>> implements MoveMaker<FirstPlayerMove, SecondPlayerMove<T>, Position<T>> {

    public Game2048MoveMaker(Game2048RulesMediator<T> server) {
        super(server);
    }

    public abstract List<Tile<T>> getAvailableTiles(Position<T> position);
    public abstract List<Coords> getEmptyCells(Position<T> position);
}
