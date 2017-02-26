package by.bsu.zmicier.game2048.game.listeners;

import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.listeners.GameAction;
import by.bsu.zmicier.meta.game.listeners.GameListener;

/**
 * Created on 19.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class ConsoleGameListener<P extends MetaPosition<P>> implements GameListener<P> {
    @Override
    public void processAction(GameResult<P> result, GameAction gameAction) {
        if(gameAction == GameAction.END_GAME) {
            System.out.println("Points: " + result.getFirstPoints() + ":" + result.getSecondPoints());
            System.out.println("Moves: " + result.getMoves());
            System.out.println(result.getPosition());
            System.out.println("---------------");
        }
    }
}
