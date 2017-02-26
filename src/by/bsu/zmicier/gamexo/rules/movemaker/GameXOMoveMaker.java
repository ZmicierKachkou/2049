package by.bsu.zmicier.gamexo.rules.movemaker;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.meta.game.rules.AbstractRule;
import by.bsu.zmicier.meta.game.rules.movemaker.MoveMaker;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager is responsible for checking moves. It uses TileMerger for correct tile merge
 */
public abstract class GameXOMoveMaker extends AbstractRule<Integer, Integer, Position, GameXORulesMediator> implements MoveMaker<Integer, Integer, Position> {

    public GameXOMoveMaker(GameXORulesMediator server) {
        super(server);
    }

}
