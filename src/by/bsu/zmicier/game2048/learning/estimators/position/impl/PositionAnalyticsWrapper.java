package by.bsu.zmicier.game2048.learning.estimators.position.impl;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.learning.estimators.position.PositionWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class PositionAnalyticsWrapper extends PositionWrapper<Integer> {

    private TreeMap<Tile<Integer>, List<Coords>> map;
    private int numOfTiles;
    private int chains[][];
    private float verticalPairs, horisontalPairs;
    private float weightedVerticalPairs, weightedHorisontalPairs;

    public PositionAnalyticsWrapper(Position<Integer> position) {
        super(position);

        numOfTiles = 0;
        verticalPairs = -1;
        horisontalPairs = -1;
        weightedHorisontalPairs = -1;
        weightedVerticalPairs = -1;

        map = new TreeMap<Tile<Integer>, List<Coords>>(new Comparator<Tile<Integer>>() {
            @Override
            public int compare(Tile<Integer> o1, Tile<Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        for(int x = 0; x < position.getSize(); x++) {
            for(int y = 0; y < position.getSize(); y++) {
                if(position.getTile(x,y) != null) {
                    numOfTiles++;
                    if(map.containsKey(position.getTile(x,y))) {
                        map.get(position.getTile(x,y)).add(new Coords(x,y));
                    }
                    else {
                        List<Coords> l = new ArrayList<Coords>();
                        l.add(new Coords(x,y));
                        map.put(position.getTile(x,y), l);
                    }
                }
            }
        }

        calculateLongestChains();
    }

    public TreeMap<Tile<Integer>, List<Coords>> getMap() {
        return map;
    }

    public int getNumOfTiles() {
        return numOfTiles;
    }

    public int[][] getChains() {
        return chains;
    }

    public float getVerticalPairs() {
        if(verticalPairs == -1) {
            verticalPairs = 0;
            Tile<Integer> lastTile = null;
            for (int y = 0; y < position.getSize(); y++) {
                for (int x = 0; x < position.getSize(); x++) {
                    if (position.getTile(x, y) != null) {
                        if (position.getTile(x, y).equals(lastTile)) {
                            verticalPairs++;
                            lastTile = null;
                        } else lastTile = position.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return verticalPairs;
    }

    public float getHorisontalPairs() {
        if(horisontalPairs == -1) {
            horisontalPairs = 0;
            Tile<Integer> lastTile = null;
            for (int x = 0; x < position.getSize(); x++) {
                for (int y = 0; y < position.getSize(); y++) {
                    if (position.getTile(x, y) != null) {
                        if (position.getTile(x, y).equals(lastTile)) {
                            horisontalPairs++;
                            lastTile = null;
                        } else {
                            lastTile = position.getTile(x, y);
                        }
                    }
                }
                lastTile = null;
            }
        }

        return horisontalPairs;
    }

    public float getWeightedVerticalPairs() {
        if(weightedVerticalPairs == -1) {
            weightedVerticalPairs = 0;
            Tile<Integer> lastTile = null;
            for (int y = 0; y < position.getSize(); y++) {
                for (int x = 0; x < position.getSize(); x++) {
                    if (position.getTile(x, y) != null) {
                        if (position.getTile(x, y).equals(lastTile)) {
                            weightedVerticalPairs += Math.log((Integer)position.getTile(x,y).getValue());
                            lastTile = null;
                        } else lastTile = position.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return weightedVerticalPairs;
    }

    public float getWeightedHorisontalPairs() {
        if(weightedHorisontalPairs == -1) {
            weightedHorisontalPairs = 0;
            Tile<Integer> lastTile = null;
            for (int x = 0; x < position.getSize(); x++) {
                for (int y = 0; y < position.getSize(); y++) {
                    if (position.getTile(x, y) != null) {
                        if (position.getTile(x, y).equals(lastTile)) {
                            weightedHorisontalPairs += Math.log((Integer)position.getTile(x,y).getValue());
                            lastTile = null;
                        } else lastTile = position.getTile(x, y);
                    }
                }
                lastTile = null;
            }
        }
        return weightedHorisontalPairs;
    }

    private void calculateLongestChains() {
        chains = new int[position.getSize()][position.getSize()];
        for(int x = 0; x < position.getSize(); x++) {
            for(int y = 0; y < position.getSize(); y++) {
                chains[x][y] = longestChainForTile(x, y, chains);
            }
        }
    }

    private int longestChainForTile(int x, int y, int[][] chains) {
        if(chains[x][y]!=0) return chains[x][y];
        if(position.getTile(x, y) == null) {
            return 0;
        }
        int chain = 0;

        if(x>0 && isCorrectСontinuation(position.getTile(x, y), position.getTile(x-1, y))) {
            int temp = longestChainForTile(x-1, y, chains);
            if(temp > chain) chain = temp;
        }
        if(x<position.getSize()-1 && isCorrectСontinuation(position.getTile(x, y), position.getTile(x+1, y))) {
            int temp = longestChainForTile(x+1, y, chains);
            if(temp > chain) chain = temp;
        }
        if(y>0 && isCorrectСontinuation(position.getTile(x, y), position.getTile(x, y-1))) {
            int temp = longestChainForTile(x, y-1, chains);
            if(temp > chain) chain = temp;
        }
        if(y<position.getSize()-1 && isCorrectСontinuation(position.getTile(x, y), position.getTile(x, y+1))) {
            int temp = longestChainForTile(x, y+1, chains);
            if(temp > chain) chain = temp;
        }
        chains[x][y]=chain;
        return chain;
    }

    private boolean isCorrectСontinuation(Tile<Integer> thisTile, Tile<Integer> thatTile) {
        if(thatTile == null || thisTile == null) {
            return false;

        }
        return thisTile.getValue() > thatTile.getValue();
    }
}
