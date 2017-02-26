package by.bsu.zmicier.game2048.learning.estimators.position.factory.impl;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class PositionAnalyticsWrapperFactory implements by.bsu.zmicier.game2048.learning.estimators.position.factory.PositionWrapperFactory<Integer, by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper> {
    private Map<Position<Integer>, PositionAnalyticsWrapper> cache = new WeakHashMap<Position<Integer>, PositionAnalyticsWrapper>();

    @Override
    public by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper getWrapper(Position<Integer> position) {
        if(cache.size() > 12000) {
            cache.clear();
        }
        by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper wrapper = cache.get(position);
        if(wrapper != null) {
            return wrapper;
        } else {
            wrapper = new by.bsu.zmicier.game2048.learning.estimators.position.impl.PositionAnalyticsWrapper(position);
            cache.put(position, wrapper);
            return wrapper;
        }
    }
}
