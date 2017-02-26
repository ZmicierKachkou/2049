package by.bsu.zmicier.meta.learning.arena.events.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;
import by.bsu.zmicier.meta.game.dto.GameResult;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class PositionCreatedArenaEvent<P extends MetaPosition<P>> extends ArenaEvent {
    private GameResult<P> result;

    public PositionCreatedArenaEvent(String playerId, String opponentId, GameResult<P> result) {
        super(playerId, opponentId);
        this.result = result;
    }

    public GameResult<P> getResult() {
        return new GameResult<P>(result.getPosition().copy(), result.getMoves(), result.getFirstPoints(), result.getSecondPoints(), result.getParams());
    }
}
