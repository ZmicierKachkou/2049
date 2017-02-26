package by.bsu.zmicier.meta.learning.sensei;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;
import by.bsu.zmicier.meta.game.player.Player;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface Sensei<M, P extends MetaPosition<P>> {
    Player<M, P> getStudent();
    Player<M, P> getMaster();

    String getId();
    void setId(String id);

    void processEvent(ArenaEvent event);

}