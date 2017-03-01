package by.bsu.zmicier.game2048.game.players.firstplayer;

import by.bsu.zmicier.game2048.game.dto.moves.FirstPlayerMove;
import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerClient;
import by.bsu.zmicier.meta.game.player.AbstractPlayer;
import by.bsu.zmicier.meta.ui.painter.PositionPainter;
import by.bsu.zmicier.meta.ui.windows.UITable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created on 26.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class UIHumanPlayer<T> extends AbstractPlayer<FirstPlayerMove, Position<T>> {
    private UITable<Position<T>> uiTable;
    private PositionPainter<Position<T>> painter;
    private ConcurrentLinkedQueue<FirstPlayerMove> pressedButtons = new ConcurrentLinkedQueue<FirstPlayerMove>();

    public UIHumanPlayer(PositionPainter<Position<T>> painter) {
        this.painter = painter;
    }

    @Override
    public void init() {
        if(uiTable == null) {
            uiTable = new UITable<Position<T>>(painter);
            uiTable.setTitle("Good luck, have fun!");
            uiTable.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                        pressedButtons.add(FirstPlayerMove.LEFT);
                    } else if(e.getKeyCode() == KeyEvent.VK_UP) {
                        pressedButtons.add(FirstPlayerMove.UP);
                    } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        pressedButtons.add(FirstPlayerMove.RIGHT);
                    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                        pressedButtons.add(FirstPlayerMove.DOWN);
                    }
                }
            });
        }
    }

    @Override
    public FirstPlayerMove move(Position<T> position, GameManagerClient<FirstPlayerMove, ?, Position<T>> client) {
        pressedButtons.clear();
        uiTable.showPosition(position);
        while(true) {
            FirstPlayerMove move = pressedButtons.poll();
            if(move != null && client.makeMyMove(position, move).isValid()) {
                return move;
            }
        }
    }
}
