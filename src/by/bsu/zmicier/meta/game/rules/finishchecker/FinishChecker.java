package by.bsu.zmicier.meta.game.rules.finishchecker;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager checkes if the game was finished
 */
public interface FinishChecker<P extends MetaPosition<P>> {
    /**
     * Checks if the game was finished
     * @param pos is a current position
     * @param next shows who will do next moveFirst
     * @return true if game was finished and false otherwise
     */
    boolean isFinish(P pos, PlayerMarker next);
}
