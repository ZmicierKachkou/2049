package by.bsu.zmicier.gamexo.rules.finishchecker;

import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;
import by.bsu.zmicier.meta.game.rules.finishchecker.FinishChecker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager checkes if the game was finished
 */
public abstract class GameXOFinishChecker extends AbstractRule<Integer, Integer, Position, GameXORulesMediator> implements FinishChecker<Position> {

    public GameXOFinishChecker(GameXORulesMediator server) {
        super(server);
    }
}
