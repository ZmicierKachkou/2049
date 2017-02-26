package by.bsu.zmicier.meta.game.player.impl.estimators;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

import java.util.List;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class BasicEstimatorPlayer<M, P extends MetaPosition<P>> extends AbstractEstimatorPlayer<M, P> {

    public BasicEstimatorPlayer() {
    }

    public BasicEstimatorPlayer(EstimationFunction<P> estimationFunction) {
        super(estimationFunction);
    }

    @Override
    public M move(P position, GameManagerClient<M, ?, P> client) {
        List<M> moves = client.getMyMoves(position);

        float bestEstimate = Float.MIN_VALUE;
        M bestMove = null;
        for(M move: moves) {
            MoveResult<P> result = client.makeMyMove(position, move);
            if(result.isValid()) {
                float currEstimate = estimationFunction.estimatePosition(result.getPosition());
                if (bestMove == null || currEstimate > bestEstimate) {
                    bestEstimate = currEstimate;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }
}
