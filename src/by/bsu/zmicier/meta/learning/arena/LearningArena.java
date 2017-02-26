package by.bsu.zmicier.meta.learning.arena;

import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.learning.sensei.Sensei;
import by.bsu.zmicier.meta.game.gamemanager.GameManagerServer;

/**
 * Created on 21.02.2017.
 *
 * @author Źmicier Dzikański
 */
public interface LearningArena<F, S, P extends MetaPosition<P>> {
    void setServer(GameManagerServer<F, S, P, ?> server);
    void setGames(int games);

    void setFirstSensei(Sensei<F, P> sensei);
    void setSecondSensei(Sensei<S, P> sensei);

    void startLearning();
}
