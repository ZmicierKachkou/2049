package by.bsu.zmicier.meta.learning.arena.listeners;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;
import by.bsu.zmicier.meta.learning.arena.events.impl.*;
import by.bsu.zmicier.meta.learning.sensei.Sensei;
import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.listeners.GameAction;
import by.bsu.zmicier.meta.game.listeners.GameListener;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class ArenaListener<F, S, P extends MetaPosition<P>> implements GameListener<P> {
    private String firstSenseiId = null;
    private String secondSenseiId = null;
    private List<Sensei<?, ?>> senseis = new LinkedList<Sensei<?, ?>>();
    private int gameCounter = 0;

    public void addSensei(Sensei<?,?> sensei) {
        sensei.setId(UUID.randomUUID().toString());
        senseis.add(sensei);
    }

    public void addFirstSensei(Sensei<F,P> sensei) {
        addSensei(sensei);
        firstSenseiId = sensei.getId();
    }

    public void addSecondSensei(Sensei<S,P> sensei) {
        addSensei(sensei);
        secondSenseiId = sensei.getId();
    }

    @Override
    public void processAction(GameResult<P> result, GameAction gameAction) {
        switch(gameAction) {
            case START_GAME:
                notifySenseis(new NewGameArenaEvent(firstSenseiId, secondSenseiId, gameCounter));
                gameCounter++;
                break;
            case POSITION_CREATED:
                notifySenseis(new PositionCreatedArenaEvent<P>(firstSenseiId, secondSenseiId, result));
                break;
            case FIRST_PLAYER_MOVE:
                notifySenseis(new MoveArenaEvent<F, P>(firstSenseiId, secondSenseiId, (F)result.getParams().get(GameResult.PARAM_LAST_FIRST_MOVE),
                        (MoveResult<P>)result.getParams().get(GameResult.PARAM_LAST_MOVE_RESULT), PlayerMarker.FIRST));
                break;
            case SECOND_PLAYER_MOVE:
                notifySenseis(new MoveArenaEvent<S, P>(secondSenseiId, firstSenseiId, (S)result.getParams().get(GameResult.PARAM_LAST_SECOND_MOVE),
                        (MoveResult<P>)result.getParams().get(GameResult.PARAM_LAST_MOVE_RESULT), PlayerMarker.SECOND));
                break;
            case END_GAME:
                notifySenseis(new EndGameArenaEvent<P>(firstSenseiId, secondSenseiId, result));
                break;
            case START_BLOCK:
                notifySenseis(new LearningStateArenaEvent(firstSenseiId, secondSenseiId, LearningStateArenaEvent.State.START));
                break;
            case END_BLOCK:
                notifySenseis(new LearningStateArenaEvent(firstSenseiId, secondSenseiId, LearningStateArenaEvent.State.FINISH));
                break;
        }
    }

    private void notifySenseis(ArenaEvent event) {
        for(Sensei<?,?> sensei: senseis) {
            sensei.processEvent(event);
        }
    }
}
