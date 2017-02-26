package by.bsu.zmicier.gamexo.rules.movemaker.impl;

import by.bsu.zmicier.gamexo.dto.CellState;
import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.gamexo.rules.movemaker.GameXOMoveMaker;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;

import java.math.BigInteger;
import java.util.*;


/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultXOMoveMaker extends GameXOMoveMaker {

    public DefaultXOMoveMaker(GameXORulesMediator server) {
        super(server);
    }

    private boolean isCorrectMovie(Position position, Integer move) {

        return position.getCell(move) == CellState.EMPTY;
    }

    @Override
    public MoveResult<Position> moveFirst(Position position, Integer move) {
        return move(position, move, CellState.X);
    }

    @Override
    public MoveResult<Position> moveSecond(Position position, Integer move) {
       return move(position, move, CellState.O);
    }

    private MoveResult<Position> move(Position position, Integer move, CellState state) {
        boolean isValid = isCorrectMovie(position, move);
        Position newPosition = position.copy();

        if(isValid) {
            newPosition.setCell(move, state);
        }

        long points = 0;
        if(getRulesMediator().getFinishChecker().isFinish(newPosition, PlayerMarker.FIRST)) {
            points = 1;
        }

        return new MoveResult<Position>(newPosition, points, -points, isValid);
    }

    @Override
    public List<Integer> getCorrectFirstPlayerMoves(Position position) {
        return getCorrectMoves(position);
    }

    @Override
    public List<Integer> getCorrectSecondPlayerMoves(Position position) {
        return getCorrectMoves(position);
    }

    private List<Integer> getCorrectMoves(Position position) {
        List<Integer> moves = new LinkedList<Integer>();
        for(int i = 0; i < Position.SIZE; i++) {
            if(position.getCell(i) == CellState.EMPTY) {
                moves.add(i);
            }
        }
        return moves;
    }
}
