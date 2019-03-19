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
public class SymbolState implements State {

    private Transition[] transitions;
    private boolean isEnd;

    public SymbolState(boolean isEnd) {
        this.isEnd = isEnd;
        this.transitions = new SymbolTransition[1];
    }

    public Transition[] getTransitions() {
        return transitions;
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public void addTransition(Transition transition) {
        if (this.transitions[0] == null) {
            this.transitions[0] = transition;
        }
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    
}
