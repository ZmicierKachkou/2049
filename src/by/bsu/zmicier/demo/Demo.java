package by.bsu.zmicier.demo;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.listeners.ConsoleGameListener;
import by.bsu.zmicier.game2048.game.listeners.StatisticsGameListener;
import by.bsu.zmicier.game2048.game.players.firstplayer.CornerPlayer;
import by.bsu.zmicier.game2048.game.players.firstplayer.UIHumanPlayer;
import by.bsu.zmicier.game2048.game.players.secondplayer.WeightedRandomPlayer;
import by.bsu.zmicier.game2048.game.rules.base.CoolGames;
import by.bsu.zmicier.game2048.game.rules.base.CoolGamesGenerator;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.positiongenerator.Game2048PositionGenerator;
import by.bsu.zmicier.game2048.game.rules.positiongenerator.impl.DefaultPositionGenerator;
import by.bsu.zmicier.game2048.learning.converter.PositionDataConverter;
import by.bsu.zmicier.game2048.learning.estimators.distance.BasicDistanceFunction;
import by.bsu.zmicier.game2048.learning.estimators.estimation.*;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;
import by.bsu.zmicier.game2048.ui.Game2048PositionPainter;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;
import by.bsu.zmicier.meta.game.player.Player;
import by.bsu.zmicier.meta.game.player.impl.KNNPlayer;
import by.bsu.zmicier.meta.game.player.impl.QuasiRandomPlayer;
import by.bsu.zmicier.meta.game.player.impl.RandomPlayer;
import by.bsu.zmicier.meta.game.player.impl.estimators.AbstractEstimatorPlayer;
import by.bsu.zmicier.meta.game.player.impl.estimators.BasicEstimatorPlayer;
import by.bsu.zmicier.meta.game.player.impl.estimators.MiniMaxPlayer;
import by.bsu.zmicier.meta.game.rules.RulesMediator;
import by.bsu.zmicier.meta.learning.algorithm.AnnealingSensei;
import by.bsu.zmicier.meta.learning.algorithm.GeneticSensei;
import by.bsu.zmicier.meta.learning.algorithm.KNNSensei;
import by.bsu.zmicier.meta.learning.algorithm.annealing.impl.LinearTemperatureController;
import by.bsu.zmicier.meta.learning.algorithm.knn.DataConverter;
import by.bsu.zmicier.meta.learning.algorithm.knn.Role;
import by.bsu.zmicier.meta.learning.arena.LearningArena;
import by.bsu.zmicier.meta.learning.arena.impl.LearningArenaImpl;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;
import by.bsu.zmicier.meta.learning.sensei.Sensei;
import by.bsu.zmicier.meta.learning.sensei.impl.EmptySensei;
import by.bsu.zmicier.meta.ui.listeners.UIGameListener;

