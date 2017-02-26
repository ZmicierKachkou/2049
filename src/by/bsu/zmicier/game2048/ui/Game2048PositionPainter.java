package by.bsu.zmicier.game2048.ui;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.game.dto.tiles.Tile;
import by.bsu.zmicier.meta.ui.painter.PositionPainter;

import java.awt.*;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class Game2048PositionPainter<T> implements PositionPainter<Position<T>>{
    @Override
    public void paintPosition(Position<T> position, Graphics g, int poleWidth, int poleHeight) {
        if(position != null) {
            int size = position.getSize();
            int tileWidth = poleWidth / position.getSize();
            int tileHeight = poleHeight / position.getSize();

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Tile<T> tile = position.getTile(x, y);
                    if (tile == null) {
                        g.setColor(new Color(230, 230, 230));
                    } else {
                        if(tile.getValue() instanceof Integer) {
                            int color = (int)(255 - 15* Math.log((Integer)tile.getValue()));
                            g.setColor(new Color(color, color-20, color-40));
                        } else {
                            g.setColor(new Color(200, 200, 200));
                        }
                    }
                    g.fillRect(y * tileWidth, x * tileHeight, tileWidth, tileHeight);

                    if(tile != null) {
                        g.setFont(new Font("Verdana", Font.PLAIN, 48));
                        g.setColor(Color.black);
                        g.drawString(tile.getValue().toString(), (int)((0.3f + y)* tileWidth), (int)((0.3f + x) * tileHeight));
                    }
                }
            }
        }
    }
}
