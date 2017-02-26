package by.bsu.zmicier.meta.game.rules;

import by.bsu.zmicier.meta.game.dto.MetaPosition;

/**
 * Created on 23.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class AbstractRule<F, S, P extends MetaPosition<P>, M extends RulesMediator<F, S, P>> {
    private M rulesMediator;

    public AbstractRule(M server) {
        this.rulesMediator = server;
    }

    public M getRulesMediator() {
        return rulesMediator;
    }

    public void setRulesMediator(M rulesMediator) {
        this.rulesMediator = rulesMediator;
    }

}
