package by.bsu.zmicier.demo;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.listeners.ConsoleGameListener;
import by.bsu.zmicier.game2048.game.listeners.StatisticsGameListener;
import by.bsu.zmicier.game2048.game.players.firstplayer.CornerPlayer;
import by.bsu.zmicier.game2048.game.players.firstplayer.UIHumanPlayer;
import by.bsu.zmicier.game2048.game.players.secondplayer.WeightedRandomPlayer;
import by.bsu.zmicier.game2048.game.rules.base.CoolGames;
import by.bsu.zmicier.game2048.game.rules.base.CoolGamesGenerator;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.learning.estimators.distance.BasicDistanceFunction;
import by.bsu.zmicier.game2048.learning.estimators.estimation.*;
import by.bsu.zmicier.game2048.learning.estimators.position.factory.impl.PositionAnalyticsWrapperFactory;
import by.bsu.zmicier.game2048.ui.Game2048PositionPainter;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;
import by.bsu.zmicier.meta.game.listeners.GameListenerChain;
import by.bsu.zmicier.meta.game.player.Player;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.game.player.impl.HumanPlayer;
import by.bsu.zmicier.meta.game.player.impl.KNNPlayer;
import by.bsu.zmicier.meta.game.player.impl.QuasiRandomPlayer;
import by.bsu.zmicier.meta.game.player.impl.RandomPlayer;
import by.bsu.zmicier.meta.game.player.impl.estimators.BasicEstimatorPlayer;
import by.bsu.zmicier.meta.game.player.impl.estimators.MiniMaxPlayer;
import by.bsu.zmicier.meta.learning.algorithm.GeneticSensei;
import by.bsu.zmicier.meta.learning.algorithm.KNNSensei;
import by.bsu.zmicier.meta.learning.algorithm.knn.DataConverter;
import by.bsu.zmicier.meta.learning.algorithm.knn.Move;
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

        server.setMediator((Game2048RulesMediator<Integer>)CoolGamesGenerator.generateCoolGame(CoolGames.ORIINAL));

        Player<FirstPlayerMove, Position<Integer>> first = new RandomPlayer<FirstPlayerMove, Position<Integer>>();
        Player<SecondPlayerMove<Integer>, Position<Integer>> second = new QuasiRandomPlayer<SecondPlayerMove<Integer>, Position<Integer>>();

        LearningArena<FirstPlayerMove, SecondPlayerMove<Integer>, Position<Integer>> arena =
                new LearningArenaImpl<FirstPlayerMove, SecondPlayerMove<Integer>, Position<Integer>>();
        arena.setServer(server);
        arena.setGames(100);

        Sensei<FirstPlayerMove, Position<Integer>> firstSensei = new GeneticSensei<FirstPlayerMove, Position<Integer>>(
                new BasicEstimatorPlayer<FirstPlayerMove, Position<Integer>>(),
                new MiniMaxPlayer<FirstPlayerMove, Position<Integer>>(4),
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
                }}
        );


        KNNSensei<FirstPlayerMove, Position<Integer>, int[]> firstSensei2 = new KNNSensei<FirstPlayerMove, Position<Integer>, int[]>(new CornerPlayer<Integer>(), Role.PLAYER);
        firstSensei2.setDataConverter(new DataConverter<int[], Position<Integer>>() {
            @Override
            public int[] convertData(Position<Integer> data) {
                int[] result = new int[data.getSize() * data.getSize()];

                for(int x = 0; x < data.getSize(); x++) {
                    for(int y = 0; y < data.getSize(); y++) {
                        if(data.getTile(x, y) == null) {
                            result[x*data.getSize() + y] = 0;
                        } else {
                            result[x * data.getSize() + y] = data.getTile(x, y).getValue();
                        }
                    }
                }
                return result;
            }
        });

        Sensei<SecondPlayerMove<Integer>, Position<Integer>> secondSensei = new EmptySensei<SecondPlayerMove<Integer>, Position<Integer>>(new WeightedRandomPlayer<Integer>(new double[]{0.9, 0.1}));

        arena.setFirstSensei(firstSensei2);
        arena.setSecondSensei(secondSensei);

        arena.startLearning();

        KNNPlayer<FirstPlayerMove, Position<Integer>, int[]> master = firstSensei2.getMaster();
        master.setDistanceFunction(new BasicDistanceFunction());
        server.setFirstPlayer(master);
        server.setSecondPlayer(new WeightedRandomPlayer<Integer>(new double[]{0.9, 0.1}));



        System.out.println("!!! GAME !!!");
        server.getChain().addListener(new StatisticsGameListener<Position<Integer>>());
        server.getChain().addListener(new ConsoleGameListener<Position<Integer>>());
        server.getChain().addListener(new UIGameListener<Position<Integer>>(new Game2048PositionPainter<Integer>()));
        server.playGames(5);
    }
}
