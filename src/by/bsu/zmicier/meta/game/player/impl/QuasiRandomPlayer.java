package by.bsu.zmicier.meta.game.player.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;

import java.util.List;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class QuasiRandomPlayer<M, P extends MetaPosition<P>> extends AbstractPlayer<M, P> {
    private int move = 0;

    @Override
    public void init() {
        move = 0;
    }

    @Override
    public M move(P position, final GameManagerClient<M, ?, P> client) {
        move++;
        List<M> moves = client.getMyMoves(position);
        return moves.get(Math.abs(move + 31 * position.hashCode()) % moves.size());
    }
}

