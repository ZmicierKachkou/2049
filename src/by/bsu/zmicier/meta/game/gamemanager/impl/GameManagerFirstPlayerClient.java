package by.bsu.zmicier.meta.game.gamemanager.impl;


import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;
import by.bsu.zmicier.meta.game.rules.RulesMediator;

import java.util.List;

/**
 * Created on 06.01.2017.
 *
 * @author Źmicier Dzikański
 */
public class GameManagerFirstPlayerClient<F, S, P extends MetaPosition<P>> implements GameManagerClient<F, S, P> {
    private GameManagerServer<F, S, P, ? extends RulesMediator<F, S, P>> server;

    public GameManagerFirstPlayerClient(GameManagerServer<F, S, P, ? extends RulesMediator<F, S, P>> server) {
        this.server = server;
    }

    @Override
    public MoveResult<P> makeMyMove(P pos, F move) {
        return server.getMediator().getMoveMaker().moveFirst(pos, move);
    }

    @Override
    public MoveResult<P> makeHisMove(P pos, S move) {
        return server.getMediator().getMoveMaker().moveSecond(pos, move);
    }

    @Override
    public List<F> getMyMoves(P position) {
        return server.getMediator().getMoveMaker().getCorrectFirstPlayerMoves(position);
    }

    @Override
    public List<S> getHisMoves(P position) {
        return server.getMediator().getMoveMaker().getCorrectSecondPlayerMoves(position);
    }
}
