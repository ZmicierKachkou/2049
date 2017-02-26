package by.bsu.zmicier.meta.learning.arena.events.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class MoveArenaEvent<M, P extends MetaPosition<P>> extends ArenaEvent {
    private M move;
    private MoveResult<P> result;

    private PlayerMarker marker;

    public MoveArenaEvent(String playerId, String opponentId, M move, MoveResult<P> result, PlayerMarker marker) {
        super(playerId, opponentId);
        this.move = move;
        this.result = result;
        this.marker = marker;
    }

    public M getMove() {
        return move;
    }

    public MoveResult<P> getResult() {
        return new MoveResult<P>(result.getPosition(), result.getMyPoints(), result.getHisPoints(), result.isValid());
    }

    public PlayerMarker getMarker() {
        return marker;
    }

    public LightMoveArenaEvent<M, P> getLightEvent() {
        return new LightMoveArenaEvent<M, P>(move, result);
    }
}
