package by.bsu.zmicier.game2048.game.players.firstplayer;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;

import java.util.List;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class CornerPlayer<T> extends AbstractPlayer<FirstPlayerMove, Position<T>> {

    @Override
    public FirstPlayerMove move(Position<T> position, GameManagerClient<FirstPlayerMove, ?, Position<T>> client) {
        List<FirstPlayerMove> moves = client.getMyMoves(position);
        if(moves.contains(FirstPlayerMove.UP)) {
            return FirstPlayerMove.UP;
        } else if(moves.contains(FirstPlayerMove.LEFT)) {
            return FirstPlayerMove.LEFT;
        } else if(moves.contains(FirstPlayerMove.RIGHT)) {
            return FirstPlayerMove.RIGHT;
        } else {
            return FirstPlayerMove.DOWN;
        }
    }
}
