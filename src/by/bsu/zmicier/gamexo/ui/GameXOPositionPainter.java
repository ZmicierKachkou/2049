package by.bsu.zmicier.gamexo.ui;

import by.bsu.zmicier.gamexo.dto.CellState;
import by.bsu.zmicier.gamexo.dto.Position;
import by.bsu.zmicier.meta.ui.painter.PositionPainter;

import java.awt.*;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class GameXOPositionPainter implements PositionPainter<Position>{
    @Override
    public void paintPosition(Position position, Graphics g, int poleWidth, int poleHeight) {
        if(position != null) {
            int size = Position.ROW;
            int cellWidth = poleWidth / size;
            int cellHeight = poleHeight / size;

            Graphics2D g2 = (Graphics2D)g;
            g2.clearRect(0, 0, poleWidth, poleHeight);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(5));

            for(int i=1; i<size; i++) {
                g.drawLine(i * cellWidth, 0, i * cellWidth, poleHeight);
                g.drawLine(0, i * cellHeight, poleWidth, i * cellHeight);
            }

            for (int x = 0; x < Position.ROW; x++) {
                for (int y = 0; y < Position.ROW; y++) {
                    CellState state = position.getCell(x, y);
                    if(state == CellState.X) {
                        g.drawLine(x * cellWidth + 10, y * cellHeight + 10, (x+1) * cellWidth - 10, (y+1) * cellHeight - 10);
                        g.drawLine(x * cellWidth + 10, (y+1) * cellHeight - 10, (x+1) * cellWidth - 10, y * cellHeight + 10);
                    } else if (state == CellState.O) {
                        g.drawOval(x * cellWidth + 10, y * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                    }
                }
            }
        }
    }
}
