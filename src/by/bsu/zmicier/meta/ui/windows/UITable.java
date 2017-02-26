package by.bsu.zmicier.meta.ui.windows;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.ui.painter.PositionPainter;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class UITable<P extends MetaPosition<P>> extends JFrame {
    private final static int SIZE = 600;
    PositionPanel<P> table;

    public UITable(PositionPainter<P> painter) {
        Container c = getContentPane();
        table = new PositionPanel<P>(painter);
        c.add(table);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(SIZE, SIZE);
        setResizable(false);
    }

    public void showPosition(P position) {

        table.setPosition(position);
        table.paintImmediately(0, 0, SIZE, SIZE);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
