package by.bsu.zmicier.meta.game.player;


import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.gamemanager.impl.GameManagerFirstPlayerClient;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;

/**
 * Created on 19.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface Player<M, P extends MetaPosition<P>> {
    /**
     * choose a moveFirst for a certain position
     *
     * @param position is a current grid with all tiles
     * @param client   is an instance containing all rules of a game
     * @return chosen moveFirst
     */
    M move(P position, final GameManagerClient<M, ?, P> client);

    /**
     * initialized method which is called before each game
     */
    void init();
}
