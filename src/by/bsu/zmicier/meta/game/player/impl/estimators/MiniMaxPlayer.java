package by.bsu.zmicier.meta.game.player.impl.estimators;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class MiniMaxPlayer <M, P extends MetaPosition<P>> extends AbstractEstimatorPlayer<M, P> {

    private int depth;

    public MiniMaxPlayer(int depth) {
        this.depth = depth;
    }

    public MiniMaxPlayer(EstimationFunction<P> estimationFunction, int depth) {
        super(estimationFunction);
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public M move(P position, GameManagerClient<M, ?, P> client) {
        float bestEstimation = Float.MIN_VALUE;
        M bestMove = null;
        for(M move: client.getMyMoves(position)) {
            float currentEstimation = estimateHisMove(client.makeMyMove(position, move).getPosition(), depth-1, client);
            if(currentEstimation >= bestEstimation) {
                bestEstimation = currentEstimation;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private float estimateMyMove(P position, int depth, GameManagerClient<M, ?, P> client) {
        if(depth <= 0) {
            return estimationFunction.estimatePosition(position);
        } else {
            float bestEstimation = Float.MIN_VALUE;
            for(M move: client.getMyMoves(position)) {
                float currentEstimation = estimateHisMove(client.makeMyMove(position, move).getPosition(), depth - 1, client);
                if(currentEstimation >= bestEstimation) {
                    bestEstimation = currentEstimation;
                }
            }
            return bestEstimation;
        }
    }

    private <S>float estimateHisMove(P position, int depth, GameManagerClient<M, S, P> client) {
        if(depth <= 0) {
            return estimationFunction.estimatePosition(position);
        } else {
            float bestEstimation = Float.MAX_VALUE;
            for(S move: client.getHisMoves(position)) {
                float currentEstimation = estimateMyMove(client.makeHisMove(position, move).getPosition(), depth-1, client);
                if(currentEstimation <= bestEstimation) {
                    bestEstimation = currentEstimation;
                }
            }
            return bestEstimation;
        }
    }

}
