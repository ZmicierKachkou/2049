package by.bsu.zmicier.meta.learning.arena.events.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class LightMoveArenaEvent<M, P extends MetaPosition<P>> {
    private M move;
    private MoveResult<P> result;

    public LightMoveArenaEvent() {
    }

    public LightMoveArenaEvent(M move, MoveResult<P> result) {
        this.move = move;
        this.result = result;
    }

    public M getMove() {
        return move;
    }

    public void setMove(M move) {
        this.move = move;
    }

    public MoveResult<P> getResult() {
        return result;
    }

    public void setResult(MoveResult<P> result) {
        this.result = result;
    }
}
