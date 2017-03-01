package by.bsu.zmicier.meta.learning.arena.events.impl;

import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;

/**
 * Created on 01.03.2017.
 *
 * @author Źmicier Dzikański
 */
public class LearningStateArenaEvent extends ArenaEvent {
    private State state;
    public enum State {
        START, FINISH;
    }

    public LearningStateArenaEvent(String playerId, String opponentId, State state) {
        super(playerId, opponentId);
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
