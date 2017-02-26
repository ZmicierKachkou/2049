package by.bsu.zmicier.meta.learning.algorithm;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.player.Player;
import by.bsu.zmicier.meta.game.player.impl.KNNPlayer;
import by.bsu.zmicier.meta.learning.algorithm.knn.DataConverter;
import by.bsu.zmicier.meta.learning.algorithm.knn.Move;
import by.bsu.zmicier.meta.learning.algorithm.knn.Role;
import by.bsu.zmicier.meta.learning.arena.events.impl.LightMoveArenaEvent;
import by.bsu.zmicier.meta.learning.arena.events.impl.PositionCreatedArenaEvent;
import by.bsu.zmicier.meta.learning.sensei.AbstractSensei;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class KNNSensei<M, P extends MetaPosition<P>, S> extends AbstractSensei<M, P> {
    private List<Move<M, S>> moves = new LinkedList<Move<M, S>>();
    private P lastPosition;

    private Player<M, P> teacher;
    private DataConverter<S, P> dataConverter;
    private Role role;

    public KNNSensei(Player<M, P> teacher, Role role) {
        this.teacher = teacher;
        this.role = role;
    }

    public DataConverter<S, P> getDataConverter() {
        return dataConverter;
    }

    public void setDataConverter(DataConverter<S, P> dataConverter) {
        this.dataConverter = dataConverter;
    }

    @Override
    public Player<M, P> getStudent() {
        return teacher;
    }

    @Override
    public KNNPlayer<M, P, S> getMaster() {
        return new KNNPlayer<M, P, S>(moves, dataConverter);
    }

    @Override
    public void processMyMoveEvent(LightMoveArenaEvent<M, P> event) {
        if(role == Role.PLAYER) {
            moves.add(new Move<M, S>(dataConverter.convertData(lastPosition), event.getMove()));
        }
    }

    @Override
    public void processOpponentsMoveEvent(LightMoveArenaEvent<?, P> event) {
        if(role == Role.PLAYER) {
            lastPosition = event.getResult().getPosition();
        }
    }

    @Override
    public void processPositionCreatedEvent(PositionCreatedArenaEvent<P> event) {
        lastPosition = event.getResult().getPosition();
    }
}
