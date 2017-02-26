package by.bsu.zmicier.meta.ui.windows;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.ui.painter.PositionPainter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 08.12.2014.
 */
public class PositionPanel<P extends MetaPosition<P>> extends JPanel {
    private P position;
    private PositionPainter<P> painter;

    public PositionPanel(PositionPainter<P> painter) {
        this.painter = painter;
    }

    public void setPosition(P position) {
        this.position = position;
    }

    public PositionPainter<P> getPainter() {
        return painter;
    }

    public void setPainter(PositionPainter<P> painter) {
        this.painter = painter;
    }

    void clear(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        painter.paintPosition(position, g, getWidth(), getHeight());

    }
}
