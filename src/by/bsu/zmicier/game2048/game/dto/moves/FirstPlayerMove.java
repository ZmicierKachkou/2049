package by.bsu.zmicier.game2048.game.dto.moves;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public enum FirstPlayerMove {
    UP(0), RIGHT(1), DOWN(2), LEFT(3), UNUSUAL_MOVIE_1(4), UNUSUAL_MOVIE_2(5), UNUSUAL_MOVIE_3(6), UNUSUAL_MOVIE_4(7);

    private final int value;

    private FirstPlayerMove(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FirstPlayerMove getMovie(Integer i) {
        FirstPlayerMove[] allMovies = FirstPlayerMove.values();
        return allMovies[i % allMovies.length];
    }

    public int getMovie(FirstPlayerMove movie) {
        return movie.getValue();
    }
}
