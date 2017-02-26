package by.bsu.zmicier.gamexo.rules.base;

import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.finishchecker.GameXOFinishChecker;
import by.bsu.zmicier.gamexo.rules.movemaker.GameXOMoveMaker;
import by.bsu.zmicier.gamexo.rules.positiongenerator.GameXOPositionGenerator;
import by.bsu.zmicier.meta.game.rules.RulesMediator;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public final class GameXORulesMediator extends RulesMediator<Integer, Integer, Position> {

    public GameXOMoveMaker getMoveMaker() {
        return (GameXOMoveMaker) moveMaker;
    }

    public void setMoveMaker(GameXOMoveMaker moveMaker) {
        this.moveMaker = moveMaker;
    }

    public GameXOFinishChecker getFinishChecker() {
        return (GameXOFinishChecker)finishChecker;
    }

    public void setFinishChecker(GameXOFinishChecker finishChecker) {
        this.finishChecker = finishChecker;
    }

    public GameXOPositionGenerator getPositionGenerator() {
        return (GameXOPositionGenerator)positionGenerator;
    }

    public void setPositionGenerator(GameXOPositionGenerator positionGenerator) {
        this.positionGenerator = positionGenerator;
    }
}
