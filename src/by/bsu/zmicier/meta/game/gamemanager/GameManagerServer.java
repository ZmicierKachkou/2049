package by.bsu.zmicier.meta.game.gamemanager;

import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.dto.MoveResult;
import by.bsu.zmicier.meta.game.gamemanager.impl.GameManagerFirstPlayerClient;
import by.bsu.zmicier.meta.game.gamemanager.impl.GameManagerSecondPlayerClient;
import by.bsu.zmicier.meta.game.listeners.GameAction;
import by.bsu.zmicier.meta.game.listeners.GameListenerChain;
import by.bsu.zmicier.meta.game.player.Player;
import by.bsu.zmicier.meta.game.player.enums.PlayerMarker;
import by.bsu.zmicier.meta.game.rules.RulesMediator;

/**
 * Created on 05.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class GameManagerServer<F, S, P extends MetaPosition<P>, M extends RulesMediator<F, S, P>> {
    private M mediator;

    private GameManagerFirstPlayerClient<F, S, P> firstClient = new GameManagerFirstPlayerClient<F, S, P>(this);
    private GameManagerSecondPlayerClient<S, F, P> secondClient = new GameManagerSecondPlayerClient<S, F, P>(this);
    private GameListenerChain<P> chain = new GameListenerChain<P>();

    private Player<F, P> firstPlayer;
    private Player<S, P> secondPlayer;

    public GameManagerFirstPlayerClient<F, S, P> getFirstClient() {
        return firstClient;
    }

    public GameManagerSecondPlayerClient<S, F, P> getSecondClient() {
        return secondClient;
    }

    public GameListenerChain<P> getChain() {
        return chain;
    }

    public M getMediator() {
        return mediator;
    }

    public void setMediator(M mediator) {
        this.mediator = mediator;
    }

    public Player<F, P> getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player<F, P> firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player<S, P> getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player<S, P> secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public GameResult<P> playGame() {
        getFirstPlayer().init();
        getSecondPlayer().init();
        GameResult<P> result = new GameResult<P>();
        getChain().processAction(result, GameAction.START_GAME);
        result.setPosition(getMediator().getPositionGenerator().generatePosition());
        getChain().processAction(result, GameAction.POSITION_CREATED);

        F movie1;
        S movie2;

        while(true) {
            result = move(result, PlayerMarker.FIRST);
            if((Boolean)result.getParams().get(GameResult.PARAM_GAME_OVER)) {
                break;
            }
            result.addMovie();
            getChain().processAction(result, GameAction.FIRST_PLAYER_MOVE);

            result = move(result, PlayerMarker.SECOND);
            if((Boolean)result.getParams().get(GameResult.PARAM_GAME_OVER)) {
                break;
            }
            getChain().processAction(result, GameAction.SECOND_PLAYER_MOVE);
        }
        getChain().processAction(result, GameAction.END_GAME);
        return result;
    }

    private GameResult<P> move(GameResult<P> currentResult, PlayerMarker next) {
        if(getMediator().getFinishChecker().isFinish(currentResult.getPosition(), next)) {
            currentResult.getParams().put(GameResult.PARAM_GAME_OVER, true);
            return currentResult;
        }
        MoveResult<P> moveResult;
        if(next == PlayerMarker.FIRST) {
            F movie = getFirstPlayer().move(currentResult.getPosition(), getFirstClient());
            moveResult = getMediator().getMoveMaker().moveFirst(currentResult.getPosition(), movie);
            currentResult.getParams().put(GameResult.PARAM_LAST_FIRST_MOVE, movie);
        } else {
            S movie = getSecondPlayer().move(currentResult.getPosition(), getSecondClient());
            moveResult = getMediator().getMoveMaker().moveSecond(currentResult.getPosition(), movie);
            currentResult.getParams().put(GameResult.PARAM_LAST_SECOND_MOVE, movie);
        }

        if(!moveResult.isValid()) {
            currentResult.getParams().put(GameResult.PARAM_GAME_OVER, true);
            System.out.println("Incorrect move!");
            return currentResult;
        }

        currentResult.addFirstPoints(next == PlayerMarker.FIRST ? moveResult.getMyPoints() : moveResult.getHisPoints());
        currentResult.addSecondPoints(next == PlayerMarker.SECOND ? moveResult.getMyPoints() : moveResult.getHisPoints());
        currentResult.setPosition(moveResult.getPosition());
        currentResult.getParams().put(GameResult.PARAM_LAST_MOVE_RESULT, moveResult);
        return currentResult;
    }

    public void playGames(int numberOfGames) {
        getChain().processAction(null, GameAction.START_BLOCK);
        for(int i = 0; i < numberOfGames; i++) {
            playGame();
        }
        getChain().processAction(null, GameAction.END_BLOCK);
    }
}
