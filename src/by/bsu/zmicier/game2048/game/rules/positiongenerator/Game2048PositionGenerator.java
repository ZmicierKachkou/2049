package by.bsu.zmicier.game2048.game.rules.positiongenerator;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;
import by.bsu.zmicier.meta.game.rules.positiongenerator.PositionGenerator;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 * this manager is used to generate first position
 */
public abstract class Game2048PositionGenerator<T> extends AbstractRule<FirstPlayerMove, SecondPlayerMove<T>, Position<T>, Game2048RulesMediator<T>> implements PositionGenerator<Position<T>> {
    public Game2048PositionGenerator(Game2048RulesMediator<T> server) {
        super(server);
    }

    /**
     * Generates first position
     * @param size is a size of position
     * @return new position
     */
    public abstract Position<T> generatePosition(int size);
}
