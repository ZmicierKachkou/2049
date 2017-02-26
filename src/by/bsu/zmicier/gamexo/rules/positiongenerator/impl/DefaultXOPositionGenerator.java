package by.bsu.zmicier.gamexo.rules.positiongenerator.impl;

import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.gamexo.rules.positiongenerator.GameXOPositionGenerator;

import java.util.List;

/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultXOPositionGenerator extends GameXOPositionGenerator {

    public DefaultXOPositionGenerator(GameXORulesMediator server) {
        super(server);
    }

    @Override
    public Position generatePosition() {
        return new Position();
    }
}
