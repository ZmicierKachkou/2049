package by.bsu.zmicier.meta.ui.listeners;

import by.bsu.zmicier.meta.game.dto.GameResult;
import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.listeners.GameAction;
import by.bsu.zmicier.meta.game.listeners.GameListener;
import by.bsu.zmicier.meta.ui.painter.PositionPainter;
import by.bsu.zmicier.meta.ui.windows.UITable;

/**
 * Created on 22.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class UIGameListener<P extends MetaPosition<P>> implements GameListener<P> {
    private UITable<P> uiTable;

    public UIGameListener(PositionPainter<P> painter) {
        this.uiTable = new UITable<P>(painter);
        uiTable.setTitle("Game Observer LIVE");
    }

    @Override
    public void processAction(GameResult<P> result, GameAction gameAction) {
        if(result != null && result.getPosition() != null) {
            uiTable.showPosition(result.getPosition());
        }
    }
}
