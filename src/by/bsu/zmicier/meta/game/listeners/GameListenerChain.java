package by.bsu.zmicier.meta.game.listeners;

import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MetaPosition;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 19.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class GameListenerChain<P  extends MetaPosition<P>> {
    private List<GameListener<P>> listenerChain = new LinkedList<GameListener<P>>();

    public void setListenerChain(List<GameListener<P>> listenerChain) {
        this.listenerChain = listenerChain;
    }

    public void addListener(GameListener<P> gameListener) {
        listenerChain.add(gameListener);
    }

    public void removeListener(GameListener<P> gameListener) {
        listenerChain.remove(gameListener);
    }

    public void processAction(GameResult<P> result, GameAction gameAction) {
        for(GameListener<P> gl: listenerChain) {
            gl.processAction(result, gameAction);
        }
    }
}
