package by.bsu.zmicier.meta.game.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class GameResult<P extends MetaPosition<P>> {
    public static final String PARAM_LAST_FIRST_MOVE = "lastFirstMove";
    public static final String PARAM_LAST_SECOND_MOVE = "lastSecondMove";
    public static final String PARAM_LAST_MOVE_RESULT = "lastMoveResult";
    public static final String PARAM_GAME_OVER = "gameOver";

    private P position;
    private int moves;
    private long firstPoints;
    private long secondPoints;
    private Map<String, Object> params;

    public GameResult() {
        position = null;
        moves = 0;
        firstPoints = 0;
        secondPoints = 0;
        params = new HashMap<String, Object>();
        params.put(PARAM_GAME_OVER, false);
    }

    public GameResult(P position, int moves, long firstPoints, long secondPoints, Map<String, Object> params) {
        this.position = position;
        this.moves = moves;
        this.firstPoints = firstPoints;
        this.secondPoints = secondPoints;
        this.params = params;
    }

    public P getPosition() {
        return position;
    }

    public void setPosition(P position) {
        this.position = position;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void addMovie() {
        moves++;
    }

    public long getFirstPoints() {
        return firstPoints;
    }

    public void setFirstPoints(int firstPoints) {
        this.firstPoints = firstPoints;
    }

    public long getSecondPoints() {
        return secondPoints;
    }

    public void setSecondPoints(long secondPoints) {
        this.secondPoints = secondPoints;
    }

    public void addFirstPoints(long points) {
        this.firstPoints += points;
    }

    public void addSecondPoints(long points) {
        this.secondPoints += points;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
