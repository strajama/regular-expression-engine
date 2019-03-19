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
public class Search {

    private boolean findsEnd;

    public Search(Nfa nfa, String word) {
        this.findsEnd = false;
        List<State> currentStates = new ArrayList<State>();
        currentStates = addNextState(nfa.getStart(), currentStates, new ArrayList<State>());

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            List<State> nextStates = new ArrayList<State>();

            for (State state : currentStates) {                
                if (state.hasSymbolTransition() && state.getSymbolTransition().getSymbol() == symbol) {
                    State nextState = state.getSymbolTransition().getTo();
                    nextStates = addNextState(nextState, nextStates, new ArrayList<State>());
                }
            }
            currentStates = nextStates;
        }
        for (State state : currentStates) {
            if (state.getIsEnd()) {
                this.findsEnd = true;
                break;
            }
        }
    }

    public boolean findsEnd() {
        return findsEnd;
    }

    private List<State> addNextState(State state, List<State> nextStates, List<State> visited) {
        if (state.hasEpsilonTransitions()) {
            for (EpsilonTransition etr : state.getEpsilonTransitions()) {
                if (etr != null && !visited.contains(etr.getTo())) {
                    visited.add(etr.getTo());
                    addNextState(etr.getTo(), nextStates, visited);
                }
            }
        } else if (state.hasSymbolTransition()) {
            nextStates.add(state);
        } else {
            nextStates.add(state);
        }
        return nextStates;
    }

}
