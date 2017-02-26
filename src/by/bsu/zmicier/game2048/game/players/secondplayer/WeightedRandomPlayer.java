package by.bsu.zmicier.game2048.game.players.secondplayer;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;

import java.util.*;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class WeightedRandomPlayer<T> extends AbstractPlayer<SecondPlayerMove<T>, Position<T>> {

    private final double[] defaultProbabilities;

    public WeightedRandomPlayer(double[] defaultProbabilities) {
        this.defaultProbabilities = defaultProbabilities;
    }

    @Override
    public SecondPlayerMove<T> move(Position<T> position, GameManagerClient<SecondPlayerMove<T>, ?, Position<T>> client) {
        Map<Coords, Set<Tile<T>>> emptyCoords = new HashMap<Coords, Set<Tile<T>>>();

        for(SecondPlayerMove<T> move: client.getMyMoves(position)) {
            Coords coords = new Coords(move.getX(), move.getY());

            if(emptyCoords.get(coords) == null) {
                Set<Tile<T>> possibleTiles;
                if(move.getTile().getValue() instanceof Comparable) {
                    possibleTiles = new TreeSet<Tile<T>>(new Comparator<Tile<T>>() {
                        @Override
                        public int compare(Tile<T> o1, Tile<T> o2) {
                            return ((Comparable)o1.getValue()).compareTo(o2.getValue());
                        }
                    });
                } else {
                    possibleTiles = new HashSet<Tile<T>>();
                }
                possibleTiles.add(move.getTile());
                emptyCoords.put(coords, possibleTiles);
            } else {
                emptyCoords.get(coords).add(move.getTile());
            }
        }
        Coords pickedCoords = getRandomElement(emptyCoords.keySet());

        Set<Tile<T>> tiles = emptyCoords.get(pickedCoords);
        double[] probabilities = prepareProbabilities(tiles.size());
        return new SecondPlayerMove<T>(pickedCoords, getWeigthedRandomElement(tiles, probabilities));
    }

    private double[] prepareProbabilities(int size) {
        double[] probs = Arrays.copyOf(defaultProbabilities, size);

        double sum = 0;

        for(int i = 0; i < size; i++) {
            sum += probs[i];
        }

        for(int i = 0; i < size; i++) {
            probs[i] /= sum;
        }

        return probs;
    }

    private <E>E getRandomElement(Set<E> set) {
        int item = new Random().nextInt(set.size());
        int i = 0;
        for(E elem : set) {
            if (i == item)
                return elem;
            i++;
        }
        return null;
    }

    private <E>E getWeigthedRandomElement(Set<E> set, double[] coeffs) {
        double rand = Math.random();
        int i = 0;
        double sum = 0.;
        for(E elem : set) {
            sum += coeffs[i];
            if (rand <= sum) {
                return elem;
            }
            i++;
        }
        return null;
    }
}
