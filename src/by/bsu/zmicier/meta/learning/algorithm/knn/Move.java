package by.bsu.zmicier.meta.learning.algorithm.knn;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.meta.game.dto.MetaPosition;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class Move<M, P> {
    private P position;
    private M move;

    public Move() {
    }

    public Move(P position, M move) {
        this.position = position;
        this.move = move;
    }

    public P getPosition() {
        return position;
    }

    public void setPosition(P position) {
        this.position = position;
    }

    public M getMove() {
        return move;
    }

    public void setMove(M movie) {
        this.move = movie;
    }
}

