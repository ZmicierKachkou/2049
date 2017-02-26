package by.bsu.zmicier.meta.game.gamemanager;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;

import java.util.List;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface GameManagerClient<F, S, P extends MetaPosition<P>> {
    MoveResult<P> makeMyMove(P pos, F move);

    MoveResult<P> makeHisMove(P pos, S move);

    List<F> getMyMoves(P position);

    List<S> getHisMoves(P position);
}
