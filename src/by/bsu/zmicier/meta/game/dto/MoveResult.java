package by.bsu.zmicier.meta.game.dto;

/**
 * Created on 06.01.2017.
 *
 * @author Źmicier Dzikański
 */
public class MoveResult<P extends MetaPosition<P>> {
    private P position;
    private long myPoints;
    private long hisPoints;
    private boolean isValid;

    public MoveResult() {
    }

    public MoveResult(P position, long myPoints, long hisPoints, boolean isValid) {
        this.position = position;
        this.myPoints = myPoints;
        this.hisPoints = hisPoints;
        this.isValid = isValid;
    }

    public P getPosition() {
        return position;
    }

    public void setPosition(P position) {
        this.position = position;
    }

    public long getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(long myPoints) {
        this.myPoints = myPoints;
    }

    public long getHisPoints() {
        return hisPoints;
    }

    public void setHisPoints(long hisPoints) {
        this.hisPoints = hisPoints;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
