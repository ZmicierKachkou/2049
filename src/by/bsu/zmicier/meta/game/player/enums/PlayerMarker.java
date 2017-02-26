package by.bsu.zmicier.meta.game.player.enums;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public enum PlayerMarker {
    FIRST, SECOND;

    public PlayerMarker getAnother() {
        if(this == FIRST) {
            return SECOND;
        } else {
            return FIRST;
        }
    }
}
