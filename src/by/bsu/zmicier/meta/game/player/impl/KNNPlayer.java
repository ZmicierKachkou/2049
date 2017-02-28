package by.bsu.zmicier.meta.game.player.impl;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;
import by.bsu.zmicier.meta.learning.algorithm.knn.DataConverter;
import by.bsu.zmicier.meta.learning.algorithm.knn.Move;
import by.bsu.zmicier.meta.learning.estimation.DistanceFunction;

import java.util.*;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class KNNPlayer<M, P extends MetaPosition<P>, S> extends AbstractPlayer<M, P> {

    private List<Move<M, S>> moves = new LinkedList<Move<M, S>>();
    private DataConverter<S, P> converter;
    private DistanceFunction<S> distanceFunction;

    public KNNPlayer(List<Move<M, S>> moves) {
        this.moves = moves;
    }

    public KNNPlayer(List<Move<M, S>> moves, DataConverter<S, P> converter) {
        this.moves = moves;
        this.converter = converter;
    }

    public KNNPlayer(List<Move<M, S>> moves, DataConverter<S, P> converter, DistanceFunction<S> distanceFunction) {
        this.moves = moves;
        this.converter = converter;
        this.distanceFunction = distanceFunction;
    }

    public DistanceFunction<S> getDistanceFunction() {
        return distanceFunction;
    }

    public void setDistanceFunction(DistanceFunction<S> distanceFunction) {
        this.distanceFunction = distanceFunction;
    }

    private static class Pair<M> {
        private float estimate;
        private M move;

        private Pair(float estimate, M move) {
            this.estimate = estimate;
            this.move = move;
        }
    }

    @Override
    public M move(P position, GameManagerClient<M, ?, P> client) {
        ArrayList<Pair<M>> pairs = new ArrayList<Pair<M>>(moves.size());

        S pos = converter.convertData(position);

        System.out.println("Choose move");
        for(Move<M, S> move: moves) {
            if(client.makeMyMove(position, move.getMove()).isValid()) {
                float currDiff = distanceFunction.getDistance(pos, move.getPosition());
                System.out.println(move.getPosition() + " --> " + currDiff);
                pairs.add(new Pair<M>(currDiff, move.getMove()));
            }
        }

        Collections.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.estimate > o2.estimate) {
                    return -1;
                } else if (o1.estimate == o2.estimate) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        if(!pairs.isEmpty()){
            return pairs.get(0).move;
        } else {
            for(M move: client.getMyMoves(position)) {
                if(client.makeMyMove(position, move).isValid()) {
                    return move;
                }
            }
        }

        return null; //unreachable
    }
}
