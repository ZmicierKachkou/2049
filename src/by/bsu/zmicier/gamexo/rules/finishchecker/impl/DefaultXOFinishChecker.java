package by.bsu.zmicier.gamexo.rules.finishchecker.impl;

import by.bsu.zmicier.gamexo.dto.CellState;
import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.gamexo.rules.finishchecker.GameXOFinishChecker;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultXOFinishChecker extends GameXOFinishChecker {
    private static final int[][] LINES = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8},{2,4,6}};

    public DefaultXOFinishChecker(GameXORulesMediator server) {
        super(server);
    }

    @Override
    public boolean isFinish(Position pos, PlayerMarker next) {
        if(getRulesMediator().getMoveMaker().getCorrectFirstPlayerMoves(pos).isEmpty()) {
            return true;
        }

        for(int[] line: LINES) {
            if(isFinishLine(pos.getCell(line[0]), pos.getCell(line[1]), pos.getCell(line[2]))) {
                return true;
            }
        }
        return false;
    }

    private boolean isFinishLine(CellState c1, CellState c2, CellState c3) {
        return c1 == c2 && c1 == c3 && c1 != CellState.EMPTY;
    }
}
