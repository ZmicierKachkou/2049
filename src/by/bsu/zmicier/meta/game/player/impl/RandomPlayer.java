package by.bsu.zmicier.meta.game.player.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;

import java.util.List;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class RandomPlayer<M, P extends MetaPosition<P>> extends AbstractPlayer<M, P> {

    @Override
    public M move(P position, final GameManagerClient<M, ?, P> client) {

        List<M> moves = client.getMyMoves(position);

        return moves.get((int)Math.floor(Math.random() * moves.size()));
    }
}
