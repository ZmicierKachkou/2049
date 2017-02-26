package by.bsu.zmicier.meta.learning.arena.events;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class ArenaEvent {
    private String playerId;
    private String opponentId;

    public ArenaEvent(String playerId, String opponentId) {
        this.playerId = playerId;
        this.opponentId = opponentId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getOpponentId() {
        return opponentId;
    }
}
