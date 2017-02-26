package by.bsu.zmicier.meta.game.rules.positiongenerator;

import by.bsu.zmicier.meta.game.dto.MetaPosition;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 * this manager is used to generate first position
 */
public interface PositionGenerator<P extends MetaPosition<P>> {

    /**
     * Generates first position of default size
     * @return new position
     */
    P generatePosition();
}
