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
public class EpsilonState implements State {

    private Transition[] transitions;
    private boolean isEnd;

    public EpsilonState(boolean isEnd) {
        this.isEnd = isEnd;
        this.transitions = new EpsilonTransition[2];
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
        } else if (this.transitions[1] == null) {
            this.transitions[1] = transition;
        }
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
    
    
}
