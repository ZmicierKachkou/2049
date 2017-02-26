package by.bsu.zmicier.demo;

import by.bsu.zmicier.game2048.game.listeners.ConsoleGameListener;
import by.bsu.zmicier.game2048.game.listeners.StatisticsGameListener;
import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.gamexo.rules.base.GameXORulesMediator;
import by.bsu.zmicier.gamexo.rules.finishchecker.impl.DefaultXOFinishChecker;
import by.bsu.zmicier.gamexo.rules.movemaker.impl.DefaultXOMoveMaker;
import by.bsu.zmicier.gamexo.rules.positiongenerator.impl.DefaultXOPositionGenerator;
import by.bsu.zmicier.gamexo.ui.GameXOPositionPainter;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;
import by.bsu.zmicier.meta.game.listeners.GameListenerChain;
import by.bsu.zmicier.meta.game.player.Player;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.game.player.impl.RandomPlayer;
import by.bsu.zmicier.meta.learning.arena.LearningArena;
import by.bsu.zmicier.meta.learning.arena.impl.LearningArenaImpl;
import by.bsu.zmicier.meta.learning.estimation.EstimationFunction;
import by.bsu.zmicier.meta.learning.sensei.Sensei;
import by.bsu.zmicier.meta.learning.sensei.impl.EmptySensei;
import by.bsu.zmicier.meta.ui.listeners.UIGameListener;

import java.util.LinkedList;

/**
 * Created on 25.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class DemoXO {
    public static void main(String[] args) {
        GameManagerServer<Integer, Integer, Position, GameXORulesMediator> server =
                new GameManagerServer<Integer, Integer, Position, GameXORulesMediator>();

        GameXORulesMediator mediator = new GameXORulesMediator();
        mediator.setFinishChecker(new DefaultXOFinishChecker(mediator));
        mediator.setMoveMaker(new DefaultXOMoveMaker(mediator));
        mediator.setPositionGenerator(new DefaultXOPositionGenerator(mediator));

        server.setMediator(mediator);

        Player<Integer, Position> first = new RandomPlayer<Integer, Position>();
        Player<Integer, Position> second = new RandomPlayer<Integer, Position>();

        server.setFirstPlayer(first);
        server.setSecondPlayer(second);
        System.out.println("!!! GAME !!!");
        server.getChain().addListener(new StatisticsGameListener<Position>());
        server.getChain().addListener(new ConsoleGameListener<Position>());
        server.getChain().addListener(new UIGameListener<Position>(new GameXOPositionPainter()));
        server.playGames(5);
    }
}
