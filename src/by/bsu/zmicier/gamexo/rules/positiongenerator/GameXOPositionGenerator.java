package by.bsu.zmicier.gamexo.rules.positiongenerator;

import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;
import by.bsu.zmicier.meta.game.rules.positiongenerator.PositionGenerator;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 * this manager is used to generate first position
 */
public abstract class GameXOPositionGenerator extends AbstractRule<Integer, Integer, Position, GameXORulesMediator> implements PositionGenerator<Position> {
    public GameXOPositionGenerator(GameXORulesMediator server) {
        super(server);
    }

}
