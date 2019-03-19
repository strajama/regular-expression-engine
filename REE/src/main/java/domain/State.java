/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author strajama
 */
public class State {

    private SymbolTransition symbolTransition;
    private List<EpsilonTransition> epsilonTransitions;
    private boolean isEnd;

    public State(boolean isEnd) {
        this.isEnd = isEnd;
        this.epsilonTransitions = new ArrayList<>();
        this.symbolTransition = null;
    }

    public SymbolTransition getSymbolTransition() {
        return symbolTransition;
    }

    public List<EpsilonTransition> getEpsilonTransitions() {
        return epsilonTransitions;
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean hasSymbolTransition() {
        return this.symbolTransition != null;
    }

    public boolean hasEpsilonTransitions() {
        return !this.epsilonTransitions.isEmpty();
    }

    public void addTransition(Transition transition) {
        if (noTransitions()) {
            if (transition.hasSymbol()) {
                this.symbolTransition = (SymbolTransition) transition;
            } else {
                this.epsilonTransitions.add((EpsilonTransition) transition);
            }
        }
    }

    private boolean noTransitions() {
        return this.symbolTransition == null && this.epsilonTransitions.isEmpty();
    }

}
