package by.bsu.zmicier.game2048.game.rules.base;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.rules.finishchecker.Game2048FinishChecker;
import by.bsu.zmicier.game2048.game.rules.movemaker.Game2048MoveMaker;
import by.bsu.zmicier.game2048.game.rules.pointscounter.Game2048PointsCounter;
import by.bsu.zmicier.game2048.game.rules.positiongenerator.Game2048PositionGenerator;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.Game2048TilesMerger;
import by.bsu.zmicier.meta.game.rules.RulesMediator;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public final class Game2048RulesMediator<T> extends RulesMediator<FirstPlayerMove, SecondPlayerMove<T>, Position<T>> {
    private static final String TILES_MERGER = "tilesMerger";
    private static final String POINTS_COUNTER = "pointsCounter";

    public Game2048MoveMaker<T> getMoveMaker() {
        return (Game2048MoveMaker<T>) moveMaker;
    }

    public void setMoveMaker(Game2048MoveMaker<T> moveMaker) {
        this.moveMaker = moveMaker;
    }

    public Game2048TilesMerger<T> getTilesMerger() {
        return (Game2048TilesMerger<T>) additionalRules.get(TILES_MERGER);
    }

    public void setTilesMerger(Game2048TilesMerger<T> tilesMerger) {
        additionalRules.put(TILES_MERGER, tilesMerger);
    }

    public Game2048PointsCounter<T> getPointsCounter() {
        return (Game2048PointsCounter<T>) additionalRules.get(POINTS_COUNTER);
    }

    public void setPointsCounter(Game2048PointsCounter<T> pointsCounter) {
        additionalRules.put(POINTS_COUNTER, pointsCounter);
    }

    public Game2048FinishChecker<T> getFinishChecker() {
        return (Game2048FinishChecker<T>)finishChecker;
    }

    public void setFinishChecker(Game2048FinishChecker<T> finishChecker) {
        this.finishChecker = finishChecker;
    }

    public Game2048PositionGenerator<T> getPositionGenerator() {
        return (Game2048PositionGenerator<T>)positionGenerator;
    }

    public void setPositionGenerator(Game2048PositionGenerator<T> positionGenerator) {
        this.positionGenerator = positionGenerator;
    }
}
