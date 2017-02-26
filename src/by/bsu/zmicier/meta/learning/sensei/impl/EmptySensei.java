package by.bsu.zmicier.meta.learning.sensei.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.learning.sensei.AbstractSensei;
import by.bsu.zmicier.meta.game.player.Player;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class EmptySensei<M, P extends MetaPosition<P>> extends AbstractSensei<M, P> {
    private Player<M, P> student;

    public EmptySensei(Player<M, P> student) {
        this.student = student;
    }

    @Override
    public Player<M, P> getStudent() {
        return student;
    }

    @Override
    public Player<M, P> getMaster() {
        return student;
    }
}
