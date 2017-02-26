package by.bsu.zmicier.meta.game.gamemanager.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;
import by.bsu.zmicier.meta.game.rules.RulesMediator;

import java.util.List;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class GameManagerSecondPlayerClient<F, S, P extends MetaPosition<P>> implements GameManagerClient<F, S, P> {
    private GameManagerServer<S, F, P, ? extends RulesMediator<S, F, P>> server;

    public GameManagerSecondPlayerClient(GameManagerServer<S, F, P, ? extends RulesMediator<S, F, P>> server) {
        this.server = server;
    }
    @Override
    public MoveResult<P> makeMyMove(P pos, F move) {
        return server.getMediator().getMoveMaker().moveSecond(pos, move);
    }

    @Override
    public MoveResult<P> makeHisMove(P pos, S move) {
        return server.getMediator().getMoveMaker().moveFirst(pos, move);
    }

    @Override
    public List<F> getMyMoves(P position) {
        return server.getMediator().getMoveMaker().getCorrectSecondPlayerMoves(position);
    }

    @Override
    public List<S> getHisMoves(P position) {
        return server.getMediator().getMoveMaker().getCorrectFirstPlayerMoves(position);
    }
}
