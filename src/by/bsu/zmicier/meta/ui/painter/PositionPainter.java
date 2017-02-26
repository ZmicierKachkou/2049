package by.bsu.zmicier.meta.ui.painter;

import by.bsu.zmicier.meta.game.dto.MetaPosition;

import java.awt.*;

/**
 * Created on 24.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface PositionPainter<P extends MetaPosition<P>> {
    void paintPosition(P position, Graphics g, int x, int y);
}
