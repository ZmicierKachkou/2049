package by.bsu.zmicier.game2048.game.rules.base;

import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.impl.DefaultAvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.impl.DiveHardAvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.impl.DiveSimpleAvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.availabletilesmaster.impl.FibonacciAvailableTilesMaster;
import by.bsu.zmicier.game2048.game.rules.finishchecker.impl.DefaultFinishChecker;
import by.bsu.zmicier.game2048.game.rules.movemaker.impl.DefaultMoveMaker;
import by.bsu.zmicier.game2048.game.rules.pointscounter.impl.DefaultPointsCounter;
import by.bsu.zmicier.game2048.game.rules.positiongenerator.impl.DefaultPositionGenerator;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.impl.DefaultTilesMerger;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.impl.DiveTilesMerger;
import by.bsu.zmicier.game2048.game.rules.tilesmerger.impl.FibonacciTilesMerger;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class CoolGamesGenerator {
    public static Game2048RulesMediator<?> generateCoolGame(CoolGames coolGame) {

        switch(coolGame) {
            case ORIGINAL:
                Game2048RulesMediator<Integer> mediatorOriginal = new Game2048RulesMediator<Integer>();
                mediatorOriginal.setMoveMaker(new DefaultMoveMaker<Integer>(mediatorOriginal));
                mediatorOriginal.setAvailableTilesMaster(new DefaultAvailableTilesMaster(mediatorOriginal));
                mediatorOriginal.setTilesMerger(new DefaultTilesMerger(mediatorOriginal));
                mediatorOriginal.setPointsCounter(new DefaultPointsCounter(mediatorOriginal));
                mediatorOriginal.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediatorOriginal));
                mediatorOriginal.setFinishChecker(new DefaultFinishChecker<Integer>(mediatorOriginal));
                return mediatorOriginal;
            case FIBONACCI:
                Game2048RulesMediator<Integer> mediatorFibonacci = new Game2048RulesMediator<Integer>();
                mediatorFibonacci.setMoveMaker(new DefaultMoveMaker<Integer>(mediatorFibonacci));
                mediatorFibonacci.setAvailableTilesMaster(new FibonacciAvailableTilesMaster(mediatorFibonacci));
                mediatorFibonacci.setTilesMerger(new FibonacciTilesMerger(mediatorFibonacci));
                mediatorFibonacci.setPointsCounter(new DefaultPointsCounter(mediatorFibonacci));
                mediatorFibonacci.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediatorFibonacci));
                mediatorFibonacci.setFinishChecker(new DefaultFinishChecker<Integer>(mediatorFibonacci));
                return mediatorFibonacci;
            case EASY_DIVE:
                Game2048RulesMediator<Integer> mediatorDiveEasy = new Game2048RulesMediator<Integer>();
                mediatorDiveEasy.setMoveMaker(new DefaultMoveMaker<Integer>(mediatorDiveEasy));
                mediatorDiveEasy.setAvailableTilesMaster(new DiveSimpleAvailableTilesMaster(mediatorDiveEasy));
                mediatorDiveEasy.setTilesMerger(new DiveTilesMerger(mediatorDiveEasy));
                mediatorDiveEasy.setPointsCounter(new DefaultPointsCounter(mediatorDiveEasy));
                mediatorDiveEasy.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediatorDiveEasy));
                mediatorDiveEasy.setFinishChecker(new DefaultFinishChecker<Integer>(mediatorDiveEasy));
                return mediatorDiveEasy;
            case HARD_DIVE:
                Game2048RulesMediator<Integer> mediatorDiveHard = new Game2048RulesMediator<Integer>();
                mediatorDiveHard.setMoveMaker(new DefaultMoveMaker<Integer>(mediatorDiveHard));
                mediatorDiveHard.setAvailableTilesMaster(new DiveHardAvailableTilesMaster(mediatorDiveHard));
                mediatorDiveHard.setTilesMerger(new DiveTilesMerger(mediatorDiveHard));
                mediatorDiveHard.setPointsCounter(new DefaultPointsCounter(mediatorDiveHard));
                mediatorDiveHard.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediatorDiveHard));
                mediatorDiveHard.setFinishChecker(new DefaultFinishChecker<Integer>(mediatorDiveHard));
                return mediatorDiveHard;
        }

        return null;
    }
}
