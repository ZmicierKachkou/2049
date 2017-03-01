package by.bsu.zmicier.meta.learning.arena.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.listeners.GameAction;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.learning.arena.LearningArena;
import by.bsu.zmicier.meta.learning.arena.listeners.ArenaListener;
import by.bsu.zmicier.meta.learning.sensei.Sensei;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class LearningArenaImpl<F, S, P extends MetaPosition<P>> implements LearningArena<F, S, P> {
    private GameManagerServer<F, S, P, ?> server;
    private int games;
    private Sensei<F, P> firstSensei;
    private Sensei<S, P> secondSensei;

    @Override
    public void setServer(GameManagerServer<F, S, P, ?> server) {
        this.server = server;
    }

    @Override
    public void setGames(int games) {
        this.games = games;
    }

    @Override
    public void setFirstSensei(Sensei<F, P> sensei) {
        this.firstSensei = sensei;
    }

    @Override
    public void setSecondSensei(Sensei<S, P> sensei) {
        this.secondSensei = sensei;
    }

    @Override
    public void startLearning() {
        ArenaListener<F, S, P> listener = new ArenaListener<F, S, P>();
        listener.addFirstSensei(firstSensei);
        listener.addSecondSensei(secondSensei);
        server.getChain().addListener(listener);

        server.getChain().processAction(null, GameAction.START_BLOCK);
        for(int i = 0; i < games; i++) {

            server.setFirstPlayer(firstSensei.getStudent());
            server.setSecondPlayer(secondSensei.getStudent());

            server.playGame();
        }
        server.getChain().processAction(null, GameAction.END_BLOCK);

        server.getChain().removeListener(listener);
    }
}
