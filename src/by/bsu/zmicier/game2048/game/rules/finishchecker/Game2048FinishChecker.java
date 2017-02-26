package by.bsu.zmicier.game2048.game.rules.finishchecker;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;
import by.bsu.zmicier.meta.game.rules.finishchecker.FinishChecker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager checkes if the game was finished
 */
public abstract class Game2048FinishChecker<T> extends AbstractRule<FirstPlayerMove, SecondPlayerMove<T>, Position<T>, Game2048RulesMediator<T>> implements FinishChecker<Position<T>> {

    public Game2048FinishChecker(Game2048RulesMediator<T> server) {
        super(server);
    }
}
