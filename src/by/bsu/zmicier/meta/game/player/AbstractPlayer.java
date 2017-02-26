package by.bsu.zmicier.meta.game.player;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.gamemanager.impl.GameManagerFirstPlayerClient;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractPlayer<M, P  extends MetaPosition<P>> implements Player<M, P> {

    public void init() {

    }
}
