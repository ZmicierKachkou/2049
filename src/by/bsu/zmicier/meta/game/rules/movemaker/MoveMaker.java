package by.bsu.zmicier.meta.game.rules.movemaker;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager is responsible for checking moves. It uses TileMerger for correct tile merge
 */
public interface MoveMaker<F, S, P extends MetaPosition<P>> {

    MoveResult<P> moveFirst(P position, F movie);
    MoveResult<P> moveSecond(P position, S movie);

    List<F> getCorrectFirstPlayerMoves(P position);
    List<S> getCorrectSecondPlayerMoves(P position);
}
