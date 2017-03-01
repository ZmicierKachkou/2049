package by.bsu.zmicier.meta.learning.sensei;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.learning.arena.events.ArenaEvent;
import by.bsu.zmicier.meta.learning.arena.events.impl.*;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractSensei<M, P extends MetaPosition<P>> implements Sensei<M, P> {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void processEvent(ArenaEvent event) {
        if(event instanceof NewGameArenaEvent) {
            processNewGameEvent((NewGameArenaEvent)event);
        } else if(event instanceof PositionCreatedArenaEvent) {
            processPositionCreatedEvent((PositionCreatedArenaEvent<P>)event);
        } else if(event instanceof MoveArenaEvent) {
            processMoveEvent((MoveArenaEvent<?, P>)event);
        } else if(event instanceof EndGameArenaEvent) {
            processEndGameEvent((EndGameArenaEvent<P>)event);
        } else if(event instanceof LearningStateArenaEvent) {
            proccessLearningStateEvent((LearningStateArenaEvent)event);
        }
    }

    public void processNewGameEvent(NewGameArenaEvent event) {  }

    public void processPositionCreatedEvent(PositionCreatedArenaEvent<P> event) {  }

    public void processMoveEvent(MoveArenaEvent<?, P> event) {
        if(event.getPlayerId().equals(id)) {
            processMyMoveEvent((LightMoveArenaEvent<M, P>)event.getLightEvent());
        } else if(event.getOpponentId().equals(id)) {
            processOpponentsMoveEvent(event.getLightEvent());
        } else if(event.getMarker() == PlayerMarker.FIRST) {
            observeFirstMove(event.getLightEvent());
        } else if(event.getMarker() == PlayerMarker.SECOND) {
            observeSecondMove(event.getLightEvent());
        }
    }

    public void observeSecondMove(LightMoveArenaEvent<?, P> event) {   }

    public void observeFirstMove(LightMoveArenaEvent<?, P> event) {  }

    public void processOpponentsMoveEvent(LightMoveArenaEvent<?, P> event) {  }

    public void processMyMoveEvent(LightMoveArenaEvent<M, P> event) {  }

    public void processEndGameEvent(EndGameArenaEvent<P> event) {  }

    public void proccessLearningStateEvent(LearningStateArenaEvent event) {
        if(event.getState() == LearningStateArenaEvent.State.START) {
            processStartLearningEvent(event);
        } else if(event.getState() == LearningStateArenaEvent.State.FINISH) {
            processFinishLearningEvent(event);
        }
    }

    public void processStartLearningEvent(LearningStateArenaEvent event) {  }

    public void processFinishLearningEvent(LearningStateArenaEvent event) {  }

}
