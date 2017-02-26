package by.bsu.zmicier.meta.learning.arena.events.impl;

import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class NewGameArenaEvent extends ArenaEvent {
    private final int gameNumber;

    public NewGameArenaEvent(String playerId, String opponentId, int gameNumber) {
        super(playerId, opponentId);
        this.gameNumber = gameNumber;
    }

    public int getGameNumber() {
        return gameNumber;
    }
}