package by.bsu.zmicier.game2048.learning.converter;

import by.bsu.zmicier.game2048.game.dto.position.Position;
import by.bsu.zmicier.meta.learning.algorithm.knn.DataConverter;

/**
 * Created on 01.03.2017.
 *
 * @author Źmicier Dzikański
 */
public class PositionDataConverter implements DataConverter<int[], Position<Integer>> {
    @Override
    public int[] convertData(Position<Integer> data) {
        int[] result = new int[data.getSize() * data.getSize()];

        for(int x = 0; x < data.getSize(); x++) {
            for(int y = 0; y < data.getSize(); y++) {
                if(data.getTile(x, y) == null) {
                    result[x*data.getSize() + y] = 0;
                } else {
                    result[x * data.getSize() + y] = data.getTile(x, y).getValue();
                }
            }
        }
        return result;
    }
}
