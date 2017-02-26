package by.bsu.zmicier.game2048.game.listeners;

import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.listeners.GameAction;
import by.bsu.zmicier.meta.game.listeners.GameListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class StatisticsGameListener<P extends MetaPosition<P>> implements GameListener<P> {
    private List<GameResult<?>> results = new LinkedList<GameResult<?>>();

    @Override
    public void processAction(GameResult<P> result, GameAction gameAction) {
        if(gameAction == GameAction.END_GAME) {
            results.add(result);
        } else if(gameAction == GameAction.END_BLOCK) {
            showStatistics();
            results.clear();
        }
    }

    private void showStatistics() {
        Collections.sort(results, new Comparator<GameResult<?>>() {
            @Override
            public int compare(GameResult<?> o1, GameResult<?> o2) {
                return (int)(o2.getFirstPoints() - o1.getFirstPoints());
            }
        });

        float mean = 0;
        float size = (float) results.size();
        int wins = 0;
        for(GameResult<?> result: results) {
            mean += (result.getFirstPoints()) / size;
            if(result.getFirstPoints() > 20000) {
                wins++;
            }
        }

        System.out.println("Best: " + results.get(0).getFirstPoints() + " points, " + results.get(0).getMoves() + " moves");
        System.out.println("Wins: " + wins);
        System.out.println("Mean: " + mean);
        System.out.println("Medium: " + results.get(results.size() / 2).getFirstPoints() + " points");
    }
}
