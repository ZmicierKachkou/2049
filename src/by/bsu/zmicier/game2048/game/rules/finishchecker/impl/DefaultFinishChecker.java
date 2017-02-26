package by.bsu.zmicier.game2048.game.rules.finishchecker.impl;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.finishchecker.Game2048FinishChecker;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultFinishChecker<T> extends Game2048FinishChecker<T> {

    public DefaultFinishChecker(Game2048RulesMediator<T> server) {
        super(server);
    }

    @Override
    public boolean isFinish(Position<T> pos, PlayerMarker next) {
        if(next == PlayerMarker.FIRST) {
            return getRulesMediator().getMoveMaker().getCorrectFirstPlayerMoves(pos).size() == 0;
        }

        return false;
    }
}
