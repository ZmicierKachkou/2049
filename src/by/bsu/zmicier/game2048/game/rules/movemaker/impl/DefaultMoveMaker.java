package by.bsu.zmicier.game2048.game.rules.movemaker.impl;

import by.bsu.zmicier.game2048.game.dto.coords.Coords;
import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.moves.SecondPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.game2048.game.rules.base.Game2048RulesMediator;
import by.bsu.zmicier.game2048.game.rules.movemaker.Game2048MoveMaker;
import by.bsu.zmicier.game2048.game.rules.movemaker.impl.dto.Direction;
import by.bsu.zmicier.meta.game.dto.MoveResult;

import java.math.BigInteger;
import java.util.*;


/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class DefaultMoveMaker<T> extends Game2048MoveMaker<T> {

    private Map<Position<T>, Map<FirstPlayerMove, MoveResult<Position<T>>>> cachedMoves = new WeakHashMap<Position<T>, Map<FirstPlayerMove, MoveResult<Position<T>>>>();

    public DefaultMoveMaker(Game2048RulesMediator<T> server) {
        super(server);
    }

    @Override
    public List<Tile<T>> getAvailableTiles(Position<T> position) {
        return getRulesMediator().getAvailableTilesMaster().getAvailableTiles(position);
    }

    private boolean isCorrectMovie(Position<T> position, FirstPlayerMove movie) {

        return movieService(position, movie).isValid();
    }

    private boolean isCorrectMovie(Position<T> position, SecondPlayerMove<T> movie) {
        return position.getTile(movie.getX(), movie.getY()) == null;
    }

    private MoveResult<Position<T>> movieService(Position<T> position, FirstPlayerMove movie) {

        Position<T> newPosition = position.copy();

        if(movie.getValue() < 0 || movie.getValue() > 3) {
            return new MoveResult<Position<T>>(newPosition, 0, 0, false);
        }

        Direction<T> d = new Direction<T>(newPosition, movie);

        MoveResult<Position<T>> moveResult = movieCore(d);

        boolean isValid = !position.equals(moveResult.getPosition());
        moveResult.setValid(isValid);

        Map<FirstPlayerMove, MoveResult<Position<T>>> map = cachedMoves.get(position);
        if(map == null) {
            map = new HashMap<FirstPlayerMove, MoveResult<Position<T>>>();
            cachedMoves.put(position, map);
        }
        map.put(movie, moveResult);
        return moveResult;
    }

    private MoveResult<Position<T>> movieCore(Direction<T> d) {
        long points = 0;

        for(int x = 0; x < d.getSize(); x++) {
            int y = 1;
            int curr = 0;
            while(y < d.getSize()) {
                if(d.getTile(x, curr) == null) {
                    if(d.getTile(x, y) != null) {
                        d.setTile(d.getTile(x, y), x, curr);
                        d.setTile(null, x, y);
                    }
                    y++;
                }
                else {
                    if(d.getTile(x, y) != null) {
                        if(getRulesMediator().getTilesMerger().isMerged(d.getTile(x, curr), d.getTile(x, y))) {
                            points += getRulesMediator().getPointsCounter().pointsForMerge(d.getTile(x, curr), d.getTile(x, y));
                            d.setTile(getRulesMediator().getTilesMerger().merge(d.getTile(x, curr), d.getTile(x, y)), x, curr);
                            d.setTile(null, x, y);
                        } else if(y > curr+1) {
                            d.setTile(d.getTile(x, y), x, curr+1);
                            d.setTile(null, x, y);
                        }
                        curr++;
                    }
                    y++;
                }
            }
        }

        return new MoveResult<Position<T>>(d.getPosition(), points, -points, true);
    }

    @Override
    public MoveResult<Position<T>> moveFirst(Position<T> position, FirstPlayerMove movie) {
        Map<FirstPlayerMove, MoveResult<Position<T>>> map = cachedMoves.get(position);

        if(map != null) {
            MoveResult<Position<T>> movieResult = map.get(movie);
            if (movieResult != null) {
                return movieResult;
            }
        }

        return movieService(position, movie);
    }

    @Override
    public MoveResult<Position<T>> moveSecond(Position<T> pos, SecondPlayerMove<T> movie) {
        boolean isValid = true;

        Position<T> newPosition = pos.copy();

        if(pos.getTile(movie.getX(), movie.getY()) == null) {
            newPosition.setTile(movie.getX(), movie.getY(), movie.getTile());
        } else {
            isValid = false;
        }

        return new MoveResult<Position<T>>(newPosition, 0, 0, isValid);
    }

    @Override
    public List<FirstPlayerMove> getCorrectFirstPlayerMoves(by.bsu.zmicier.game2048.game.dto.position.Position<T> pos) {
        List<FirstPlayerMove> list = new ArrayList<FirstPlayerMove>();
        for(FirstPlayerMove movie: FirstPlayerMove.values()) {
            if(isCorrectMovie(pos, movie)) {
                list.add(movie);
            }
        }
        return list;
    }

    @Override
    public List<Coords> getEmptyCells(Position<T> pos) {
        if(pos == null) return null;
        List<by.bsu.zmicier.game2048.game.dto.coords.Coords> list = new ArrayList<by.bsu.zmicier.game2048.game.dto.coords.Coords>();
        for(int x=0; x < pos.getSize(); x++) {
            for(int y=0; y < pos.getSize(); y++) {
                if(pos.getTile(x, y) == null) {
                    list.add(new Coords(x, y));
                }
            }
        }
        return list;
    }

    @Override
    public List<SecondPlayerMove<T>> getCorrectSecondPlayerMoves(Position<T> position) {
        List<SecondPlayerMove<T>> moves = new LinkedList<SecondPlayerMove<T>>();
        for(Coords coords: getEmptyCells(position)) {
            for(Tile<T> tile: getAvailableTiles(position)) {
                moves.add(new SecondPlayerMove<T>(coords, tile));
            }
        }

        return moves;
    }
}
