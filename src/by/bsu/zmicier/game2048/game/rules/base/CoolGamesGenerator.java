package by.bsu.zmicier.game2048.game.rules.base;

import by.bsu.zmicier.game2048.game.rules.finishchecker.impl.DefaultFinishChecker;
import by.bsu.zmicier.game2048.game.rules.movemaker.impl.DefaultMoveMaker;
import by.bsu.zmicier.game2048.game.rules.movemaker.impl.FibonacciMoveMaker;
import by.bsu.zmicier.game2048.game.rules.pointscounter.impl.DefaultPointsCounter;
import by.bsu.zmicier.game2048.game.rules.positiongenerator.impl.DefaultPositionGenerator;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.impl.DefaultTilesMerger;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.impl.FibonacciTilesMerger;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class CoolGamesGenerator {
    public static Game2048RulesMediator<?> generateCoolGame(CoolGames coolGame) {

        switch(coolGame) {
            case ORIINAL:
                Game2048RulesMediator<Integer> mediatorOriginal = new Game2048RulesMediator<Integer>();
                mediatorOriginal.setMoveMaker(new DefaultMoveMaker(mediatorOriginal));
                mediatorOriginal.setTilesMerger(new DefaultTilesMerger(mediatorOriginal));
                mediatorOriginal.setPointsCounter(new DefaultPointsCounter(mediatorOriginal));
                mediatorOriginal.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediatorOriginal));
                mediatorOriginal.setFinishChecker(new DefaultFinishChecker<Integer>(mediatorOriginal));
                return mediatorOriginal;
            case FIBONACCI:
                Game2048RulesMediator<Integer> mediatorFibonacci = new Game2048RulesMediator<Integer>();
                mediatorFibonacci.setMoveMaker(new FibonacciMoveMaker(mediatorFibonacci));
                mediatorFibonacci.setTilesMerger(new FibonacciTilesMerger(mediatorFibonacci));
                mediatorFibonacci.setPointsCounter(new DefaultPointsCounter(mediatorFibonacci));
                mediatorFibonacci.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediatorFibonacci));
                mediatorFibonacci.setFinishChecker(new DefaultFinishChecker<Integer>(mediatorFibonacci));
                return mediatorFibonacci;
        }

        return null;
    }
}
