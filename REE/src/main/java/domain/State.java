/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author strajama
 */
public class State {

    private SymbolTransition symbolTransition;
    private EpsilonTransition[] epsilonTransitions;
    private boolean isEnd;

    public State(boolean isEnd) {
        this.isEnd = isEnd;
        this.epsilonTransitions = new EpsilonTransition[2];
        this.symbolTransition = null;
    }

    public SymbolTransition getSymbolTransition() {
        return symbolTransition;
    }

    public EpsilonTransition[] getEpsilonTransitions() {
        return epsilonTransitions;
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean hasSymbolTransitions() {
        return this.symbolTransition != null;
    }

    public boolean hasEpsilonTransitions() {
        return this.epsilonTransitions[0] != null;
    }

    public void addTransition(Transition transition) {
        if (noTransitions()) {
            if (transition.hasSymbol()) {
                this.symbolTransition = (SymbolTransition) transition;
            } else {
                if (this.epsilonTransitions[0] == null) {
                    this.epsilonTransitions[0] = (EpsilonTransition) transition;
                } else if (this.epsilonTransitions[1] == null) {
                    this.epsilonTransitions[1] = (EpsilonTransition) transition;
                }
            }
        }
    }

    private boolean noTransitions() {
        return this.symbolTransition == null && (this.epsilonTransitions[0] == null || this.epsilonTransitions[1] == null);
    }

}
