package by.bsu.zmicier.meta.game.rules;


import by.bsu.zmicier.meta.game.dto.MetaPosition;
import by.bsu.zmicier.meta.game.rules.finishchecker.FinishChecker;
import by.bsu.zmicier.meta.game.rules.movemaker.MoveMaker;
import by.bsu.zmicier.meta.game.rules.positiongenerator.PositionGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 20.02.2017.
 *
 * @author Źmicier Dzikański
 */
public class RulesMediator<F, S, P extends MetaPosition<P>> {
    protected MoveMaker<F, S, P> moveMaker;
    protected FinishChecker<P> finishChecker;
    protected PositionGenerator<P> positionGenerator;
    protected Map<String, AbstractRule<F, S, P, ? extends RulesMediator<F, S, P>>> additionalRules =
            new HashMap<String, AbstractRule<F, S, P, ? extends RulesMediator<F, S, P>>>();

    public  MoveMaker<F, S, P> getMoveMaker() {
        return moveMaker;
    }

    public void setMoveMaker( MoveMaker<F, S, P> moveMaker) {
        this.moveMaker = moveMaker;
    }

    public FinishChecker<P> getFinishChecker() {
        return finishChecker;
    }

    public void setFinishChecker(FinishChecker<P> finishChecker) {
        this.finishChecker = finishChecker;
    }

    public PositionGenerator<P> getPositionGenerator() {
        return positionGenerator;
    }

    public void setPositionGenerator(PositionGenerator<P> positionGenerator) {
        this.positionGenerator = positionGenerator;
    }

    public void addRule(String name, AbstractRule<F, S, P, ? extends RulesMediator<F, S, P>> rule) {
        additionalRules.put(name, rule);
    }

    public Object getRule(String name) {
        return additionalRules.get(name);
    }
}
