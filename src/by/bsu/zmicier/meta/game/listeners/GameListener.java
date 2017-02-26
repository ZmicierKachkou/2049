package by.bsu.zmicier.meta.game.listeners;

import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MetaPosition;

/**
 * Created on 19.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface GameListener<P extends MetaPosition<P>> {
    /**
     * event handler
     * @param result is a current state of a game
     * @param gameAction is a marker of the action which has happened
     */
    void processAction(GameResult<P> result, GameAction gameAction);
}