import java.util.LinkedList;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class Demo {
    public static void main(String[] args) {
        GameManagerServer<FirstPlayerMove, SecondPlayerMove<Integer>, Position<Integer>, Game2048RulesMediator<Integer>> server =
                new GameManagerServer<FirstPlayerMove, SecondPlayerMove<Integer>, Position<Integer>, Game2048RulesMediator<Integer>>();

        Game2048RulesMediator<Integer> mediator = (Game2048RulesMediator<Integer>)CoolGamesGenerator.generateCoolGame(CoolGames.ORIGINAL);

        mediator.setPositionGenerator(new Game2048PositionGenerator<Integer>(mediator) {
            @Override
            public Position<Integer> generatePosition(int size) {
                return null;
            }

            @Override
            public Position<Integer> generatePosition() {
                Position<Integer> p = new Position<Integer>();
                p.setTile(1,2, new Tile<Integer>(2));
                p.setTile(3,3, new Tile<Integer>(4));
                return p;
            }
        });
        server.setMediator(mediator);

        LearningArena<FirstPlayerMove, SecondPlayerMove<Integer>, Position<Integer>> arena =
                new LearningArenaImpl<FirstPlayerMove, SecondPlayerMove<Integer>, Position<Integer>>();
        arena.setServer(server);
        arena.setGames(2000);

        Sensei<FirstPlayerMove, Position<Integer>> firstSensei = new AnnealingSensei<FirstPlayerMove, Position<Integer>>(
                new LinkedList<EstimationFunction<Position<Integer>>>() {{
                    PositionAnalyticsWrapperFactory factory = new PositionAnalyticsWrapperFactory();
                    add(new BothDirectionPairsEstimationFunction(factory));
                    add(new ColumnPicksEstimationFunction());
                    add(new CornerMaxTileEstimationFunction(factory));
                    add(new DifferentTilesEstimationFunction(factory));
                    add(new EmptyCellsEstimationFunction(factory));
                    add(new GoodColumnsEstimationFunction());
                    add(new GoodRowsEstimationFunction());
                    add(new HorisontalPairsEstimationFunction(factory));
                    add(new LargeTilesEstimationFunction(factory));
                    add(new LongestChainEstimationFunction(factory));
                    add(new LongestChainFromMaxTileEstimationFunction(factory));
                    add(new MaxFragmentsEstimationFunction(factory));
                    add(new MaxPairsEstimationFunction(factory));
                    add(new MaxTileEstimationFunction(factory));
                    add(new NotCornerMaxTileEstimationFunction(factory));
                    add(new RowPicksEstimationFunction());
                    add(new VerticalPairsEstimationFunction(factory));
                    add(new WeightedBothDirectionPairsEstimationFunction(factory));
                    add(new WeightedHorisontalPairsEstimationFunction(factory));
                    add(new WeightedMaxPairsEstimationFunction(factory));
                    add(new WeightedVerticalPairsEstimationFunction(factory));
                }},
                new LinearTemperatureController(1000., 0., 0.001),
                new BasicEstimatorPlayer<FirstPlayerMove, Position<Integer>>(),
                new MiniMaxPlayer<FirstPlayerMove, Position<Integer>>(5)
        );


        KNNSensei<FirstPlayerMove, Position<Integer>, int[]> firstSensei2 = new KNNSensei<FirstPlayerMove, Position<Integer>, int[]>(new
                UIHumanPlayer<Integer>(new Game2048PositionPainter<Integer>()), Role.PLAYER);
        firstSensei2.setDataConverter(new PositionDataConverter());

        Sensei<SecondPlayerMove<Integer>, Position<Integer>> secondSensei = new EmptySensei<SecondPlayerMove<Integer>, Position<Integer>>(new
                QuasiRandomPlayer<SecondPlayerMove<Integer>, Position<Integer>>(GeneticSensei.GAMES_FOR_FITNESS_COUNTING));

        arena.setFirstSensei(firstSensei);
        arena.setSecondSensei(secondSensei);

        arena.startLearning();

        //KNNPlayer<FirstPlayerMove, Position<Integer>, int[]> master = firstSensei2.getMaster();
        //master.setDistanceFunction(new BasicDistanceFunction());
        mediator.setPositionGenerator(new DefaultPositionGenerator<Integer>(mediator));
        server.setFirstPlayer(new BasicEstimatorPlayer<FirstPlayerMove, Position<Integer>>(((AbstractEstimatorPlayer<FirstPlayerMove, Position<Integer>>)
                firstSensei.getMaster()).getEstimationFunction()));
        server.setSecondPlayer(new RandomPlayer<SecondPlayerMove<Integer>, Position<Integer>>());

        System.out.println("!!! GAME !!!");
        server.getChain().addListener(new StatisticsGameListener<Position<Integer>>());
        server.getChain().addListener(new ConsoleGameListener<Position<Integer>>());
        //server.getChain().addListener(new UIGameListener<Position<Integer>>(new Game2048PositionPainter<Integer>()));
        server.playGames(10);
    }
}
